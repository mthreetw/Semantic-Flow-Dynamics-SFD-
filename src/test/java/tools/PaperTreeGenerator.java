package tools;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * 論文引用關係圖產生器
 */
public class PaperTreeGenerator {

    private static final String SOURCE_DIR = "src/main/java";
    private static final String OUTPUT_FILE = "PaperTree.html";
    private static final String FORMALIZATION_FILE = "formalization.md";

    record PaperNode(
        String uuid,
        String folderName,
        String title,
        String englishTitle,
        List<String> references,
        boolean isExternal,
        Path folderPath
    ) {}

    public static void main(String[] args) throws IOException {
        Path root = resolveProjectRoot(Path.of("").toAbsolutePath());
        Path sourceDir = root.resolve(SOURCE_DIR);
        Path outputFile = root.resolve(OUTPUT_FILE);

        if (!Files.isDirectory(sourceDir)) {
            System.err.println("錯誤：找不到 " + sourceDir);
            System.exit(1);
        }

        System.out.println("專案根目錄：" + root);
        System.out.println("掃描來源：" + sourceDir);

        List<PaperNode> nodes = new ArrayList<>(scanDirs(sourceDir, "paper_", false));
        System.out.println("找到 " + nodes.size() + " 篇論文");

        String html = generateHtml(nodes);
        Files.writeString(outputFile, html);
        System.out.println("已產出：" + outputFile);
    }

    static Path resolveProjectRoot(Path cwd) {
        Path p = cwd;
        while (p != null) {
            if (Files.exists(p.resolve("pom.xml")) ||
                Files.isDirectory(p.resolve(SOURCE_DIR)))
                return p;
            p = p.getParent();
        }
        return cwd;
    }

    static List<PaperNode> scanDirs(Path parent, String prefix, boolean isExternal) throws IOException {
        List<PaperNode> results = new ArrayList<>();
        try (DirectoryStream<Path> dirs = Files.newDirectoryStream(parent, prefix + "*")) {
            for (Path dir : dirs) {
                if (!Files.isDirectory(dir)) continue;
                parseFormalization(dir, isExternal).ifPresent(results::add);
            }
        }
        return results;
    }

    static Optional<PaperNode> parseFormalization(Path dir, boolean isExternal) {
        Path file = dir.resolve(FORMALIZATION_FILE);
        String folderName = dir.getFileName().toString();

        if (!Files.isRegularFile(file)) {
            String uuid = folderNameToUuid(folderName, isExternal);
            if (uuid != null) {
                return Optional.of(new PaperNode(
                    uuid, folderName, "(尚無形式化文件)", "",
                    List.of(), isExternal, dir));
            }
            return Optional.empty();
        }

        try {
            String content = Files.readString(file);

            String uuid = extractField(content, "UUID：");
            if (uuid == null) uuid = folderNameToUuid(folderName, isExternal);
            if (uuid == null) return Optional.empty();

            String title = extractField(content, "論文：");
            if (title != null) title = title.replaceAll("[《》]", "");
            else title = "(未知標題)";

            String englishTitle = extractField(content, "英文標題：");
            if (englishTitle == null) englishTitle = "";

            List<String> references = extractReferences(content);

            return Optional.of(new PaperNode(
                uuid, folderName, title, englishTitle,
                references, isExternal, dir));

        } catch (IOException e) {
            System.err.println("警告：無法讀取 " + file + " → " + e.getMessage());
            return Optional.empty();
        }
    }

    static String extractField(String content, String label) {
        int idx = content.indexOf(label);
        if (idx < 0) return null;
        int start = idx + label.length();
        int end = content.indexOf('\n', start);
        if (end < 0) end = content.length();
        String value = content.substring(start, end).trim();
        if (value.endsWith("\r")) value = value.substring(0, value.length() - 1);
        return value.isEmpty() ? null : value;
    }

