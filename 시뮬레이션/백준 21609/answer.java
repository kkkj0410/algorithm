import java.util.*;
import java.io.*;

class Block
{
    public int y,x,color,cnt,rainbowCnt;
    
    public Block(int y, int x, int color, int cnt, int rainbowCnt)
    {
        this.y = y;
        this.x = x;
        this.color = color;
        this.cnt = cnt;
        this.rainbowCnt = rainbowCnt;
    }
}

class XY
{
    public int y,x;
    public XY(int y, int x)
    {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    public static int N, M;
    public static int[][] map;
    public static int[][] move = {{0,1},{1,0},{0,-1},{-1,0}};
    public static boolean[][] visited;
    
    public static Block FindMaxBlock()
    {
        Block MaxBlock = new Block(0,0,0,0,0);
        visited = new boolean[N][N];
        
        for(int i = 0; i<N; i++)
        {
            for(int j = 0; j<N; j++)
            {
                if(visited[i][j] == true)
                {
                    continue;
                }

                //empty
                if(map[i][j] == -2)
                {
                    continue;
                }

                //black
                if(map[i][j] == -1)
                {
                    continue;
                }
                
                //rainbow
                if(map[i][j] == 0)
                {
                    continue;
                }
                
                RainbowNotVisit();
                
                Block CurBlock = BfsBlock(i,j,map[i][j]);
                
                if(MaxBlock.cnt < CurBlock.cnt)
                {
                    MaxBlock = CurBlock;
                }
                else if(MaxBlock.cnt == CurBlock.cnt && MaxBlock.rainbowCnt <= CurBlock.rainbowCnt)
                {
                    MaxBlock = CurBlock;
                }

            }
        }
        
        return MaxBlock;
    }
    
    
    public static Block BfsBlock(int y, int x, int color)
    {
        Block block = new Block(y,x,color,1,0);
        
        Queue<XY> q = new LinkedList<>();
        q.add(new XY(y,x));
        visited[y][x] = true;
        
        while(!q.isEmpty())
        {
            XY CurXY = q.peek();
            q.remove();
            
            for(int i = 0; i<4; i++)
            {
                int moveY = CurXY.y + move[i][0];
                int moveX = CurXY.x + move[i][1];
                
                if(moveY < 0 || moveY>= N || moveX < 0 || moveX >= N)
                {
                    continue;
                }
                
                if(visited[moveY][moveX] == true)
                {
                    continue;
                }
                
                //black
                if(map[moveY][moveX] == -1)
                {
                    continue;
                }
                
                //empty
                if(map[moveY][moveX] == -2)
                {
                    continue;
                }

                if(map[moveY][moveX] == 0 || map[moveY][moveX] == color)
                {
                    visited[moveY][moveX] = true;
                    q.add(new XY(moveY,moveX));
                    block.cnt++;
                    if(map[moveY][moveX] == 0)
                    {
                        block.rainbowCnt++;
                    }
                }
                
            }
        }
        
        return block;
    }
    
    public static void DeleteBlock(Block block)
    {
        
        Queue<XY> q = new LinkedList<>();
        q.add(new XY(block.y,block.x));
        
        
        //-2 => empty
        map[block.y][block.x] = -2;
        while(!q.isEmpty())
        {
            XY CurXY = q.peek();
            q.remove();
            
            for(int i = 0; i<4; i++)
            {
                int moveY = CurXY.y + move[i][0];
                int moveX = CurXY.x + move[i][1];
                
                if(moveY < 0 || moveY >= N || moveX < 0 || moveX >=N)
                {
                    continue;
                }
                
                //black
                if(map[moveY][moveX] == -1)
                {
                    continue;
                }
                
                //-2 => empty
                if(map[moveY][moveX] == -2)
                {
                    continue;
                }
                
                if(map[moveY][moveX] == 0 || map[moveY][moveX] == block.color)
                {
                    map[moveY][moveX] = -2;
                    q.add(new XY(moveY,moveX));
                }
                

                
            }
        }
    }
    
    public static void RainbowNotVisit()
    {
        for(int i = 0; i<N; i++)
        {
            for(int j = 0; j<N; j++)
            {
                if(map[i][j] == 0)
                {
                    visited[i][j] = false;
                }
            }
        }
    }
    
    
    public static void Gravity()
    {
        for(int x = 0; x<N; x++)
        {
            for(int y = N-2; y>=0; y--)
            {
                if(map[y][x] == -1 || map[y][x] == -2)
                {
                    continue;
                }

                for(int k = y+1; k<N; k++)
                {
                    if(map[k][x] == -2)
                    {
                        map[k][x] = map[k-1][x];
                        map[k-1][x] = -2;

                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
    }
    
    public static void Rotate()
    {
        int[][] ChangeMap = new int[N][N];
        for(int i = 0; i<N; i++)
        {
            for(int j = 0; j<N; j++)
            {
                ChangeMap[i][j] = map[0 + j][N-1 - i];
            }
        }

        map = ChangeMap;
    }
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        
        for(int i = 0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int result = 0;
        while(true)
        {
            Block MaxBlock = FindMaxBlock();
            if(MaxBlock.cnt < 2)
            {
                break;
            }
            
            result += Math.pow(MaxBlock.cnt,2);
            
            DeleteBlock(MaxBlock);
            
            Gravity();
            Rotate();
            Gravity();

        }

        System.out.println(result);
    }
}
