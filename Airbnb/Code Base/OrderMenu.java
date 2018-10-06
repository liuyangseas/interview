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
		
		HashMap<String, Double> test = new HashMap<>();
		
		test.put("a",1.2);
		test.put("b",1.11);
		test.put("c",3.2);
		test.put("d",4.4);
		test.put("e",4.6);
		test.put("f",4.9);
		test.put("g",5.3);
		test.put("h",8.9);
		test.put("i",5.5);
		test.put("j",5.7);
		System.out.println(getCombo2(test, 10.1));
	}
	
	public static class Dish{
		String name;
		Double price;
		
		public Dish(String val, Double num) {
			this.name = val;
			this.price = num;
		}
	}
	
	// 如果说我们的输入是一个HashMap，而不是我们的基本的那个array
	public static List<ArrayList<String>> getCombo2(HashMap<String, Double> map, double target){
		// convert from HashMap to Array
		List<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		List<Dish> list = new ArrayList<>();
		Iterator it = map.entrySet().iterator();
		for (Map.Entry<String, Double> entry : map.entrySet()) {
		    String key = entry.getKey();
		    Double value = entry.getValue();
		    Dish dish = new Dish(key,value);
		    list.add(dish);
		}
		
		Collections.sort(list, new Comparator<Dish>() {
	        public int compare(Dish o1, Dish o2) {
	            // compare two instance of `Score` and return `int` as result.
	            return (int) (o1.price - o2.price);
	        }
	    });
		dfs(res, list,target,0.0, new ArrayList<>(), 0);
		return res;
	}
	
	public static void dfs(List<ArrayList<String>> res, List<Dish> list, double target, double cur, ArrayList<String> temp, int pos) {
		if (Math.abs(cur - target) < acc) {
			res.add(new ArrayList<>(temp));
			return;
		}
		
		for (int i = pos; i < list.size(); i++) {
			// if contains duplicate, if(i > pos && price[i] == price[i-1])   continue;
			if (cur + list.get(i).price > target + acc) {
				break;
			}
			
			temp.add(list.get(i).name);
			dfs(res, list, target, cur + list.get(i).price, temp, i + 1);
			temp.remove(temp.size() - 1);
		}
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


// 网上的代码
class MenuOrder {

	public List<List<Double>> getOrder(double[] prices, double target)
	{
		List<List<Double>> res = new ArrayList();
		if (prices == null || prices.length == 0 || target <= 0)
			return res;
		
		Arrays.sort(prices);
		
		search(prices, 0, new ArrayList(), target, res);
		return res;
		
	}
	
	public void search(double[] prices, int start, ArrayList<Double> line, double target, List<List<Double>> res)
	{
		if (target < 0.01)
		{
			res.add(new ArrayList(line));
			return;
		}
		
		for(int i = start; i < prices.length; i++)
		{
			if (prices[i] > target)
				break;
			line.add(prices[i]);
			search(prices, i, line, target - prices[i], res);
			line.remove(line.size() -1);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MenuOrder mo = new MenuOrder();
		double[] prices = new double[1];
		prices[0] = 2.15;
		
		List<List<Double>> res = mo.getOrder(prices, 15.05);
		System.out.println(res);
	}

}
