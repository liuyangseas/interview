You are given an array of csv strings indicating search result of listings.
Each has a host_id, listing_id, score, and city. Initially, results are sorted by highest score


We’d like to display these search results on a web page. 
Write a function that returns groups of listings to be displayed on each page.
However, note that a given host may have several listings that show up in the results.
Reorder the list so that a host shows up at most once on a page if possible, but otherwise preserves the ordering
Your program should return the new array and print out the results in blocks representing the pages

You’re given an array of CSV strings representing search results. Results are sorted 
by a score initially. A given host may have several listings that show up in these results.
Suppose we want to show 12 results per page, but we don’t want the same host to dominate 
the results. Write a function that will reorder the list so that a host shows up at most 
once on a page if possible, but otherwise preserves the ordering. Your program should return 
the new array and print out the results in blocks representing the pages.

Input:
*  An array of csv strings, with sort score
*  number of results per page

  Format:
  host_id, listing_id, score, city,


  String[] inputCsvArray = {
    "1,28,300.1,San Francisco",
    "4,5,209.1,San Francisco",
    "20,7,208.1,San Francisco",
    "23,8,207.1,San Francisco",
    "16,10,206.1,Oakland",
    "1,16,205.1,San Francisco",
    "6,29,204.1,San Francisco",
    "7,20,203.1,San Francisco",
    "8,21,202.1,San Francisco",
    "2,18,201.1,San Francisco",
    "2,30,200.1,San Francisco",
    "15,27,109.1,Oakland",
    "10,13,108.1,Oakland",
    "11,26,107.1,Oakland",
    "12,9,106.1,Oakland",
    "13,1,105.1,Oakland",
    "22,17,104.1,Oakland",
    "1,2,103.1,Oakland",
    "28,24,102.1,Oakland",
    "18,14,11.1,San Jose",
    "6,25,10.1,Oakland",
    "19,15,9.1,San Jose",
    "3,19,8.1,San Jose",
    "3,11,7.1,Oakland",
    "27,12,6.1,Oakland",
    "1,3,5.1,Oakland",
    "25,4,4.1,San Jose",
    "5,6,3.1,San Jose",
    "29,22,2.1,San Jose",
    "30,23,1.1,San Jose"
  };
  
  sample_output = [
    "1,28,300.1,San Francisco",
    "4,5,209.1,San Francisco",
    "20,7,208.1,San Francisco",
    "23,8,207.1,San Francisco",
    "16,10,206.1,Oakland",
    "6,29,204.1,San Francisco",
    "7,20,203.1,San Francisco",
    "8,21,202.1,San Francisco",
    "2,18,201.1,San Francisco",
    "15,27,109.1,Oakland",
    "10,13,108.1,Oakland",
    "11,26,107.1,Oakland",
    "1,16,205.1,San Francisco",
    "2,30,200.1,San Francisco",
    "12,9,106.1,Oakland",
    "13,1,105.1,Oakland",
    "22,17,104.1,Oakland",
    "28,24,102.1,Oakland",
    "18,14,11.1,San Jose",
    "6,25,10.1,Oakland",
    "19,15,9.1,San Jose",
    "3,19,8.1,San Jose",
    "27,12,6.1,Oakland",
    "25,4,4.1,San Jose",
    "1,2,103.1,Oakland",
    "3,11,7.1,Oakland",
    "1,3,5.1,Oakland",
    "5,6,3.1,San Jose",
    "29,22,2.1,San Jose",
    "30,23,1.1,San Jose"
  ]


  public String[] reorderListings(String[] inputCsvArray, int maxListingsPerPage) {
    ...
  }


// 需要实现不删除原来的input
package Airbnb;
import java.util.*;
 
public class PageSplit {
  public static void displayPages(List<String> input) {
    if (input == null || input.size() == 0) {
      return;
    }
     
    Set<String> visited = new HashSet<>();
    Iterator<String> iterator = input.iterator();
    int pageNum = 1;
     
    System.out.println("Page " + pageNum);
     
    while (iterator.hasNext()) {
      String curr = iterator.next();
      String hostId = curr.split(",")[0];
      if (!visited.contains(hostId)) {
        System.out.println(curr);
        visited.add(hostId);
        iterator.remove();
      }  
      // New page
      if (visited.size() == 12 || (!iterator.hasNext())) {
        visited.clear();
        iterator = input.iterator();
        if (!input.isEmpty()) {
          pageNum++;
          System.out.println("Page " + pageNum);
        }
      }
    }
  }
  
