package tools;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 掃描 record 依賴結構，生成概念星圖 HTML。
 * 放在 src/test/java/tools/ 下。
 */
public class StarMapGenerator {

    public static void main(String[] args) throws IOException {
        JavaClasses allClasses = new ClassFileImporter().importClasspath();

        Set<String> nodeIds = new HashSet<>();
        List<String[]> nodeData = new ArrayList<>();   // [id, simpleName, pkg, chName, type]
        List<String[]> edgeData = new ArrayList<>();   // [source, target, cross, edgeType]
        Set<String> groups = new LinkedHashSet<>();

        // 讀中文概念名（迴圈中要用）
        Map<String, String> chNames = readChineseNames();

        for (JavaClass cls : allClasses) {
            String pkg = cls.getPackageName();
            if (!pkg.startsWith("paper_") && !pkg.equals("core")) {
                continue;
            }

            String fullName = cls.getName();        // e.g. "paper_xxx.Concepts$冗餘"
            String simpleName = cls.getSimpleName(); // e.g. "冗餘"
            boolean isNested = fullName.contains("$");

            if (pkg.equals("core")) {
                // core 包：只取頂層類，排除 Postulate
                if (isNested) continue;
                if ("Postulate".equals(simpleName)) continue;
            } else {
                // paper_ 包：只取巢狀類（Concepts 的成員）
                if (!isNested) continue;
            }

            String id = pkg + "." + simpleName;
            if (nodeIds.contains(id)) continue; // 避免重複
            nodeIds.add(id);
            String chName = chNames.getOrDefault(simpleName, simpleName);
            String type = cls.isInterface() ? "interface" : "record";
            nodeData.add(new String[]{ id, simpleName, pkg, chName, type });
            groups.add(pkg);

            // 掃描 field 建邊（field 依賴）
            for (JavaField field : cls.getFields()) {
                String tPkg = field.getRawType().getPackageName();
                if (!tPkg.startsWith("paper_") && !tPkg.equals("core")) continue;
                String tId = tPkg + "." + field.getRawType().getSimpleName();
                boolean cross = !tPkg.equals(pkg);
                edgeData.add(new String[]{ id, tId, String.valueOf(cross), "field" });
            }

            // 掃描 implements 建邊（sealed interface 封閉分類）
            for (JavaClass iface : cls.getRawInterfaces()) { // 改用 getRawInterfaces()
                String iPkg = iface.getPackageName();
                if (!iPkg.startsWith("paper_") && !iPkg.equals("core")) continue;
                String iId = iPkg + "." + iface.getSimpleName();
                boolean cross = !iPkg.equals(pkg);
                edgeData.add(new String[]{ id, iId, String.valueOf(cross), "implements" });
            }
        }

        // 過濾：target 必須存在於節點集
        edgeData.removeIf(e -> !nodeIds.contains(e[1]));

        // 讀論文標題
        Map<String, String> titles = readTitles();

        // ── 生成 JSON ──
        StringBuilder nodesJson = new StringBuilder("[");
        for (int i = 0; i < nodeData.size(); i++) {
            String[] n = nodeData.get(i);
            if (i > 0) nodesJson.append(",");
            nodesJson.append("{\"id\":\"").append(n[0])
                .append("\",\"name\":\"").append(n[1])
                .append("\",\"chName\":\"").append(n[3])
                .append("\",\"group\":\"").append(n[2])
                .append("\",\"type\":\"").append(n[4]).append("\"}");
        }
        nodesJson.append("]");

        StringBuilder edgesJson = new StringBuilder("[");
        for (int i = 0; i < edgeData.size(); i++) {
            String[] e = edgeData.get(i);
            if (i > 0) edgesJson.append(",");
            edgesJson.append("{\"source\":\"").append(e[0])
                .append("\",\"target\":\"").append(e[1])
                .append("\",\"cross\":").append(e[2])
                .append(",\"edgeType\":\"").append(e[3]).append("\"}");
        }
        edgesJson.append("]");

        // 顏色：黃金角度
        StringBuilder papersJson = new StringBuilder("{");
        int ci = 0;
        int paperIdx = 0;
        for (String g : groups) {
            if (ci > 0) papersJson.append(",");
            String label = titles.getOrDefault(g, g.equals("core") ? "Core 原語" : g);
            String color;
            if (g.equals("core")) {
                color = "#c8c8d0";
            } else {
                int hue = ((int)(paperIdx * 137.508)) % 360;
                color = hslToHex(hue, 70, 65);
                paperIdx++;
            }
            papersJson.append("\"").append(g).append("\":{\"label\":\"").append(label)
                .append("\",\"color\":\"").append(color).append("\"}");
            ci++;
        }
        papersJson.append("}");

        // 組裝
        String html = HTML_TEMPLATE
            .replace("/*PAPERS_DATA*/", papersJson.toString())
            .replace("/*NODES_DATA*/", nodesJson.toString())
            .replace("/*EDGES_DATA*/", edgesJson.toString());

        Path out = args.length > 0 ? Path.of(args[0]) : Path.of("starmap.html");
        Files.writeString(out, html);
        System.out.println("done: " + out.toAbsolutePath());
        System.out.println("  nodes=" + nodeData.size() + " edges=" + edgeData.size()
            + " papers=" + (groups.size() - (groups.contains("core") ? 1 : 0)));
    }

