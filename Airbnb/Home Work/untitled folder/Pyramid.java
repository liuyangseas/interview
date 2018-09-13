package ab;
import java.util.*;

public class Pyramid {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : allowed) {
            String key = "" + (char) s.charAt(0) + (char) s.charAt(1);
            String value = "" + s.charAt(2);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(value);
        }
        Map<String, Boolean> visited = new HashMap<>();
        return search(bottom, map, visited);
    }
    
    public boolean search(String s, Map<String, List<String>> map, Map<String, Boolean> visited) {
        if (s.length() == 1) {
            return true;
        }
        List<String> next = new ArrayList<>();
        if (!getNext(next, s, map, new StringBuilder(), 0)) {
            return false;
        }
        for (String child : next) {
            if (visited.containsKey(child)) {
                return visited.get(child);
            }
            boolean rs = search(child, map, visited);
            visited.put(child, rs);
            if (rs) {
                return true;
            }
        }
        return false;
    }
    
    public boolean getNext(List<String> next, String s, Map<String, List<String>> map, StringBuilder sb, int pos) {
        if (sb.length() == s.length() - 1) {
            next.add(sb.toString());
            return true;
        }
        String key = "" + (char) s.charAt(pos) + (char) s.charAt(pos + 1);
        if (!map.containsKey(key)) {
            return false;
        }
        for (String temp : map.get(key)) {
            sb.append(temp);
            getNext(next, s, map, sb, pos + 1);
            sb.deleteCharAt(sb.length() - 1); 
        }
        return true;
    }
}
