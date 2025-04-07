import java.util.*;
import java.io.*;

class Dust
{
    public int y,x,value;
    
    public Dust(int y, int x, int value)
    {
        this.y = y;
        this.x = x;
        this.value = value;
    }
}

class AirCleaner
{
    public int topY,topX;
    public int bottomY,bottomX;
    
    public AirCleaner(int topY, int topX, int bottomY, int bottomX)
    {
        this.topY = topY;
        this.topX = topX;
        
        this.bottomY = bottomY;
        this.bottomX = bottomX;
    }
}


public class Main {
    public static int[][] map;
    public static int R,C;
    public static int[][] move = {{-1,0},{0,-1},{1,0},{0,1}};
    
    public static AirCleaner airCleaner;
    
    public static void MoveDust()
    {
        int[][] curMap = new int[R][C];
        
        for(int i = 0; i<R; i++)
        {
            for(int j = 0; j<C; j++)
            {
                if(map[i][j] == -1)
                {
                    curMap[i][j] = -1;
                    continue;
                }

                else if(map[i][j] == 0)
                {
                    continue;
                }

                curMap[i][j] += map[i][j];
                int AmountDust = map[i][j] / 5;
                for(int k = 0; k<4; k++)
                {
                    int moveY = i + move[k][0];
                    int moveX = j + move[k][1];
                    
                    
                    //escape map
                    if(moveY < 0 || moveY >=R || moveX <0 || moveX >= C)
                    {
                        continue;
                    }
                    
                    //meet with airCleaner
                    if(map[moveY][moveX] == -1)
                    {
                        continue;
                    }
                    

                    curMap[moveY][moveX] += AmountDust;
                    curMap[i][j] -= AmountDust;

                }
            }
        }

        map = curMap;
    
        
    }
    
    public static void MoveAir()
    {
        int ty = airCleaner.topY;
        int tx = airCleaner.topX;
        int by = airCleaner.bottomY;
        int bx = airCleaner.bottomX;
        
        //1. top
        
        //up
        for(int i = ty-1; i>0; i--)
        {
            map[i][tx] = map[i-1][tx];
        }
        //right
        for(int i = 0; i<C-1; i++)
        {
            map[0][i] = map[0][i+1];
        }
        //down
        for(int i = 0; i<ty; i++)
        {
            map[i][C-1] = map[i+1][C-1];
        }
        //left
        for(int i = C-1; i>tx+1; i--)
        {
            map[ty][i] = map[ty][i-1];
        }
        map[ty][tx+1] = 0;

        //2. bottom
        
        //down
        for(int i = by+1; i<R-1; i++)
        {
            map[i][0] = map[i+1][0];
        }
        //right
        for(int i = 0; i<C-1; i++)
        {
            map[R-1][i] = map[R-1][i+1];
        }
        //up
        for(int i = R-1; i>by; i--)
        {
            map[i][C-1] = map[i-1][C-1];
        }
        //left
        for(int i = C-1; i>bx+1; i--)
        {
            map[by][i] = map[by][i-1];
        }
        map[by][bx+1] = 0;
    }
    
    public static void Print()
    {
        System.out.println("-------");
        for(int i = 0; i<R; i++)
        {
            for(int j = 0; j<C; j++)
            {
                System.out.print(map[i][j] + "   ");
            }
            System.out.println();
        }
    }


    public static int AmountDust()
    {
        int value = 0;
        for(int i = 0; i<R; i++)
        {
            for(int j = 0; j<C; j++)
            {
                if(map[i][j] == 0 || map[i][j] == -1)
                {
                    continue;
                }
                
                value += map[i][j];
            }
        }
        
        return value;
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        
        for(int i = 0; i<R; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<C; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //find AirCleaner
        boolean TN = false;
        for(int i = 0; i<R; i++)
        {
            if(TN == true)
            {
                break;
            }
            for(int j = 0; j<C; j++)
            {
                if(map[i][j] == -1)
                {
                    airCleaner = new AirCleaner(i,j,i+1,j);
                    TN = true;
                    break;
                }
            }
        }
        
        
        for(int i = 0; i < T; i++)
        {
            MoveDust();
            MoveAir();
        }


        //Print();
        System.out.println(AmountDust());
    }
}
