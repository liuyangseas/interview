你的GPA是X.XX吗
介绍一个你的recent project
你认为5年后的你会是怎样
你为什么申请epic
你需要H1B吗
如果EPIC给你offer你最早能何时工作
每个病人有很多indicator, 这些indicator能决定这个病人心脏病发作的概率.
现在来了一个病人, 什么indicator都不知道, 预测他的心脏病发作的概率.
设计一个数据结构存储这些indicators
in Memory / in Disk          12 array    

1.给你一个整数, 判断这个整数是否满足条件”它的某个整数倍是自己的anagram”.
2. 两个字符串, 也许不互为anagram; 如果它们互为anagram, 输出按以下规则将字符串1转化为字符串2的所有步骤. 规则: 只能互换相邻的两个字符.
        例子: 输入abc, cba
        输出: abc
            Acb
            Cab
            Cba
        不要求互换次数最小.
3. 忘了
4, 一个打字员,每个手指各有一组可能按下的按键; 有的手指什么都不按. 输入一个字符串表示一个打字员动手指的次序; 输出这个打字员所有可能打出的内容. (似乎每个手指按键无交集)







Onsite

分presentation和case study
Case study: 医生给病人开抗凝血药, 每个病人自己都有一个目标用药量, 低了会血栓高了会伤口不愈合. 药片只有几种不同的剂量(如1.5, 2.5, 3, 4…)为了达到”一段时间内用药平均值为目标用量”, 医生会在一个时间区间(几天)内设定一个用药sequence: 如一周7天依次3,4,4,3,4,3,4. 问题分从前端和从后端解决两个选项. 我选的后端
问题1: 医生需要能够为病人设定服药sequence. 如何存储这些sequence? 设计数据结构. (同一个病人随治疗时期不同, sequence会有调整, 所以有时间段不同下的多个sequence)
追问1: 刚才的是在最后加一个sequence, 那如果医生要查询病人在过去某个日期的药量, 应该如何设计数据结构和设计查询方法?
追问2: 如果医生需要临时修改几天的用药量, 在这几天后又恢复之前的用药计划, 应该如何设计数据结构和修改的function?

问题2: 已知病人目标用药量, 以及药片的各种size; 设计function, 生成sequence. (讲解算法和whiteboard, 一开始没让我whiteboard, 讲完算法开始whiteboard, 写了几行之后给的时间快到了,就不用写了)

Presentation:
介绍一个你做过的project, 会被问到各种细节. 比如我说的location inference of social network users, 就被问到为什么采用最终的算法(design decisions), 这个算法优势在哪里(quick/high accuracy), 有没有counterexample, 用时多久, 是否按时 成, 每个阶段干了什么和用时, 难点在哪里
追问: 如何改进, 比如如果数据集太大计算机memory放不下全部dataset时怎么办
然后时间还没用完, 面试官会介绍自己的工作, 聊一聊熟悉的语言和IDE, 比较不同语言和IDE, 让你问他问题, 等等(Q: epic用的数据库cache有什么优势? A: 单个数据存取快, based on B+ tree)









google    
Snap

--Snapshot
--Snapchat

input Snap
output  true/false

manually input Snapshot, Snapchat

------

input(ABC)
input(Snapchat)
check(Google)  ---   return false
input(Snapshot)
check(Snap)  ----   return true

<M M>
Snap

Snap

leave ->word
parent nodes of leave-> prefix of word

true/false A B C D …

input ABC

root
->new node(A) false
->new node(B) false
->new node(C) true












class Node{
    char Character;
    public boolean isWord;
    Node[] next = new Node[26];
Node(){
    
}
Node(char c){
    Character = c;
}
}

class Prefix{
Node root = new Node();
abc

abd

public int charToIndex(char c){
    
}

public void insert(String s){
    if (s==null) return; 
    Node prev = root;
    Node curr;
for (int i=0; i<s.length; i++){
        char c = s[i];
        //curr = prev.next.get(c);
        
        if(curr==null){
            curr = new Node(c);
            prev.next.put(c, curr);
}
        prev = curr;    
}
prev.isWord = true;
}

public boolean search(String prefix){

Node prev = root;

for (int i=0; i<prefix.length;i++){
    char c =prefix[i];
    Node curr = prev.next.get(c);
    if (curr==null) return false;
    prev = curr;
} //change to for
    return prev.isWord; 
}

public boolean searchPrefix(String prefix){
if (prefix==null) return false;
Node prev = root;

for (int i=0; i<prefix.length;i++){
    char c =prefix[i];
    Node curr = prev.next.get(c);
    if (curr==null) return false;
    prev = curr;
}
return true;
}



























class TrieNode {
    public char val;
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
    TrieNode(char c){
       TrieNode node = new TrieNode();
       node.val = c;
    }
}

public class Trie {
    private TrieNode root;
    public Trie() {
       root = new TrieNode();
       root.val = ' ';
    }

    public void insert(String word) {
       TrieNode ws = root;
       for(int i = 0; i < word.length(); i++){
           char c = word.charAt(i);
           if(ws.children[c - 'a'] == null){
               ws.children[c - 'a'] = new TrieNode(c);
           }
           ws = ws.children[c - 'a'];
       }
       ws.isWord = true;
    }

    public boolean search(String word) {
       TrieNode ws = root;
       for(int i = 0; i < word.length(); i++){
           char c = word.charAt(i);
           if(ws.children[c - 'a'] == null) return false;
           ws = ws.children[c - 'a'];
       }
       return ws.isWord;
    }

    public boolean startsWith(String prefix) {
       TrieNode ws = root;
       for(int i = 0; i < prefix.length(); i++){
           char c = prefix.charAt(i);
           if(ws.children[c - 'a'] == null) return false;
           ws = ws.children[c - 'a'];
       }
       return true;
    }
变形：  :)   :(  ::)  a  b   c    :::)(:)(::()(   return abc
google search 智能搜索   
