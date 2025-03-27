import java.util.*;


class Object
{
    public int w;
    public int v;
    
    public Object(int w, int v)
    {
        this.w = w;
        this.v = v;
    }
}

public class Main {
    int n, k;
    Object[] arr = new Object[n+1];
    
    public int DP()
    {
        int MAX = 0;
        int[][] dp = new int[n+1][k+1];
        for(int i = 1; i<=k; i++)
        {
            if(i >= arr[1].w)
            {
                dp[1][i] = arr[1].v;
                MAX = Math.max(MAX,dp[1][i]);
            }
        }
        
        for(int i = 2; i<=n; i++)
        {
            for(int j = 1; j<=k; j++)
            {
                dp[i][j] = dp[i-1][j];
                if(arr[i].w <= j)
                {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-arr[i].w]+arr[i].v);
                    MAX = Math.max(MAX, dp[i][j]);
                }
            }
        }
        
        return MAX;
        
    }
    
    public static void main(String[] args) {
        Main target = new Main();
        Scanner scanner = new Scanner(System.in);
        
        target.n = scanner.nextInt();
        target.k = scanner.nextInt();
        
        target.arr = new Object[target.n+1];
        for(int i = 1; i<=target.n; i++)
        {
            int w,v;
            w = scanner.nextInt();
            v = scanner.nextInt();
            target.arr[i] = new Object(w,v);
        }
        
        int max = target.DP();
        
        System.out.print(max);
    }
}
