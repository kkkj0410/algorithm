import java.util.*;
import java.io.*;

class Main {
    public static int[] dice = new int[7];
    public static int[][] map;
    public static int R,C;
    public static int x,y;
    
    
    public static int move(int direction)
    {
        //north
        if(direction == 3)
        {
            if(y-1 < 0)
            {
                return -1;
            }
            
            y = y-1;
            
            int temp = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[6];
            dice[6] = temp;
        }
        
        //south
        else if(direction == 4)
        {
            if(y+1 >= R)
            {
                return -1;
            }
            
            y = y+1;
            
            int temp = dice[6];
            dice[6] = dice[5];
            dice[5] = dice[3];
            dice[3] = dice[1];
            dice[1] = temp;
        }
        
        //east
        else if(direction == 1)
        {
            if(x+1 >= C)
            {
                return -1;
            }
            
            x = x+1;
            
            int temp = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[2];
            dice[2] = dice[3];
            dice[3] = temp;
  
        }
        
        //west
        else if(direction == 2)
        {
            if(x-1 < 0)
            {
                return -1;
            }
            x = x-1;
            
            int temp = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[4];
            dice[4] = dice[3];
            dice[3] = temp;
        }
        
        if(map[y][x] == 0)
        {
            map[y][x] = dice[3];
        }
        
        else
        {
            dice[3] = map[y][x];
            map[y][x] = 0;
        }

        return dice[6];
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = 0;
        C = 0;
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        
        int K = 0;
        K = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i<R; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<C; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<K; i++)
        {
            int command = Integer.parseInt(st.nextToken());
            
            int result = move(command);
            
            if(result == -1)
            {
                continue;
            }
            
            System.out.println(result);
        }
        
    }
}
