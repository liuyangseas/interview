
1.BST和hashtable有什么区别，然后问我如果要实现你手机里的通讯录要用什么数据结果，如果要求跟手机里一样按名字排序用什么数据结构
2. 然后问了个BST level order traverse，然后就结束了，又要我问他问题。。。

1. run length encoding of a sorted integer array, 一个sorted array里面有duplicate, 输出一个新array同时里面的元素是 （value, position, count）
2. Sorted array to BST.

问了很多概念题
logical and vs bitwise and (&& vs &)
what is virtual memory
how override method works (答案要提到 virtual method table)
binary search tree vs hashtable(performance, how do you decide when to use which)
how much would you resize hashtable to reduce collision to an acceptable rate
what is encapsulation. 
advantage/disavantage of encapsulation (bonus)
what is polymorphism
what is the two most commont data structures you use, and why
given n html files, find all the phone numbers in there(我说在unix里你可以直接在cmd用regex, 他就问别的enviroment不行吗? followup: how would you do it in a programatic way, and how would you test that you actually get valid phone numbers)

leetcode的clone graph

top 1000 most frequent words。我说统计完直接sort, 更简单的用quick select。问interviewer实现哪个，他说简单的直接sort吧。我写完了用了20分钟不到，然后他挑了两个小bug, 
都很快改正了20分钟以后出了一个stock1类似题，唯一区别就是每天有很多数据，找出收益最大的一天，他说每天结束前要把股票卖了。就是调用很多次stock1然后返回收益最大的一天。我10分钟不到写完，然后他直接问test cases，我把regular和corner cases都说了，说corner case的时候在code里面加了个判断。他问除了返回-1， 0这种异常值还有什么方法，我说throw exception. 他也很满意。

一开始他介绍了一下自己，然后问我喜欢什么方面。 第二个问题是为什么选择Tableau。
technical 问题是设计 minesweeper（扫雷）：
https://leetcode.com/problems/minesweeper/description/
并不是很难 只需要我写board class 和 click method。 player 一开始可以instantiate一个board，需要给予row，col，# of mine。click 这个method给予row 和 col，然后判断有没有踩到雷。
1）踩到雷，游戏结束。
2）没踩到雷，两个情况：
  a）immediate adjacent (diagonals included) 有雷，需要告知有几颗雷
  b）附近没雷，把附近没雷的格子也都click. 

就问了一个问题 电面了一个小时，本以为让面试官hint了很多会没机会了 不过面试完几个小时后马上就收到recruiter的恭喜要进行下一轮。来回报一下地里。

几点注意：
1）如何populate gird with # mines。一开始我用rand来generate random coordinate，check是否已经有mine。面试官说worst case是forever，因为有可能会keep generating the same coordinate。就换成coordinate pool，从里面随机pop。
2）需要考虑到click过的coordinate不再被click，比如在2x2（0 mine）的grid里 如果recursively click 会导致infinity loop。
3）recursively click附近的coordinate, excluding self，因为我用的是nested for loop，要skip被点的 self coordinate（3x3，and skip self），比如在 1x1（0 mine）的grid里，不在nested loop里skip self的话也会导致infinity loop。
4）如果点的corner，check adjacent coordinates不能越界。
5）面试官告诉我写for loop尽量避开 int i = 0这样命名variable，他说 readability很重要。

过了两天HR说要约二面，只有一轮45分钟，这回是Test的英国manager.
上周五下午面的，也不知道是信号的问题还是我听力的问题，面试官说的我都听不太清楚... 题目是这样的：
Given a value, Find a node that has the cloest value (less than the given value) in an ordered binary tree. 我上来就觉得这个不是那个find closest value in BST么。 
然后我就问这个ordered binary tree是BST么？面试官说不是...就是left < root < right... 我开始想用LC原题的方法做，后来想想要是按照他说的的性质好像是行不通的...
磨叽了5min决定用暴力traversal，面试官说可以你随便用啥方法都行...我就写了个BFS然后就完事了。之后面试官说我们留了20min聊天，你想问啥问啥吧...我就问了几个关于test的问题，然后就88了。

第一轮，美国小哥Mike, 上来直接出题，Integer To English words, 和LC不同的一点是还要加上“-”， 比如two hundred-twenty-five这种。 
第二轮，本来约的是test组的印度男manager，我一接电话发现是女的声音，有点蒙B了，开始听着口音还正常，说了五分钟就觉得完了，印度口音已经出现了。题目很简单，是Stock1, 不过返回的是最大收益的买入时间和卖出时间，

