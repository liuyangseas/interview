Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
Example 1:
Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:
Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]

// https://blog.csdn.net/liuchonge/article/details/60966952

// 思路一：嵌套循环，这种方法时间复杂度很高，提交代码会提示超时。
   public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<words.length-1; i++)
            for(int j=i+1; j<words.length; j++){
                String word = words[i] + words[j];
                if(isPalindrome(word)){
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.add(j);
                    res.add(tmp);
                }
                String word1 = words[j] + words[i];
                if(isPalindrome(word1)){
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(j);
                    tmp.add(i);
                    res.add(tmp);
                }
            }
        return res;
    }
	
// 那么就改进一下，我们首先使用map来保存所有字符串，然后再遍历，对每个字符串判断其头和尾（分别对应前后两种拼接方式）的反转是否已经存在于map中，若存在，则接着判断该字符串剩下的一部分是否满足回文条件，若满足则记录，否则返回判断下一个。
  public static List<List<Integer>> palindromePairs1(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<words.length; i++) map.put(words[i], i);
        for(int i=0; i<words.length; i++){
            int l=0, r=0;
            while(l<=r){
                String s = words[i].substring(l, r);
                Integer j = map.get(new StringBuilder(s).reverse().toString());
                if(j != null && i != j && isPalindrome(words[i].substring(l == 0 ? r : 0, l == 0 ? words[i].length() :l)))
                    res.add(Arrays.asList(l == 0 ? new Integer []{i, j} : new Integer[]{j, i}));
                if(r < words[i].length()) ++r;
                else ++l;
            }
        }
        return res;
    }

// 一种改进的方法就是只使用一个下标，然后同时得到0->j和j->n两个子串。  O(n*k^2)
class Solution {
   public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>(); 
        if (words == null || words.length < 2){
            return res;
        }
       
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i=0; i<words.length; i++){
            map.put(words[i], i);
        }
       
        for (int i=0; i<words.length; i++) {
            for (int j=0; j<=words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(map.get(str2rvs));
                        list.add(i);
                        res.add(list);
                    }
                }else if (isPalindrome(str2)) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    // check "str.length() != 0" to avoid duplicates
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length()!=0) { 
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(map.get(str1rvs));
                        res.add(list);
                    }
                }
            }
        }
		
        return res;
    }
    
    public static boolean isPalindrome (String word){
        for(int i=0, j=word.length()-1; i<j; i++, j--){
            if(word.charAt(i) == word.charAt(j))
                continue;
            else
                return false;
        }
		
        return true;
    }
}

// Trie
class Solution {
    private static class TrieNode {
    TrieNode[] next;
    int index;
    List<Integer> list;
    	
    TrieNode() {
    	next = new TrieNode[26];
    	index = -1;
    	list = new ArrayList<>();
    }
}
    
public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> res = new ArrayList<>();

    TrieNode root = new TrieNode();
    for (int i = 0; i < words.length; i++){
		addWord(root, words[i], i);
	}
	
    for (int i = 0; i < words.length; i++){
		search(words, i, root, res);
	}
    
    return res;
}
    
private void addWord(TrieNode root, String word, int index) {
    for (int i = word.length() - 1; i >= 0; i--) {
        int j = word.charAt(i) - 'a';
    	if (root.next[j] == null){
			root.next[j] = new TrieNode();
		}
		
    	if (isPalindrome(word, 0, i)){
			root.list.add(index);
		}
		
    	root = root.next[j];
    }
    	
    root.list.add(index);
    root.index = index;
}
    
private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
    for (int j = 0; j < words[i].length(); j++) {	
    	if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
    	    res.add(Arrays.asList(i, root.index));
    	}
    		
    	root = root.next[words[i].charAt(j) - 'a'];
      	if (root == null) return;
    }
    	
    for (int j : root.list) {
    	if (i == j) continue;
    	res.add(Arrays.asList(i, j));
    }
}
    
private boolean isPalindrome(String word, int i, int j) {
    while (i < j) {
    	if (word.charAt(i++) != word.charAt(j--)) return false;
    }
    	
    return true;
}
}