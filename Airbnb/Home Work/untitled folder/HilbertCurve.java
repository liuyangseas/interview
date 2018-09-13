// 想象Hilbert Curve 在你面前 （Hilbert曲线可以无限阶下去 从1阶开始） 
//落在一个矩阵里 让你写个function  三个参数 （x,y,阶数return 这个点（x,y）是在这阶curve里第几步

// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=146537&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3046%255D%255Bvalue%255D%3D37%26searchoption%255B3046%255D%255Btype%255D%3Dradio&page=1
package ab;
public class HilbertCurve {
    public static int hilbert_curve(int x, int y, int iter){
        if(iter == 0)
            return 1;

        int harfLen = (1 << (iter - 1));
        int harfNum = (1 << (2 * iter - 2));
        
        if(x >= harfLen && y >= harfLen)
            return 2 * harfNum + hilbert_curve(x - harfLen, y - harfLen, iter - 1);
        else if (x < harfLen && y >= harfLen)
            return harfNum + hilbert_curve(x, y - harfLen, iter - 1);
        else if (x < harfLen && y < harfLen)
            return hilbert_curve(y, x, iter - 1);
        else 
            return 3 * harfNum + hilbert_curve(harfLen - 1 - y, 2 * harfLen - 1 - x, iter - 1);
        
    }
    
    public static void main(String[] args) {
    	System.out.println(hilbert_curve(1, 1, 2));
    	System.out.println(hilbert_curve(0, 1, 1));
    	System.out.println(hilbert_curve(2, 2, 2));
    	System.out.println(hilbert_curve(1, 3, 2));
    	System.out.println(hilbert_curve(3, 1, 2));
    	System.out.println(hilbert_curve(19, 1, 5));
    	System.out.println(hilbert_curve(1, 0, 2));
    }
}