为什么想来tabluea
1 LeetCode Product of Array Except Self
3 Leetcode Binary search tree level order traversal
什么是多态
hash 和 bst 区别

一个欧洲大叔面我的。
1. 互相自我介绍下
2. Why tableau
3. 里特抠的恩皇后。我做的时候检查当前位置是否有效是用bruite-force的方法检查的。所以有个follow-up是如何O(1)时间检查行和列还有diagonal。

题目其实是geekforgeeks上面两个经典问题拼接的：
1. run length encoding of a sorted integer array, 一个sorted array里面有duplicate, 输出一个新array同时里面的元素是 （value, position, count）
2. Sorted array to BST.

写完code之后分析了一下time和sapce，然后问了一下怎么改进以及standard libray的内部实现和complexity。最后问我构建BST可不可以不用array，我一开始没明白， 后来发现其实他问的是sorted linked list to BST.-google 1point3acres
答出来后小哥表示满意，就让我提问了。 move on, 求pass..Tabluea 电面

On campus 面试和这个贴一模一样：http://www.1point3acres.com/bbs/thread-143499-1-1.html
一周后安排了两轮电面：
第一轮： Integer to English Word ＋ test cases
LC 273. Integer to English Words
第二轮： given a special point, find the top k closest points from an array of points ＋ test cases： 写的时候出了些脑残小错，后来改回来了。
behaviour question： 之前的经历，为什么申tableau这类的

Coding 题目是，不是leetcode上的，我一下子想不起来文字怎么描述了，我举个例子：
[23, 7, 6, 23，9, 6] ==> [23, 6, 23, 6] 就是删去所有的只出现一次的，
我先问了他能不能用 additional space, 他说ok， 我就用了一个 hashmap 去存 出现的次数, 一个set 存 只出现一次的，
中间有个小插曲是我边说边打，结果打完了他告诉我他有一半的代码他没看到，刷新了一下才看到，
之后一起track了一遍这个代码，讲清楚了原理，就问我test case有什么， 我写完几种之后问他有什么他的suggestion，他觉得it's good, 然后他让我问问题，
我就问了他 对我code有没有什么suggestion， 他觉得挺好的，说虽然浪费了一些空间，但时间复杂度是…… 他停住又来追问我 时间复杂度是多少，我说了 linear time。
之后就是几个简单的小问题，问了问什么时候能知道结果，他说他会把反馈给 recruiter，recruiter什么时候联系你我也不知道

刚收到拒信，心中有点疑惑，所以分享出来，希望大家也能给点意见。
楼主来美时间不长，在美国一家小于1000人的公司做SDET两年半的时间，最近想骑驴找马，linked in上给推荐了几个职位，抱着试试看的想法投了几家不算著名公司，想找找感觉，这家Tableau是第一家电面的公司，面的是SDET的职位。
面试官一个是manager， 一个是senior software developer，全程只有manager说话。
BQ 20分钟左右，然后做题，利口71，只是改成windows文件路径。当时刷题这题看了下，没写，知道肯定用stack。
正常写出来了，manager问了很多follow up，主要是让想各种边缘case，比如遇到“..\path”这种怎么处理。做题用的纯白板，不能编译和run，整个过程下来自我感觉没太大问题。

朋友内推，反应很快，一周内HR联系说安排面试，很快就开面了。电话那头有三个人，一个负责聊天。问了些behavior question：
1. 你最骄傲的项目
2. 为什么最骄傲，有什么挑战。
3. 喜欢在怎样的项目工作。

题目： 给两个array，找出两个array重叠的数。有重复。
给出brute force的解法和用hashset的解法。面试官详细问清楚了解法后说了句输出也可以重复，当时心里就咯噔了一下，马上说可以换成hashmap。
然后问了两个的时间复杂度。面试官又说排序好了你有更好的解法吗？给了两个的index的解法。最后写了几分钟代码。
follow up，如果数组很大怎么办。回答放硬盘里再读取到内存，用个buffer。如果没排序就用外排序排好再找。又问了下如果一个硬盘放不下怎么办，就提到了分布式。没有继续深入。

感觉和面试官交流有点困难， 有的问题让面试官重复了好几遍
最开始他做了自我介绍， 是来自 calculation team
让我做自我介绍.
问我用没用过 tableau 产品， 有没有什么改进的地方

然后就聊了一些别的， 兴趣爱好
然后给了一道coding题
是 input 一个 integer
output 一个 array, sum of array 等于 input integer
要 random

