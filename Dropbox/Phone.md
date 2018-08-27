# interview

1. find bytes pattern in a file; 注意输入是byte[]， 我一直以为是char[], 结果面的时候有点手忙脚乱
public boolean find(String fileName, byte[]) {...}

2. find duplicate files；最好按照size filter-> sample hash filter-> full content hash filter -> full content compare这么处理，效率会高一点，而且对大文件也适用。. 留学申请论坛-一亩三分地
public List<Set<String>> findDuplicateFiles(String root) {...}

问了file system，给了File class和他的一些API，比如 isDir(), listDir()
先写了最简单的case，我用了dfs把得到的files都按他们的hash放到map里，用hash function生成key，value是list of files，最后return大于1的list
写完问了如何test code，列举了一些test case，比如symbolic link和different file types

Followup是如何处理大file的问题，我提了generate partial hash chunk by chunk然后join成一个hash。面试官又问还有什么optimization可以做，于是提了可以先check file size。.留学论坛-一亩-三分地
要求写code，这期间面试官追问了几次如何尽量避免compute hash，因为我写的solution都是不管如何先generate hash，但是对于unique file size的大file其实已经可以filter掉了。

最后提供的solution就是先有一个file size to files的map，然后只针对大于1的list里面的files生成hash key。


(1) 如何确定文件类型？遍历目录文件时候，如何确定这是个regular文件，是一个符号链接，是一个特殊的设备文件，还是目录？这是在遍历时候的一个必要步骤。不同的语言和操作系统下可能有不同的方法。如果能够谈到linux下文件系统下有多种文件类型，Linux提供了多种API，比如is_regular_file(), is_block_file等保证确定文件类型，应该是加分项。
(2) 如何确定两个文件相同？所有文件对每个文件跟其它文件逐个byte比较是一个很直接了当的办法。但这样非常不高效。改进的办法应该是对文件产生指纹，比如用MD5，或者SHA256等hash算法来产生指纹。这样在指纹库里面进行匹配查找就容易多了。
(3) 但MD5等hash算法依然存在着冲突的可能性，也就是说，两个文件可能有同样的MD5值。怎么解决这个问题？MD5值相同的情况下，再对两个文件进行逐个byte比较。
(4) 都是大文件怎么优化这个重复文件检测？重复文件意味着两个大文件的每一个块都是相同的。可以将文件进行切块，对每个块生成MD5，这样大文件的比较就是比较每个文件的MD5列表。只有两个MD5列表完全一致，才说明两个文件完全相同。但在比较过程中，只要遇到不同的MD5，就可以跳出检测，节省时间。
(5) 如果文件数量非常多怎么办？这个考虑两点优化：第一点要对MD5指纹库查询并行化，首先是单机内多线程的查询，文件规模巨大的情况下，就是多机分布式查询。这个涉及到system design方面了。第二点是考虑文件自身的特性。文件大小相同才有可能是重复文件。所以在查询时，首先找到系统中其他相同大小的文件，然后只跟这些个文件的MD5列表对比，这样就缩小了查询范围。另外，对于一些小文件，是否可以有特殊的处理。比如空文件。比如文件特别小，那是否可以不产生MD5，直接进行byte比较。这个是开放性的提问，只要保证回答合理就行。
(6) 如果目录里面有soft symbol link怎么办？这个是要考虑是否出现死循环的情况。所以如果提到了第一个问题的解答，在这里就可以表明如果是symbol link就直接跳过。
(7) 如果目录很深怎么办？这个需要考虑，用来存储文件路径名的字符数组是否有溢出的可能性。. more info on 1point3acres
(8) 如果重复文件查询过程中死机了怎么办？第一点可以考虑checkpointing，定期保存进度。这样重新启动查询的时候，不需要从零开始。

// Question: given a file path, find duplicate files in the file system and list their paths in List<List<String>>
// Duplicate files: files with the same content but different file names. 一亩-三分-地，独家发布
// Example:
// root/a 1.txt("abcd") 2.txt("efgh")
// root/c 3.txt("abcd"). 1point3acres
// root/c/d 4.txt("efgh"). From 1point 3acres bbs
// Output: [[root/a/1.txt, root/c/3.txt], [root/a/2.txt, root/c/d/4.txt]]