    private static String hslToHex(int h, int s, int l) {
        float hf = h / 360f;
        float sf = s / 100f;
        float lf = l / 100f;
        float c = (1 - Math.abs(2 * lf - 1)) * sf;
        float x = c * (1 - Math.abs((hf * 6) % 2 - 1));
        float m = lf - c / 2;
        float r, g, b;
        int sector = (int)(hf * 6) % 6;
        if (sector == 0)      { r=c; g=x; b=0; }
        else if (sector == 1) { r=x; g=c; b=0; }
        else if (sector == 2) { r=0; g=c; b=x; }
        else if (sector == 3) { r=0; g=x; b=c; }
        else if (sector == 4) { r=x; g=0; b=c; }
        else                  { r=c; g=0; b=x; }
        return String.format("#%02x%02x%02x",
            Math.round((r + m) * 255),
            Math.round((g + m) * 255),
            Math.round((b + m) * 255));
    }

    private static Map<String, String> readTitles() {
        Map<String, String> titles = new HashMap<>();
        Path base = Path.of("src/main/java");
        if (!Files.isDirectory(base)) return titles;
        try {
            for (Path dir : Files.newDirectoryStream(base, "paper_*")) {
                String pkg = dir.getFileName().toString();
                Path fm = dir.resolve("formalization.md");
                if (Files.isRegularFile(fm)) {
                    Matcher mt = Pattern.compile("論文：《(.+?)》").matcher(Files.readString(fm));
                    if (mt.find()) { titles.put(pkg, mt.group(1)); continue; }
                }
                Path cj = dir.resolve("Concepts.java");
                if (Files.isRegularFile(cj)) {
                    Matcher mt = Pattern.compile("《(.+?)》").matcher(Files.readString(cj));
                    if (mt.find()) { titles.put(pkg, mt.group(1)); }
                }
            }
        } catch (IOException ignored) {}
        return titles;
    }

    private static Map<String, String> readChineseNames() {
        Map<String, String> names = new HashMap<>();
        // core 原語已使用中文 record 名（時間、空間、個體、刺激、死亡），不需要映射

        Path base = Path.of("src/main/java");
        if (!Files.isDirectory(base)) return names;
        // 匹配形式化文件中「中文（English）」格式，建立 English → 中文映射
        // 規範格式：概念：宗教張力（ReligiousTension）
        Pattern p = Pattern.compile("([\\p{IsHan}]+)（(\\w+)）");
        try {
            for (Path dir : Files.newDirectoryStream(base, "paper_*")) {
                Path fm = dir.resolve("formalization.md");
                if (!Files.isRegularFile(fm)) continue;
                String content = Files.readString(fm);
                Matcher m = p.matcher(content);
                while (m.find()) {
                    names.putIfAbsent(m.group(2), m.group(1));  // English → 中文
                }
            }
        } catch (IOException ignored) {}
        return names;
    }

