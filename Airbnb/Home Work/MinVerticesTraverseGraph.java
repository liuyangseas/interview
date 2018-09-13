/**
 * Given a directed graph, find the minimal number of vertices that can traverse all the vertices from them.
 * For example
 * 2->3->1->2->0, 4->5
 * Then we need [1, 4] (or [2, 4], [3, 4]) to traverse all the vertices.
 * Only one solution is needed if there are more than one possibilities.
 */

import java.util.*;

public class MinVerticesTraverseGraph {
  public List<Integer> getMin(int[][] edges, int n) {
    Set<Integer> res = new HashSet<>();

    Map<Integer, Set<Integer>> nodes = new HashMap<>();
    for (int i = 0; i < n; i++) {
      nodes.put(i, new HashSet<>());
    }
    for (int[] edge : edges) {
      nodes.get(edge[0]).add(edge[1]);
    }

    Set<Integer> visited = new HashSet<>();
    for (int i = 0; i < n; i++) {
      if (visited.contains(i)) {
        continue;
      }

      res.add(i);
      visited.add(i);
      Set<Integer> thisTimeVisited = new HashSet<>();
      dfs(res, nodes, i, i, visited, thisTimeVisited);
    }

    return new ArrayList<>(res);
  }

  private void dfs(Set<Integer> res, Map<Integer, Set<Integer>> nodes, int cur, int start,
                   Set<Integer> visited, Set<Integer> thisTimeVisited) {
    for (int next : nodes.get(cur)) {
      if (res.contains(next) && next != start) {
        res.remove(next);
      }
      if (!thisTimeVisited.contains(next)) {
        thisTimeVisited.add(next);
        visited.add(next);
        dfs(res, nodes, next, start, visited, thisTimeVisited);
      }
    }
  }


}

//public class Main {
//  public static void main(String[] args) {
//    MinVerticesTraverseGraph mvtg = new MinVerticesTraverseGraph();
////    1->2->3->1, 2->0->0
//    int[][] edges1 = {{0, 0}, {1, 2}, {2, 0}, {2, 3}, {3, 1}};
//    System.out.println(mvtg.getMin(edges1, 4));
////      0  1  2  3  4  5  6  7  8  9
////    0[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
////    1[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
////    2[0, 0, 0 ,0, 0, 0, 0, 0, 0, 1]
////    3[0, 0, 0, 1, 0, 1, 0, 1, 0, 0]
////    4[0, 0, 0, 0, 0, 0 ,0, 0, 1, 0]
////    5[0, 0, 0, 0, 0, 0, 0, 0, 1, 0]
////    6[0, 0, 0, 0, 0, 0, 1, 0, 0 ,0]
////    7[0, 0, 0, 0, 1, 0, 0, 0, 0, 0]
////    8[0, 0, 0, 0, 0, 0, 0, 1, 0, 0]
////    9[0, 0, 0, 1, 0, 0, 1, 0, 0, 0]
//    int[][] edges2 = {{2, 9}, {3, 3}, {3, 5}, {3, 7}, {4, 8}, {5, 8}, {6, 6}, {7, 4}, {8, 7}, {9, 3}, {9, 6}};
//    System.out.println(mvtg.getMin(edges2, 10));
////    0->1->0, 2->3->2->1
//    int[][] edges3 = {{0, 1}, {1, 0}, {2, 1}, {2, 3}, {3, 2}};
//    System.out.println(mvtg.getMin(edges3, 4));
//  }
//}

package ab;
import java.util.*;

/*
 
 题目：找最少的点能遍历整个图。 比如1->2->3->4,只要返回1这个点。
 
 算法： 从每个点开始遍历，然后两个set一个存所有遍历过的点，一个存结果，遍历过程中要是碰到了结果集中的点那就删除
 
 */

public class Vertical {
	public static Set<Integer> find(Map<Integer, List<Integer>> g) {
		Set<Integer> rs = new HashSet<>();
		Set<Integer> visited = new HashSet<>();
		for (int i : g.keySet()) {
			if (!visited.contains(i)) {
				search(g, rs, visited, i, new HashSet<>());
				rs.add(i);
			}
		}
		return rs;
	}
	
	public static void search(Map<Integer, List<Integer>> g, Set<Integer> rs, Set<Integer> visited, int cur, Set<Integer> curVisited) {
		if (rs.contains(cur)) {
			rs.remove(cur);
		}
		visited.add(cur);
		curVisited.add(cur);
		for (int child : g.get(cur)) {
			if (!curVisited.contains(child)) {
				search(g, rs, visited, child, curVisited);
			}
		}
	}
	
	public static void main(String[] args) {
		Map<Integer, List<Integer>> g = new HashMap<>();
		for (int i = 1; i <= 6; i++) {
			g.put(i, new ArrayList<>());
		}
		/*
		g.get(1).add(2);
		g.get(2).add(3);
		g.get(3).add(4);
		g.get(4).add(1);
		g.get(5).add(1);
		g.get(6).add(2);
		g.get(1).add(6);
		*/
		g.get(1).add(2);
		g.get(2).add(3);
		g.get(4).add(2);
		g.get(4).add(5);
		g.get(1).add(6);
		
		Set<Integer> rs = find(g);
		System.out.println(rs);
	}
}