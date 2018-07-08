public List<String> TopColor(ArrayList<ArrayList<String>> color){
		if(color == null) return null;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i < color.size(); i++){
			for(int j = 0; j < color.get(i).size(); j++){
				if(map.contains(color.get(i).get(j)){
					map.put(color.get(i).get(j), map.get(color.get(i).get(j))+1);
				}else{
					map.put(color.get(i).get(j),1);
				}
			}
		}
		LinkedList<String> res = new LinkedList<String>();
		int max = Integer.MIN_VALUE;
		for(String key : map.keySet()){
			if(map.get(key) >= max){
				if(map.get(key) > max){
					res = new LinkedList<String>();
				}
			res.add(key);
			max = map.get(key);
			}
		}
		if(res.size() > 1) Collections.sort(res);
		return res;
}
