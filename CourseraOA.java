第一题是经典题 metal rods。 在地里看过面经，但是没找到答案，跟地里说的情况一样，用暴力破解会出现三个test cases 过不了的情况。是这样的，因为这几个testcases 里面的cut cost 很高，但是单位售价很低，导致求完totalLength之时会出现异常。举个例子。cut的cost是10000，现给3根铁棒， 11， 10， 10， 单位售价1。那么最佳解答应该是直接卖出两根10， 11的那根根本就不cut。如果你是求全部铁棒按10切割，得到一共三根10以及切一刀的结果，收益小于直接卖出。
第二题是task master。 花了很长时间理解题意。一开始以为是拓扑排序，最后发现是union find就可以了。题目中要求每个task最多只依赖一个task，所以每一个task最多只会处在一个task cycle里面。最后的结果就是总的task数减去cycle的个数就好了。

拖了很久终于写掉了...两题
第一题是647变形，需要distinct的substring的个数
第二题是Non-dominatable Entities，需要写stdin和stdout
看帖子好多也是今天写了明天悲剧...默默祈祷一下...

第一题：
   prefix 和 suffix变形，要结trie tree来做，好变态，完全不会
   Leetcode 746 变形
第二题：
   又是变态到不行的一道题目，关于区间的，同志们，帮你们到这里了
   Leetcode 715 变形 

如果做了Leetcode 746 和 leetcode715 可能会好一些，之前没见过如此的题目和相关的知识点，祝各位加油


不同Palindrome Substring的个数

最长subarray，和小于k

https://www.hackerrank.com/contests/juniper-hackathon/challenges/metals



第一题：给一组数和一个数k，找到最长的sub array，array里的数加起来小于等于k
第二题：prefix suffix match

.1. Subarray Product (全pass). 
2. Reaching Points (Fail).

在站内找了大部分的题目做，起码16道题有答案了，它娘亲的竟然出了道Reaching Points，就是没做过，用了DFS有四个case 过不了。。考完后发现别人用DP，也没法理解，DP真的不懂，也不知道怎么练习好，求Reaching Points的DP解法谢谢。。如果有人能分享一下的话。。

PS:
也快半年了，一直跪，老实说一直告诉自己不要气馁，但是真的一直跪。
记性真的被玩坏了，现在越来越难记住事情，看来还没开始就已经萎了。。
虚心地想问一下，平时大家都花多少时间去学习的？怎样可以把题目思路牢记？. 1point3acres.com/bbs
我发现今天解完题，隔两三天就忘了，更不要说面试的时候了。。
新年新气象，顺便祝大家新年快乐吧


做之前搜了一遍地里的面经，没找到今年的，就点开做了。题目是两道coding，没有选择题。
给三个字符串，分别是text, prefix, suffix，要求从text里面找到一个字串sub，使得prefix和sub尾首相接，sub和suffix尾首相接，且两个相接的部分加起来长度最长。比如text="nothing", prefix="bruno", suffix="ingenious"，返回结果是"nothing"，相接部分加起来长度是5（2+3）。如果长度相同，取字典序较小的sub。这题就是直接枚举所有sub，不断查找sub第一个字符在prefix中出现的位置并比较两个部分是否相接，如果相接就记下来，并且这就是当前这个sub和prefix最长的相接部分。suffix同理，只不过是查找suffix第一个字符在sub中第一个出现且相接的位置。需要注意的是找到第一个符合相接的位置后就要立刻停止循环，不然后面可能会找到更短的相接字串然后覆盖前面的结果。我就是这里犯了2，结果有几个test case没过。
给一些颜色常量，用RGB表示，然后给一个二进制的颜色数组，用24位二进制表示，每8位表示RGB中的一个参数，问这个颜色和哪个颜色常量最接近。如果和两个都接近，返回"Ambigious"。这题比较简单，直接全比较一遍即可。
12/3投的4号就给OA了Hackerrank： Coursera Software Engineer, Entry-Level Challenge一共两题，60min，
第一题是Approximate Matching: https://www.careercup.com/question?id=5651582337155072
第二题是 Non-dominatable Entities: https://stackoverflow.com/questi ... non-dominated-pairs. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴

题目可以看上面两个链接，里面的code对不对就不知道

1. palindrome Substring,  找出所有是palindrome的substring

2. cut metal rod 地里有

第一题 Task Manager, 详情可见：http://www.1point3acres.com/bbs/ ... ;highlight=coursera
第二题 计算array里所有元素都不一样时的最小合

1. final discounted price . from: 1point3acres.com/bbs 
2. array game 就是lc 453那个题. 
之前朋友的oa
1.reformating dates
2. segment .

Q1: Task Manager
Q2: Magical Binary Strings

.
