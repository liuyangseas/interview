http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=199979&highlight=Two%2BSigma	
https://stackoverflow.com/questions/6737283/weighted-randomness-in-java	
https://stackoverflow.com/questions/20327958/random-number-with-probabilities	
	
/**		*	Design	a	data	structure	to	hold	objects	with	a	corresponding	integer	weight.	It	should		support:	
Obtain	an	object	randomly	with	probability	equal	to				(weight	of	the	element)	/	(sum	of	the	weights).			
Set	an	object-weight	pair.	If	the	object	is	already	in	the	structure,	its	weight	will be	updated.	
Otherwise,	the	object	will	be	inserted	and	set	to	its	weight.	If	the	weight is	zero,	the	object	can	be	removed.	
	
put("A",3)						3/33				
put("B",30)		                30/33	*		*/	


package com.leetcode.java.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WeightedRandomNumber<T> {

    private int total;
    private Map<T, Integer> weightMap;
    private Random rand;

    public WeightedRandomNumber() {
        total = 0;
        weightMap = new HashMap<>();
        rand = new Random();
    }

    public void put(T key, int weight) {
        total = total + weight - weightMap.getOrDefault(key, 0);
        weightMap.put(key, weight);
    }

    public T fetch() {
        int cur = rand.nextInt(total);
        for (Map.Entry<T, Integer> entry : weightMap.entrySet()) {
            cur -= entry.getValue();
            if (cur < 0) {
                return entry.getKey();
            }
        }
        return null;
    }
	
	public class RandomCollection<E> {
    private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private final Random random;
    private double total = 0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public RandomCollection<E> add(double weight, E result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }

    public E next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }
}

//    private int total;
//    private List<T> weightList;
//    private Random rand;
//
//    public WeightedRandomNumber() {
//        total = 0;
//        weightList = new ArrayList<>();
//        rand = new Random();
//    }
//
//    public void put(T key, int weight) {
//        int cur = weight;
//        while (cur > 0) {
//            weightList.add(key);
//            cur--;
//        }
//        total += weight;
//    }
//
//    public T fetch() {
//        int cur = rand.nextInt(total);
//        return weightList.get(cur);
//    }
}
