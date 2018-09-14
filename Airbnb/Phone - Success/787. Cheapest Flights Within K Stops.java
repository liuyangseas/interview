There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and fights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
Note:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.



####  Dijkstra's algorithm uses a priority queue to continually search the next node with the lowest cost.
/*
	Dijkstra算法是BFS的升级版。
	当一个图中的每条边都加上权值后，BFS就没办法求一个点到另一个点的最短路径了。
	这时候，需要用到Dijkstra算法。从最基本原理上讲，把BFS改成Dijkstra算法，只需要把“队列”改成“优先队列”就可以了。
	If we've come to a node and it has a lower recorded cost or we've taken too many steps, we don't need to search it. 
	If we reach our destination, because we are searching in order of lowest cost first, it must have the lowest cost.
	Otherwise, for every outbound flight from node that is better, we'll add it to our priority queue of things to search.
*/

/**
 * 给一些edge和cost，指定起始点和终点，找最多stop k次的最便宜的价格
 * A->B,100,
 * B->C,100,
 * A->C,500.
 * 如果k是1的话，起点终点是A，C的话，那A->B->C的cost最小是200
 *
 * 本质是BFS一层层往外搜，可以把从起点到当前node的最小值存在这个node中，可以用来加速剪枝
 * follow up可以是输出路线，需要保存parent信息
 */

package Airbnb;
import java.util.*;

public class Flight {

	public static void main(String[] args) {
		// 普通输入
		int[][] flight = {{0,1,100},{1,2,100},{0,2,500}};
		System.out.println(findCheapestPrice(3,flight,0,2,0));
		System.out.println(BFS(3,flight,0,2,0));
		System.out.println(DFS(3,flight,0,2,0));
		
		// 当我们的输入不规则的时候，而且需要打印路径的时候
	    MinCostKStops mcks = new MinCostKStops();
	    List<String> lines = new ArrayList<>();
	    lines.add("A->B,100");
	    lines.add("A->C,400");
	    lines.add("B->C,100");
	    lines.add("C->D,100");
	    lines.add("C->A,10");
	    System.out.println(mcks.minCost(lines, "A", "D", 0));
	    System.out.println(mcks.minCost(lines, "A", "D", 1));
	    System.out.println(mcks.minCost(lines, "A", "D", 2));
	}
	
	// 最优解
	public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		 // 还是用我们的HashMap来表示图，这里我们的每一个起点作为key，value有一个list，比如我们的Map，我们遍历所有的keySet就是遍历所有可能的终点
            Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
         
            for (int[] f : flights) {
                if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
                prices.get(f[0]).put(f[1], f[2]);
            }
         
            Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
            pq.add(new int[] {0, src, k + 1});
            while (!pq.isEmpty()) {
                int[] top = pq.remove();
                int price = top[0];
                int city = top[1];
                int stops = top[2];
                if (city == dst) return price;
                if (stops > 0) {
                    Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                    for (int a : adj.keySet()) {
                        pq.add(new int[] {price + adj.get(a), a, stops - 1});
                    }
                }
            }
            
