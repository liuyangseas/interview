Class bussinessInfo{
       int id;
       List<String> category;
}
Class Solution{
	Public List<Integer> find(List<bussinessInfo> s, String A, String B){
		List<Integer> res = new ArrayList<Integer>();
		for(int i = 0; i < s.size(); i++){
			if(s.get(i).category.contains(A) && s.get(i).category.contains(B)){
				res.add(s.get(i).id);
			}
		}
		return res;
	}
}
		


