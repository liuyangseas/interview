/**
 * Connected to a server, there is a number from 1111 to 6666
 * Send your guess to the server, it will return you with how many numbers are correct and on the
 * right position, how many numbers are correct but on the wrong position
 * Guess as less as possible
 *
 * For example
 * correct code: 3264
 * GUESS 1111 => 0 0 (no correct digits)
 * GUESS 1214 => 2 0 (digits 2 and 4 are correct and on correct position)
 * GUESS 6111 => 0 1 (digit 6 is present, but on a different position)
 * GUESS 6211 => 1 1 (digit 2 is not counted towards the second count!)
 *
 * Solution
 * 从1111开始猜，每次改变1位，比如以最高位为例，第一个Iteration猜 2111，3111，4111，5111
 * 如果改一个数字正确的变少了，说明这一位就是1
 * 如果改一个数字正确的变多了，说明这一位就是你现在猜的数字
 * 如果正确的数字一直是一样的，说明这一位是6
 *
 * worst case是6666，最多需要猜
 * 1111
 * 2111，3111，4111，5111
 * 1211，1311，1411，1511
 * 1121，1131，1141，1151
 * 1112，1113，1114，1115
 * 共17次
 *
 * 参考 http://www.1point3acres.com/bbs/thread-290126-1-1.html
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuessNumber {
  private int count = 0;
  private List<Integer> target = new ArrayList<>();

  // Simulation method, to generate or reset the random number, don't have to focus on it
  public void reset() {
    target.clear();
    for (int i = 0; i < 4; ++i) {
      target.add((int)(Math.random() * 6) + 1);
    }
    count = 0;
  }

  // Simulation method, don't have to focus it
  public String sendAndReceive(String str) {
    if (str.toLowerCase().equals("start")) {
      reset();
      return "Ready, target # is " + target.get(0) + target.get(1) + target.get(2) + target.get(3);
    }
    System.out.println("Times of method call: " + ++count + ", coming number: " + str);
    int a = 0;
    List<Integer> copyOfTarget = new ArrayList<>(target);
    List<Integer> t = new ArrayList<>();
    List<Integer> g = new ArrayList<>();

    for (int i = 0; i < 4; ++i) {
      int digit = copyOfTarget.get(i);
      char c = str.charAt(i);

      if (digit == c - '0') {
        ++a;
      } else {
        t.add(digit);
        g.add(c - '0');
      }
    }

    int size = g.size();
    g.removeAll(t);
    int b = size - g.size();

    return a + " " + b;
  }

  public String guess() {
    String base = "1111";
    int firstResp = Integer.parseInt(sendAndReceive(base).split(" ")[0]);
    if (firstResp == 4) {
      return base;
    }

    char[] res = new char[4];
    Arrays.fill(res, '0');
    for (int i = 0; i < 4; i++) {
      int lastResp = firstResp;
      char[] charBase = base.toCharArray();
      for (int j = 2; j < 6; j++) {
        charBase[i] = (char)('0' + j);
        int resp = Integer.parseInt(sendAndReceive(new String(charBase)).split(" ")[0]);
        if (resp == 4) {
          return new String(charBase);
        }
        if (resp != lastResp) {
          res[i] = lastResp > resp ? '1' : (char)('0' + j);
          break;
        }
      }
      if (res[i] == '0') {
        res[i] = '6';
      }
    }

    return new String(res);
  }
}

//class Main {
//  public static void main(String[] args) {
//    GuessNumber gn = new GuessNumber();
//    System.out.println(gn.sendAndReceive("start"));
//    System.out.println("Result: " + gn.guess());
//    System.out.println(gn.sendAndReceive("start"));
//    System.out.println("Result: " + gn.guess());
//    System.out.println(gn.sendAndReceive("start"));
//    System.out.println("Result: " + gn.guess());
//    System.out.println(gn.sendAndReceive("start"));
//    System.out.println("Result: " + gn.guess());
//  }
//}


// Michael code/*
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
