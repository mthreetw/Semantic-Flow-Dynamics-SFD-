package tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.*;

/**
 * 讀取各論文的 formalization.md，從形式化表達式的概念共現關係生成概念星圖 HTML。
 *
 * <ul>
 *   <li>節點：每篇論文 {@code ## 概念摘要} 中列出的概念。
 *   <li>邊：含形式符號的行——同一行出現的所有概念兩兩連接。
 * </ul>
 *
 * 執行路徑需為專案根目錄（使相對路徑 {@code src/main/java} 有效）。
 * 不依賴已編譯的 .class 檔案，直接讀取 Markdown。
 */
public class StarMapGenerator {

    // ── Compiled patterns ─────────────────────────────────────────────────────

    /** 形式化文件頭的 UUID 欄位 */
    private static final Pattern RE_UUID_HEADER = Pattern.compile(
            "^UUID[：:] *([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})",
            Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

    /** 論文標題 */
    private static final Pattern RE_TITLE = Pattern.compile("論文：《(.+?)》");

    /**
     * 任意形式邏輯／集合論符號——命中即視該行為形式化表達式。
     * 涵蓋 形式化手冊 §1.1 的所有符號。
     */
    private static final Pattern RE_FORMAL = Pattern.compile(
            "[≡→←⊂∈∧∨⊕⇒⇔↔⊃⊆⊇∪∩≠≈≺≻≼≽∼≅⊥⊤□◇∝⊗⊢⊨↦↝∂∫⫫¬∀∃∄Δ∑∏√±∞∅]");

    /**
     * 上游概念引用：{@code (ext:)UUID.中文名}。
     * Group 1 = UUID，Group 2 = 概念名。
     */
    private static final Pattern RE_UPSTREAM = Pattern.compile(
            "(?:ext:)?([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})" +
            "\\.([\\u4e00-\\u9fff\\u3400-\\u4dbf][\\u4e00-\\u9fff\\u3400-\\u4dbf\\d\\u2080-\\u2089]*)");

    /** 本篇前綴概念：{@code 本篇.中文名}。Group 1 = 概念名。 */
    private static final Pattern RE_LOCAL_PREFIX = Pattern.compile(
            "本篇\\.([\\u4e00-\\u9fff\\u3400-\\u4dbf][\\u4e00-\\u9fff\\u3400-\\u4dbf\\d\\u2080-\\u2089]*)");

    /** 裸漢字序列（可帶尾部數字或下標 U+2080-U+2089） */
    private static final Pattern RE_CHINESE = Pattern.compile(
            "[\\u4e00-\\u9fff\\u3400-\\u4dbf][\\u4e00-\\u9fff\\u3400-\\u4dbf\\d\\u2080-\\u2089]*");

    /**
     * 概念摘要條目行：{@code   概念名 (EnglishName)} 或 {@code   概念名（英文）}。
     * Group 1 = 概念名。
     */
    private static final Pattern RE_SUMMARY_CONCEPT = Pattern.compile(
            "^[ \\t]*([\\u4e00-\\u9fff\\u3400-\\u4dbf][\\u4e00-\\u9fff\\u3400-\\u4dbf\\d\\u2080-\\u2089]*)[ \\t]*[（(]",
            Pattern.MULTILINE);

    // ─────────────────────────────────────────────────────────────────────────

    public static void main(String[] args) throws IOException {
        Path base = Path.of("src/main/java");
        if (!Files.isDirectory(base)) {
            System.err.println("❌  找不到 src/main/java — 請從專案根目錄執行");
            return;
        }

        // nodeId (pkg.概念名) → [id, name, pkg]
        Map<String, String[]>    nodeMap       = new LinkedHashMap<>();
        List<String[]>           edgeData      = new ArrayList<>();      // [src, tgt, cross]
        Set<String>              groups        = new LinkedHashSet<>();
        Map<String, String>      titles        = new HashMap<>();
        Map<String, String>      uuidToPkg     = new HashMap<>();         // uuid → pkg
        Map<String, Set<String>> localConcepts = new LinkedHashMap<>();   // pkg → concept names

        // 收集並排序 paper_ 目錄（保證輸出確定性）
        List<Path> paperDirs = new ArrayList<>();
        try (var stream = Files.newDirectoryStream(base, "paper_*")) {
            for (Path d : stream) paperDirs.add(d);
        }
        paperDirs.sort(Comparator.comparing(p -> p.getFileName().toString()));

        // ── Pass 1：建立 UUID 映射 + 收集節點 ────────────────────────────────
        for (Path dir : paperDirs) {
            String pkg = dir.getFileName().toString();
            Path fm    = dir.resolve("formalization.md");
            if (!Files.isRegularFile(fm)) continue;

            String content = Files.readString(fm);

            // UUID → pkg
            Matcher um = RE_UUID_HEADER.matcher(content);
            if (um.find()) uuidToPkg.put(um.group(1).toLowerCase(), pkg);

            // 標題
            Matcher tm = RE_TITLE.matcher(content);
            if (tm.find()) titles.put(pkg, tm.group(1));

            // 節點：來自 ## 概念摘要
            Set<String> concepts = parseSummaryConcepts(content);
            if (concepts.isEmpty()) continue;

            localConcepts.put(pkg, concepts);
            groups.add(pkg);
            for (String c : concepts) {
                String id = pkg + "." + c;
                nodeMap.put(id, new String[]{id, c, pkg});
            }
        }

        // ── Pass 2：從形式化表達式建邊 ────────────────────────────────────────
        Set<String> globalSeen = new HashSet<>();  // 全域去重

        for (Path dir : paperDirs) {
            String pkg  = dir.getFileName().toString();
            Path fm     = dir.resolve("formalization.md");
            if (!Files.isRegularFile(fm)) continue;

            String      content     = Files.readString(fm);
            Set<String> mine        = localConcepts.getOrDefault(pkg, Set.of());
            boolean     skipSection = false;

            for (String line : content.split("\n")) {
                // 追蹤 section 邊界
                if (line.startsWith("#")) {
                    if (line.startsWith("## ")) {
                        // 概念摘要、待決項：純列表／自然語言，不提取邊
                        skipSection = line.contains("概念摘要") || line.contains("待決項");
                    }
                    continue;  // 標題行本身一律跳過
                }
                if (skipSection || line.isBlank()) continue;
                if (!RE_FORMAL.matcher(line).find()) continue;

                List<String> ids = extractConceptIds(line, pkg, mine, uuidToPkg);
                if (ids.size() < 2) continue;

                for (int i = 0; i < ids.size(); i++) {
                    for (int j = i + 1; j < ids.size(); j++) {
                        String a = ids.get(i), b = ids.get(j);
                        if (!nodeMap.containsKey(a) || !nodeMap.containsKey(b)) continue;
                        String key = a.compareTo(b) < 0 ? a + "|" + b : b + "|" + a;
                        if (!globalSeen.add(key)) continue;
                        boolean cross = !nodeMap.get(a)[2].equals(nodeMap.get(b)[2]);
                        edgeData.add(new String[]{a, b, String.valueOf(cross)});
                    }
                }
            }
        }

        // ── 序列化 JSON ────────────────────────────────────────────────────────
        StringBuilder nodesJson = new StringBuilder("[");
        boolean firstN = true;
        for (String[] n : nodeMap.values()) {
            if (!firstN) nodesJson.append(",");
            firstN = false;
            nodesJson.append("{\"id\":\"").append(esc(n[0]))
                .append("\",\"name\":\"").append(esc(n[1]))
                .append("\",\"chName\":\"").append(esc(n[1]))
                .append("\",\"group\":\"").append(esc(n[2]))
                .append("\",\"type\":\"record\"}");
        }
        nodesJson.append("]");

        StringBuilder edgesJson = new StringBuilder("[");
        for (int i = 0; i < edgeData.size(); i++) {
            String[] e = edgeData.get(i);
            if (i > 0) edgesJson.append(",");
            edgesJson.append("{\"source\":\"").append(esc(e[0]))
                .append("\",\"target\":\"").append(esc(e[1]))
                .append("\",\"cross\":").append(e[2])
                .append(",\"edgeType\":\"formal\"}");
        }
        edgesJson.append("]");

        StringBuilder papersJson = new StringBuilder("{");
        int ci = 0, pi = 0;
        for (String g : groups) {
            if (ci++ > 0) papersJson.append(",");
            int hue = ((int)(pi++ * 137.508)) % 360;
            papersJson.append("\"").append(esc(g)).append("\":{\"label\":\"")
                .append(esc(titles.getOrDefault(g, g)))
                .append("\",\"color\":\"").append(hslToHex(hue, 70, 65)).append("\"}");
        }
        papersJson.append("}");

        // ── 輸出 HTML ─────────────────────────────────────────────────────────
        String html = HTML_TEMPLATE
            .replace("/*PAPERS_DATA*/", papersJson)
            .replace("/*NODES_DATA*/",  nodesJson)
            .replace("/*EDGES_DATA*/",  edgesJson);

        Path out = args.length > 0 ? Path.of(args[0]) : Path.of("starmap.html");
        Files.writeString(out, html);
        System.out.printf("done  →  %s%n", out.toAbsolutePath());
        System.out.printf("  nodes=%d  edges=%d  papers=%d%n",
            nodeMap.size(), edgeData.size(), groups.size());
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /** 從 {@code ## 概念摘要} 區塊提取概念名稱列表。 */
    private static Set<String> parseSummaryConcepts(String content) {
        Set<String> result = new LinkedHashSet<>();
        int start = content.indexOf("## 概念摘要");
        if (start < 0) return result;
        int end = content.indexOf("\n## ", start + 1);
        String section = end < 0 ? content.substring(start) : content.substring(start, end);
        Matcher m = RE_SUMMARY_CONCEPT.matcher(section);
        while (m.find()) result.add(m.group(1));
        return result;
    }

    /**
     * 從一行形式化表達式中提取所有可辨識的概念節點 ID。
     */
    private static List<String> extractConceptIds(
            String line, String currentPkg,
            Set<String> localConcepts, Map<String, String> uuidToPkg) {

        Set<String> ids = new LinkedHashSet<>();

        Matcher um = RE_UPSTREAM.matcher(line);
        while (um.find()) {
            String tPkg = uuidToPkg.get(um.group(1).toLowerCase());
            if (tPkg != null) ids.add(tPkg + "." + um.group(2));
        }
        String s1 = RE_UPSTREAM.matcher(line).replaceAll(" ");

        Matcher lm = RE_LOCAL_PREFIX.matcher(s1);
        while (lm.find()) {
            String c = lm.group(1);
            if (localConcepts.contains(c)) ids.add(currentPkg + "." + c);
        }
        String s2 = RE_LOCAL_PREFIX.matcher(s1).replaceAll(" ");

        Matcher cm = RE_CHINESE.matcher(s2);
        while (cm.find()) {
            String c = cm.group();
            if (localConcepts.contains(c)) ids.add(currentPkg + "." + c);
        }

        return new ArrayList<>(ids);
    }

    private static String esc(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static String hslToHex(int h, int s, int l) {
        float hf = h / 360f, sf = s / 100f, lf = l / 100f;
        float c = (1 - Math.abs(2 * lf - 1)) * sf;
        float x = c * (1 - Math.abs((hf * 6) % 2 - 1));
        float m = lf - c / 2;
        float r, g, b;
        int sec = (int)(hf * 6) % 6;
        if      (sec == 0) { r = c; g = x; b = 0; }
        else if (sec == 1) { r = x; g = c; b = 0; }
        else if (sec == 2) { r = 0; g = c; b = x; }
        else if (sec == 3) { r = 0; g = x; b = c; }
        else if (sec == 4) { r = x; g = 0; b = c; }
        else               { r = c; g = 0; b = x; }
        return String.format("#%02x%02x%02x",
            Math.round((r + m) * 255), Math.round((g + m) * 255), Math.round((b + m) * 255));
    }

    // ── HTML template ─────────────────────────────────────────────────────────
    private static final String HTML_TEMPLATE = """
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
<title>概念星圖 (動態洗牌版)</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/7.9.0/d3.min.js"></script>
<style>
*{margin:0;padding:0;box-sizing:border-box}
body{background:#080c18;overflow:hidden;font-family:'PingFang TC','Noto Sans TC',system-ui,sans-serif}
svg{display:block}
.p{position:fixed;z-index:10;user-select:none}
#tt{top:12px;left:14px}
#tt span:first-child{color:#999;font-size:14px;font-weight:500}
#tt span:last-child{color:#555;font-size:12px;margin-left:6px}
#ct{top:12px;right:14px;display:flex;gap:3px;flex-wrap:wrap;justify-content:flex-end;max-width:240px}
#ct button{background:transparent;color:#555;border:1px solid rgba(255,255,255,0.08);
  border-radius:4px;padding:3px 9px;font-size:11px;cursor:pointer;font-family:inherit}
#ct button:hover{background:rgba(255,255,255,0.06);color:#aaa}
#ct button.on{background:rgba(255,255,255,0.1);color:#bbb}
#lg{bottom:16px;left:14px;background:rgba(8,12,24,0.88);
  border:1px solid rgba(255,255,255,0.07);border-radius:6px;padding:8px 12px;max-height:40vh;overflow-y:auto}
#nf{bottom:16px;right:14px;background:rgba(8,12,24,0.9);
  border:1px solid rgba(255,255,255,0.07);border-radius:6px;padding:10px 14px;
  min-width:140px;display:none}
#fi{top:40px;right:14px;color:#444;font-size:10px;text-align:right}
#ht{bottom:6px;left:50%;transform:translateX(-50%);color:#282828;font-size:9px}
</style>
</head>
<body>
<div id="tt" class="p"><span>語意流動力學</span><span>概念星圖 (動態洗牌版)</span></div>
<div id="ct" class="p">
  <button id="ba" class="on">全部論文</button>
  <button id="br">🎲 隨機抽 3 篇</button>
  <button id="bs">✨ 原地打亂重排</button>
</div>
<div id="fi" class="p"></div>
<div id="lg" class="p"></div>
<div id="nf" class="p"></div>
<div id="ht" class="p">滾輪縮放 · 拖曳平移 · 點擊鎖定</div>

<script>
var PAPERS = /*PAPERS_DATA*/;
var ALL_N  = /*NODES_DATA*/;
var ALL_L  = /*EDGES_DATA*/;
var pks = Object.keys(PAPERS);
var RC  = Math.min(3, pks.length);
if(pks.length <= RC) document.getElementById('br').style.display = 'none';
else document.getElementById('br').textContent = '🎲 隨機抽 ' + RC + ' 篇';

var curSim = null;
var curSel = null; // 狀態記憶

function filt(sel){
  var ns = JSON.parse(JSON.stringify(ALL_N));
  var ls = JSON.parse(JSON.stringify(ALL_L));
  if(sel){
    var gs = {}; sel.forEach(function(s){gs[s] = 1;});
    ns = ns.filter(function(n){return gs[n.group];});
    var ni = {}; ns.forEach(function(n){ni[n.id] = 1;});
    ls = ls.filter(function(l){return ni[l.source] && ni[l.target];});
  }
  var deg = {};
  ns.forEach(function(n){deg[n.id] = 0;});
  ls.forEach(function(l){
    deg[l.source] = (deg[l.source] || 0) + 1;
    deg[l.target] = (deg[l.target] || 0) + 1;
  });
  ns.forEach(function(n){n.degree = deg[n.id] || 0;});
  ls.forEach(function(l){
    if(l.cross){
      l.color = '#ffffff';
    } else {
      var src = ns.find(function(n){return n.id === l.source;});
      l.color = src ? PAPERS[src.group].color : '#444';
    }
  });
  return {nodes: ns, links: ls};
}

function draw(nodes, links, isShuffle){
  if(curSim) curSim.stop();
  d3.selectAll('svg').remove(); // 絕對清空所有 SVG
  
  var W = window.innerWidth, H = window.innerHeight;
  var svg = d3.select('body').append('svg').attr('width', W).attr('height', H);
  var defs = svg.append('defs');

  function mg(id, s){
    var f = defs.append('filter').attr('id', id)
      .attr('x','-50%').attr('y','-50%').attr('width','200%').attr('height','200%');
    f.append('feGaussianBlur').attr('in','SourceGraphic').attr('stdDeviation', s).attr('result','b');
    var m = f.append('feMerge');
    m.append('feMergeNode').attr('in','b');
    m.append('feMergeNode').attr('in','SourceGraphic');
  }
  mg('gs', 2); mg('gl', 4);

  var bg = svg.append('g');
  for(var i = 0; i < 300; i++){
    bg.append('circle')
      .attr('cx', Math.random() * W).attr('cy', Math.random() * H)
      .attr('r', Math.random() * 0.7 + 0.1)
      .attr('fill','#fff').attr('opacity', Math.random() * 0.25 + 0.03);
  }

  var g = svg.append('g');
  var lbSel;
  var zm = d3.zoom().scaleExtent([0.1, 8]).on('zoom', function(e){
    g.attr('transform', e.transform);
    if(lbSel){
      var k = e.transform.k;
      lbSel.style('font-size', Math.max(8, 13 / k) + 'px')
           .style('opacity', k > 1.5 ? 0.9 : k > 0.7 ? 0.7 : 0.35);
    }
  });
  svg.call(zm);
  svg.call(zm.transform, d3.zoomIdentity.translate(W * 0.17, H * 0.17).scale(0.7));

  var ag = [];
  nodes.forEach(function(n){if(ag.indexOf(n.group) < 0) ag.push(n.group);});
  
  if(isShuffle){
    for(var i = ag.length - 1; i > 0; i--){
      var j = Math.floor(Math.random() * (i + 1));
      var t = ag[i]; ag[i] = ag[j]; ag[j] = t;
    }
    nodes.forEach(function(n){
      n.x = Math.random() * W;
      n.y = Math.random() * H;
    });
  }

  var gc = {};
  ag.forEach(function(p, i){
    var a = (i / ag.length) * Math.PI * 2 - Math.PI / 2;
    gc[p] = [W * 0.5 + W * 0.28 * Math.cos(a), H * 0.5 + H * 0.3 * Math.sin(a)];
  });

  var sim = d3.forceSimulation(nodes)
    .force('link', d3.forceLink(links).id(function(d){return d.id;})
      .distance(function(d){return d.cross ? 130 : 50;})
      .strength(function(d){return d.cross ? 0.1 : 0.5;}))
    .force('charge', d3.forceManyBody().strength(-130))
    .force('cx', d3.forceX(function(d){return (gc[d.group] || [W / 2])[0];}).strength(0.07))
    .force('cy', d3.forceY(function(d){return (gc[d.group] || [0, H / 2])[1];}).strength(0.07))
    .force('collide', d3.forceCollide(16))
    .alphaDecay(0.015).velocityDecay(0.3);
  curSim = sim;

  var lkSel = g.append('g').selectAll('line').data(links).join('line')
    .attr('stroke', function(d){return d.color;})
    .attr('stroke-opacity', function(d){return d.cross ? 0.1 : 0.07;})
    .attr('stroke-width',   function(d){return d.cross ? 0.7 : 0.4;})
    .attr('stroke-dasharray', function(d){return d.cross ? '3,2' : null;});

  var ndSel = g.append('g').selectAll('circle').data(nodes).join('circle')
    .attr('r', function(d){return Math.max(2.5, 2 + d.degree * 0.35);})
    .attr('fill', function(d){return PAPERS[d.group] ? PAPERS[d.group].color : '#888';})
    .attr('stroke', 'none')
    .attr('stroke-width', 0)
    .attr('filter', 'url(#gs)')
    .attr('opacity', 0.9).style('cursor', 'pointer');

  lbSel = g.append('g').selectAll('text').data(nodes).join('text')
    .text(function(d){return d.chName || d.name;})
    .attr('fill','#ffffff').attr('dx', 8).attr('dy', 3)
    .style('font-size','13px')
    .style('font-family',"'PingFang TC','Noto Sans TC',system-ui,sans-serif")
    .style('font-weight','600')
    .style('opacity', 0.7).style('pointer-events','none').style('user-select','none');

  function si(l){return typeof l.source === 'object' ? l.source.id : l.source;}
  function ti(l){return typeof l.target === 'object' ? l.target.id : l.target;}
  var locked = null;

  function hi(d){
    var nb = {}; nb[d.id] = 1;
    links.forEach(function(l){
      if(si(l) === d.id) nb[ti(l)] = 1;
      if(ti(l) === d.id) nb[si(l)] = 1;
    });
    ndSel.attr('opacity', function(n){return nb[n.id] ? 1 : 0.06;})
         .attr('filter',  function(n){return nb[n.id] ? 'url(#gl)' : null;});
    lkSel.attr('stroke-opacity', function(l){return (si(l)===d.id||ti(l)===d.id) ? 0.7 : 0.01;})
         .attr('stroke-width',   function(l){return (si(l)===d.id||ti(l)===d.id) ? 1.5 : 0.2;});
    lbSel.style('opacity',     function(n){return nb[n.id] ? 1 : 0.05;})
         .style('font-weight', function(n){return n.id === d.id ? '700' : '600';});
    var cx = 0;
    links.forEach(function(l){if((si(l)===d.id||ti(l)===d.id) && l.cross) cx++;});
    var el = document.getElementById('nf');
    el.style.display = 'block';
    el.style.borderColor = PAPERS[d.group].color + '22';
    el.innerHTML =
      '<div style="color:' + PAPERS[d.group].color + ';font-size:13px;font-weight:600">'
        + (d.chName || d.name) + '</div>'
      + '<div style="color:#555;font-size:10px;line-height:1.7;margin-top:4px">'
        + '<div>' + PAPERS[d.group].label + '</div>'
        + '<div>連接：' + d.degree + '</div>'
        + (cx > 0 ? '<div style="color:#777">跨論文：' + cx + '</div>' : '')
      + '</div>';
  }

  function lo(){
    ndSel.attr('opacity', 0.9)
         .attr('fill', function(d){return PAPERS[d.group] ? PAPERS[d.group].color : '#888';})
         .attr('filter', 'url(#gs)');
    lkSel.attr('stroke-opacity', function(d){return d.cross ? 0.1 : 0.07;})
         .attr('stroke-width',   function(d){return d.cross ? 0.7 : 0.4;});
    lbSel.style('opacity', 0.7).style('font-weight', '600');
    document.getElementById('nf').style.display = 'none';
  }

  ndSel.on('mouseenter', function(_, d){if(!locked) hi(d);})
       .on('mouseleave', function(){if(!locked) lo();})
       .on('click', function(e, d){
         e.stopPropagation();
         if(locked === d.id){locked = null; lo();}
         else{locked = d.id; hi(d);}
       });
  svg.on('click', function(){if(locked){locked = null; lo();}});

  ndSel.call(d3.drag()
    .on('start', function(e, d){if(!e.active) sim.alphaTarget(0.12).restart(); d.fx = d.x; d.fy = d.y;})
    .on('drag',  function(e, d){d.fx = e.x; d.fy = e.y;})
    .on('end',   function(e, d){if(!e.active) sim.alphaTarget(0); d.fx = null; d.fy = null;}));

  sim.on('tick', function(){
    lkSel.attr('x1', function(d){return d.source.x;}).attr('y1', function(d){return d.source.y;})
         .attr('x2', function(d){return d.target.x;}).attr('y2', function(d){return d.target.y;});
    ndSel.attr('cx', function(d){return d.x;}).attr('cy', function(d){return d.y;});
    lbSel.attr('x',  function(d){return d.x;}).attr('y',  function(d){return d.y;});
  });
}

function buildLeg(gs){
  var el = document.getElementById('lg'); el.innerHTML = '';
  var ks = gs || Object.keys(PAPERS);
  ks.forEach(function(k){
    var v = PAPERS[k]; if(!v) return;
    var r = document.createElement('div');
    r.style.cssText = 'display:flex;align-items:center;gap:7px;margin-bottom:2px';
    r.innerHTML =
      '<div style="width:6px;height:6px;border-radius:50%;background:' + v.color
      + ';box-shadow:0 0 4px ' + v.color + '55;flex-shrink:0"></div>'
      + '<span style="color:' + v.color + ';font-size:10px;opacity:0.75">' + v.label + '</span>';
    el.appendChild(r);
  });
  var ed = document.createElement('div');
  ed.style.cssText = 'margin-top:6px;border-top:1px solid rgba(255,255,255,0.05);padding-top:5px';
  ed.innerHTML =
    '<div style="display:flex;align-items:center;gap:7px;margin-bottom:2px">'
    + '<svg width="14" height="2"><line x1="0" y1="1" x2="14" y2="1" stroke="rgba(255,255,255,0.4)" stroke-width="1"/></svg>'
    + '<span style="color:#555;font-size:9px">論文內</span></div>'
    + '<div style="display:flex;align-items:center;gap:7px">'
    + '<svg width="14" height="2"><line x1="0" y1="1" x2="14" y2="1" stroke="rgba(255,255,255,0.4)" stroke-width="1" stroke-dasharray="3,2"/></svg>'
    + '<span style="color:#555;font-size:9px">跨論文</span></div>';
  el.appendChild(ed);
}

document.getElementById('ba').onclick = function(){
  this.classList.add('on'); document.getElementById('br').classList.remove('on');
  document.getElementById('fi').textContent = '';
  curSel = null;
  var d = filt(curSel); buildLeg(curSel); 
  console.log("📍 [全部論文] 節點總數: ", d.nodes.length);
  draw(d.nodes, d.links, false); 
};
document.getElementById('br').onclick = function(){
  var arr = pks.slice();
  for(var i = arr.length - 1; i > 0; i--){
    var j = Math.floor(Math.random() * (i + 1));
    var t = arr[i]; arr[i] = arr[j]; arr[j] = t;
  }
  var picked = arr.slice(0, RC);
  this.classList.add('on'); document.getElementById('ba').classList.remove('on');
  document.getElementById('fi').textContent = picked.map(function(p){return PAPERS[p].label;}).join('、');
  curSel = picked;
  var d = filt(curSel); buildLeg(curSel); 
  console.log("🎲 [隨機抽 3 篇] 選中論文 ID: ", picked);
  console.log("🎲 [隨機抽 3 篇] 提取節點數: ", d.nodes.length);
  draw(d.nodes, d.links, true); 
};
document.getElementById('bs').onclick = function(){
  var d = filt(curSel);
  console.log("✨ [原地打亂] 重新分配方位...");
  draw(d.nodes, d.links, true);
};

buildLeg(null);
var init = filt(null);
draw(init.nodes, init.links, false); 
</script>
</body>
</html>
""";
}