"""Generate Java placeholder Solution classes matching existing LeetCode repo style."""

from __future__ import annotations

from pathlib import Path

from curriculum_data import (
    NEEDS_GRAPH_NODE,
    NEEDS_LIST_NODE,
    NEEDS_RANDOM_NODE,
    NEEDS_TREE_NODE,
    PROBLEMS,
)

ROOT = Path(__file__).resolve().parent.parent
SRC = ROOT / "src"

LIST_NODE = """
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
""".strip()

TREE_NODE = """
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
""".strip()

GRAPH_NODE = """
// Definition for a Node (graph).
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
""".strip()

RANDOM_NODE = """
// Definition for a Node (list with random pointer).
class Node {
    int val;
    Node next;
    Node random;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
""".strip()


def _imports_for(nc: int, java: str) -> str:
    lines = []
    needs_list = (
        "List<" in java
        or "ArrayList" in java
        or "Map<" in java
        or "HashMap" in java
        or "Deque" in java
        or "PriorityQueue" in java
        or "Collections" in java
    )
    if needs_list or nc in NEEDS_GRAPH_NODE:
        lines.append("import java.util.*;")
    return "\n".join(lines)


def _helpers(nc: int) -> str:
    parts = []
    if nc in NEEDS_LIST_NODE:
        parts.append(LIST_NODE)
    if nc in NEEDS_TREE_NODE:
        parts.append(TREE_NODE)
    if nc in NEEDS_GRAPH_NODE:
        parts.append(GRAPH_NODE)
    if nc in NEEDS_RANDOM_NODE:
        parts.append(RANDOM_NODE)
    return ("\n\n" + "\n\n".join(parts)) if parts else ""


def render_solution(problem: dict) -> str:
    nc = problem["nc"]
    lc = problem["lc"]
    title = problem["title"]
    pattern = problem["pattern"]
    difficulty = problem["difficulty"]
    explanation = problem["explanation"]
    java = problem["java"]
    pkg = f"LeetCode{lc}"

    imports = _imports_for(nc, java)
    helpers = _helpers(nc)

    import_block = (imports + "\n\n") if imports else ""
    helper_block = ("\n" + helpers + "\n") if helpers else ""

    similar_lines = "\n".join(
        f" *   - {t} (LC {lcnum})" for t, lcnum in problem.get("similar", [])
    ) or " *   (none listed)"

    main_block = _indent_method(_main_method(problem), 4)
    description_block = _problem_description_block(title, problem.get("url") or "")

    extra_imports = ""
    if "Arrays." in main_block and "import java.util" not in import_block:
        extra_imports = "import java.util.Arrays;\n\n"

    return f"""package {pkg};

{import_block}{extra_imports}/**
 * NeetCode #{nc} | LeetCode {lc}: {title}
 * Pattern: {pattern} | Difficulty: {difficulty}
 *
 * Approach hint:
 * {explanation}
 *
 * Similar problems:
{similar_lines}
 *
 * Problem:
{description_block}
 *
 * Link: https://leetcode.com/problems/{_slug(title)}/
 */
class Solution {{
{_indent_method(java, 4)}

{main_block}
}}
{helper_block}"""


