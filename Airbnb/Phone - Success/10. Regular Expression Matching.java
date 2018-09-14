Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false

/*
boolean[][] dp 表示s从0到i和p从0到j是否匹配 那么dp[0][0] = true
Here are some conditions to figure out, then the logic can be very straightforward.
1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];  等于任意字符，也不需要看
3, If p.charAt(j) == '*': 
   here are two sub conditions:
               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty  也就是我们把前面的消掉了，所以两个都不要，同归于尽  aab vs c*aab
               2   if p.charAt(j-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a    aaa vs a*
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a      aa vs a* 
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty 就像我们的1的情况
*/
Here is the solution
public boolean isMatch(String s, String p) {
    if (s == null || p == null) {
        return false;
    }
	
	// 初始化00一定要设为true
    boolean[][] dp = new boolean[s.length()+1][p.length()+1];
    dp[0][0] = true;
	
	//c* 这种情况，我们可以设为初始值，后面不能包括
    for (int i = 0; i < p.length(); i++) {
        if (p.charAt(i) == '*' && dp[0][i-1]) {
            dp[0][i+1] = true;
        }
    }
	
    for (int i = 0 ; i < s.length(); i++) {
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '.') {
                dp[i+1][j+1] = dp[i][j];
            }
            if (p.charAt(j) == s.charAt(i)) {
                dp[i+1][j+1] = dp[i][j];
            }
            if (p.charAt(j) == '*') {
                if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                    dp[i+1][j+1] = dp[i+1][j-1];
                } else {
                    dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                }
            }
        }
    }
	
    return dp[s.length()][p.length()];
}

//Bottom-up solution
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[s.length()][p.length()]=true;
        for (int i=s.length();i>=0;i--) {
            for (int j=p.length()-1;j>=0;j--) {
                boolean firstMatch = (i<s.length() && ( s.charAt(i)==p.charAt(j) || p.charAt(j)=='.'));
                if (j+1<p.length() && p.charAt(j+1)=='*'){   // conditon 3
                    dp[i][j] = dp[i][j+2] || firstMatch && dp[i+1][j];
                }
                else {
                    dp[i][j] = firstMatch && dp[i+1][j+1];   //condition 1 & 2
                }
            }
        }
        return dp[0][0];
    }
}


/*
这里我们升级了，我们多了一个加号
Implement a simple regex parser which, given a string and a pattern, returns a boolean indicating
whether the input matches the pattern. By simple, we mean that the regex can only contain special
character: * (star), . (dot), + (plus). The star means what you'd expect, that there will be zero or more of
previous character in that place in the pattern. The dot means any character for that position. The plus

means one or more of previous character in that place in the pattern.
*/

public class Regular {
    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < p.length(); i += 2) {
            if (p.charAt(i) == '*') {
                dp[0][i + 1] = true;
            } else {
                break;
            }
        }
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else if (p.charAt(j - 1) == '+') {
                	if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                		dp[i][j] = dp[i - 1][j] || dp[i - 1][j - 2];
                	} 
                	
                } else {
                    if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
    
    public static void main(String[] args) {
    	String s = "a";
    	String p = "aa+";
    	System.out.println(isMatch(s, p));
    }
}
