import java.util.*;
import java.io.*;


public class Main {
    static int N,M;
    static int[][] map;
    static int[][] dp;
    static int[][] temp;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        dp = new int[N][M];
        temp = new int[2][M];
        
        for(int i = 0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //accumulate
        dp[0][0] = map[0][0];
        for(int i = 1; i<M; i++)
        {
            dp[0][i] = dp[0][i-1] + map[0][i];
        }


        for(int i = 1; i<N; i++)
        {
            temp[0][0] = dp[i-1][0] + map[i][0];
            for(int j = 1; j<M; j++)
            {
                temp[0][j] = Math.max(dp[i-1][j], temp[0][j-1]) + map[i][j];
            }

            temp[1][M-1] = dp[i-1][M-1] + map[i][M-1];
            for(int j = M-2; j>=0; j--)
            {
                temp[1][j] = Math.max(dp[i-1][j], temp[1][j+1]) + map[i][j];
            }

            for(int j = 0; j<M; j++)
            {
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }

        }

        System.out.println(dp[N-1][M-1]);
    }
}
