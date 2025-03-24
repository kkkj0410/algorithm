import java.util.*;
import java.io.*;

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
    static int R,C;
    //0 = jihun
    //-1 = empty
    //-2 = wall
    //-3 = fire
    static int[][] map;
    static int[][] visited;
    static int[][] move = {{1,0},{0,1},{-1,0},{0,-1}};
    static Queue<XY> JihunQueue = new LinkedList<>();
    static Queue<XY> FireQueue = new LinkedList<>();
    
    public static void FireBfs()
    {
        Queue<XY> nextQueue = new LinkedList<>();
        
        while(!FireQueue.isEmpty())
        {
            XY cur = FireQueue.peek();
            FireQueue.remove();
            
            for(int i = 0; i<4; i++)
            {
                int moveY = cur.y + move[i][0];
                int moveX = cur.x + move[i][1];
                
                if(moveY < 0 || moveY >= R || moveX < 0 || moveX >= C)
                {
                    continue;
                }
                
                if(map[moveY][moveX] != -1 && map[moveY][moveX] < 0)
                {
                    continue;
                }
                
                map[moveY][moveX] = -3;
                nextQueue.add(new XY(moveY,moveX));
            }
        }
        FireQueue = nextQueue;
    }
    
    public static int JihunBfs()
    {
        Queue<XY> nextQueue = new LinkedList<>();
        
        while(!JihunQueue.isEmpty())
        {
            XY cur = JihunQueue.peek();
            JihunQueue.remove();
            
            if(map[cur.y][cur.x] == -3)
            {
                continue;
            }
            
            for(int i = 0; i<4; i++)
            {
                int moveY = cur.y + move[i][0];
                int moveX = cur.x + move[i][1];
                
                if(moveY < 0 || moveY >= R || moveX < 0 || moveX >= C)
                {
                    return map[cur.y][cur.x] + 1;
                }
                
                if(map[moveY][moveX] != -1)
                {
                    continue;
                }
                
                map[moveY][moveX] = map[cur.y][cur.x] + 1;
                nextQueue.add(new XY(moveY,moveX));
            }
        }
        
        JihunQueue = nextQueue;
        return -1;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new int[R][C];
        visited = new int[R][C];
        
        for(int i = 0; i<R; i++)
        {
            String Line = br.readLine();
            for(int j = 0; j<C; j++)
            {
                char token = Line.charAt(j);
                if(token == '.')
                {
                    map[i][j] = -1;
                }
                else if(token == '#')
                {
                    map[i][j] = -2;
                }
                
                else if(token == 'J')
                {
                    map[i][j] = 0;
                    JihunQueue.add(new XY(i,j));
                }
                else if(token == 'F')
                {
                    map[i][j] = -3;
                    FireQueue.add(new XY(i,j));
                }
            }
        }
        
        int result = -1;
        while(true)
        {
            result = JihunBfs();
            if(result > 0 || JihunQueue.size() == 0)
            {
                break;
            }
            
            FireBfs();
        }
        
        if(result == -1)
        {
            System.out.println("IMPOSSIBLE");
        }
        else
        {
            System.out.println(result);
        }
    }
}
