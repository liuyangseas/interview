You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
Example 1:
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:
Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
			 
// 我们可以通过最后一个求的状态转移方程， res[i]表示前i个房子中，偷到的最大价值

public class Solution {
    /**
     * @param A: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    //---方法一---
    public long houseRobber(int[] A) {
        // write your code here
        int n = A.length;
        if(n == 0)
            return 0;
        long []res = new long[n+1];
        
        // 初始化一定要把第0个设置为0
        res[0] = 0;
        res[1] = A[0];
        for(int i = 2; i <= n; i++) {
            res[i] = Math.max(res[i-1], res[i-2] + A[i-1]);
        }
		
        return res[n];
    }
	
    //---方法二---  空间优化，滚动数组   i的这个数组只与i-1 i-2有关   f[i] = max(f[i-1],f[i-2] + A[i]) based on f[i-1] f[i-2]
	// f[i%2] = max(f[i-1)%2  f(i-2)%2]
    public long houseRobber(int[] A) {
        // write your code here
        int n = A.length;
        if(n == 0)
            return 0;
		
		// 只需要两个数组值就好了， 模几就跟你几个状态相关，这里只有两个相关，我就只需要%2
        long []res = new long[2];
        
        res[0] = 0;
        res[1] = A[0];
        for(int i = 2; i <= n; i++) {
            res[i%2] = Math.max(res[(i-1)%2], res[(i-2)%2] + A[i-1]);
        }
		
        return res[n%2];
    }

	//---方法三---
	class Solution {
      public int rob(int[] num) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : num) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
			
			//这里做了一个挪位
            prevMax = temp;
        }
        return currMax;
    }
}

// 这里如果我们的follow up是如何能够把这个路径打印出来呢？
/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing
 * each of them is that adjacent houses have security system connected and it will automatically
 * contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * ALSO, print out the houses you choose to reach the maximum.
 */

import java.util.Arrays;

public class HouseRobber {
  public int rob(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      System.out.println("Houses: 0");
      return nums[0];
    }

    int[] dp = new int[nums.length];
    int[] prev = new int[nums.length];
    Arrays.fill(prev, -1);

    dp[0] = nums[0];
    prev[0] = 0;
    dp[1] = Math.max(nums[0], nums[1]);
    prev[1] = dp[1] == nums[1] ? 1 : -1;


    for (int i = 2; i < nums.length; i++) {
      int robCur = nums[i] + dp[i - 2];
      if (robCur > dp[i - 1]) {
        dp[i] = robCur;
        prev[i] = i - 2;
      } else {
        dp[i] = dp[i - 1];
      }
    }

    int index = nums.length - 1;
    while (prev[index] != index) {
      if (prev[index] == -1) {
        index--;
      } else {
        System.out.print(index + " ");
        index = prev[index];
      }
    }
    System.out.println(index);

    return dp[nums.length - 1];
  }
}

//class Main {
//  public static void main(String[] args) {
//    HouseRobber hr = new HouseRobber();
//    int[] nums1 = {3, 5, 6, 1, 2, 4, 1};
//    System.out.println(hr.rob(nums1));
//    int[] nums2 = {5, 2, 4, 7, 3, 1};
//    System.out.println(hr.rob(nums2));
//  }
//}
