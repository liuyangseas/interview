/**
 * Given a start ip address and a number of ips we need to cover,
 * return a list of CIDR representation of the range.
 *
 * Example
 * Input: 10 IPs start from 255.0.0.7
 * Output: [255.0.0.7/32, 255.0.0.8/29, 255.0.0.16/32]
 */

import java.util.ArrayList;
import java.util.List;

public class Ip2Cidr {
  public List<String> getCIDRrange(String startIp, int range) {
    // check parameters
    long start = ipToLong(startIp);
    long end = start + range - 1;

    List<String> res = new ArrayList<>();
    while (start <= end) {
      // identify the location of first 1's from lower bit to higher bit of start IP
      // e.g. 00000001.00000001.00000001.01101100, return 4 (100)
      long locOfFirstOne = start & (-start);
      int maxMask = 32 - (int)(Math.log(locOfFirstOne) / Math.log(2));

      // calculate how many IP addresses between the start and end
      // e.g. between 1.1.1.111 and 1.1.1.120, there are 10 IP address
      // 3 bits to represent 8 IPs, from 1.1.1.112 to 1.1.1.119 (119 - 112 + 1 = 8)
      double curRange = Math.log(end - start + 1) / Math.log(2);
      int maxDiff = 32 - (int)Math.floor(curRange);

      // why max?
      // if the maxDiff is larger than maxMask
      // which means the numbers of IPs from start to end is smaller than mask range
      // so we can't use as many as bits we want to mask the start IP to avoid exceed the end IP
      // Otherwise, if maxDiff is smaller than maxMask, which means number of IPs is larger than mask range
      // in this case we can use maxMask to mask as many as IPs from start we want.
      maxMask = Math.max(maxDiff, maxMask);

      // Add to results
      String ip = longToIP(start);
      res.add(ip + "/" + maxMask);
      // We have already included 2^(32 - maxMask) numbers of IP into result
      // So the next round start must add that number
      start += Math.pow(2, (32 - maxMask));
    }
    return res;
  }

  // 256-based number to 10-based
  private long ipToLong(String strIP) {
    long[] ip = new long[4];
    String[] ipSec = strIP.split("\\.");
    for (int k = 0; k < 4; k++) {
      ip[k] = Long.valueOf(ipSec[k]);
    }

    return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
  }

  // 10-based number to 256-based
  private String longToIP(long longIP) {
    StringBuffer sb = new StringBuffer("");
    sb.append(String.valueOf(longIP >>> 24));
    sb.append(".");
    sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));
    sb.append(".");
    sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));
    sb.append(".");
    sb.append(String.valueOf(longIP & 0x000000FF));

    return sb.toString();
  }
}

//class Main {
//  public static void main(String[] args) {
//    Ip2Cidr ic = new Ip2Cidr();
//    System.out.println(ic.getCIDRrange("255.0.0.7", 10));
//    System.out.println(ic.getCIDRrange("255.0.0.0", 256));
//    System.out.println(ic.getCIDRrange("255.0.1.0", 1024));
//    System.out.println(ic.getCIDRrange("255.0.10.25", 231));
//  }
//}

public class IpCidr {
  public List<String> getCIDRs(String startIp, int range) {
    long start = ip2Long(startIp);
    long end = start + range - 1;

    List<String> res = new ArrayList<>();
    while (start <= end) {
      long maskCovered = start & (-start);
      int maskBits = (int)(Math.log(maskCovered) / Math.log(2));
      long remain = end - start + 1;
      int remainBits = (int)(Math.log(remain) / Math.log(2));

      StringBuilder sb = new StringBuilder();
      int actualBits = Math.min(maskBits, remainBits);
      res.add(sb.append(long2Ip(start)).append("/").append(32 - actualBits).toString());

      start += (long)Math.pow(2, actualBits);
    }

    return res;
  }

  // 256-based to 10-based
  private long ip2Long(String ip) {
    String[] parts = ip.split("\\.");
    long sum = 0;
    for (int i = 0; i < 4; i++) {
      sum += Long.parseLong(parts[i]);
      if (i < 3) {
        sum <<= 8;
      }
    }

    return sum;
  }

  // 10-based to 256-based
  private String long2Ip(long num) {
    StringBuilder sb = new StringBuilder();
    sb.append(num >> 24);
    sb.append(".");
    sb.append((num & 0x00FFFFFF) >> 16);
    sb.append(".");
    sb.append((num & 0x0000FFFF) >> 8);
    sb.append(".");
    sb.append((num & 0x000000FF));
    return sb.toString();
  }
}


// Michael code
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