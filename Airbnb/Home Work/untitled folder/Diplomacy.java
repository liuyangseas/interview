package ab;
import java.util.*;

public class Diplomacy {
	public static List<String> diplomacy(String[] moves) {
		// army and strength
		Map<String, Integer> army = new HashMap<>();
		// location -> army
		Map<String, List<String>> loc = new HashMap<>();
		// army -> location
		Map<String, String> map= new HashMap<>();
		
		//put army and location, assign army to location
		for (String move : moves) {
			String[] info = move.split(" ");
			String arm = info[0];
			String cur = info[1];
			String action = info[2];
			String target = info[3];
			if (action.equals("move")) {
				army.putIfAbsent(arm, 0);
				map.put(arm, target);
				loc.putIfAbsent(cur, new ArrayList<>());
				loc.putIfAbsent(target, new ArrayList<>());
				loc.get(target).add(arm);
			} else if (action.equals("hold")) {
				army.putIfAbsent(arm, 0);
				map.put(arm, target);
				loc.putIfAbsent(cur, new ArrayList<>());
				loc.get(cur).add(arm);
			} else {
				army.putIfAbsent(arm, 0);
				loc.putIfAbsent(cur, new ArrayList<>());
				army.putIfAbsent(target, 0);
				map.put(arm, cur);
				loc.get(cur).add(arm);
			}
		}
		
		// calculate strength
		for (String move : moves) {
			String[] info = move.split(" ");
			String arm = info[0];
			String cur = info[1];
			String action = info[2];
			String target = info[3];
			if (action.equals("support")) {
				//System.out.println(arm);
				String city = map.get(arm);
				if (loc.get(city).size() == 1) {
					army.put(target, army.get(target) + 1);
				}
			}
		}
		List<String> rs = new ArrayList<>();
		// calculate result
		for (String lo : loc.keySet()) {
			List<String> armys = loc.get(lo);
			System.out.println(armys);
			if (armys.size() <= 1) {
				for (String ar : armys) {
					String temp = ar + " " + lo;
					rs.add(temp);
				}
			} else {
				Collections.sort(armys, (o1, o2) -> (army.get(o2) - army.get(o1)));
				for (int i = 0; i < armys.size(); i++) {
					if (i == 0 && army.get(armys.get(0)) > army.get(armys.get(1))) {
						String temp = armys.get(0) + " " + lo;
						rs.add(temp);
						continue;
					}
					String temp = armys.get(i) + " Dead";
					rs.add(temp);
				}
			}
		}
		return rs;
	}
	
	public static void main(String[] args) {
		String[] moves = {
				"A Paris move London",
				"B York support A",
				"C Seattle move London",
				"D Redmon move London",
				"E Sanfrancisco support C",
				"F Sanfrancisco hold Sanfrancisco"
		};
		System.out.println(diplomacy(moves));
	}
}
