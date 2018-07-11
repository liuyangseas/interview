 Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1
Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 
Binary Search (dfs): most preferable
  public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k-1-count); // 1 is counted as current node
        }
        
        return root.val;
    }
    
    public int countNodes(TreeNode n) {
        if (n == null) return 0;
        
        return 1 + countNodes(n.left) + countNodes(n.right);
    }
DFS in-order recursive:
    // better keep these two variables in a wrapper class
    private static int number = 0;
    private static int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }
    
    public void helper(TreeNode n) {
        if (n.left != null) helper(n.left);
        count--;
        if (count == 0) {
            number = n.val;
            return;
        }
        if (n.right != null) helper(n.right);
    }
DFS in-order iterative:
  public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();
        
        while (root != null) {
            st.push(root);
            root = root.left;
        }
            
        while (k != 0) {
            TreeNode n = st.pop();
            k--;
            if (k == 0) return n.val;
            TreeNode right = n.right;
            while (right != null) {
                st.push(right);
                right = right.left;
            }
        }
        
        return -1; // never hit if k is valid
  }
   
   
   #1 DFS inorder iterative
   public int kthSmallest(TreeNode root, int k) {
        int count = k;
        Stack<TreeNode> s = new Stack<>();
        TreeNode curr = root;
        while(!s.isEmpty() || curr != null) {
            while(curr != null) {
                s.push(curr);
                curr = curr.left;
            }
            curr = s.pop();
            if((--count) == 0) {
                return curr.val;
            }
            curr = curr.right;    
        }
        return 0;
    }

   #2 DFS inorder recursive
class Solution {
    //private int cnt = 0;
    public int kthSmallest(TreeNode root, int k) {
         if(root == null) return 0;
         int[] count = new int[2];
         count[1] = k;
         helper(root, k, count);
         return count[0];
   }
   public void helper(TreeNode root, int k, int[] count) {
         if(root == null) return ;
         if(root.left != null) helper(root.left, k, count);
         count[1] --;
         if(count[1] == 0) {
            count[0] = root.val;
            return ;
         }
         if(root.right != null) helper(root.right, k, count);
   }
}
   #3 Binary Search
   class Solution {
      public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left, k);
        if ((count + 1) == k) {
            return root.val;
        } else if (k <= count) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k - count - 1);
        }
            
      }
    public int countNodes(TreeNode root, int k) {
         if(root == null) return 0;
         return 1 + countNodes(root.left, k) + countNodes(root.right, k);
    }
   }
   ---------------------------------------------------------------------------------------------------------------------------
   Follow up: What if the BST is modified(delete/insert) frequently and you still need to find the kth smallest? How would you 
              optimize the kth smallest routine?
   Ans: add new field in BST node class. 
         or use a hashmap instead of a new tree structure and store the numbers of nodes in left branches only.
