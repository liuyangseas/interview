按rating降序，rating相同时，保持id不变


class BusinessInfo{
	int id;
	int rating;
	
	public BusinessInfor(int id, int rating){
		this.id = id;
		this.rating = rating;
	}
}

public static List<BusinessInfo> sortBusinessByRating(List<BusinessInfo> business){
	public int compare(BusinessInfo a, BusinessInfo b){
		return b.rating – a.rating;
	}

	Collections.sort(business, compare);
	Return business;
}
