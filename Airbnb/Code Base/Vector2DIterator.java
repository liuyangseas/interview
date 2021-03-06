package Airbnb;
import java.util.Iterator;
import java.util.List;
import java.util.*;

/**
 * Given an array of arrays, implement an iterator class to allow the client to traverse
 * and remove elements in the array list.
 * This iterator should provide three public class member functions:
 *
 * boolean hasNext()
 * return true if there is another element in the set
 *
 * int next()
 * return the value of the next element in the array
 *
 * void remove()
 * remove the last element returned by the iterator.
 * That is, remove the element that the previous next() returned
 * This method can be called only once per call to next(), otherwise an exception will be thrown.
 */

public class Vector {
		  public static void main(String[] args) {
		    List<List<Integer>> matrix = new ArrayList<>();
		    List<Integer> row1 = new ArrayList<>();
		    row1.add(1);
		    row1.add(2);
		    matrix.add(row1);
		    List<Integer> row2 = new ArrayList<>();
		    row2.add(3);
		    matrix.add(row2);
		    List<Integer> row3 = new ArrayList<>();
		    matrix.add(row3);
		    List<Integer> row4 = new ArrayList<>();
		    row4.add(4);
		    row4.add(5);
		    row4.add(6);
		    matrix.add(row4);

		    Vector2DIterator iter = new Vector2DIterator(matrix);
		    System.out.println(iter.hasNext());
		    System.out.println(iter.next());
		    iter.remove();
		    System.out.println(iter.hasNext());
		    System.out.println(iter.next());
		    iter.remove();
		    System.out.println(iter.next());
		    System.out.println(iter.next());
		    iter.remove();
		    System.out.println(iter.hasNext());
		    System.out.println(iter.next());
		    iter.remove();
		    System.out.println(iter.next());
		    System.out.println(iter.hasNext());
		    iter.remove();
		  }
}

// 这是我自己的代码，利用Vector那道题目的逻辑做出来的
class Vector2DIterator implements Iterator<Integer> {
  private Integer cur;
  private int outerIndex;
  private int innerIndex;
  private int lastOuterIndex;
  private int lastInnerIndex;
  private List<List<Integer>> vector;

  public Vector2DIterator(List<List<Integer>> vec2d) {
    this.vector = vec2d;
    this.outerIndex = 0;
    this.innerIndex = 0;
    this.lastOuterIndex = -1;
    this.lastInnerIndex = -1;
  }

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		 while(outerIndex < vector.size()){
	            if(innerIndex < vector.get(outerIndex).size())
	                return true;
	            else{
	            	outerIndex++;
	            	innerIndex = 0;
	            }
	      }
	        
	      return false;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		if(!hasNext()) {
			return -1;
		}
		
		int temp = vector.get(outerIndex).get(innerIndex);
		lastOuterIndex = outerIndex;
		lastInnerIndex = innerIndex;
		
		innerIndex++;
		return temp;
   }
	
	 @Override
	  public void remove() {
	    if (lastOuterIndex == -1 && lastInnerIndex == -1) {
	      throw new IllegalStateException("remove() can be called only once per call to next()");
	    }
	
	    vector.get(lastOuterIndex).remove(lastInnerIndex);
	    if (lastOuterIndex == outerIndex) {
	      innerIndex--;
	      
	      if(vector.get(lastOuterIndex).size() == 0) {
	    	  outerIndex++;
	      }
	    }
	    
	    lastOuterIndex = -1;
	    lastInnerIndex = -1;
	  }
}

//这是网上的代码
class Vector2DIterators implements Iterator<Integer> {
  private Integer cur;
  private int outerIndex;
  private int innerIndex;
  private int lastOuterIndex;
  private int lastInnerIndex;
  private List<List<Integer>> vector;

  public Vector2DIterators(List<List<Integer>> vec2d) {
    this.vector = vec2d;
    this.outerIndex = 0;
    this.innerIndex = 0;
    this.lastOuterIndex = -1;
    this.lastInnerIndex = -1;
    searchNext();
  }

  @Override
  public Integer next() {
    if (!hasNext()) {
      return null;
    }
    
    int temp = cur;
    lastOuterIndex = outerIndex;
    lastInnerIndex = innerIndex;
    innerIndex++;
    searchNext();
    return temp;
  }

  @Override
  public boolean hasNext() {
    return cur != null;
  }

  @Override
  public void remove() {
    if (lastOuterIndex == -1 && lastInnerIndex == -1) {
      throw new IllegalStateException("remove() can be called only once per call to next()");
    }

    vector.get(lastOuterIndex).remove(lastInnerIndex);
    if (lastOuterIndex == outerIndex) {
      innerIndex--;
    }
    
    lastOuterIndex = -1;
    lastInnerIndex = -1;
  }

  // 每一次相当于refresh一次，把目前的val到位
  private void searchNext() {
    if (outerIndex < vector.size()) {
      if (innerIndex < vector.get(outerIndex).size()) {
        cur = vector.get(outerIndex).get(innerIndex);
      } else {
        outerIndex++;
        innerIndex = 0;
        searchNext();
      }
    } else {
      cur = null;
    }
  }
}



// Michael version
class TwoDimensionArrayIterator2 {
    private int row;
    private int col;
    private int preRow;
    private int preCol;
    private List<List<Integer>> buffer;
    boolean canRemove = false;
    
    public TwoDimensionArrayIterator2(List<List<Integer>> vec2d) {
        row = 0;
        col = 0;
        preRow = -1;
        preCol = -1;
        buffer = vec2d;
        while (row < buffer.size() && buffer.get(row).size() == 0) {
            row++;
        }
    }

    public Integer next() {
    	if (row >= buffer.size()) {
    		return null;
    	}
    	preRow = row;
    	preCol = col;
        int temp = buffer.get(row).get(col++);
        if (col >= buffer.get(row).size()) {
            row++;
            while (row < buffer.size() && buffer.get(row).size() == 0) {
                row++;
            }
            col = 0;
        }
        canRemove = true;
        return temp;
    }

    public boolean hasNext() {
        return row < buffer.size() && col < buffer.get(row).size();
    }
    
    public void remove() throws Exception {
    	if (!canRemove) {
			throw new Exception("You must call next() before remove()");
    	}
    	buffer.get(preRow).remove(preCol);
    	if (row == preRow) {
    		col = preCol;
    	}
    	canRemove = false;
    }
}