// Idea: Your solution needs to be tackle a couple of problems: obtaining a list of all the files in the file system (e.g. via DFS), binning the lists into 
// possible matches, repeat via swappable heuristics until your certainty is 100%. (eg size 1st, md5 2nd, byte stream 3rd)
// 1. parse the whole file system-google 1point3acres
//                File class method: boolean isDirectory(), boolean isFile(), String[] list(), long length(), read(buffer, offset, size)
// 2. Binning the lists into possible matches
//                1) hashing: MD5, SHA1, SHA256
//                2) metadata: file size; the first 1kb of data, the second 1kb of data....

 给n个文件， 输出每一对内容相同的文件。 回答大概是， 文件内容不大能放进内存就用trie判重， 大的话， 算md5判重。 被追问了md5的安全缺点。 读文件事 io intensive task， 让我继续优化。 给的方法是， 先判断 文件的metadata， 看size跟filetype， 相同的情况下再算文件整体的哈希值判重
 
我的解法：
我一开始按文件大小做hash
#1 后来改成对文件内容做hash（面试官假设没有冲撞）
#2 follow up，这样太慢了，我提出一个快速检验文件的方法，（例如检查第一个字符，第10个字符）等等（但是这个时候忽略了可以按照文件大小操作的事了）
而且后来没时间了，程序中同时使用两种检查方法（#1 和 #2）的逻辑没说清楚.本文

3. highest minimum sharpness
// Given a 2-d array of "sharpness" values. Find a path from the leftmost column to the rightmost column which has the highest minimum sharpness.. visit 1point3acres for more.
// Output the highest minimum sharpness. Each move can only move to the top right, right or bottom right grid.
// Example: 3*3 matrix
// 5 7 2
// 7 5 8
// 9 1 5
// The path with highest minimum sharpness is 7-->7-->8, because 7 is the highest minimum value in all the paths.
// Idea: Use DP dp[r][c] = min(max(dp[r-1][c-1], dp[r][c-1], dp[r+1][c-1]), grid[r][c]). 

```java```
public int highestMinimumSharpness(int[][] input){
    // corner case ignored
    
    int row = input.length;
    int col = input[0].length;
    
    int[][] dp = new int[row][col];
    
    // init
    for(int i = 0; i < row; i++){
      dp[i][0] = input[i][0]; 
    }
    
    
     for(int j = 1; j < col; j++){
		for(int i = 0; i < row; i++){
            int temp = dp[i][j-1];
            if(i > 0){
                temp = Math.max(temp, dp[i-1][j-1]);
            }
            
            if(i < row - 1){
                temp = Math.max(temp, dp[i-1][j+1]);
            }
          
            dp[i][j] = Math.min(temp, input[i][j]);
        }
    }
    
    // result
    int result = 0;
    for(int i = 0; i < row; i++){
        result = Math.max(result, dp[i][col-1]);
    }
    
    return result;
    
}
```

You’re given an elevation map for a rectangular area of land. The map is represented by 2-D array of numbers where each cell contains the elevation above sea level of the corresponding area of the map.  
You need a path that connects the west edge of the map with the east edge of the map. Starting at the west edge of the map you can only move in single cell steps east, southeast, or northeast.
You need to find how much can the sea level rise before submerging all such paths. Write a function that takes in a 2-D array and returns a single number.-google 1point3acres

例子：
west         east
----------------- north
| 1 | 2 | 3 | 9 |. 
-----------------
| 8 | 6 | 10 | 8 |
-----------------
| 9 | 4| 11| 12|
-----------------  south// sea=6


4. 给一堆Chunk和一个file size,问给定的一堆Chunk能不能组成complete file.
Chunk就是一个左开右闭的区间, 如[0, 4)表示这个chunk包含0, 1, 2, 3这4个byte. 给定的size是这个文件大小.
boolean isCompleteFile(List<Chunk> chunks, int size). From 1point 3acres bbs
例如:
chunks = [[0, 1), [1, 3), [3, 4)]  size = 4   => 结果是true
chunks = [[0, 1), [1, 3), [3, 4)]  size = 5   => 结果是false
chunks = [[0, 1), [2, 3), [3, 4)]  size = 4   => 结果是false

2. generate / release id
也是用hashset就行.. 优先拿released pool里的id，不要求先进先出，而且有可能重复release，所以hashset足矣。followup让我省空间，可以牺牲时间，那就用bitset.. 由于每次找没用过的id需要O(n)，要优化时间的话就用地里有人说过的用线段树之类的代表，最后提了下线段树可以用array表示.. 这轮运气好，面试官是白人妹子也很好..