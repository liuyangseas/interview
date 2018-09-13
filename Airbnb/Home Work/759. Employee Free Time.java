We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:
Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:
Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:

schedule and schedule[i] are lists with lengths in range [1, 50].
0 <= schedule[i].start < schedule[i].end <= 10^8.


这道题和之前那道Merge Intervals基本没有太大的区别，那道题是求合并后的区间，这道题求合并后区间中间不相连的区间。那么只要我们合并好了区间，就很容易做了。
那么我么首先应该给所有的区间排个序，按照起始位置从小到大来排。因为我们总不可能一会处理前面的，一会处理后面的区间。
排好序以后，我们先取出第一个区间赋给t，然后开始遍历所有的区间内所有的区间，如果t的结束位置小于当前遍历到的区间i的起始位置，说明二者没有交集，
那么把不相交的部分加入结果res中，然后把当前区间i赋值给t；否则如果区间t和区间i有交集，那么我们更新t的结束位置为二者中的较大值，
因为按顺序遍历区间的时候，区间t的结束位置是比较的基准，越大越容易和后面的区间进行合并，参见代码如下：

// 刚开始用我们的扫描线段树来做，但是感觉会超时，因为我们的时间range太长，我们的schedule[i].end <= 10^8。 而且这里时间并没有得到提升，因为你始终得遍历所有的数组
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        if(schedule == null || schedule.size() == 0){
            return null;
        }
        
        List<Interval> res = new ArrayList<>();
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(List<Interval> list : schedule){
            for(Interval num : list){
                max = Math.max(max, num.end);
                // 第一个开始会议之前的时间都不算
                min = Math.min(min, num.start);
            }
        }
        
        int[] array = new int[max+1];
        
        for(List<Interval> list : schedule){
            for(Interval num : list){
               array[num.start]++;
               array[num.end]--;
            }
        }
        
        int preIndex = -1;
		// 这里遍历整个array的时间太长了，我们可以遍历所有的events
        for(int i = 1; i < array.length; i++){
            // 第一个地方，79之间的8为0
            if(preIndex != -1 && array[i] != 0){
                res.add(new Interval(preIndex,i));
                preIndex = -1;
            }
            array[i] += array[i-1];
            if(array[i] == 0 && i >= min){
                if(preIndex == -1){
                    System.out.println(i);
                    preIndex = i;
                }
            }
        }
        
        return res;
    }
}

// 改进型的扫描线段树
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        int OPEN = 0, CLOSE = 1;

        List<int[]> events = new ArrayList();
        for (List<Interval> employee: avails)
            for (Interval iv: employee) {
                events.add(new int[]{iv.start, OPEN});
                events.add(new int[]{iv.end, CLOSE});
            }

        Collections.sort(events, (a, b) -> a[0] != b[0] ? a[0]-b[0] : a[1]-b[1]);
        List<Interval> ans = new ArrayList();

        int prev = -1, bal = 0;
		// 这里只需要遍历我们的event，也就很好的避免了我们的start必须在第一个会议开始以后，end必须在最后一个会议结束之前
        for (int[] event: events) {
            // event[0] = time, event[1] = command
            if (bal == 0 && prev >= 0)
                ans.add(new Interval(prev, event[0]));
            bal += event[1] == OPEN ? 1 : -1;
            prev = event[0];
        }

        return ans;
    }
}

// 用带sort的HashMap也就是TreeMap来存我们的扫描线段树， Michael的版本
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
	        TreeMap<Integer, Integer> map = new TreeMap<>();
	        List<Interval> res = new ArrayList<>();
	        for (List<Interval> employee: schedule) {
	            for (Interval i: employee) {
					map.put(i.start, map.getOrDefault(i.start, 0) + 1);
					map.put(i.end, map.getOrDefault(i.end, 0) - 1);
				}
	        }
			
	        int pre = -1;
	        int total = 0;
	        for(int i:  map.keySet()) {
	            if (total == 0 && pre != -1) {
	                res.add(new Interval(pre, i));
	            }  
	            total += map.get(i);
	            pre = i;
	        }
	    
	        return res;
	 }

// 用Priority Queue来做
public List<Interval> employeeFreeTime(List<List<Interval>> avails) {

	List<Interval> result = new ArrayList<>();

	PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
	avails.forEach(e -> pq.addAll(e));

	Interval temp = pq.poll();
	while(!pq.isEmpty()) {
		if(temp.end < pq.peek().start) { // no intersect
			result.add(new Interval(temp.end, pq.peek().start));
			temp = pq.poll(); // becomes the next temp interval
		}else { // intersect or sub merged
			temp = temp.end < pq.peek().end ? pq.peek() : temp;
			pq.poll();
		}
	}
	return result;
}



 public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
	List<Interval> result = new ArrayList<>();
	List<Interval> timeLine = new ArrayList<>();
	avails.forEach(e -> timeLine.addAll(e));
	Collections.sort(timeLine, ((a, b) -> a.start - b.start));

	Interval temp = timeLine.get(0);
	for(Interval each : timeLine) {
		if(temp.end < each.start) {
			result.add(new Interval(temp.end, each.start));
			temp = each;
		}else{
			temp = temp.end < each.end ? each : temp;
		}
	}
	return result;
}