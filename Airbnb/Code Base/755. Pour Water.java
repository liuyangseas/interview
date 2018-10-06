We are given an elevation map, heights[i] representing the height of the terrain at that index. The width at each index is 1. After V units of water fall at index K, how much water is at each index?

Water first drops at index K and rests on top of the highest terrain or water at that index. Then, it flows according to the following rules:

If the droplet would eventually fall by moving left, then move left.
Otherwise, if the droplet would eventually fall by moving right, then move right.
Otherwise, rise at it's current position.
Here, "eventually fall" means that the droplet will eventually be at a lower level if it moves in that direction. Also, "level" means the height of the terrain plus any water in that column.
We can assume there's infinitely high terrain on the two sides out of bounds of the array. Also, there could not be partial water being spread out evenly on more than 1 grid block - each unit of water has to be in exactly one block.

Example 1:
Input: heights = [2,1,1,2,1,2,2], V = 4, K = 3
Output: [2,2,2,3,2,2,2]
Explanation:
#       #
#       #
##  # ###
#########
 0123456    <- index

The first drop of water lands at index K = 3:

#       #
#   w   #
##  # ###
#########
 0123456    

When moving left or right, the water can only move to the same level or a lower level.
(By level, we mean the total height of the terrain plus any water in that column.)
Since moving left will eventually make it fall, it moves left.
(A droplet "made to fall" means go to a lower height than it was at previously.)

#       #
#       #
## w# ###
#########
 0123456    

Since moving left will not make it fall, it stays in place.  The next droplet falls:

#       #
#   w   #
## w# ###
#########
 0123456  

Since the new droplet moving left will eventually make it fall, it moves left.
Notice that the droplet still preferred to move left,
even though it could move right (and moving right makes it fall quicker.)

#       #
#  w    #
## w# ###
#########
 0123456  

#       #
#       #
##ww# ###
#########
 0123456  

After those steps, the third droplet falls.
Since moving left would not eventually make it fall, it tries to move right.
Since moving right would eventually make it fall, it moves right.

#       #
#   w   #
##ww# ###
#########
 0123456  

#       #
#       #
##ww#w###
#########
 0123456  

Finally, the fourth droplet falls.
Since moving left would not eventually make it fall, it tries to move right.
Since moving right would not eventually make it fall, it stays in place:

#       #
#   w   #
##ww#w###
#########
 0123456  

The final answer is [2,2,2,3,2,2,2]:

    #    
 ####### 
 ####### 
 0123456 
Example 2:
Input: heights = [1,2,3,4], V = 2, K = 2
Output: [2,3,3,4]
Explanation:
The last droplet settles at index 1, since moving further left would not cause it to eventually fall to a lower height.
Example 3:
Input: heights = [3,1,3], V = 5, K = 1
Output: [4,4,4]
Note:

heights will have length in [1, 100] and contain integers in [0, 99].
V will be in range [0, 2000].
K will be in range [0, heights.length - 1].

public int[] pourWater(int[] heights, int V, int K) {
        if (heights == null || heights.length == 0 || V == 0) {
            return heights;
        }
        int index;
        while (V > 0) {
            index = K;
			// 我们先往左边走，看看左边能不能有办法
            for (int i = K - 1; i >= 0; i--) {
				// 高的阻挡了，就不能走了
                if (heights[i] > heights[index]) {
                    break;
                } else if (heights[i] < heights[index]) {
                    index = i;
                }
            }
            if (index != K) {
                heights[index]++;
                V--;
                continue;
            }
            for (int i = K + 1; i < heights.length; i++) {
                if (heights[i] > heights[index]) {
                    break;
                } else if (heights[i] < heights[index]) {
                    index = i;
                }
            }
            heights[index]++;
            V--;
        }
        return heights;
    }
	
	

