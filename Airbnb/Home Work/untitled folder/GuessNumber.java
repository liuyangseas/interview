package ab;

/*
猜 1 - 6
可以猜范围外的数就猜 1999 2999 3999 4999 5999 确定第一位 然后 9199 9299。。。确定其他位数，最多猜20次
不能猜范围外的就 1111 2222 3333 4444 5555 6666 确定4个数然后做permutation来猜，最多 5 + 4*3*2 = 29次
*/
public class GuessNumber {
	static String secret = "1266"; 
	public static String getHint(String guess) {
	    int bulls = 0;
	    int cows = 0;
	    int[] numbers = new int[10];
	    for (int i = 0; i<secret.length(); i++) {
	        if (secret.charAt(i) == guess.charAt(i)) bulls++;
	        else {
	            if (numbers[secret.charAt(i)-'0']++ < 0) cows++;
	            if (numbers[guess.charAt(i)-'0']-- > 0) cows++;
	        }
	    }
	    return bulls + "A" + cows + "B";
	}
	static String candidate = "";
	// 猜1-6可以猜范围外的
	public static int guess() {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			boolean guessed = false;
			for (int j = 1; j <= 5; j++) {
				StringBuilder sb = new StringBuilder("999");
				sb.insert(i, j);
				String rs = getHint(sb.toString());
				count++;
				if (rs.charAt(0) == '1') {
					candidate += j;
					guessed = true;
					break;
				}
			}
			if (!guessed) {
				candidate += 6;
			}
			
		}
		return count;
	}
	
	//猜1-6 不可以猜范围外的
	
	public static int guess2() {
		return 1;
	}
	/*
1111是一次，这个返回结果需要存下来。
最差的结果是Target是6666.
1111 -> "0 0"
2111 -> "0 0"
3111 -> "0 0". 
4111 -> "0 0". 
5111 -> "0 0"
这五次能确定第一位是6

1211 -> "0 0"
1311 -> "0 0"
1411 -> "0 0"
1511 -> "0 0"
这四次能确定第二位是6
1121 -> "0 0"
1131 -> "0 0"
1141 -> "0 0"
1151 -> "0 0"
这四次能确定第三位是6

1112 -> "0 0"
1113 -> "0 0"
1114 -> "0 0"
1115 -> "0 0"
这四次能确定第四位是6

worst case 17次
	 */
	public static int guess3() {
        String base = "1111";
        int count = 1;
        int first = getHint(base).charAt(0) - '0';
        if (4 == first) {
            candidate = base;
        	return count;
        }
        char[] res = new char[4];

        for (int i = 0; i < 4; ++i) {
            int lastResponse = first;
            char[] chBase = base.toCharArray();
            for (int j = 2; j < 6; ++j) {
                chBase[i] = (char)('0' + j);
                int response = getHint(String.valueOf(chBase)).charAt(0) - '0';
                count++;
                if (4 == response) {
                	candidate = String.valueOf(chBase);
                	return count;
                }
                if (response != lastResponse) {
                    res[i] = lastResponse > response ? '1' : (char)('0' + j);
                    break;
                }
                lastResponse = response;
            }
            if (0 == res[i]) res[i] = '6';
        }
        candidate = String.valueOf(res);
        return count;
    }
	
	public static void main(String[] args) {
		System.out.println(guess3());
		System.out.println(candidate);
		
	}
}
