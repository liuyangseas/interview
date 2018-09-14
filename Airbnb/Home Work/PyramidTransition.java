/**
 * 给你一个字符对的转换matrix，表示这个字符对会转化成一个字符，但是有的字符对可能有多个能够转化成的字符。
 * 再给你一个rule，代表若干合法的结果
 * 多次询问，每次一个字符串如果有一个方法能够走到合法状态就算是YES，否则NO
 * http://www.1point3acres.com/bbs/thread-146537-1-1.html
 *
 *   A   B   C  D
 * A AC  CD  D  B
 * B B   C   A  CD
 * C A   C   D  B
 * D BC  D   A  C
 *
 * Assum input to be:
 * A,A,AC
 * A,B,CD
 * A,C,D
 * ...
 *
 * 多次call check
 */

import java.util.*;

public class PyramidTransition {
  private Set<Character> ruleSet;
  private Map<Character, Map<Character, Set<Character>>> transitionDict;
  private Map<String, Boolean> cache;

  public PyramidTransition(String[] lines, String rule) {
    ruleSet = new HashSet<>();
    for (int i = 0; i < rule.length(); i++) {
      ruleSet.add(rule.charAt(i));
    }

    transitionDict = new HashMap<>();
    for (String line : lines) {
      String[] parts = line.split(",");
      char left = parts[0].charAt(0);
      char right = parts[1].charAt(0);

      if (!transitionDict.containsKey(left)) {
        transitionDict.put(left, new HashMap<>());
      }
      transitionDict.get(left).put(right, new HashSet<>());

      for (int i = 0; i < parts[2].length(); i++) {
        transitionDict.get(left).get(right).add(parts[2].charAt(i));
      }
    }

    cache = new HashMap<>();
  }

  public boolean check(String input) {
    if (cache.containsKey(input)) {
      return cache.get(input);
    }

    if (input.length() == 1) {
      cache.put(input, ruleSet.contains(input.charAt(0)));
      return cache.get(input);
    }

    List<String> nextLevel = new ArrayList<>();
    getNextLevel(nextLevel, input, 0, new StringBuilder());

    for (String nextInput : nextLevel) {
      if (check(nextInput)) {
        cache.put(input, true);
        return true;
      }
    }

    cache.put(input, false);
    return false;
  }

  private void getNextLevel(List<String> res, String input, int start, StringBuilder sb) {
    if (start == input.length() - 1) {
      res.add(sb.toString());
      return;
    }

    char left = input.charAt(start);
    char right = input.charAt(start + 1);
    for (char c : transitionDict.get(left).get(right)) {
      sb.append(c);
      getNextLevel(res, input, start + 1, sb);
      sb.setLength(sb.length() - 1);
    }
  }
}

//class Main {
//  public static void main(String[] args) {
//    String[] lines = {
//            "A,A,AC", "A,B,CD", "A,C,D", "A,D,B",
//            "B,A,B", "B,B,C", "B,C,A", "B,D,CD",
//            "C,A,A", "C,B,C", "C,C,D", "C,D,B",
//            "D,A,BC", "D,B,D", "D,C,A", "D,D,C"
//    };
//    PyramidTransition pt = new PyramidTransition(lines, "CD");
//    System.out.println(pt.check("ABCD"));
//    System.out.println(pt.check("AACC"));
//    System.out.println(pt.check("AAAA"));
//  }
//}

// Mihcael code
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
