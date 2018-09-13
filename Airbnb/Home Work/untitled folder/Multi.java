package ab;

public class Multi {
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] temp = new int[m + n];
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int pos1 =  i + j;
                int pos2 = i + j + 1;
                int sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + temp[pos2];
                temp[pos1] += sum / 10;
                temp[pos2] = sum % 10;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int num : temp) {
            if (sb.length() == 0 && num == 0) {
                continue;
            }
            sb.append(num);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