    private static final String HTML_TEMPLATE = """
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
<title>概念星圖</title>
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
<div id="tt" class="p"><span>語意流動力學</span><span>概念星圖</span></div>
<div id="ct" class="p">
  <button id="ba" class="on">全部</button>
  <button id="br">隨機 3 篇</button>
  <button id="bs">↻ 打亂</button>
</div>
<div id="fi" class="p"></div>
<div id="lg" class="p"></div>
<div id="nf" class="p"></div>
<div id="ht" class="p">滾輪縮放 · 拖曳平移 · 點擊鎖定</div>

<script>
var PAPERS = /*PAPERS_DATA*/;
var ALL_N = /*NODES_DATA*/;
var ALL_L = /*EDGES_DATA*/;
var pks = Object.keys(PAPERS).filter(function(k){return k!=='core';});
var RC = Math.min(3, pks.length);
if(pks.length<=RC) document.getElementById('br').style.display='none';
else document.getElementById('br').textContent='隨機 '+RC+' 篇';

var curSim = null;

function filt(sel){
  var ns = JSON.parse(JSON.stringify(ALL_N));
  var ls = JSON.parse(JSON.stringify(ALL_L));
  if(sel){
    var gs = {}; sel.forEach(function(s){gs[s]=1;}); gs['core']=1;
    ns = ns.filter(function(n){return gs[n.group];});
    var ni = {}; ns.forEach(function(n){ni[n.id]=1;});
    ls = ls.filter(function(l){return ni[l.source]&&ni[l.target];});
  }
  var deg = {};
  ns.forEach(function(n){deg[n.id]=0;});
  ls.forEach(function(l){deg[l.source]=(deg[l.source]||0)+1;deg[l.target]=(deg[l.target]||0)+1;});
  ns.forEach(function(n){n.degree=deg[n.id]||0;});
  ls.forEach(function(l){
    if(l.cross){l.color='#ffffff';}
    else{var s=ns.find(function(n){return n.id===l.source;});l.color=s?PAPERS[s.group].color:'#444';}
  });
  return {nodes:ns,links:ls};
}

function draw(nodes,links){
  if(curSim)curSim.stop();
  d3.select('svg').remove();
  var W=window.innerWidth, H=window.innerHeight;
  var svg=d3.select('body').append('svg').attr('width',W).attr('height',H);
  var defs=svg.append('defs');
  function mg(id,s){var f=defs.append('filter').attr('id',id).attr('x','-50%').attr('y','-50%').attr('width','200%').attr('height','200%');f.append('feGaussianBlur').attr('in','SourceGraphic').attr('stdDeviation',s).attr('result','b');var m=f.append('feMerge');m.append('feMergeNode').attr('in','b');m.append('feMergeNode').attr('in','SourceGraphic');}
  mg('gs',2);mg('gl',4);
  var bg=svg.append('g');
  for(var i=0;i<300;i++)bg.append('circle').attr('cx',Math.random()*W).attr('cy',Math.random()*H).attr('r',Math.random()*0.7+0.1).attr('fill','#fff').attr('opacity',Math.random()*0.25+0.03);
  var g=svg.append('g');
  var lbSel;
  var zm=d3.zoom().scaleExtent([0.1,8]).on('zoom',function(e){
    g.attr('transform',e.transform);
    if(lbSel){var k=e.transform.k;lbSel.style('font-size',Math.max(8,13/k)+'px').style('opacity',k>1.5?0.9:k>0.7?0.7:0.35);}
  });
  svg.call(zm);
  svg.call(zm.transform,d3.zoomIdentity.translate(W*0.17,H*0.17).scale(0.7));

  var ag=[]; nodes.forEach(function(n){if(n.group!=='core'&&ag.indexOf(n.group)<0)ag.push(n.group);});
  var gc={core:[W*0.5,H*0.5]};
  ag.forEach(function(p,i){
    var a=(i/ag.length)*Math.PI*2-Math.PI/2;
    gc[p]=[W*0.5+W*0.28*Math.cos(a),H*0.5+H*0.3*Math.sin(a)];
  });

  var sim=d3.forceSimulation(nodes)
    .force('link',d3.forceLink(links).id(function(d){return d.id;}).distance(function(d){return d.cross?130:50;}).strength(function(d){return d.cross?0.1:0.5;}))
    .force('charge',d3.forceManyBody().strength(-130))
    .force('cx',d3.forceX(function(d){return (gc[d.group]||[W/2])[0];}).strength(0.07))
    .force('cy',d3.forceY(function(d){return (gc[d.group]||[0,H/2])[1];}).strength(0.07))
    .force('collide',d3.forceCollide(16))
    .alphaDecay(0.015).velocityDecay(0.3);
  curSim=sim;

  var lkSel=g.append('g').selectAll('line').data(links).join('line')
    .attr('stroke',function(d){return d.color;})
    .attr('stroke-opacity',function(d){if(d.edgeType==='implements')return 0.15;return d.cross?0.1:0.07;})
    .attr('stroke-width',function(d){if(d.edgeType==='implements')return 0.9;return d.cross?0.7:0.4;})
    .attr('stroke-dasharray',function(d){
      if(d.edgeType==='implements') return '2,3';
      if(d.cross) return '3,2';
      return null;
    });
  var ndSel=g.append('g').selectAll('circle').data(nodes).join('circle')
    .attr('r',function(d){
      if(d.group==='core') return 5;
      return d.type==='interface'?Math.max(4,2.5+d.degree*0.4):Math.max(2.5,2+d.degree*0.4);
    })
    .attr('fill',function(d){return d.type==='interface'?'transparent':PAPERS[d.group].color;})
    .attr('stroke',function(d){return d.type==='interface'?PAPERS[d.group].color:'none';})
    .attr('stroke-width',function(d){return d.type==='interface'?1.5:0;})
    .attr('filter',function(d){return d.group==='core'?'url(#gl)':'url(#gs)';})
    .attr('opacity',0.9).style('cursor','pointer');
  lbSel=g.append('g').selectAll('text').data(nodes).join('text')
    .text(function(d){return d.chName||d.name;}).attr('fill','#ffffff')
    .attr('dx',8).attr('dy',3)
    .style('font-size','13px').style('font-family',"'PingFang TC','Noto Sans TC',system-ui,sans-serif")
    .style('font-weight','600')
    .style('opacity',0.7).style('pointer-events','none').style('user-select','none');

  function si(l){return typeof l.source==='object'?l.source.id:l.source;}
  function ti(l){return typeof l.target==='object'?l.target.id:l.target;}
  var locked=null;
  function hi(d){
    var nb={};nb[d.id]=1;
    links.forEach(function(l){if(si(l)===d.id)nb[ti(l)]=1;if(ti(l)===d.id)nb[si(l)]=1;});
    ndSel.attr('opacity',function(n){return nb[n.id]?1:0.06;}).attr('filter',function(n){return nb[n.id]?'url(#gl)':null;});
    lkSel.attr('stroke-opacity',function(l){return(si(l)===d.id||ti(l)===d.id)?0.7:0.01;})
      .attr('stroke-width',function(l){return(si(l)===d.id||ti(l)===d.id)?1.5:0.2;});
    lbSel.style('opacity',function(n){return nb[n.id]?1:0.05;}).style('font-weight',function(n){return n.id===d.id?'700':'600';});
    var cx=0;links.forEach(function(l){if((si(l)===d.id||ti(l)===d.id)&&l.cross)cx++;});
    var imp=0;links.forEach(function(l){if((si(l)===d.id||ti(l)===d.id)&&l.edgeType==='implements')imp++;});
    var el=document.getElementById('nf');el.style.display='block';el.style.borderColor=PAPERS[d.group].color+'22';
    el.innerHTML='<div style="color:'+PAPERS[d.group].color+';font-size:13px;font-weight:600">'+(d.chName||d.name)+'</div>'
      +'<div style="color:#555;font-size:10px;line-height:1.7;margin-top:4px"><div>'+PAPERS[d.group].label+'</div>'
      +(d.type==='interface'?'<div style="color:#777">封閉分類</div>':'')
      +'<div>連接：'+d.degree+'</div>'
      +(cx>0?'<div style="color:#777">跨論文：'+cx+'</div>':'')
      +(imp>0?'<div style="color:#777">封閉分類邊：'+imp+'</div>':'')
      +'</div>';
  }
  function lo(){
    ndSel.attr('opacity',0.9)
      .attr('fill',function(d){return d.type==='interface'?'transparent':PAPERS[d.group].color;})
      .attr('stroke',function(d){return d.type==='interface'?PAPERS[d.group].color:'none';})
      .attr('filter',function(d){return d.group==='core'?'url(#gl)':'url(#gs)';});
    lkSel.attr('stroke-opacity',function(d){if(d.edgeType==='implements')return 0.15;return d.cross?0.1:0.07;})
      .attr('stroke-width',function(d){if(d.edgeType==='implements')return 0.9;return d.cross?0.7:0.4;});
    lbSel.style('opacity',0.7).style('font-weight','600');
    document.getElementById('nf').style.display='none';
  }
  ndSel.on('mouseenter',function(_,d){if(!locked)hi(d);}).on('mouseleave',function(){if(!locked)lo();})
    .on('click',function(e,d){e.stopPropagation();if(locked===d.id){locked=null;lo();}else{locked=d.id;hi(d);}});
  svg.on('click',function(){if(locked){locked=null;lo();}});
  ndSel.call(d3.drag()
    .on('start',function(e,d){if(!e.active)sim.alphaTarget(0.12).restart();d.fx=d.x;d.fy=d.y;})
    .on('drag',function(e,d){d.fx=e.x;d.fy=e.y;})
    .on('end',function(e,d){if(!e.active)sim.alphaTarget(0);d.fx=null;d.fy=null;}));
  sim.on('tick',function(){
    lkSel.attr('x1',function(d){return d.source.x;}).attr('y1',function(d){return d.source.y;}).attr('x2',function(d){return d.target.x;}).attr('y2',function(d){return d.target.y;});
    ndSel.attr('cx',function(d){return d.x;}).attr('cy',function(d){return d.y;});
    lbSel.attr('x',function(d){return d.x;}).attr('y',function(d){return d.y;});
  });
  document.getElementById('bs').onclick=function(){
    sim.nodes().forEach(function(n){n.x=Math.random()*W;n.y=Math.random()*H;n.vx=0;n.vy=0;n.fx=null;n.fy=null;});
    sim.alpha(1).restart();
  };
}

function buildLeg(gs){
  var el=document.getElementById('lg');el.innerHTML='';
  var ks=gs||Object.keys(PAPERS);
  ks.forEach(function(k){
    var v=PAPERS[k];if(!v)return;
    var r=document.createElement('div');r.style.cssText='display:flex;align-items:center;gap:7px;margin-bottom:2px';
    r.innerHTML='<div style="width:6px;height:6px;border-radius:50%;background:'+v.color+';box-shadow:0 0 4px '+v.color+'55;flex-shrink:0"></div><span style="color:'+v.color+';font-size:10px;opacity:0.75">'+v.label+'</span>';
    el.appendChild(r);
  });
  var ed=document.createElement('div');ed.style.cssText='margin-top:6px;border-top:1px solid rgba(255,255,255,0.05);padding-top:5px';
  ed.innerHTML='<div style="display:flex;align-items:center;gap:7px;margin-bottom:2px"><svg width="14" height="2"><line x1="0" y1="1" x2="14" y2="1" stroke="rgba(255,255,255,0.4)" stroke-width="1"/></svg><span style="color:#555;font-size:9px">論文內</span></div><div style="display:flex;align-items:center;gap:7px;margin-bottom:2px"><svg width="14" height="2"><line x1="0" y1="1" x2="14" y2="1" stroke="rgba(255,255,255,0.4)" stroke-width="1" stroke-dasharray="3,2"/></svg><span style="color:#555;font-size:9px">跨論文</span></div><div style="display:flex;align-items:center;gap:7px"><svg width="14" height="2"><line x1="0" y1="1" x2="14" y2="1" stroke="rgba(255,255,255,0.4)" stroke-width="1" stroke-dasharray="1,3"/></svg><span style="color:#555;font-size:9px">封閉分類</span></div>';
  el.appendChild(ed);
  var nd=document.createElement('div');nd.style.cssText='margin-top:5px;border-top:1px solid rgba(255,255,255,0.05);padding-top:5px';
  nd.innerHTML='<div style="display:flex;align-items:center;gap:7px;margin-bottom:2px"><svg width="8" height="8"><circle cx="4" cy="4" r="3" fill="rgba(255,255,255,0.4)"/></svg><span style="color:#555;font-size:9px">概念</span></div><div style="display:flex;align-items:center;gap:7px"><svg width="8" height="8"><circle cx="4" cy="4" r="3" fill="none" stroke="rgba(255,255,255,0.4)" stroke-width="1.2"/></svg><span style="color:#555;font-size:9px">封閉分類</span></div>';
  el.appendChild(nd);
}

document.getElementById('ba').onclick=function(){
  this.classList.add('on');document.getElementById('br').classList.remove('on');
  document.getElementById('fi').textContent='';
  var d=filt(null);buildLeg(null);draw(d.nodes,d.links);
};
document.getElementById('br').onclick=function(){
  var arr=pks.slice();
  for(var i=arr.length-1;i>0;i--){var j=Math.floor(Math.random()*(i+1));var t=arr[i];arr[i]=arr[j];arr[j]=t;}
  var picked=arr.slice(0,RC);
  this.classList.add('on');document.getElementById('ba').classList.remove('on');
  document.getElementById('fi').textContent=picked.map(function(p){return PAPERS[p].label;}).join('、');
  var gs=['core'].concat(picked);
  var d=filt(gs);buildLeg(gs);draw(d.nodes,d.links);
};

buildLeg(null);
var init=filt(null);
draw(init.nodes,init.links);
</script>
</body>
</html>
""";
}
