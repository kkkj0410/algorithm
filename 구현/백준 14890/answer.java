import java.util.*;
import java.io.*;


class Main{
    public static int[][] map;
    public static boolean[][] visited;
    public static int N;
    public static int ramp;
    
    public static boolean Col(int x)
    {
        //lock_x
        for(int i = 1; i<N; i++)
        {
            int target = map[i-1][x];
            int cur = map[i][x];
            
            int interval = Math.abs(target-cur);
            if(interval >= 2)
            {
                return false;
            }
            
            
            if(target == cur)
            {
                continue;
            }
            
            //high
            else if(target > cur)
            {
                int distance = ramp;
                int moveY = i;
                while(distance > 0)
                {
                    if(moveY < 0 || moveY >= N || visited[moveY][x] == true || map[moveY][x] != cur)
                    {
                        return false;
                    }
                    visited[moveY][x] = true;
                    moveY++;
                    distance--;
                }
            }
            
            //low
            else
            {
                int distance = ramp;
                int moveY = i-1;
                while(distance > 0)
                {
                    if(moveY < 0 || moveY >= N || visited[moveY][x] == true || map[moveY][x] != target)
                    {
                        return false;
                    }
                    visited[moveY][x] = true;
                    moveY--;
                    distance--;
                }
            }
        }
        
        return true;
    }
    
    
    
    public static boolean Row(int y)
    {
        //lock_y
        for(int i = 1; i<N; i++)
        {
            int target = map[y][i-1];
            int cur = map[y][i];
            
            int interval = Math.abs(target-cur);
            if(interval >= 2)
            {
                return false;
            }
            
            
            if(target == cur)
            {
                continue;
            }
            
            //high
            else if(target > cur)
            {
                int distance = ramp;
                int moveX = i;
                while(distance > 0)
                {
                    if(moveX < 0 || moveX >= N || visited[y][moveX] == true || map[y][moveX] != cur)
                    {
                        return false;
                    }
                    visited[y][moveX] = true;
                    moveX++;
                    distance--;
                }
            }
            
            //low
            else
            {
                int distance = ramp;
                int moveX = i-1;
                while(distance > 0)
                {
                    if(moveX < 0 || moveX >= N || visited[y][moveX] == true || map[y][moveX] != target)
                    {
                        return false;
                    }
                    visited[y][moveX] = true;
                    moveX--;
                    distance--;
                }
            }
        }
        
        return true;
    }
    
    public static void reset(boolean[][] visited)
    {
        for(int i = 0; i<visited.length; i++)
        {
            for(int j = 0; j<visited.length; j++)
            {
                visited[i][j] = false;
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        ramp = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int result = 0;
        for(int i = 0; i<N; i++)
        {
            if(Row(i))
            {
                result++;
            }
        }
        reset(visited);
        for(int i = 0; i<N; i++)
        {
            if(Col(i))
            {
                result++;
            }
        }
        System.out.println(result);
        
    }
}
