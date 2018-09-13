On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].

class Solution {
     public int slidingPuzzle(int[][] board) {
        Set<String> seen = new HashSet<>(); // used to avoid duplicates
        String target = "123450";
        // convert board to string - initial state.
        String s = Arrays.deepToString(board).replaceAll("\\[|\\]|,|\\s", "");
        Queue<String> q = new LinkedList<>(Arrays.asList(s));
        seen.add(s); // add initial state to set.
        int ans = 0; // record the # of rounds of Breadth Search
        while (!q.isEmpty()) { // Not traverse all states yet?
            // loop used to control search breadth.
            for (int sz = q.size(); sz > 0; --sz) { 
                String str = q.poll();
                if (str.equals(target)) { return ans; } // found target.
                int i = str.indexOf('0'); // locate '0'
                int[] d = { 1, -1, 3, -3 }; // potential swap displacements.
                for (int k = 0; k < 4; ++k) { // traverse all options.
                    int j = i + d[k]; // potential swap index.
                    // conditional used to avoid invalid swaps.
                    if (j < 0 || j > 5 || i == 2 && j == 3 || i == 3 && j == 2) { continue; } 
                    char[] ch = str.toCharArray();
                    // swap ch[i] and ch[j].
                    char tmp = ch[i];
                    ch[i] = ch[j];
                    ch[j] = tmp;
                    s = String.valueOf(ch); // a new candidate state.
                    if (seen.add(s)) { q.offer(s); } //Avoid duplicate.
                }
            }
            ++ans; // finished a round of Breadth Search, plus 1.
        }
        return -1;
    }
}

// DFS is also ok
class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    Map<Integer, Integer> map = new HashMap<>();
    int min_move = Integer.MAX_VALUE;
    public int slidingPuzzle(int[][] board) {
        map.put(123450, 0);
        int[] zero = new int[2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    zero[0] = i;
                    zero[1] = j;
                    break;
                }
            }
        }
        helper(board, zero[0], zero[1], 0);
        return min_move == Integer.MAX_VALUE ? -1 : min_move;
    }
	
    private void helper(int[][] board, int x, int y, int move) {
        if (move > min_move) 
			return;
		
        int code = encode(board);
        if (code == 123450) {
            min_move = move;
            return;
        }
		
        if (map.containsKey(code)) {
            if (move > map.get(code)) return;
        }
		
        map.put(code, move);
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < 2 && ny >= 0 && ny < 3) {
                swap(board, x, y, nx, ny);
                helper(board, nx, ny, move + 1);
                swap(board, nx, ny, x, y);
            }
        }
    }
	
    private void swap (int[][] board, int i, int j, int ii, int jj) {
        int temp = board[i][j];
        board[i][j] = board[ii][jj];
        board[ii][jj] = temp;
    }
	
    private int encode(int[][] board) {
        int code = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                code *= 10;
                code += board[i][j];
            }
        }
		
        return code;
    }
}

// Michael
public class sliding {
    public int slidingPuzzle(int[][] board) {
        String target = "123450";
        String start = "";
        for (int[] row : board) {
            for (int num : row) {
                start += num;
            }
        }
        Queue<String> bfs = new LinkedList();
        Set<String> visited = new HashSet();
        bfs.offer(start);
        int step = 0;
        int[] dir = {1, -1, 3, -3};
        while (bfs.size() > 0) {
            int size = bfs.size();
            for (int i = 0; i < size; i++) {
                String cur = bfs.poll();
                if (cur.equals(target)) {
                    return step;
                }
                if (visited.contains(cur)) {
                    continue;
                }
                int index = cur.indexOf('0');
                for (int offset : dir) {
                    int newIndex = index + offset;
                    if (newIndex >= 0 && newIndex < 6 && !(newIndex == 2 && index == 3) && !(newIndex == 3 && index == 2)) {
                        char[] temp = cur.toCharArray();      
                        char t = temp[index];
                        temp[index] = temp[newIndex];
                        temp[newIndex] = t;
                        bfs.offer(String.valueOf(temp));
                    }
                }
                visited.add(cur);
            }
            step++;
        }
        return -1;
    }
}
