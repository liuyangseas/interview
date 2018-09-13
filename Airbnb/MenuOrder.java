import java.util.*;

public class MenuOrder {

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
