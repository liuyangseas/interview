Like 26. Remove Duplicates from Sorted Array

https://www.geeksforgeeks.org/remove-duplicates-from-a-given-string/

public string ReduceString(string s){
		if(s == null) return null;
		StringBuilder res = new StringBuilder();
		char word = s.charAt(0);
		while(i < s.length()){
			if(s.charAt(i) == word){
				i++;
			}else{
				res.append(String.valueOf(word);
				word = s.charAt(i);
			}
		}
		res.append(String.valueOf(word));
		return res;
}
