import java.util.*;
import java.io.*;

class Cleaner
{
    public int y,x;
    
    //0=north, 1=east, ~~~
    public int compass;
    
    Cleaner(int y, int x, int compass)
    {
        this.y = y;
        this.x = x;
        this.compass = compass;
    }
}

public class Main {
    public static int[][] map;
    public static int N,M;
    public static Cleaner cleaner;
    public static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    
    public static int work()
    {
        //map[i][j] = -1 => clean
        int result = 0;
        while(true)
        {
            //Part1
            if(map[cleaner.y][cleaner.x] == 0)
            {
                map[cleaner.y][cleaner.x] = -1;
                result++;
            }
            
            //Part2 ~ 3
            else
            {
                boolean part2 = true;
                boolean part3 = false;
                for(int i = 0; i<4; i++)
                {
                    int moveY = cleaner.y + move[i][0];
                    int moveX = cleaner.x + move[i][1];
                    
                    if(moveY < 0 || moveY >= N || moveX < 0 || moveX >= M)
                    {
                        continue;
                    }
                    
                    if(map[moveY][moveX] == 0)
                    {
                        part3 = true;
                        part2 = false;
                    }
                }
                
                if(part2 == true)
                {
                    int target = (cleaner.compass + 2) % 4;
                    int targetY = cleaner.y + move[target][0];
                    int targetX = cleaner.x + move[target][1];
                    
                    if(targetY < 0 || targetY >= N || targetX < 0 || targetX >= M || map[targetY][targetX] == 1)
                    {
                        break;
                    }
                    
                    cleaner.y = targetY;
                    cleaner.x = targetX;
                    
                }
                
                //part3
                else if(part3 == true)
                {
                    if(cleaner.compass == 0)
                    {
                        cleaner.compass = 3;
                    }
                    else
                    {
                        cleaner.compass--;
                    }
                    
                    int targetY = cleaner.y + move[cleaner.compass][0];
                    int targetX = cleaner.x + move[cleaner.compass][1];
                    if(targetY < 0 || targetY >= N || targetX < 0 || targetX >= M || map[targetY][targetX] != 0)
                    {
                        continue;
                    }
                    
                    cleaner.y = targetY;
                    cleaner.x = targetX;
                }
            }
        }
            
            return result;
        }
        
    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //map_size
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        
        //cleaner_data
        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int compass = Integer.parseInt(st.nextToken());
        cleaner = new Cleaner(y,x,compass);
        
        //make_map
        for(int i = 0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(work());
        
    }
}
