import java.util.*;



public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        
        long[] dp = new long[N+1];
        
        for(int i = 1; i<=N; i++)
        {
            dp[i] = dp[i-1] + 1;
            
            int cnt = 2;
            for(int j = i-3; j>=1; j--)
            {
                dp[i] = Math.max(dp[i], dp[j]*cnt++);
            }
        }
        
        
        System.out.println(dp[N]);
        
    }
}

