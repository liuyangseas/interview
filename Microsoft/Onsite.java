2. 一个应用题，之前刷题没遇到过。题目大概：一个硬盘里有N个blocks，每个block有一个id (0, ..., N-1) 和一个指针。文件存在block1~N-1中(block0不存文件), 当存入一个文件时，该文件可能存在一个或多个block中。. visit 1point3acres.com for more.
给定两个API：API1检验一个block是否in use，如果是，调用API2返回该block指向的下一个block(类似LinkedList).  Eg：file1 依次存在block1 -> block3 -> block5中，那么调用API1在block1，3，5均返回true，调用API2在block1，3，5依次返回block3，block5和null。
问题是：遍历block1~N-1，找到所有文件存储的第一个block，上面的例子就是(file1, bloc
BST  next successor   
BST把每一层连成链表
sort color
palidrome 变形题目
Set Matrix Zeroes
Populating Next Right Pointers in Each Node  
Implement Trie (Prefix Tree)
LIS DP
Serialize and deserialize binary tree
Design question, input is a book, output alphabetical order of all words.
What if memory is not enough (for input, for out put, for data structure you need in your code)
    11. Level order with queue, follow up: no queue, only with next pointer
    12. power of 2, stock
    13. letter combination of phone number
    14. Find Minimum in Rotated Sorted Array I II
   15. merge two binary search trees
   16.sqrt（x）变形
   17. Merge sorted array
   18. Delete Node in a Linked List  
   19. Search Range in Binary Search Tree
   20. Spiral Matrix
   21. Search a 2D Matrix II  
   22. 2叉树，LCA，点在树里   叉树，LCA，点不在树里
battle ship游戏设计，ood，主要是设计炸船的function。一开始的解法不太行，然后说到union find去了。最后一刻，醒悟用一个hashmap就能解决。
Path sum (112)
Autocomplete, 不是系统设计，用Trie做
nth end of a linkedlist
regular expression match
copy list with random node,
longest common substring between 2 string,
same tree
Linkedlist 换成level order traverse的binary tree
写了个把matrix转90度
izzbuzz，括号匹配，青蛙过河
Find medium from data stream
带括号的四则运算式
用circular linked list实现queue
两个链表交点
利用array实现queue
给一个数找质因数组合
sudoku game
Word break2
Longest increating array
Burst balloons
string multiple
word search
题目就是一个移零和用最少op来移零
1 3 0 2 0 1
1 3 2 1 0 0

旋转填充矩阵
given 2 n-ary tree, find all common treenode, which must be have same value and same children treenode  以后任何的binary tree的题目你都想想如何follow up成n nary tree
merge k sorted list
linkedlist to binary tree





head first java

2. 一个应用题，之前刷题没遇到过。题目大概：一个硬盘里有N个blocks，每个block有一个id (0, ..., N-1) 和一个指针。文件存在block1~N-1中(block0不存文件), 当存入一个文件时，该文件可能存在一个或多个block中。. visit 1point3acres.com for more.
给定两个API：API1检验一个block是否in use，如果是，调用API2返回该block指向的下一个block(类似LinkedList).  Eg：file1 依次存在block1 -> block3 -> block5中，那么调用API1在block1，3，5均返回true，调用API2在block1，3，5依次返回block3，block5和null。
问题是：遍历block1~N-1，找到所有文件存储的第一个block，上面的例子就是(file1, block1)

1. 白人mgr，BigInteger实现，分析各种设计的优劣（big vs little endinan, list vs array），很简单。这轮应该还不错。

system design，设计一个读写log的系统
逆波兰表达式


第一轮是skyline，我答的不好。。。
第二轮是max depth of binary tree和intersection of two list
第三轮是设计tic tac toe。第二轮和第三轮我觉得答的还行
第四轮是有parent pointer inorder traversal。结果我答的一般，有bug，然后没空改了。。。

1.罗马数字转10禁止，以及如何验证罗马数字是valid
2. 2SUM  分布式环境下的实现以及效率 scalability和有可能出现的问题
3. building outline 求最优解

多线程的hash table
最短路径
相同二叉树
把list变成tree
重叠长方形，给出任意两个长方形的位置，然后求他们的重叠的部分

1. merge two list
2. 10进制数字转换成26进制abc
3.LRU Cache

1. tic tac toe
2. reverse list what if the list has cycle
3. Tries implement
4. 2-d matrix and how to find a way out
5. how to validate a XML

1.Skyline
2.count and say
3.preorder in-order BST convert
4. how to implement windows management

1.word search II
2. decode ways I follow up II
3. 给一个string写出所有大小写组合
4.给一个int random_next_int(int a,int b) api. random n个int，memory performance  distributed random，说白了就是map reduce random function

buy stock
maximum sum

1.读取一个特别大的文件，存下来每个词的行号和offset，先写了一下代码怎么存在一个map里面，然后讨论大数据怎么存，怎么merge、partition data
2. course schedule II
3. Lowest common ancestor  + word break II
4. implement a trie tree, 外面包裹了一些没啥用的描述，二叉树里面有重复元素，找一条任意点到任意点的最长的repeated path

1. LC 36，37
2. LC  362
3. LC 203
4. 很多设计题  问答的形式，一个一个的问

1. 给一个character board和一个dictionary找出所有单词，自己设计dictioanry，直接用trie
2. decode ways  I  follow up II
3. 给一个string 输出所有大小写组合
4.给一个int random_next_int(int a, int b)  api     random n 个 int，主要是follow up    memory  perfomance  最后我给的是 distributed random说白了就是Mapreduce random function


find missing number
find duplicate character in two strings
bitmap

convert leaf node of binary tree to linked list
no global/ static variables

graph shortest path, return path and steps

topological sort

resume, project deep dive


1.
