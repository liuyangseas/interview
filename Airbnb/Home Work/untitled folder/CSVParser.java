package ab;
import java.util.*;
 

// 还要写一个倒着来的

public class CSVParser {
  public static String parseCSV(String s) {
    List<String> result = new ArrayList<>();
    if (s == null || s.length() == 0) {
      return "";
    }
     
    boolean inQuote = false;
    StringBuffer sb = new StringBuffer();
     
    for (int i = 0; i < s.length(); i++) {
      if (inQuote) {
        if (s.charAt(i) == '"') {
          if (i == s.length() - 1) {
            result.add(sb.toString());
            return printStr(result);
          } else if (s.charAt(i + 1) == '"') {
            sb.append('"');
            i++;
          } else {
            result.add(sb.toString());
            sb.setLength(0);
            inQuote = false;
            i++;
          }
        } else {
          sb.append(s.charAt(i));
        }
      } else {
        if (s.charAt(i) == '"') {
          inQuote = true;
        } else if (s.charAt(i) == ',') {
          result.add(sb.toString());
          sb.setLength(0);
        } else {
          sb.append(s.charAt(i));
        }
      }
    }
     
    if (sb.length() > 0) {
      result.add(sb.toString());
    }
     
    return printStr(result);
  }
   
  private static String printStr(List<String> input) {
    if (input == null || input.size() == 0) {
      return "";
    }
     
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < input.size(); i++) {
      sb.append(input.get(i));
      if (i == input.size() - 1) {
        break;
      }
      sb.append("|");
    }
     
    return sb.toString();
  }
  
  public static String parseCSV2(String str) {
	  List<String> res = new ArrayList<>();
	  boolean inQuote = false;
	  StringBuilder sb = new StringBuilder();
	  for (int i = 0; i < str.length(); i++) {
		  if (inQuote) {
			  if (str.charAt(i) == '\"') {
				  if (i < str.length() - 1 && str.charAt(i + 1) == '\"') {
					  sb.append("\"");
					  i++;
				  } else {
					  inQuote = false;
				  }
			  } else {
				  sb.append(str.charAt(i));
			  }
		  } else {
			  if (str.charAt(i) == '\"') {
				  inQuote = true;
			  } else if (str.charAt(i) == ',') {
				  res.add(sb.toString());
				  sb.setLength(0);
			  } else {
				  sb.append(str.charAt(i));
			  }
		  }
	  }
	  if (sb.length() > 0) {
		  res.add(sb.toString());
	  }

	  return String.join("|", res);
  }
   
  public static void main(String[] args) {
    String input1 = "John,Smith,john.smith@gmail.com,Los Angeles,1";
    System.out.println(parseCSV2(input1));
     
    String input2 = "Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0";
    System.out.println(parseCSV2(input2));
     
    String input3 = "\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1";
    System.out.println(parseCSV2(input3));
     
    String input4 = "\"\"\"Alexandra Alex\"\"\"";
    System.out.println(parseCSV2(input4));
  }
}