         return -1;
	   }
	
	// BFS
	 public static int BFS(int n, int[][] flights, int src, int dst, int K) {
		   Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
	        for (int[] flight : flights) {
	            graph.putIfAbsent(flight[0], new HashMap<>());
	            graph.get(flight[0]).put(flight[1], flight[2]);
	        }
	        
	        int stop = -1;
	        Queue<int[]> q = new LinkedList<>();
	        q.offer(new int[] {src, 0});
	        int cost = -1;
	        while (q.size() > 0 && stop <= K) {
	            int size = q.size();
	            for (int i = 0; i < size; i++) {
	                int[] cur = q.poll();
	                if (cur[0] == dst) {
	                    cost = cost == -1 ? cur[1] : Math.min(cost, cur[1]);
	                }
	                Map<Integer,Integer> adj = graph.getOrDefault(cur[0], new HashMap<>());
	                for (int a : adj.keySet()) {
	                    q.offer(new int[] {a, cur[1] + adj.get(a)});
	                }
	            }
	            stop++;
	        }
	        
	        return cost;
	}
	
	// DFS
	public static int min = Integer.MAX_VALUE;
    public static int DFS(int n, int[][] flights, int src, int dst, int K) {
    	Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.putIfAbsent(flight[0], new HashMap<>());
            graph.get(flight[0]).put(flight[1], flight[2]);
        }
        
        Set<Integer> visited = new HashSet<>();
        search(graph, src, dst, K, -1, 0, visited);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
	    
    public static void search(Map<Integer, Map<Integer, Integer>> graph, int src, int dst, int K, int step, int cost, Set<Integer> visited){
        if (src == dst && step <= K) {
            min = Math.min(min, cost);
            return;
        }
        if (step > K) {
            return;
        }

        Map<Integer,Integer> adj = graph.getOrDefault(src, new HashMap<>());
        for (int a : adj.keySet()) {
            int station = a;
            int price = adj.get(a);
            visited.add(src);
            if (!visited.contains(station)) {
                search(graph, station, dst, K, step + 1, cost + price, visited);
            }
            visited.remove(src);
        }
    }

}

// 如果需要打印路径，或者输入不是规则的二维数列
class MinCostKStops {
	  public int minCost(List<String> lines, String source, String target, int k) {
	    if (lines.size() == 0 || k < 0) {
	      return 0;
	    }

	    Map<String, Node> nodes = new HashMap<>();
	    for (String line : lines) {
	      String[] parts = line.split(",");
	      String[] twoNodes = parts[0].split("->");
	      if (!nodes.containsKey(twoNodes[0])) {
	        nodes.put(twoNodes[0], new Node(twoNodes[0]));
	      }
	      if (!nodes.containsKey(twoNodes[1])) {
	        nodes.put(twoNodes[1], new Node(twoNodes[1]));
	      }
	      nodes.get(twoNodes[0]).nextNodes.put(twoNodes[1], Integer.parseInt(parts[1]));
	    }

	    // Parent map for path
	    Map<String, String> parent = new HashMap<>();

	    boolean find = bfs(nodes, source, target, k, parent);

	    List<String> path = new ArrayList<>();
	    // Output path
	    if (find) {
	      String cur = target;
	      while (!cur.equals(source)) {
	        path.add(cur);
	        cur = parent.get(cur);
	      }
	      path.add(source);
	      Collections.reverse(path);
	      System.out.println(path);
	    } else {
	      System.out.println("");
	    }

	    return find ? nodes.get(target).minCost : -1;
	  }

	  private boolean bfs(Map<String, Node> nodes, String source, String target, int k, Map<String, String> parent) {
	    boolean find = false;

	    Queue<String> nodeQ = new LinkedList<>();
	    Queue<Integer> costQ = new LinkedList<>();
	    nodeQ.offer(source);
	    costQ.offer(0);

	    int stop = -1;
	    while (!nodeQ.isEmpty()) {
	      stop++;
	      if (stop > k + 1) {
	        break;
	      }
	      int size = nodeQ.size();
	      for (int i = 0; i < size; i++) {
	        Node cur = nodes.get(nodeQ.poll());
	        int curCost = costQ.poll();
	        cur.minCost = Math.min(cur.minCost, curCost);

	        if (cur.name.equals(target)) {
	          find = true;
	          continue;
	        }

	        for (String next : cur.nextNodes.keySet()) {
	          int nextCost = curCost + cur.nextNodes.get(next);
	          if (nextCost < nodes.get(next).minCost && (stop < k || stop == k && next.equals(target))) {
	            // Update path
	            parent.put(next, cur.name);
	            nodeQ.offer(next);
	            costQ.offer(nextCost);
	          }
	        }
	      }
	    }

	    return find;
	  }
	}

class Node {
  String name;
  int minCost;
  Map<String, Integer> nextNodes;
  Node(String name) {
    this.name = name;
    this.minCost = Integer.MAX_VALUE;
    this.nextNodes = new HashMap<>();
  }
}