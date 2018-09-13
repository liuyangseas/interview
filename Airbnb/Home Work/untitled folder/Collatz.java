package ab;
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
