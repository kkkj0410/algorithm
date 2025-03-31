import java.util.*;
import java.io.*;


public class Main {
    static long A,B;
    static long[] dp;
    static int MAX = 53;
    
    public static void DP()
    {
        //dp[n] = 이진수의 길이가 n일때, 만들 수 있는 1의 개수 총합
        dp = new long[MAX+1];
        
        dp[1] = 1;
        for(int i = 2; i<=MAX; i++){
            dp[i] = dp[i-1]*2 + (1L << (i-1));
        }
    }
    
    public static long resolve(long N){
        long result = 0;
        
        int size = (int) (Math.log(N) / Math.log(2));
        for(int cur = size; cur>0; cur--)
        {
            if((N & (1L<<cur)) == 0L)
            {
                continue;
            }
            
            result += dp[cur] + (N - (1L<<cur)) + 1;
            N -= (1L << cur);
            
        }
        
        result += (N & 1);
        
        return result;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        
        DP();
        
        long A_result = resolve(A-1);
        long B_result = resolve(B);
        
        System.out.println(B_result - A_result);
    }
}
