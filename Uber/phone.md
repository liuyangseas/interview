Uber 面经：

1面：

给一个List of tuple，每个tuple有两个值， 这两个值代表村庄的ID，第一个值是starting point，第二个值是end point.  当end point的值为-1时，代表这条路的终结。求最长路径，只需要print out经过村庄的数目，不需要具体路径。For example：
Given：
(1, 5)
(5, 10)
(10, 12)
(12, -1)
(7, 10)
1->5->10->12从1开始经过4个村庄（最长）
5->10->12 从5开始经过3个村庄
10->12从10开始经过2个村庄
7->10->12 从7开始经过3个村庄

讨论之后的Corner case：不会有环出现，一定会有值为-1的ending point，不能go back，只能go forward，不会出现一个起始村庄对应两个终点村庄：(1,5) (1,7) 这种情况不会出现。
(我的解法BFS + hashMap, 就是遍历，然后记录从这个村庄达到终点的count，当遇到重复的ID，直接加上这个ID对应的count值即可)

2面：

第一题：LC 22（秒）
第二题：Html+JS写一个countdown timer，输出：从10开始，每隔一秒减1，直到0（包含0）For example： 输出10，隔一秒 ，输出9，隔一秒，输出8， 。。。隔一秒，输出0。（跪）