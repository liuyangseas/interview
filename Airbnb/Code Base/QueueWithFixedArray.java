package Airbnb;

/**
 * Implement a queue with fixed-size arrays (or arraylist)
 *
 * Solution
 * Use the last position of the array to store the next array
 */

import java.util.ArrayList;
import java.util.List;

public class QueueWithFixedArray {

  public static void main(String[] args) {
		// TODO Auto-generated method stub
	    QueueWithFixedArray queue = new QueueWithFixedArray(5);
	    System.out.println(queue.poll());
	    queue.offer(1);
	    queue.offer(2);
	    System.out.println(queue.poll());
	    queue.offer(3);
	    queue.offer(4);
	    queue.offer(5);
	    queue.offer(6);
	    //System.out.println(queue.poll());
	    //System.out.println(queue.poll());
	    //System.out.println(queue.poll());
	   // System.out.println(queue.poll());
	   // System.out.println(queue.poll());
	    
	    ArQueue q = new ArQueue(2);
		for (int i = 1; i <= 20; i++) {
			q.push(i);
		}
		Integer l = q.pop();
		while (l != null) {
			System.out.println(l);
			l = q.pop();
		}
  }

  private int fixedSize;
  private int count;
  private int head;
  private int tail;
  private List<Object> headList;
  private List<Object> tailList;

  public QueueWithFixedArray(int fixedSize) {
    this.fixedSize = fixedSize;
    this.count = 0;
    this.head = 0;
    this.tail = 0;
    this.headList = new ArrayList<>();
    this.tailList = this.headList;
  }

  public void offer(int num) {
    if (tail == fixedSize - 1) {
      List<Object> newList = new ArrayList<>();
      newList.add(num);
      tailList.add(newList);
      tailList = (List<Object>)tailList.get(tail);
      tail = 0;
    } else {
      tailList.add(num);
    }
    count++;
    tail++;
  }

  public Integer poll() {
    if (count == 0) {
      return null;
    }

    int num = (int)headList.get(head);
    head++;
    count--;

    if (head == fixedSize - 1) {
      List<Object> newList = (List<Object>)headList.get(head);
      headList.clear();
      headList = newList;
      head = 0;
    }

    return num;
  }

  public int size() {
    return count;
  }
}

class ArQueue {
	class ListNode {
		ListNode next;
		ListNode pre;
		int[] buffer;
		int first;
		int last;
		
		public ListNode(int size) {
			buffer = new int[size];
			first = 0;
			last = -1;
		}
		public ListNode() {
			
		}
	}
	
	ListNode head;
	ListNode tail;
	int size;
	public ArQueue(int size) {
		head = new ListNode();
		tail = new ListNode();
		this.size = size;
		head.next = tail;
		tail.pre = head;
	}
	
	public Integer pop() {
		if (head.next == tail) {
			return null;
		}
		
		ListNode cur = head.next;
		Integer item = cur.buffer[cur.first];
		cur.first++;
		if (cur.first == size) {
			head.next = cur.next;
			cur.next.pre = head;
			cur.next = null;
			cur.pre = null;
		}
		
		return item;
	}
	
	public void push(int n) {
		ListNode cur;
		if (tail.pre == head || tail.pre.last == size - 1) {
			cur = new ListNode(size);
			ListNode temp = tail.pre;
			cur.next = tail;
			tail.pre = cur;
			temp.next = cur;
			cur.pre = temp;
		} else {
			cur = tail.pre;
		}
		
		cur.buffer[++cur.last] = n;
	}	
}
