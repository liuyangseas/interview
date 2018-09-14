/**
 * https://en.wikipedia.org/wiki/Collatz_conjecture
 * 考拉兹猜想
 * 给你公式，比如偶数的话除2，奇数的话就变成3*n+1，对于任何一个正数，数学猜想是最终他会变成1。
 * 每变一步步数加1，给一个上限，让找出范围内最长步数
 *
 * 记忆化搜索
 *
 * 这题如果follow up还可以考虑输出最长的序列，那么我们就需要一个map来保存 integer -> list(integer)
 */

import java.util.HashMap;
import java.util.Map;

public class CollatzConjecture {
  public int findLongestSequence(int n) {
    Map<Integer, Integer> cache = new HashMap<>();
    cache.put(1, 1);
    int longest = 0;
    for (int i = 1; i <= n; i++) {
      longest = Math.max(longest, helper(i, 0, cache));
    }

    return longest;
  }

  private int helper(int n, int curLen, Map<Integer, Integer> cache) {
    if (cache.containsKey(n)) {
      return curLen + cache.get(n);
    }

    int len = n % 2 == 0 ? helper(n / 2, curLen + 1, cache) : helper(n * 3 + 1, curLen + 1, cache);
    cache.put(n, len - curLen);
    return len;
  }
}

//class Main {
//  public static void main(String[] args) {
//    CollatzConjecture cc = new CollatzConjecture();
//
//    // 1
//    // 2 -> 1
//    // 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
//    // 4 -> 2 -> 1
//    // 5 -> 16 -> 8 -> 4 -> 2 -> 1
//    // 6 -> 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
//    // 7 -> 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
//    // ...
//
//    for (int i = 1; i <= 7; i++) {
//      System.out.println(cc.findLongestSequence(i));
//    }
//    System.out.println(cc.findLongestSequence(1000));
//  }
//}

// Michael code
package Airbnb;

import java.util.*;
public class Collatz {
	public static int collatz(long n) {
		Map<Long, Integer> map = new HashMap();
		//Map<Long, Integer> map2 = new HashMap();
		int max = 0;
		int max2 = 0;
		for (long i = 1; i <= n; i++) {
			int ans = helper(i, map);
			//int ans2 = helper2(i, map2);
			max = Math.max(max, ans);
			//max2 = Math.max(max2, ans2);
			map.put(i, ans);
		}
		//System.out.println(max + "   " + max2);
		return max;
	}
	
	public static int helper(long n, Map<Long, Integer> map) {
		if (n == 1) {
			return 1;
		}
		if (map.containsKey(n)) {
			return map.get(n);
		}
		if (n % 2 == 0) {
			return 1 + helper(n / 2, map);
		} else {
			return 1 + helper(3 * n + 1, map);
		}
		
	}
	
	public static int helper2(long n, Map<Long, Integer> map) {
		int step = 1;
		while (n > 1) {
			if (map.containsKey(n)) {
				return step + map.get(n);
			}
			if (n % 2 == 0) {
				n = n / 2;
			} else {
				n = 3 * n + 1;
			}
			step++;
		}
		map.put(n, step);
		return step;
	}
	
	public static void main(String[] args) {
		int n = 1000000;
		System.out.println(collatz(n));
	}
	
}
