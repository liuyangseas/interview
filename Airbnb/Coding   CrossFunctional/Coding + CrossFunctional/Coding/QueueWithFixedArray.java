/**
 * Implement a queue with fixed-size arrays (or arraylist)
 *
 * Solution
 * Use the last position of the array to store the next array
 
	 // Build a queue class with the enqueue and dequeue methods. The queue can store an *UNLIMITED* number of elements but you are limited to using arrays that can store up to 5 elements max.

	// 1. Dequeue 1, will throw
	// 2. Enqueue 1, Dequeue 1
	// 3. Enqueue 7, Dequeue 7
	// 4. Enqueue 7, Dequeue 8, will throw
	// 5. Enqueue 4, Dequeue 4
	// 6. Enqueue 5, Dequeue 5
	// 7. Enqueue 4, Dequeue 4, Enqueue 1, Dequeue 1
	// 8. Enqueue 5, Dequeue 5, Enqueue 3, Dequeue 3
	// 9. Enqueue 7, Dequeue 3, Enqueue 3, Dequeue 8, will throw
	. 1point3acres

Enqueue 7, Dequeue 7 -> 意思是装7个然后 poll 7个， 那么 第八次dequeue 就会 throw exception

 */

import java.util.ArrayList;
import java.util.List;

public class QueueWithFixedArray {
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

//class Main {
//  public static void main(String[] args) {
//    QueueWithFixedArray queue = new QueueWithFixedArray(5);
//    System.out.println(queue.poll());
//    queue.offer(1);
//    queue.offer(2);
//    System.out.println(queue.poll());
//    queue.offer(3);
//    queue.offer(4);
//    queue.offer(5);
//    queue.offer(6);
//    System.out.println(queue.poll());
//    System.out.println(queue.poll());
//    System.out.println(queue.poll());
//    System.out.println(queue.poll());
//    System.out.println(queue.poll());
//  }
//}
