package ab;
import java.util.*;

public class Ip {
    public static List<String> ipToCIDR(String ip, int n) {
        String[] address = ip.split("\\.");
        long num = 0;
        for (String s : address) {
            int v = Integer.valueOf(s);
            num = num * 256 + v;
        }
        List<String> rs = new ArrayList<>();
        while (n > 0) {
            int lastBit = (int) Long.lowestOneBit(num);
            // lastBit may bigger than n
            while (lastBit > n) {
                lastBit /= 2;
            }
            String temp = convert(num, lastBit);
            rs.add(temp);
            n -= lastBit;
            num += lastBit;
        }
        return rs;
    }
    
    public static String convert(long num, int c) {
        String rs = "";
        for (int i = 0; i < 4; i++) {
            long k = num & 255;
            num >>= 8;
            rs = i == 0 ? k + rs : k + "." + rs;
        }
        int count = 33;
        while (c > 0) {
            c /= 2;
            count--;
        }
        return rs + "/" + count;
    }
    
	public static void main(String[] args) {
		int a = 16;
		System.out.print(Integer.lowestOneBit(a));
	}

}
