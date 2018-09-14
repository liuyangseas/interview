package Airbnb;

import java.util.*;
//follow up print path

/**
 * There are 10 wizards, 0-9.
 * you are given a list that each entry is a list of wizards known by wizard.
 * Define the cost between wizard[i] and wizard[j] as square of different of i and j
 * i.e. Cost(i, j) = (i - j) ^ 2
 *
 * Find the min cost between 0 and 9. Out put the min cost path.
 *
 * Input
 * List<List<Integer>>, 最外层的List的size保证为10， 一次对应编号为0，1,...,9的人认识那些人
 * Output
 * List<Integer> min cost path
 *
 * This is exactly the same problem as MinCostKStops (Flight Tickets) without k stops constrains.
 *
 * We can also use Dijkstra's Algorithm as well
 */

public class Wizard {
	// dijkstra 
	public static int findCost(Map<Integer, List<Integer>> graph) {
		int[] dist = new int[10];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		boolean[] decided = new boolean[10];
		decided[0] = true;
		for (int i : graph.get(0)) {
			dist[i] = i * i;
		}
		for (int i = 0; i < 10; i++) {
			int vertical = -1;
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < 10; j++) {
				if (dist[j] < min && !decided[j]) {
					min = dist[j];
					vertical = j;
				}
			}
			
			if (vertical != -1) {
				decided[vertical] = true;
				for (int node : graph.get(vertical)) {
					int value = Math.abs(node - vertical) * Math.abs(node - vertical);
					if (value + dist[vertical] < dist[node]) {
						dist[node] = value + dist[vertical];
					}
				}
			}
			/*
			for (int k : dist) {
				System.out.print(k + "   ");
			}
			System.out.println();
			*/
		}

		return dist[9];
	}
	
	// bfs
	public static int bfs(Map<Integer, List<Integer>> graph) {
		int min = Integer.MAX_VALUE;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0});
		Set<Integer> visited = new HashSet<>();
		while (q.size() > 0) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				visited.add(cur[0]);
				if (cur[0] == 9) {
					min = Math.min(min, cur[1]);
				}
				for (int child : graph.get(cur[0])) {
					if (visited.contains(child)) {
						continue;
					}
					q.offer(new int[] { child, cur[1] + Math.abs(child - cur[0]) * Math.abs(child - cur[0])});
				}
			}
		}
		return min;
	}
	
	//dfs with print path
	static int min = Integer.MAX_VALUE;
	static String p;
	public static int dfs(int source, int target, Map<Integer, List<Integer>> graph) {
		search(source, target, 0, new HashSet<>(), new StringBuilder(), graph);
		return min;
	}
	
	public static void search(int source, int target, int cost, Set<Integer> visited, StringBuilder path, Map<Integer, List<Integer>> graph) {
		visited.add(source);
		path.append(source);
		if (source == target) {
			System.out.println(cost);
			if (cost < min) {
				min = cost;
				p = path.toString();
			}
			return;
		}
		for (int i : graph.get(source)) {
			if (!visited.contains(i)) {
				int temp = Math.abs(i - source) * Math.abs(i - source);
				search(i, target, cost + temp, visited, path, graph );
			}
		}
		visited.remove(source);
		path.deleteCharAt(path.length() - 1);
	}
	
	public static void main(String[] args) {
		// 第一种input， 朱翔的代码
		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (int i = 0; i <= 9; i++) {
			graph.put(i, new ArrayList<>());
		}
		graph.get(0).add(1);
		graph.get(0).add(5);
		graph.get(0).add(9);
		graph.get(1).add(2);
		graph.get(1).add(3);
		graph.get(1).add(9);
		graph.get(2).add(4);
		graph.get(5).add(9);
		// System.out.println(findCost(graph));
		// System.out.println(bfs(graph));
		//System.out.println(dfs(0,9,graph));
		// System.out.println(p);
		
		
		// 第二种input，也就是网上的代码
		WizardShortestDistances wsd = new WizardShortestDistances();
		int[][] ids = { {1,5,9},{2,3,9},{4},{},{},{9},{},{},{},{} };
	    List<List<Integer>> wizards = new ArrayList<>();
		    for (int i = 0; i < ids.length; i++) {
		      List<Integer> wizard = new ArrayList<>();
		      for (int j = 0; j < ids[i].length; j++) {
		        wizard.add(ids[i][j]);
		      }
		      wizards.add(wizard);
		    }
		    wsd.getShortestPath(wizards, 0, 9);
	    System.out.println(wsd.getShortestPath(wizards, 0, 9));
	}
}

