import java.util.*;
import java.io.*;


public class Main {
    public static int N,M;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[][] move = {{0,1},{1,0},{0,-1},{-1,0}};
    public static int value = 0;
    
    public static void dfs(int x, int y, int cnt, int result)
    {
        if(cnt == 4)
        {
            value = Math.max(value, result);
            return;
        }
        
        for(int i = 0; i<4; i++)
        {
            int moveX = x + move[i][0];
            int moveY = y + move[i][1];
            
            if(moveX < 0 || moveX >= M || moveY < 0 || moveY >= N || visited[moveY][moveX] == true)
            {
                continue;
            }
            
            if(cnt == 2)
            {
                visited[moveY][moveX] = true;
                dfs(x,y,cnt+1,result+map[moveY][moveX]);
                visited[moveY][moveX] = false;
            }
            
            visited[moveY][moveX] = true;
            dfs(moveX,moveY, cnt+1, result + map[moveY][moveX]);
            visited[moveY][moveX] = false;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for(int i = 0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0; i<N; i++)
        {
            for(int j = 0; j<M; j++)
            {
                visited[i][j] = true;
                dfs(j,i,1,map[i][j]);
                visited[i][j] = false;
            }
        }
        
        System.out.println(value);
        
    }
}