    static List<String> extractReferences(String content) {
        List<String> refs = new ArrayList<>();
        int idx = content.indexOf("引用：");
        if (idx < 0) return refs;
        int start = content.indexOf('\n', idx);
        if (start < 0) return refs;

        String[] lines = content.substring(start + 1).split("\n");
        for (String rawLine : lines) {
            String line = rawLine.trim().replace("\r", "");
            if (line.isEmpty()) continue;
            if (line.startsWith("#") || line.startsWith("形式化") || line.startsWith("論文")) break;
            if (line.equals("無") || line.equals("（無引用則寫「無」）")) break;
            if (line.startsWith("ext:")) continue;
            if (line.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"))
                refs.add(line);
        }
        return refs;
    }

    static String folderNameToUuid(String folderName, boolean isExternal) {
        String prefix = isExternal ? "ext_" : "paper_";
        if (!folderName.startsWith(prefix)) return null;
        String withDashes = folderName.substring(prefix.length()).replace('_', '-');
        if (withDashes.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"))
            return withDashes;
        return null;
    }

    static String generateHtml(List<PaperNode> nodes) {
        Map<String, Integer> uuidIndex = new HashMap<>();
        for (int i = 0; i < nodes.size(); i++)
            uuidIndex.put(nodes.get(i).uuid(), i);

        Map<String, Integer> refCount = new HashMap<>();
        for (PaperNode node : nodes)
            for (String ref : node.references())
                refCount.merge(ref, 1, Integer::sum);

        StringBuilder nj = new StringBuilder("[");
        for (int i = 0; i < nodes.size(); i++) {
            PaperNode n = nodes.get(i);
            int cited = refCount.getOrDefault(n.uuid(), 0);
            if (i > 0) nj.append(",");
            nj.append(String.format(
                "{\"id\":%d,\"uuid\":\"%s\",\"title\":\"%s\",\"en\":\"%s\"," +
                "\"folder\":\"%s\",\"cited\":%d,\"refs\":%d}",
                i, esc(n.uuid()), esc(n.title()), esc(n.englishTitle()),
                esc(n.folderName()), cited, n.references().size()));
        }
        nj.append("]");

        StringBuilder lj = new StringBuilder("[");
        boolean first = true;
        for (int i = 0; i < nodes.size(); i++) {
            for (String ref : nodes.get(i).references()) {
                Integer target = uuidIndex.get(ref);
                if (target == null) continue;
                if (!first) lj.append(",");
                first = false;
                lj.append(String.format("{\"source\":%d,\"target\":%d}", i, target));
            }
        }
        lj.append("]");

        Set<String> known = new HashSet<>(uuidIndex.keySet());
        StringBuilder mj = new StringBuilder("[");
        boolean mf = true;
        for (PaperNode node : nodes)
            for (String ref : node.references()) {
                if (!known.contains(ref)) {
                    if (!mf) mj.append(",");
                    mf = false;
                    mj.append("\"").append(esc(ref + " ← " + node.title())).append("\"");
                }
            }
        mj.append("]");

        return HTML_TEMPLATE
            .replace("/*__NODES__*/", nj.toString())
            .replace("/*__LINKS__*/", lj.toString())
            .replace("/*__MISSING__*/", mj.toString())
            .replace("/*__COUNT__*/", String.valueOf(nodes.size()));
    }

    static String esc(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"")
                .replace("\n", "\\n").replace("\r", "").replace("\t", "\\t");
    }

    static final String HTML_TEMPLATE = """
    		<!DOCTYPE html>
    		<html lang="zh-Hant">
    		<head>
    		<meta charset="utf-8">
    		<meta name="viewport" content="width=device-width, initial-scale=1">
    		<title>群星圖 — 論文引用關係</title>
    		<style>
    		  * { margin:0; padding:0; box-sizing:border-box; }
    		  body {
    		    background:#0c0e15; color:#cdd0da;
    		    font-family:'Inter',-apple-system,'Segoe UI',sans-serif;
    		    overflow:hidden; height:100vh; width:100vw;
    		  }
    		  header {
    		    position:fixed; top:0; left:0; right:0; z-index:10;
    		    display:flex; align-items:center; gap:16px;
    		    padding:10px 20px;
    		    background:#151822; border-bottom:1px solid #232736;
    		  }
    		  header h1 { font-size:14px; font-weight:600; letter-spacing:.03em; }
    		  .stat {
    		    color:#5f6378;
    		    font-family:'SF Mono','Cascadia Code','Fira Code',monospace;
    		    font-size:12px;
    		  }
    		  #search {
    		    margin-left:auto;
    		    padding:4px 10px; border-radius:4px;
    		    border:1px solid #232736; background:#0c0e15;
    		    color:#cdd0da; font-size:12px; width:200px;
    		  }
    		  #search:focus { outline:none; border-color:#5b7cf7; }
    		  #graph { position:absolute; top:44px; left:0; right:0; bottom:0; }
    		  svg { width:100%; height:100%; }
    		  .node-label { font-size:11px; pointer-events:none; text-anchor:middle; font-weight:500; }
    		  .node-uuid { font-size:8.5px; fill:#5f6378; pointer-events:none; text-anchor:middle; font-family:monospace; }
    		</style>
    		</head>
    		<body>
    		<header>
    		  <h1>群星圖</h1>
    		  <span class="stat">/*__COUNT__*/ 篇</span>
    		  <input type="text" id="search" placeholder="搜尋論文…">
    		</header>
    		<div id="graph"></div>

    		<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/7.9.0/d3.min.js"></script>
    		<script>
    		const nodes = /*__NODES__*/;
    		const allLinks = /*__LINKS__*/;
    		const SRC = 'src/main/java/';

    		// === 1. 遞移約減 ===
    		const directParents = new Map();
    		nodes.forEach(n => directParents.set(n.id, new Set()));
    		allLinks.forEach(l => directParents.get(l.source).add(l.target));

    		function reachableWithout(start, skip) {
    		  const visited = new Set();
    		  const queue = [];
    		  for (const p of directParents.get(start)) {
    		    if (p === skip) continue;
    		    if (!visited.has(p)) { visited.add(p); queue.push(p); }
    		  }
    		  while (queue.length > 0) {
    		    const cur = queue.shift();
    		    for (const p of directParents.get(cur)) {
    		      if (!visited.has(p)) { visited.add(p); queue.push(p); }
    		    }
    		  }
    		  return visited;
    		}
    		const links = allLinks.filter(l => !reachableWithout(l.source, l.target).has(l.target));

    		// === 2. DAG 分層 ===
    		const parentsAll = new Map();
    		nodes.forEach(n => parentsAll.set(n.id, []));
    		allLinks.forEach(l => parentsAll.get(l.source).push(l.target)); 

    		const layer = new Map();
    		let changed = true;
    		let attempts = 0;
    		while (changed && attempts < 1000) {
    		  changed = false;
    		  attempts++;
    		  nodes.forEach(n => {
    		    const ps = parentsAll.get(n.id);
    		    if (ps.length === 0) {
    		      if (!layer.has(n.id)) { layer.set(n.id, 0); changed = true; }
    		      return;
    		    }
    		    const pL = ps.map(p => layer.get(p)).filter(v => v !== undefined);
    		    if (pL.length === 0) return;
    		    const nL = Math.max(...pL) + 1;
    		    if (!layer.has(n.id) || layer.get(n.id) < nL) {
    		      layer.set(n.id, nL); changed = true;
    		    }
    		  });
    		}

    		const maxLayer = Math.max(0, ...layer.values());
    		const nodeMap = new Map();
    		nodes.forEach(n => {
    		  if (!layer.has(n.id)) layer.set(n.id, maxLayer + 1);
    		  n.layer = layer.get(n.id);
    		  nodeMap.set(n.id, n);
    		});

    		// === 3. 嚴格挑選「連續最長路徑」作為骨幹 (Tree) ===
    		links.forEach(l => l.isPrimary = false);
    		nodes.forEach(n => {
    		  if (n.layer === 0) return;
    		  const outgoing = links.filter(l => l.source === n.id);
    		  if (outgoing.length === 0) return;
    		  let primary = outgoing.find(l => nodeMap.get(l.target).layer === n.layer - 1);
    		  if (!primary) primary = outgoing[0];
    		  primary.isPrimary = true;
    		});

    		const treeParents = new Map();
    		const treeChildren = new Map();
    		nodes.forEach(n => { treeParents.set(n.id, []); treeChildren.set(n.id, []); });
    		links.filter(l => l.isPrimary).forEach(l => {
    		  treeParents.get(l.source).push(l.target);
    		  treeChildren.get(l.target).push(l.source);
    		});

    		// === 4. 分支染色 ===
    		const roots = nodes.filter(n => n.layer === 0);
    		const l1Nodes = nodes.filter(n => n.layer === 1);
    		const hueStep = l1Nodes.length > 0 ? 360 / l1Nodes.length : 60;

    		const nodeHue = new Map();    
    		roots.forEach(n => nodeHue.set(n.id, -1));
    		l1Nodes.forEach((n, i) => nodeHue.set(n.id, (i * hueStep + 220) % 360));

    		function resolveNodeHue(id, visited = new Set()) {
    		  if (nodeHue.has(id)) return nodeHue.get(id);
    		  if (visited.has(id)) return 0;
    		  visited.add(id);

    		  const ps = treeParents.get(id) || [];
    		  if (ps.length === 0) return 0;

    		  for (const pId of ps) {
    		    const h = resolveNodeHue(pId, visited);
    		    if (h !== undefined && h !== -1) {
    		      nodeHue.set(id, h);
    		      return h;
    		    }
    		  }
    		  return 0;
    		}
    		nodes.forEach(n => { if (!nodeHue.has(n.id)) resolveNodeHue(n.id); });

    		function nodeStroke(d) {
    		  const h = nodeHue.get(d.id) || 0;
    		  if (h === -1) return '#a0a8c0';
    		  const depth = d.layer - 1;
    		  const sat = Math.max(30, Math.min(100, 75 - depth * 5));
    		  const lit = Math.max(25, Math.min(80, 65 - depth * 4));
    		  return `hsl(${h}, ${sat}%, ${lit}%)`;
    		}
    		function nodeFill(d) {
    		  const h = nodeHue.get(d.id) || 0;
    		  if (h === -1) return '#2a2d3a';
    		  const depth = d.layer - 1;
    		  const sat = Math.max(20, Math.min(100, 50 - depth * 5));
    		  const lit = Math.max(12, Math.min(60, 25 - depth * 2));
    		  return `hsl(${h}, ${sat}%, ${lit}%)`;
    		}
    		function labelColor(d) {
    		  const h = nodeHue.get(d.id) || 0;
    		  if (h === -1) return '#cdd0da';
    		  const depth = d.layer - 1;
    		  const sat = Math.max(20, Math.min(100, 60 - depth * 5));
    		  const lit = Math.max(45, Math.min(95, 78 - depth * 3));
    		  return `hsl(${h}, ${sat}%, ${lit}%)`;
    		}
    		function edgeColor(l) {
    		  const sid = typeof l.source === 'object' ? l.source.id : l.source;
    		  const h = nodeHue.get(sid) || 0;
    		  if (nodeHue.get(sid) === -1) return '#2a3050';
    		  return `hsla(${h}, 40%, 35%, 0.6)`;
    		}

    		// === 5. 骨幹重心排版 ===
    		const layerGroups = new Map();
    		nodes.forEach(n => {
    		  if (!layerGroups.has(n.layer)) layerGroups.set(n.layer, []);
    		  layerGroups.get(n.layer).push(n);
    		});

    		const order = new Map();
    		(layerGroups.get(0) || []).forEach((n, i) => order.set(n.id, i));

    		for (let pass = 0; pass < 8; pass++) {
    		  if (pass % 2 === 0) {
    		    for (let l = 1; l <= maxLayer; l++) {
    		      const grp = layerGroups.get(l) || [];
    		      grp.forEach(n => {
    		        const ps = treeParents.get(n.id);
    		        const pO = ps.map(p => order.get(p)).filter(v => v !== undefined);
    		        if (pO.length > 0) order.set(n.id, pO.reduce((a,b)=>a+b,0)/pO.length);
    		      });
    		      grp.sort((a,b) => order.get(a.id) - order.get(b.id));
    		      grp.forEach((n,i) => order.set(n.id, i));
    		    }
    		  } else {
    		    for (let l = maxLayer - 1; l >= 0; l--) {
    		      const grp = layerGroups.get(l) || [];
    		      grp.forEach(n => {
    		        const cs = treeChildren.get(n.id);
    		        const cO = cs.map(c => order.get(c)).filter(v => v !== undefined);
    		        if (cO.length > 0) order.set(n.id, (order.get(n.id)+cO.reduce((a,b)=>a+b,0)/cO.length)/2);
    		      });
    		      grp.sort((a,b) => order.get(a.id) - order.get(b.id));
    		      grp.forEach((n,i) => order.set(n.id, i));
    		    }
    		  }
    		}

    		// === 目標 X/Y 與自然亂序 Jitter ===
    		const ct = document.getElementById('graph');
    		const W = ct.clientWidth, H = ct.clientHeight;
    		const LAYER_H = 130, NODE_GAP = 160, PAD_TOP = 80;

    		for (let l = 0; l <= maxLayer; l++) {
    		  const group = layerGroups.get(l) || [];
    		  group.forEach((n, i) => {
    		    const jitterX = (Math.random() - 0.5) * 60;
    		    const jitterY = (Math.random() - 0.5) * 40;
    		    n.targetY = PAD_TOP + l * LAYER_H + jitterY;
    		    n.targetX = W / 2 + (i - (group.length - 1) / 2) * NODE_GAP + jitterX;
    		    n.x = n.targetX;
    		    n.y = n.targetY;
    		  });
    		}

    		const svgH = Math.max(H, PAD_TOP * 2 + (maxLayer + 1) * LAYER_H);
    		const svg = d3.select('#graph').append('svg').attr('viewBox', [0, 0, W, svgH]);
    		const g = svg.append('g');
    		const zoom = d3.zoom().scaleExtent([.08, 5]).on('zoom', e => g.attr('transform', e.transform));
    		svg.call(zoom);
    		svg.call(zoom.transform, d3.zoomIdentity.translate(W / 2 - (W / 2) * 0.85, PAD_TOP * 0.5).scale(0.85));

    		const defs = svg.append('defs');
    		links.forEach((l, i) => {
    		  const sid = typeof l.source === 'object' ? l.source.id : l.source;
    		  const h = nodeHue.get(sid) || 0;
    		  const color = nodeHue.get(sid) === -1 ? '#2a3050' : `hsl(${h}, 40%, 35%)`;
    		  defs.append('marker').attr('id', 'arr-' + i).attr('viewBox','0 0 10 6').attr('refX',10).attr('refY',3).attr('markerWidth',7).attr('markerHeight',5).attr('orient','auto').append('path').attr('d','M0,0L10,3L0,6').attr('fill', color);
    		});
    		defs.append('marker').attr('id', 'arr-hl').attr('viewBox','0 0 10 6').attr('refX',10).attr('refY',3).attr('markerWidth',7).attr('markerHeight',5).attr('orient','auto').append('path').attr('d','M0,0L10,3L0,6').attr('fill', '#fff');

    		const R = 9;
    		const link = g.append('g').selectAll('line').data(links).join('line')
    		  .attr('stroke', d => edgeColor(d)).attr('stroke-width', 1.5).attr('marker-end', (d,i) => 'url(#arr-' + i + ')')
    		  .style('display', d => d.isPrimary ? 'inline' : 'none');

    		// === 6. 力學引擎 (包含拖曳事件) ===
    		links.forEach(l => {
    		  if (typeof l.source !== 'object') l.source = nodeMap.get(l.source);
    		  if (typeof l.target !== 'object') l.target = nodeMap.get(l.target);
    		});

    		const sim = d3.forceSimulation(nodes)
    		  .force('y', d3.forceY(d => d.targetY).strength(0.3))
    		  .force('x', d3.forceX(d => d.targetX).strength(0.3))
    		  .force('link', d3.forceLink(links.filter(l => l.isPrimary)).distance(LAYER_H * 0.9).strength(0.1))
    		  .force('charge', d3.forceManyBody().strength(-400))
    		  .force('collision', d3.forceCollide().radius(65));

    		const node = g.append('g').selectAll('g').data(nodes).join('g')
    		  .style('cursor','pointer')
    		  .call(d3.drag()
    		    .on('start', (ev,d) => { if (!ev.active) sim.alphaTarget(.3).restart(); d.fx = d.x; d.fy = d.y; })
    		    .on('drag', (ev,d) => { d.fx = ev.x; d.fy = ev.y; })
    		    .on('end', (ev,d) => { if (!ev.active) sim.alphaTarget(0); d.fx = null; d.fy = null; }));

    		node.append('circle').attr('r', d => 7 + d.cited * 1.5)
    		  .attr('fill', d => nodeFill(d)).attr('stroke', d => nodeStroke(d)).attr('stroke-width', 1.5);

    		node.each(function(d) {
    		  const lines = [];
    		  for (let i = 0; i < d.title.length; i += 10) lines.push(d.title.substring(i, i + 10));
    		  const r = 7 + d.cited * 1.5;
    		  const text = d3.select(this).append('text').attr('class', 'node-label').attr('fill', labelColor(d)).attr('x', 0);
    		  lines.forEach((line, i) => text.append('tspan').attr('x', 0).attr('dy', i === 0 ? -(r + 4 + (lines.length - 1) * 14) : 14).text(line));
    		});
    		node.append('text').attr('class','node-uuid').attr('dy', d => 19 + d.cited * 1.5).text(d => d.uuid.substring(0, 8));

    		sim.on('tick', () => {
    		  link.attr('x1', d => d.target.x).attr('y1', d => d.target.y + R)
    		      .attr('x2', d => d.source.x).attr('y2', d => d.source.y - R);
    		  node.attr('transform', d => 'translate('+d.x+','+d.y+')');
    		});

    		// === 7. Hover 互動 ===
    		node.on('mouseover', (ev, d) => {
    		  link
    		    .style('display', l => (l.source.id === d.id || l.target.id === d.id || l.isPrimary) ? 'inline' : 'none')
    		    .style('opacity', l => (l.source.id === d.id || l.target.id === d.id) ? 1 : 0.2)
    		    .attr('stroke', (l,i) => (l.source.id === d.id || l.target.id === d.id) ? '#fff' : edgeColor(l))
    		    .attr('stroke-width', l => (l.source.id === d.id || l.target.id === d.id) ? 2.5 : 1.5)
    		    .attr('marker-end', (l,i) => (l.source.id === d.id || l.target.id === d.id) ? 'url(#arr-hl)' : 'url(#arr-'+i+')');

    		  const related = new Set([d.id]);
    		  links.forEach(l => {
    		    if (l.source.id === d.id) related.add(l.target.id);
    		    if (l.target.id === d.id) related.add(l.source.id);
    		  });
    		  node.style('opacity', n => related.has(n.id) ? 1 : 0.2);
    		}).on('mouseout', () => {
    		  link.style('display', d => d.isPrimary ? 'inline' : 'none')
    		      .style('opacity', 1).attr('stroke', d => edgeColor(d)).attr('stroke-width', 1.5).attr('marker-end', (d,i) => 'url(#arr-'+i+')');
    		  node.style('opacity', 1);
    		}).on('click', (ev, d) => { window.open(SRC + d.folder + '/', '_blank'); });

    		document.getElementById('search').addEventListener('input', function() {
    		  const q = this.value.toLowerCase();
    		  node.style('opacity', d => q === '' ? 1 : (d.title.toLowerCase().includes(q) || d.uuid.includes(q) || (d.en && d.en.toLowerCase().includes(q))) ? 1 : 0.1);
    		  link.style('display', l => l.isPrimary ? 'inline' : 'none').style('opacity', q === '' ? 1 : 0.06);
    		});
    		</script>
    		</body>
    		</html>
    		""";
}