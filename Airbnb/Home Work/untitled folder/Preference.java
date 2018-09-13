package ab;
import java.util.*;

// follow up : break tie with 1st person's preference list

public class Preference {
	public static List<Integer> canTie(List<List<Integer>> prefer) {
		Map<Integer, Integer> inDegree = new HashMap<>();
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		
		for (List<Integer> l : prefer) {
			for (int i = 0; i < l.size(); i++) {
				graph.putIfAbsent(l.get(i), new HashSet<>());
				inDegree.putIfAbsent(l.get(i), 0);
			}
			for (int i = 1; i < l.size(); i++) {
				inDegree.put(l.get(i), inDegree.get(l.get(i)) + 1);
				graph.get(l.get(i - 1)).add(l.get(i));
			}
		}
		
		Queue<Integer> q = new LinkedList<>();
		for (int key : inDegree.keySet()) {
			if (inDegree.get(key) == 0) {
				q.offer(key);
			}
		}
		List<Integer> rs = new ArrayList<>();
		while (q.size() > 0) {
			int cur = q.poll();
			rs.add(cur);
			for (int children : graph.get(cur)) {
				inDegree.put(children, inDegree.get(children) - 1);
				if (inDegree.get(children) == 0) {
					q.offer(children);
				}
			}
		}
		return rs.size() == inDegree.size() ? rs : new ArrayList<>();
	}
	
	
	
	
	public static void main(String[] args) {
		List<List<Integer>> prefer = new ArrayList<>();
		for (int i = 1; i < 4; i++) {
			prefer.add(new ArrayList<>());
		}
		prefer.get(2).add(3);
		prefer.get(2).add(5);
		prefer.get(2).add(7);
		prefer.get(2).add(9);
		
		
		prefer.get(1).add(2);
		prefer.get(1).add(3);
		prefer.get(1).add(8);
		
		prefer.get(0).add(5);
		prefer.get(0).add(8);
		
		System.out.println(canTie(prefer));
	}
}