def _main_method(problem: dict) -> str:
    """Sample main() so the stub can be run locally with example inputs."""
    title = problem.get("title") or f"LC {problem.get('lc')}"
    java = problem.get("java") or ""
    first_sig = ""
    for line in java.splitlines():
        stripped = line.strip()
        if stripped.startswith("public ") and "(" in stripped and not stripped.startswith("public static"):
            first_sig = stripped
            break

    calls: list[str] = []
    if "containsDuplicate(" in java and "Nearby" not in java:
        calls = [
            'System.out.println(sol.containsDuplicate(new int[]{1, 2, 3, 1})); // true',
            'System.out.println(sol.containsDuplicate(new int[]{1, 2, 3, 4})); // false',
        ]
    elif "isAnagram(" in java:
        calls = [
            'System.out.println(sol.isAnagram("anagram", "nagaram")); // true',
            'System.out.println(sol.isAnagram("rat", "car")); // false',
        ]
    elif "twoSum(" in java:
        calls = [
            'System.out.println(java.util.Arrays.toString(sol.twoSum(new int[]{2, 7, 11, 15}, 9))); // [0, 1]',
            'System.out.println(java.util.Arrays.toString(sol.twoSum(new int[]{3, 2, 4}, 6))); // [1, 2]',
        ]
    elif "groupAnagrams(" in java:
        calls = [
            'System.out.println(sol.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));',
        ]
    elif "containsNearbyDuplicate(" in java:
        calls = [
            'System.out.println(sol.containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3)); // true',
            'System.out.println(sol.containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2)); // false',
        ]
    elif "fourSum(" in java:
        calls = [
            'System.out.println(sol.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));',
        ]
    else:
        comment = f"// TODO: add sample calls for {title}"
        if first_sig:
            comment += f"\n        // Signature: {first_sig}"
        calls = [comment]

    body = "\n        ".join(calls)
    return f"""public static void main(String[] args) {{
        Solution sol = new Solution();
        {body}
    }}"""


def _indent_method(text: str, spaces: int) -> str:
    """Place method block inside class using brace depth for indentation."""
    out: list[str] = []
    depth = 0
    base = " " * spaces
    for raw in text.splitlines():
        stripped = raw.strip()
        if not stripped:
            out.append("")
            continue
        if stripped.startswith("}"):
            depth = max(0, depth - 1)
        out.append(base + ("    " * depth) + stripped)
        depth += stripped.count("{")
        if not stripped.startswith("}"):
            depth -= stripped.count("}")
        depth = max(0, depth)
    return "\n".join(out)


def _slug(title: str) -> str:
    s = title.lower()
    for ch in "()[],'":
        s = s.replace(ch, "")
    return "-".join(s.replace("/", " ").split())


def _problem_description_block(title: str, url: str = "") -> str:
    try:
        from leetcode_problem_text import description_for_problem
    except ImportError:
        import sys
        job_agent = Path.home() / "job-agent"
        if str(job_agent) not in sys.path:
            sys.path.insert(0, str(job_agent))
        try:
            from leetcode_problem_text import description_for_problem
        except ImportError:
            return " * (description unavailable)"
    block = description_for_problem(title=title, url=url)
    return block or " * (description unavailable)"


def write_stub(problem: dict, *, overwrite: bool = False) -> Path:
    lc = problem["lc"]
    pkg_dir = SRC / f"LeetCode{lc}"
    pkg_dir.mkdir(parents=True, exist_ok=True)
    path = pkg_dir / "Solution.java"
    if path.exists() and not overwrite:
        # Don't clobber solutions that already have real code (no TODO-only stubs check:
        # skip if file exists unless overwrite)
        return path
    path.write_text(render_solution(problem))
    return path


def write_stubs_for_lcs(lc_numbers: list[int], *, overwrite: bool = False) -> list[Path]:
    by_lc = {p["lc"]: p for p in PROBLEMS}
    written = []
    for lc in lc_numbers:
        if lc not in by_lc:
            print(f"Warning: LC {lc} not in curriculum")
            continue
        written.append(write_stub(by_lc[lc], overwrite=overwrite))
    return written


if __name__ == "__main__":
    import argparse

    parser = argparse.ArgumentParser()
    parser.add_argument("--lc", type=int, nargs="+", help="LeetCode numbers to stub")
    parser.add_argument("--all", action="store_true", help="Generate stubs for all 150")
    parser.add_argument("--overwrite", action="store_true")
    args = parser.parse_args()

    if args.all:
        paths = [write_stub(p, overwrite=args.overwrite) for p in PROBLEMS]
        print(f"Wrote/kept {len(paths)} stubs under src/")
    elif args.lc:
        paths = write_stubs_for_lcs(args.lc, overwrite=args.overwrite)
        for p in paths:
            print(p)
    else:
        parser.print_help()