  public static void displayPagesFull(List<String> input) {
	    if (input == null || input.size() == 0) {
	      return;
	    }
	     
	    Set<String> visited = new HashSet<>();
	    Iterator<String> iterator = input.iterator();
	    int pageNum = 1;
	     
	    System.out.println("Page " + pageNum);
	     
	    while (iterator.hasNext()) {
	      String curr = iterator.next();
	      String hostId = curr.split(",")[0];
	      if (!visited.contains(hostId)) {
	        System.out.println(curr);
	        visited.add(hostId);
	        iterator.remove();
	      }  
	      // New page
	      if (visited.size() == 12) {
	        visited.clear();
	        iterator = input.iterator();
	        if (!input.isEmpty()) {
	          pageNum++;
	          System.out.println("Page " + pageNum);
	        }
	      } else if (!iterator.hasNext()) {
		      visited.clear();
		      iterator = input.iterator();
	      }
	    }
	  }
  public static List<String> displayPages3(List<String> input) {
	    if (input == null || input.size() == 0) {
	      return new ArrayList<>();
	    }
	    List<String> rs = new ArrayList<>();
	    Set<String> visited = new HashSet<>();
	    Iterator<String> iterator = input.iterator();
	    int pageNum = 1;
	     
	    System.out.println("Page " + pageNum);
	     
	    while (iterator.hasNext()) {
	      String curr = iterator.next();
	      String hostId = curr.split(",")[0];
	      if (!visited.contains(hostId)) {
	        visited.add(hostId);
	        iterator.remove();
	        rs.add(curr);
	        System.out.println(curr);
	      }  
	      // New page
	      if (visited.size() == 12 || (!iterator.hasNext())) {
	        visited.clear();
	        // 这个时候因为我们的iterator的指针走到了最后，我们只需要回到第一个就好了，因为前面还有很多跳过的东西
	        iterator = input.iterator();
	      }
	    }
	    return rs;
	  }
  static class Node{
	  Node next;
	  Node tail;
	  String value;
	  double score;
	  public Node(String value, double score) {
		  this.value = value;
		  this.score = score;
	  }
  }
  
  public static List<String> best(List<String> input) {
	  Map<String, Node> map = new HashMap<>();
	  for (String s : input) {
		  String[] info = s.split(",");
		  String id = info[0];
		  double score = Double.valueOf(info[2]);
		  if (!map.containsKey(id)) {
			  Node head = new Node(s, score);
			  head.tail = head;
			  map.put(id, head);
		  } else {
			  Node head = map.get(id);
			  Node temp = new Node(s, score);
			  head.tail.next = temp;
			  head.tail = temp;
		  }
	  }
	  Queue<Node> cur = new PriorityQueue<>((n1 ,n2) -> (Double.compare(n2.score, n1.score)));
	  Queue<Node> backup = new PriorityQueue<>((n1 ,n2) -> (Double.compare(n2.score, n1.score)));
	  for (Node n : map.values()) {
		  cur.offer(n);
	  }
	  List<String> rs = new ArrayList<>();
	  int count = 0;
	  while (rs.size() < input.size()) {
		  Node temp = cur.poll();
		  rs.add(temp.value);
		  //System.out.println(temp.value);
		  
		  temp = temp.next;
		  if (temp != null) {
			  backup.offer(temp);
		  }
		  count++;
		  if (count == 12) {
			  while (backup.size() > 0) {
				  cur.offer(backup.poll());
				  //System.out.println(1);
			  }
			  count = 0;
			  continue;
		  }
		  if (cur.size() == 0) {
			  Queue<Node> t = new PriorityQueue<>((n1 ,n2) -> (Double.compare(n2.score, n1.score)));
			  t = cur;
			  cur = backup;
			  backup = t;
		  } 
	  }
	  return rs;
  }
  
           
  public static void main(String[] args) {
    String[] strs = new String[]{
      "1,28,300.1,SanFrancisco",
      "4,5,209.1,SanFrancisco",
      "20,7,208.1,SanFrancisco",
      "23,8,207.1,SanFrancisco",
      "16,10,206.1,Oakland",
      "1,16,205.1,SanFrancisco",
      "6,29,204.1,SanFrancisco",
      "7,20,203.1,SanFrancisco",
      "8,21,202.1,SanFrancisco",
      "2,18,201.1,SanFrancisco",
      "2,30,200.1,SanFrancisco",
      "15,27,109.1,Oakland",
      "10,13,108.1,Oakland",
      "11,26,107.1,Oakland",
      "12,9,106.1,Oakland",
      "13,1,105.1,Oakland",
      "22,17,104.1,Oakland",
      "1,2,103.1,Oakland",
      "28,24,102.1,Oakland",
      "18,14,11.1,SanJose",
      "6,25,10.1,Oakland",
      "19,15,9.1,SanJose",
      "3,19,8.1,SanJose",
      "3,11,7.1,Oakland",
      "27,12,6.1,Oakland",
      "1,3,5.1,Oakland",
      "25,4,4.1,SanJose",
      "5,6,3.1,SanJose",
      "29,22,2.1,SanJose",
      "30,23,1.1,SanJose"
    };
    List<String> input = new ArrayList<>(Arrays.asList(strs));
    List<String> input2 = new ArrayList<>(Arrays.asList(strs));
    //displayPages(input);
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("=============================");
    System.out.println();
    System.out.println();
    System.out.println();
    //displayPagesFull(input2);
    
    System.out.println("=============================");
    //System.out.println(input);
    List<String> rs = displayPages3(input);
    List<String> r = best(input2);
    for (int i = 0; i < rs.size(); i++) {
    	System.out.println(rs.get(i));
    	if ((i + 1) % 12 == 0) {
    		System.out.println("new page:");
    	}
    }
    

    for (int i = 0; i < r.size(); i++) {
    	if (!rs.get(i).equals(r.get(i))) {
    		System.out.println("fail");
    	}
    }
    System.out.println("success");
  }       
}
