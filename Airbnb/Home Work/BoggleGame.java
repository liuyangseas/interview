package Airbnb;

/**
 * 本质上是word search 2
 * 但是呢比如你现在走了一个词apple, 那么a, p, p, l, e这几个char的位置不能继续用了
 * 于是给你一个board, 一个dict让你计算最多能有多少个valid单词出现在这个Board上面
 */

import java.util.*;

class ReturnType {
  boolean hasPrefix;
  boolean hasWord;
  ReturnType(boolean hasPrefix, boolean hasWord) {
    this.hasPrefix = hasPrefix;
    this.hasWord = hasWord;
  }
}

class TrieNode {
  char c;
  boolean isEnd;
  Map<Character, TrieNode> children;
  public TrieNode(char c, boolean isEnd) {
    this.c = c;
    this.isEnd = isEnd;
    this.children = new HashMap<>();
  }
}

class Trie {
  private TrieNode root;
  public Trie() {
    this.root = new TrieNode(' ', false);
  }

  public void insert(String word) {
    TrieNode cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (!cur.children.containsKey(c)) {
        cur.children.put(c, new TrieNode(c, false));
      }
      cur = cur.children.get(c);
    }
    cur.isEnd = true;
  }

  public ReturnType search(String word) {
    TrieNode cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (!cur.children.containsKey(c)) {
        return new ReturnType(false, false);
      }
      cur = cur.children.get(c);
    }
    return new ReturnType(true, cur.isEnd);
  }
}

public class BoggleGame {
  private static final int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
  
	public static void main(String[] args) {
		BoggleGame bg = new BoggleGame();
		char[][] board = {
		        { 'o','a','t','h' },
		        { 'e','t','a','e' },
		        { 'i','h','k','r' },
		        { 'i','f','l','v' }
		};
		Set<String> dict = new HashSet<>();
		dict.add("oath");
		dict.add("pea");
		dict.add("eat");
		dict.add("rain");
		System.out.println(bg.findMostStr(board, dict));
	}
	
  public List<String> findMostStr(char[][] board, Set<String> dict) {
    List<List<int[]>> paths = new ArrayList<>();

    Trie trie = new Trie();
    for (String word : dict) {
      trie.insert(word);
    }

    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        boolean[][] visited = new boolean[m][n];
        visited[i][j] = true;
        List<int[]> curPath = new ArrayList<>();
        curPath.add(new int[] { i, j });
        dfs(paths, board, i, j, trie, visited, curPath);
      }
    }

    List<String> res = new ArrayList<>();
    searchWords(res, new ArrayList<>(), paths, 0, new boolean[m][n], board);

    return res;
  }

  private void searchWords(List<String> res, List<String> curWords, List<List<int[]>> paths,
                           int start, boolean[][] visited, char[][] board) {
    if (start == paths.size()) {
      if (curWords.size() > res.size()) {
        res.clear();
        res.addAll(curWords);
      }
      return;
    }

    for (int i = start; i < paths.size(); i++) {
      boolean canUse = true;
      for (int[] coor : paths.get(i)) {
        if (visited[coor[0]][coor[1]]) {
          canUse = false;
          break;
        }
      }

      if (canUse) {
        for (int[] coor : paths.get(i)) {
          visited[coor[0]][coor[1]] = true;
        }
        curWords.add(path2Word(board, paths.get(i)));
        searchWords(res, curWords, paths, i + 1, visited, board);
        curWords.remove(curWords.size() - 1);
        for (int[] coor : paths.get(i)) {
          visited[coor[0]][coor[1]] = false;
        }
      }
    }
  }

  private void dfs(List<List<int[]>> paths, char[][] board, int x, int y, Trie trie,
                   boolean[][] visited, List<int[]> curPath) {
    String curWord = path2Word(board, curPath);
    ReturnType flag = trie.search(curWord);
    if (!flag.hasPrefix) {
      return;
    }
    if (flag.hasWord) {
      paths.add(new ArrayList<>(curPath));
    }

    int m = board.length;
    int n = board[0].length;

    for (int[] dir : dirs) {
      int xx = x + dir[0];
      int yy = y + dir[1];

      if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
        continue;
      }

      visited[xx][yy] = true;
      curPath.add(new int[] { xx, yy });
      dfs(paths, board, xx, yy, trie, visited, curPath);
      curPath.remove(curPath.size() - 1);
      visited[xx][yy] = false;
    }
  }

  private String path2Word(char[][] board, List<int[]> curPath) {
    StringBuilder sb = new StringBuilder();
    for (int[] coor : curPath) {
      sb.append(board[coor[0]][coor[1]]);
    }
    return sb.toString();
  }
}

//class Main {
//  public static void main(String[] args) {
//    BoggleGame bg = new BoggleGame();
//    char[][] board = {
//            { 'o','a','t','h' },
//            { 'e','t','a','e' },
//            { 'i','h','k','r' },
//            { 'i','f','l','v' }
//    };
//    Set<String> dict = new HashSet<>();
//    dict.add("oath");
//    dict.add("pea");
//    dict.add("eat");
//    dict.add("rain");
//    System.out.println(bg.findMostStr(board, dict));
//  }
//}

// Michael code
 class Boggle {
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

