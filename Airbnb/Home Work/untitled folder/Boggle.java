package ab;
import java.util.*;
/*
用 trie+DFS 找到一个 word 之后,wordCounter 加 1,从这个 word 最后一个 character 的位
置再用 trie+DFS 继续查找,知道相邻位置都用过了,或者没有在 trie 里找到 match。

用 trie+DFS 找到所有 words 可能出现的位置序列. 然后根据每个单词出现的序列在分别做
DFS,分别是不取这个单词,取第一个序列,取第二个序列,etc。
*/
public class Boggle {
	class TrieNode {
		TrieNode[] children;
		char value;
		boolean leaf;
		public TrieNode(char c) {
			value = c;
			children = new TrieNode[26];
			leaf = false;
		}
	}
	
	class Trie {
		TrieNode root;
		public Trie() {
			root = new TrieNode('a');
		}
		
		public void add(String word) {
			TrieNode cur = root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (cur.children[c - 'a'] == null) {
					TrieNode temp = new TrieNode(c);
					cur.children[c - 'a'] = temp;
				}
				if (i == word.length() - 1) {
					cur.children[c - 'a'].leaf = true;
				}
				cur = cur.children[c - 'a'];
			}
		}
	}
	
	int max = 0;
	String path = "";
	public String find(char[][] board, Set<String> dict) {
		Trie trie = new Trie();
		for (String s : dict) {
			trie.add(s);
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (trie.root.children[board[i][j] - 'a'] == null) {
					continue;
				}
				StringBuilder cur = new StringBuilder();
				Set<String> visited = new HashSet<>();
				helper2(board, trie.root, cur, visited, i, j, 0, trie.root);
			}
		}

		return path;
	}
	/*
	public void helper(char[][] board, TrieNode head, StringBuilder cur, Set<String> visited, int row, int col, int count, TrieNode root) {
		char c = board[row][col];

		if (head.children[c - 'a'] == null) {
			return;
		}
		cur.append(c);
		System.out.println("cur:   " + cur);
		String key = row + "#" + col;
		visited.add(key);
		
		if (head.children[c - 'a'].leaf) {
			count++;
			head = root;
			//System.out.println("kkkkkk");
			//sb.append(cur);
			//System.out.println("aaaa   " + sb);
		} else {
			head = head.children[c - 'a'];
		}
		
		if (count > max) {
			path = cur.toString();
			max = count;
		}
		
		int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		for (int[] dir : dirs) {
			int x = row + dir[0];
			int y = col + dir[1];
			String k = x + "#" + y;
			if (x >= board.length || x < 0 || y >= board[0].length || y < 0) {
				continue;
			}
			if (head.children[board[x][y] - 'a'] == null || visited.contains(k)) {
				continue;
			}
			helper(board, head, cur, visited, x, y, count, root);
			
		}
		cur.deleteCharAt(cur.length() - 1);
		visited.remove(key);
	}
	*/
	//这个是找最长一条路径
	
	public void helper2(char[][] board, TrieNode head, StringBuilder cur, Set<String> visited, int row, int col, int count, TrieNode root) {
		char c = board[row][col];

		if (head.children[c - 'a'] == null) {
			return;
		}
		cur.append(c);
		System.out.println("cur:   " + cur);
		String key = row + "#" + col;
		visited.add(key);
		int index = 1;
		if (head.children[c - 'a'].leaf) {
			//System.out.println("kkkkkk");
			index = 2;
			if (count + 1 > max) {
				path = cur.toString();
				max = count;
			}
		} 
		int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		//System.out.println("index: " + index);

		for (int i = 0; i < index; i++) {
			if (i == 0) {
				head = head.children[c - 'a'];
			} else {
				head = root;
				count += 1;
			}
			for (int[] dir : dirs) {
				int x = row + dir[0];
				int y = col + dir[1];
				String k = x + "#" + y;
				if (x >= board.length || x < 0 || y >= board[0].length || y < 0) {
					continue;
				}
				if (head.children[board[x][y] - 'a'] == null || visited.contains(k)) {
					continue;
				}
				helper2(board, head, cur, visited, x, y, count, root);			
			}
		}

		cur.deleteCharAt(cur.length() - 1);
		visited.remove(key);
	}
	
	//找整个board里面包含不重复位置
	/*
	给你一个字典，一个board，让你在上面找到一个最大的集合，返回一组单词都在字典中，
	但他们的位置不能重合交叉。面试官expect的做法是这样，可以写一个trie加速搜索，
	每次你发现这是一个单词的前缀你就往下搜，搜到一个单词了，你有两种选择，
	一种是你把这个置visited传到下一次搜索，就是dfs里再重扫整个board选一个新的位置的单词出来，
	还有一种就是你压根不管，这个单词你不要，那就再见不用再管了。
	然后就是你往四个方向搜索的时候每个方向返回给你一个最大的集合，你比较一下返回当前这层找到的最大的集合
	*/
	
	public static void main(String[] args) {
		/*
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
		 */
		
		char[][] board = {{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}};
		Set<String> dict = new HashSet<>();
		/*
		dict.add("oa");
		dict.add("th");
		dict.add("fl");
		dict.add("aae");
		*/
		dict.add("oaa");
		dict.add("oa");
		dict.add("ak");
		Boggle s = new Boggle();
		System.out.println(s.find(board, dict));
	}
	
}
