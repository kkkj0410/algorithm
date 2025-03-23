import java.util.*;
import java.io.*;


//combination
//bfs

class Virus
{
    public int y,x;
    
    public Virus(int y, int x)
    {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    public static List<Virus> virusList = new ArrayList<>();
    public static int N,M;
    public static int[][] map;
    public static int[] visited;
    public static int[][] move = {{-1,0},{0,-1},{1,0},{0,1}};
    public static int result = 9999999;
    public static int space = 0;
    public static int[][] curMap;
    public static Queue<Virus>q = new LinkedList<>();

    //a->b
    public static void Copy(int[][] a, int[][] b)
    {
        for(int i = 0; i<a.length; i++)
        {
            for(int j = 0; j<a[0].length; j++)
            {
                
                
                //blank
                if(a[i][j] == 0)
                {
                    b[i][j] = -1;
                }
                
                //wall
                else if(a[i][j] == 1)
                {
                    b[i][j] = -2;
                }
                
                //virus
                else
                {
                    b[i][j] = -3;
                }
            }
        }
    }
    
    public static int MaxResult(int[][] map)
    {
        for(int i = 0; i<virusList.size(); i++)
        {
            Virus virus = virusList.get(i);
            map[virus.y][virus.x] = 0;
        }
        int result = 0;
        for(int i = 0; i<map.length; i++)
        {
            for(int j = 0; j<map[0].length; j++)
            {
                if(map[i][j] == -1)
                {
                    return 9999999;
                }

                if(map[i][j] > result)
                {
                    result = map[i][j];
                }
            }
        }
        
        return result;
    }
    
    public static void combination(int depth, int start)
    {
        if(depth == M)
        {
			bfs();
            return;
        }
        
        for(int i = start; i<virusList.size(); i++)
        { 
            visited[depth] = i;
            combination(depth+1, i+1);
        }
    }
    
    public static void bfs()
    {
        Copy(map,curMap);

        for(int i = 0; i<M; i++)
        {
            Virus virus = virusList.get(visited[i]);
            q.add(virus);
            curMap[virus.y][virus.x] = 0;
        }
        
        while(!q.isEmpty())
        {
            Virus curVirus = q.peek();
            q.remove();

            int movePoint = curMap[curVirus.y][curVirus.x] + 1;
            for(int i = 0; i<4; i++)
            {
                int moveY = curVirus.y + move[i][0];
                int moveX = curVirus.x + move[i][1];

                if(moveY < 0 || moveY >= N || moveX<0 || moveX >= N ||curMap[moveY][moveX] == -2)
                {
                    continue;
                }
                
                if(curMap[moveY][moveX] >= movePoint)
                {
                    continue;
                }

                if(curMap[moveY][moveX] == -1 || curMap[moveY][moveX] == -3)
                {
                    curMap[moveY][moveX] = movePoint;
                    q.add(new Virus(moveY,moveX));
                }
            }

        }

        result = Math.min(MaxResult(curMap), result);
    }
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        curMap = new int[N][N];

        for(int i = 0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++)
            {
                map[i][j] =Integer.parseInt(st.nextToken()); 
                
                if(map[i][j] == 2)
                {
                    virusList.add(new Virus(i,j));
                }

                else if(map[i][j] == 0)
                {
                    space++;
                }
            }
        }
        
        visited = new int[M];
        
		combination(0,0);

        if(result == 9999999)
        {
            System.out.println(-1);
        }
        else
        {
            System.out.println(result);
        }
    }
}
