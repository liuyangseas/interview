package Airbnb;
import java.util.*;
public class OrderMenu {
	/*
	 * Now when we know the problem with equality operator, lets solve it.
	 *  Using programming, we cannot change the way these floating point numbers are stored or computed. 
	 *  So we have to adapt a solution where we agree that a determine the differences in both values which we can tolerate 
	 *  and still consider the numbers equal. This agreed upon difference in values is called the threshold or epsilon.
	 *  So now to use ‘threshold based floating point comparison‘, 
	 *  we can use the Math.abs() method to compute a difference between the two numbers and compare the difference to a threshold value.
	 *  本质是combination sum，但要注意价格为double，不能直接用 == 比较
     * 1. 转成cents，即全部乘以100，这样可以作为int处理
     * 2. 设定精度epsilon，两个数相差小于epsilon就认为是相等
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] price = {1.2, 1.11, 3.2, 4.4, 4.6, 4.9, 5.3, 8.9, 5.5, 5.7};
		double target = 10.1;
		System.out.println(getCombo(price, target));
	}

	// version 1 double compare  我们还是用浮点数精度的方法来做
	static double acc = 0.001;
	public static List<List<Double>> getCombo(double[] price, double target) {
		List<List<Double>> res = new ArrayList<>();
		Arrays.sort(price);

		helper(res, price, target, 0.0, new ArrayList<>(), 0);
		return res;
	}
	
	public static void helper(List<List<Double>> rs, double[] price, double target, double cur, List<Double> temp, int pos) {
		if (Math.abs(cur - target) < acc) {
			rs.add(new ArrayList<>(temp));
			return;
		}
		
		for (int i = pos; i < price.length; i++) {
			// if contains duplicate, if(i > pos && price[i] == price[i-1])   continue;
			if (cur + price[i] > target + acc) {
				break;
			}
			
			temp.add(price[i]);
			helper(rs, price, target, cur + price[i], temp, i + 1);
			temp.remove(temp.size() - 1);
		}
	}
	
	// 这里我们乘以100转化为整数来做
	 public List<List<Double>> getCombos(double[] prices, double target) {
		    List<List<Double>> res = new ArrayList<>();
		    if (prices == null || prices.length == 0 || target <= 0) {
		      return res;
		    }

		    int centsTarget = (int)Math.round(target * 100);
		    Arrays.sort(prices);
		    int[] centsPrices = new int[prices.length];
		    for (int i = 0; i < prices.length; i++) {
		      centsPrices[i] = (int)Math.round(prices[i] * 100);
		    }

		    search(res, centsPrices, 0, centsTarget, new ArrayList<>(), prices);
		    return res;
		  }

		  private void search(List<List<Double>> res, int[] centsPrices, int start, int centsTarget,List<Double> curCombo, double[] prices) {
		    if (centsTarget == 0) {
		      res.add(new ArrayList<>(curCombo));
		      return;
		    }

		    for (int i = start; i < centsPrices.length; i++) {
		    	// if contains duplicate,这里假设我们有重复的数字
		      if (i > start && centsPrices[i] == centsPrices[i - 1]) {
		        continue;
		      }
		      
		      if (centsPrices[i] > centsTarget) {
		        break;
		      }
		      
		      curCombo.add(prices[i]);
		      search(res, centsPrices, i + 1, centsTarget - centsPrices[i], curCombo, prices);
		      curCombo.remove(curCombo.size() - 1);
		    }
		  }
}
