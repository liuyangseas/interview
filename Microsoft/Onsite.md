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