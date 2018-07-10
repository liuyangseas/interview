04/04 亚麻面经
一个三哥，也是上来寒暄几句,都没告诉我他们组干嘛的，直接上题。 一开始就说有两道题，20分钟一道，想着应该不会太难，然后地狱模式开启

Problem 1. LRU(还不明着说LRU，只说implement cache, 要求put和 get的time是O(1)). 刚开始想的没有给case capacity， cache无限大的话只用hashMap 就能解决, 然后三哥说只用hashMap不对， 因为remove的话不是O(1).  
既然牵扯到remove，那就默认capacity不是无限大了，只好说用hash + doubleLinkedlist. 三哥说嗯，你继续吧。 当时只剩15分钟， 心中一阵凉凉，心想20分钟你给我来个hard难度的题目， 
真是作死呀，于是一遍心中骂娘，一般默默打码，时间一晃而过，介于水平有限，实在不能在15分钟码出一道hard题，三哥无情的说，时间到了，我们下一题。我说这道题再给个5分钟就好，下道题可以用时少点，三哥不说话，直接把网页关了，换上下一题。。。(卒)

Problem 2. JAVAscript题 (见上图)， 看到这儿就觉得凉透了，js只是寥寥的会一点,很长时间不用又忘了。 于是跟三哥说，我虽然是full stack，但最近半年都没用js了，生疏了，能不能换个题，三个冷漠的说，你先试试，我们继续。 
3分钟后三哥见我憋不出来东西， 就说那我们换道题吧，其实到这儿已经觉得凉透了, 不过还是说好吧。接着三个问我们来道machine learning的题目吧(擦， 当我是三项全能嘛。。。) 
我说我简历中没有machine learning的项目， 我只懂一些基础的concept， 跟他扯了knn，k-means，neural network, 然而三哥立马打断，说好吧， 我已经了解你所有的信息，等recruiter消息吧。
60分钟只面了42分钟就结束了，必挂无疑。。。move on了。。。

题外话：这周面试的两次都是三哥三姐，之前onsite四轮三轮是阿三，我们国人面试都去哪了。。。求站出来啊！！！

问题非常简单但却很考验基础，就是让我自己来建一个hashmap，需要实现get和put 的功能
然后用系统默认的hashcode() % 16得到key的位置。
之后用类似linkedlist的数据结构来把每一个entry串联起来。
第一个follow up 需要我实现delete功能
第二个follow up是让我的hashmap能够更generic,因为我先直接用string来作为key 和value，所以我就用了java的map<K k, V v>来作为entry
最后一个是让我实现getrandom value，我问能不能直接用java内置的random api来做，他说可以，我就直接写了一个random函数来去1-16里面随机的数。
后来他说不能返回null，必须要返回一个有效的值，所以我就写了一个while loop 来检查是否null，不是null就返回那个key值，这样就能保证得到一个有效的数。

import java.util.ArrayList;
import java.util.List;

public class MyHashMap {

	class Container{
		Object key;
		Object value;
		public void insert(Object k, Object v){
			this.key=k;
			this.value=v;
		}
	}
	
	private Container c;
	private List<Container> recordList;
	
	public MyHashMap(){
		
		this.recordList=new ArrayList<Container>();
	}
	
	public void put(Object k, Object v){
		this.c=new Container();
		c.insert(k, v);
		//check for the same key before adding
		for(int i=0; i<recordList.size(); i++){
			Container c1=recordList.get(i);
			if(c1.key.equals(k)){
				//remove the existing object
				recordList.remove(i);
				break;
			}
		}
		recordList.add(c);
	}
	
	public Object get(Object k){
		for(int i=0; i<this.recordList.size(); i++){
			Container con = recordList.get(i);
			//System.out.println("k.toString():"+k.toString()+"con.key.toString()"+con.key.toString());
			if (k.toString()==con.key.toString()) {
				
				return con.value;
			}
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		MyHashMap hm = new MyHashMap();
		hm.put("1", "1");
		hm.put("2", "2");
		hm.put("3", "3");
		System.out.println(hm.get("3"));
		hm.put("3", "4");
		
		System.out.println(hm.get("1"));
		System.out.println(hm.get("3"));
		System.out.println(hm.get("8"));
	}

}

// 高级版： https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/


*** Binary Tree ****
leetcode 314

find longest repeat substring

Leetcode 151

138. Copy List with Random Pointer
102. Binary Tree Level Order Traversal