// version online
public class PourWater {
  public void pourWater(int[] heights, int water, int location) {
    int[] waters = new int[heights.length];
    int pourLocation;

    while (water > 0) {
      int left = location - 1;
      while (left >= 0) {
        if (heights[left] + waters[left] > heights[left + 1] + waters[left + 1]) {
          break;
        }
        left--;
      }
      if (heights[left + 1] + waters[left + 1] < heights[location] + waters[location]) {
        pourLocation = left + 1;
        waters[pourLocation]++;
        water--;
        continue;
      }

      int right = location + 1;
      while (right < heights.length) {
        if (heights[right] + waters[right] > heights[right - 1] + waters[right - 1]) {
          break;
        }
        right++;
      }
      if (heights[right - 1] + waters[right - 1] < heights[location] + waters[location]) {
        pourLocation = right - 1;
        waters[pourLocation]++;
        water--;
        continue;
      }

      pourLocation = location;
      waters[pourLocation]++;
      water--;
    }

    print(heights, waters);
  }

  private void print(int[] heights, int[] waters) {
    int n = heights.length;

    int maxHeight = 0;
    for (int i = 0; i < n; i++) {
      maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
    }

    for (int height = maxHeight; height >= 0; height--) {
      for (int i = 0; i < n; i++) {
        if (height <= heights[i]) {
          System.out.print("+");
        } else if (height > heights[i] && height <= heights[i] + waters[i]) {
          System.out.print("W");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }
}

//public class Main {
//  public static void main(String[] args) {
//    PourWater pw = new PourWater();
//    int[] heights = {5,4,2,1,2,3,2,1,0,1,2,4};
//    for (int i = 1; i <= 9; i++) {
//      pw.pourWater(heights, i, 5);
//    }
//  }
//}


// version Michael
public class Water {
	public static void print(int[] height) {
		int max = 0;
		for (int i : height) {
			max = Math.max(max, i);
		}
		for (int i = max; i >= 0; i--) {
			for (int h : height) {
				if (h >= i) {
					System.out.print("+");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	
	public static void print2(int[] height, int[] after) {
		int max = 0;
		for (int i : after) {
			max = Math.max(max, i);
		}
		for (int i = max; i >= 0; i--) {
			for (int j = 0; j < after.length; j++) {
				if (after[j] >= i && height[j] >= i) {
					System.out.print("+");
				} else if (after[j] >= i && height[j] < i) {
					System.out.print("w");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	// 两边都是海洋
	public static int[] pourWaterOcean(int[] heights, int K, int V) {
        int[] h = heights.clone();
        for (int i = 1; i <= V; i++) {
            // try left
            int left = K - 1;
            int best = K;
            while (left >= 0 && h[left] <= h[left + 1]) {
                if (h[left] < h[best]) {
                    best = left;
                }
                left--;
            }
            if (left == -1) {
            	continue;
            }
            if (h[best] < h[K]) {
                h[best]++;
                continue;
            }
            //try right
            int right = K + 1;
            best = K;
            while (right <= h.length - 1 && h[right] <= h[right - 1]) {
                if (h[right] < h[best]) {
                    best = right;
                }
                right++;
            }
            if (right == h.length) {
            	continue;
            }
            if (h[best] < h[K]) {
                h[best]++;
                continue;
            }
            //stay
            h[K]++;
        }
        return h;
	}
	
    public static int[] pourWaterWall(int[] heights, int V, int K) {
        int[] h = heights.clone();
        for (int i = 1; i <= V; i++) {
            // try left
            int left = K - 1;
            int best = K;
            while (left >= 0 && h[left] <= h[left + 1]) {
                if (h[left] < h[best]) {
                    best = left;
                }
                left--;
            }
            if (h[best] < h[K]) {
                h[best]++;
                continue;
            }
            //try right
            int right = K + 1;
            best = K;
            while (right <= h.length - 1 && h[right] <= h[right - 1]) {
                if (h[right] < h[best]) {
                    best = right;
                }
                right++;
            }
            if (h[best] < h[K]) {
                h[best]++;
                continue;
            }
            //stay
            h[K]++;
        }
        return h;
    }
	
	public static void main(String[] args) {
		int[] h = {5,4,2,1,2,3,2,1,0,1,2,4};
		int[] k = {3,3,3,4,5,6,2,1,0,1,2,4};
		int[] s = pourWaterOcean(k, 3, 4);
		print2(k, s);
	}
	
}
