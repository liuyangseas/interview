int m = list1.size(), n = list2.size();
List<Business> res = new List<Business>(m+n);
int i = 0, j = 0;
while(i < m && j < n){
	if(list1.get(i).value > list2.get(j).value){
		res.add(list1.get(i));
		i++;
		}else{
			res.add(list2.get(j)
			j++;
		}
}
while(i < m){
	res.add(list1.get(i));
	i++;
}
while(j < n){
	res.add(list2.get(j));
	j++;
}