电话准时2：00PM打过来，打电话的是招人的manager， 她的team是big data，主要做数据connector，比如说把hadoop连上tableau。现在的opening是front end和back-end。
后台的role希望有database 开发的经验，比如说query optimization； 前台的role希望对desktop和web 的gui的开发。

236. Lowest Common Ancestor of a Binary Tree
最后25分钟写一个binary tree lowest common node.
需要自己定义node， function signature, 讨论edge cases和测试方法。

写了一个bottom up solution，

Tableau
聊经验，简历上的项目。
coding: best time to buy and sell stock 1+2+3,有些变体，还要输出买入的date，卖出的date

电面： 主要是基本概念，和这个帖子相似 http://www.1point3acres.com/bbs/thread-140899-1-1.html
Polymorphism
Encapsulation
Disadvantage of Encapsulation
BFS binary tree traversal
BFS binary tree traversal w/ level spliter (insert a null at end of each level)
BST vs Hash (Differences on insert, search, deletion operations)
如何实现多态(Vptr)
Database normalization：这个回答的时候直接跟面试官说没学过database，人也就没继续问，最后onsite估计悲剧在database上了

前20分钟是概念问题：
1.BST vs Hash，
2.HashTable的complexity以及如何处理collision，
3.封装 继承 多态的概念，
4. C++ complier 如何实现多态，
5.封装有什么缺点，
6. logical and vs bitwise and
7. Virtual memory
8. Page fault
9. 比较Virtual 和 Physical memory的效率差距

最后是behavior: hardest Bug solved
. visit 1point3acres.com for more.
面试完了之后还留了个homework：
1. Write binary search function that searches within a sorted array of float values.

2. Write a method to reverse a string

almost product array
BST Graph

1) Print a tree in level ordered
2) Given a array of ints with one peak, find the peak.

mergesort | implement DFS

Write a function that takes two sorted arrays and merges them to create a sorted array.

Difference between linked list, tree, and hash map. Create a stream reader in C++ that consolidates multiple different stream inputs.

Explain and implement a serialization of a binary tree.

Implement malloc (not free) using some given function pagemalloc

1.justify if a string consists of valid paranthess. 2.find a value in a rotated array

Code an iterator that returns the in-order traversal of a binary tree.

remove dup char in string

Delete the numbers that have more than one occurrence from the array.
Find the smallest unique integer in a random integer array with possible duplicates.

Given a list of numbers delete the numbers that only appear once in the list. e.e [3,2,1,3,2] =>[3,2]  https://www.geeksforgeeks.org/find-the-element-that-appears-once/

a maze question that ask you to get out of the maze

Implement a binary search tree for floating-point values.

Asked to implement a BFS on a BST. Asked about the time complexity of search, deletion, etc

Telephonic - clone a graph
Find closet match in a BST

227. Basic Calculator II

LC 121. Best Time to Buy and Sell Stock
LC  124. Binary Tree Maximum Path Sum

里特抠的恩皇后。我做的时候检查当前位置是否有效是用bruite-force的方法检查的。所以有个follow-up是如何O(1)时间检查行和列还有diagonal

题目： 给两个array，找出两个array重叠的数。有重复。
给出brute force的解法和用hashset的解法。面试官详细问清楚了解法后说了句输出也可以重复，当时心里就咯噔了一下，马上说可以换成hashmap。
然后问了两个的时间复杂度。面试官又说排序好了你有更好的解法吗？给了两个的index的解法。最后写了几分钟代码。
follow up，如果数组很大怎么办。回答放硬盘里再读取到内存，用个buffer。如果没排序就用外排序排好再找。又问了下如果一个硬盘放不下怎么办，就提到了分布式。没有继续深入

利口71

一个树的label有 0 1 2三个状态，如果他所有的Children都是0 那么他的label也是0，如果都是1那么他就是1，如果有1有0那他的label就是2，写一个方法set一个node到 0 1的状态，更新树

Given an unsorted array guaranteed to only have the letter 'r', 'w', and 'b', sort the array in place.

一个三哥面的，然后写个扫雷游戏，只要写model就好了。对扫雷不是很懂。初始化设计好状态表，要求自己设计初始化的机制，我就丢几个随机数进去，follow up如果你填满了雷collision 太多咋办，我给的是如果雷数过半随机生产不是雷的。
写一个onClick. 我写的是BFS, 从点到不是雷的为止遍历周围方向没有被翻过来的不是雷的。因为规则没搞懂，我把没雷的都给翻了。后来搜了下扫雷规则，碰到有雷的个数的翻过来不再入列，只有周围没雷的入列。没时间了他就说你这都给翻了就没改了。