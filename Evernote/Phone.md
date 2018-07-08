Hi

How do you know about this position? 
How do you know about this company and product?
Competitors:   MS: One Note    Google: Google doc
Improvement?   Alignment  format, only 3 devices

       Behavior:
Most challenge projects
Most difficult
Most pround project

    What is static?
    What is interface?
    What’s the difference between abstract class and interface?

https://career.guru99.com/top-50-oops-interview-questions/




LRU Cache  leetocde search LRU Cache  
Combination Sum


Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.
Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7,
A solution set is:
[
 [7],
 [2, 2, 3]
]

Ask questions



Public class {


Public static List<List<Integer>> rst = new ArrayList<>();
Public static List<Integer> lst = new ArrayList<>();
    
Public static List<List<Integer>> combinationSum(int[] input, int target) {
    if(input.length == 0) {
        Return null;
}
Int sum = 0;
for(int i = 0; i < input.length; i++) {
    Sum += input[0];
    if(sum < target) {
        helper(input, i, target, lst, rst);
}else if(sum > target) {
helper(input, i + 1, target, lst, rst);
}

    if(sum == target) {
        lst.add(sum);
        rst.add(lst);
        lst.remove(lst.get());
}
}
}

Public static void helper(int[] input, int start, int target, List<Integer> lst, List<List<Integer>> rst) {

}

}








-------------------------------------------------------------------------------------------------------------------------
import java.util.*;

public class Solution {
    
    public static ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
   public static ArrayList<Integer> list = new ArrayList<Integer>();
    
    
   public static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
       
       
       if(candidates.length == 0) {
           return null;
       }
       Arrays.sort(candidates);
       helper(candidates, target, 0, 0, ret, list);
       return ret;
   }
   
   public static void helper(int[] input, int target, int start, int sum, ArrayList<ArrayList<Integer>> ret,ArrayList<Integer> list) {
       if(sum > target) {
           return;
       }
       
       for(int i = start; i < input.length; i++) {
           list.add(input[i]);
           sum += input[i];
           if(sum == target) {
               ret.add(new ArrayList<Integer>(list));
               sum -= list.get(list.size() - 1);
               list.remove(list.size() - 1);
               return;
           }
           if(sum < target) {
               helper(input, target, i, sum, ret, list);
           } else {
               helper(input, target, i+1, sum, ret, list);
           }

           sum -= list.get(list.size() - 1);
           list.remove(list.size() - 1);
       }
       return;
   }
   
   
   public static void main(String[] args) {
       int target = 1;
       int[] candidates = {2};
       ArrayList<ArrayList<Integer>> rst = combinationSum(candidates, target);
       for(ArrayList lst: rst) {
           System.out.println("------start-----");
           for(int i = 0; i < lst.size(); i++) {
               System.out.println("element =" + lst.get(i));
           }
           System.out.println("------end-----");
       }
   }
   
}
























Backtracking

public List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
     //List<Integer> tempList = new ArrayList();
    Arrays.sort(nums);
    Helper(list, new ArrayList<>(), nums,0, 0);
    return list;
}

private void Helper(List<List<Integer>> list, List<Integer> tempList, int [] nums, int sum, int start){
    if(sum > target) return;  // sum > target
    else if(sum == target) list.add(new ArrayList<>(tempList)); // sum == target  return..
    else{
       for(int i = start; i < nums.length; i++){
           tempList.add(nums[i]);1,3   start =1, i=2
           Helper(list, tempList, nums, sum + nums[i], i); // not i + 1 because we can reuse same elements
           tempList.remove(tempList.size() - 1);
       }
    }
}























We can not reuse the same elements:
public List<List<Integer>> combinationSum2(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, target, 0);
    return list;
    
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
    if(remain < 0) return;
    else if(remain == 0) list.add(new ArrayList<>(tempList));
    else{
       for(int i = start; i < nums.length; i++){
           if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
           tempList.add(nums[i]);
           backtrack(list, tempList, nums, remain - nums[i], i + 1);
           tempList.remove(tempList.size() - 1);
       }
    }
}




1,2,3,4,5          6

Templist [1]

I = 1;

TempList [1 , 2]



https://leetcode.com/problems/combination-sum/discuss/



Head first Java

Remote monitor

Object a = b;

`
Backtracking    number + name