// 我们这里的方法，只能够找到这个最小的cost是多少，但是找到最短的这条path，并且全部打印出来需要另外的思路
class WizardShortestDistances {
	  public int getShortestPath(List<List<Integer>> wizards, int source, int target) {
		  List<Integer> res = new ArrayList<>();
		  HashMap<Integer, HashMap<Integer,Integer>> map = new HashMap<Integer, HashMap<Integer,Integer>>();
		  for(int i = 0; i < wizards.size(); i++) {
			  for(int j = 0; j < wizards.get(i).size();j++) {
				  HashMap<Integer,Integer> temp = map.get(i);
				  if(temp == null) {
					  map.put(i, new HashMap<Integer,Integer>());
				  }

				  int weight = (int)Math.pow(Math.abs(i - wizards.get(i).get(j)), 2);
				  map.get(i).put(wizards.get(i).get(j),weight);
			  }
		  }
		  
		Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
        pq.add(new int[] {0, source});
		int num = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            int[] top = pq.remove();
            int price = top[0];
            int city = top[1];
            res.add(city);
            //System.out.println(city);

            if (city == target) {
            	num = Math.min(num, price);
            }
          	 // return res;
            
            Map<Integer, Integer> adj = map.getOrDefault(city, new HashMap<>());
            for (int next : adj.keySet()) {
                pq.add(new int[] {price + adj.get(next), next});
            }
         }
		  
		  return num;
	  }
}

//
class WizardShortestDistanceOnline {
	  public List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target) {
	    int[] parent = new int[wizards.size()];
	    Map<Integer, Wizzard> map = new HashMap<>();
	    for (int i = 0; i < wizards.size(); i++) {
	      parent[i] = i;
	      Wizzard cur = new Wizzard(i);
	      for (int j = 0; j < wizards.get(i).size(); j++) {
	        int cost = (int)Math.pow(i - wizards.get(i).get(j), 2);
	        cur.costs.put(wizards.get(i).get(j), cost);
	      }
	      map.put(i, cur);
	    }

	    bfs(map, source, target, parent);

	    // Output path
	    List<Integer> path = new ArrayList<>();
	    int cur = target;
	    while (cur != source) {
	      path.add(cur);
	      cur = parent[cur];
	    }
	    path.add(source);
	    Collections.reverse(path);

	    return path;
	  }

	  private void bfs(Map<Integer, Wizzard> map, int source, int target, int[] parent) {
	    Queue<Integer> idQ = new LinkedList<>();
	    Queue<Integer> costQ = new LinkedList<>();
	    idQ.offer(source);
	    costQ.offer(0);

	    while (!idQ.isEmpty()) {
	      int size = idQ.size();
	      for (int i = 0; i < size; i++) {
	        int cur = idQ.poll();
	        int curCost = costQ.poll();

	        Wizzard curWizard = map.get(cur);
	        curWizard.dist = Math.min(curWizard.dist, curCost);
	        if (cur == target) {
	          continue;
	        }
	        for (int next : curWizard.costs.keySet()) {
	          int nextCost = curCost + curWizard.costs.get(next);
	          if (nextCost < map.get(next).dist) {
	            parent[next] = cur;
	            idQ.offer(next);
	            costQ.offer(nextCost);
	          }
	        }
	      }
	    }
	  }
	//  // For Dijkstra
	//  public List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target) {
//	    List<Integer> path = new ArrayList<>();
	//
//	    int[] parent = new int[wizards.size()];
//	    Map<Integer, Wizard> map = new HashMap<>();
//	    for (int i = 0; i < wizards.size(); i++) {
//	      parent[i] = i;
//	      map.put(i, new Wizard(i));
//	    }
	//
//	    map.get(source).dist = 0;
//	    Queue<Wizard> heap = new PriorityQueue<>();
//	    heap.offer(map.get(source));
	//
//	    while (!heap.isEmpty()) {
//	      Wizard cur = heap.poll();
//	      List<Integer> neighbors = wizards.get(cur.id);
//	      for (int neighbor : neighbors) {
//	        Wizard next = map.get(neighbor);
//	        int weight = (int)Math.pow(cur.id - next.id, 2);
//	        if (cur.dist + weight < next.dist) {
//	          heap.remove(next);
//	          parent[next.id] = cur.id;
//	          next.dist = cur.dist + weight;
//	          heap.offer(next);
//	        }
//	      }
//	    }
	//
//	    int index = target;
//	    while (index != source) {
//	      path.add(index);
//	      index = parent[index];
//	    }
//	    path.add(source);
//	    Collections.reverse(path);
//	    return path;
	//  }
	}

	class Wizzard {
	  int id;
	  int dist;
	  Map<Integer, Integer> costs;
	  Wizzard(int id) {
	    this.id = id;
	    // the dist to source, then we can decide which leg to keep
	    this.dist = Integer.MAX_VALUE;
	    this.costs = new HashMap<>();
	  }
	}

	//// For Dijkstra
	//class Wizard implements Comparable<Wizard> {
	//  int id;
	//  int dist;
	//  Wizard(int id) {
//	    this.id = id;
//	    this.dist = Integer.MAX_VALUE;
	//  }
	//
	//  @Override
	//  public int compareTo(Wizard that) {
//	    return this.dist - that.dist;
	//  }
	//}
