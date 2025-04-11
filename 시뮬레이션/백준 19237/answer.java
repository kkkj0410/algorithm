import java.util.*;
import java.io.*;

class Shark
{
    public int y,x;
    public int[][] command;
    public boolean live = true;
    public int way;
    
    public Shark(int y,int x)
    {
        this.y = y;
        this.x = x;
    }
    
    public void Command(int[][] command)
    {
        this.command = command;
    }
}

class Smell
{
    public int num, residue;
    
}

public class Main {
    static int N,M,K;
    static int[][] SharkMap;
    static Smell[][] SmellMap;
    static int[][] NumberOneMap;
    static Shark[] SharkList;
    static int[][] move = {{0,0},{-1,0},{1,0},{0,-1},{0,1}};
    
    public static void CopyMap(Smell[][] map, Smell[][] copyMap)
    {
        for(int i = 1; i<=N; i++)
        {
            for(int j = 1; j<=N; j++)
            {
                copyMap[i][j].num = map[i][j].num;
                copyMap[i][j].residue = map[i][j].residue;
            }
        }
    }

    public static void SharkMove()
    {
        Smell[][] copySmellMap = new Smell[N+1][N+1];
        for(int i = 1; i<=N; i++)
        {
            for(int j = 1; j<=N; j++)
            {
                copySmellMap[i][j] = new Smell();
            }
        }
        CopyMap(SmellMap, copySmellMap);


        for(int i = 1; i<=M; i++)
        {
            int num = i;
            Shark shark = SharkList[i];
            
            if(shark.live == false)
            {
                continue;
            }
        
            int way = shark.way;
            int MoveWay = -1;
            int MoveY = -1;
            int MoveX = -1;


            boolean NoSmell = false;
            for(int j = 1; j<=4; j++)
            {
                MoveWay = shark.command[way][j];
                MoveY = shark.y + move[MoveWay][0];
                MoveX = shark.x + move[MoveWay][1];
                
                if(MoveY < 1 || MoveY > N || MoveX < 1 || MoveX > N)
                {
                    continue;
                }
                
                if(copySmellMap[MoveY][MoveX].residue != 0)
                {
                    continue;
                }
                
                NoSmell = true;
                break;
                
            }
            
            boolean MySmell = false;
            if(NoSmell == false)
            {
                for(int j = 1; j<=4; j++)
                {
                    MoveWay = shark.command[way][j];
                    MoveY = shark.y + move[MoveWay][0];
                    MoveX = shark.x + move[MoveWay][1];
                    
                    if(MoveY < 1 || MoveY > N || MoveX < 1 || MoveX > N)
                    {
                        continue;
                    }
                    
                    if(copySmellMap[MoveY][MoveX].num != num)
                    {
                        continue;
                    }
                    
                    MySmell = true;
                    break;

                }
            }

            if(NoSmell == true || MySmell == true)
            {
                SmellMap[MoveY][MoveX].residue = K;
                SharkMap[shark.y][shark.x] = 0;
                
                if(SharkMap[MoveY][MoveX] != 0 && SharkMap[MoveY][MoveX] < num)
                {
                    shark.live = false;
                }

                else
                {
                    int DeleteTarget = SharkMap[MoveY][MoveX];
                    if(DeleteTarget != 0)
                    {
                        SharkList[DeleteTarget].live = false;
                    }

                    SmellMap[MoveY][MoveX].num = num;
                    SharkMap[MoveY][MoveX] = num;

                    shark.way = MoveWay;
                    shark.y = MoveY;
                    shark.x = MoveX;
                }
            }
        }
        
        //next : EraseSmell()
    }
    
    public static void EraseSmell()
    {
        for(int i = 1; i<=N; i++)
        {
            for(int j = 1; j<=N; j++)
            {
                if(SharkMap[i][j] != 0)
                {
                    continue;
                }
                
                if(SmellMap[i][j].residue == 0)
                {
                    continue;
                }
                
                SmellMap[i][j].residue--;
                if(SmellMap[i][j].residue == 0)
                {
                    SmellMap[i][j].num = 0;
                }
            }
        }
    }
    
    
    public static boolean OnlyOneShark()
    {
        for(int i = 2; i<=M; i++)
        {
            if(SharkList[i].live == true)
            {
                return false;
            }
        }
        
        return true;
    }
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        SharkMap = new int[N+1][N+1];
        SmellMap = new Smell[N+1][N+1];
        SharkList = new Shark[M+1];
        
        
        for(int i = 1; i<=N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++)
            {
                SmellMap[i][j] = new Smell();
                SmellMap[i][j].num = Integer.parseInt(st.nextToken());
                
                if(SmellMap[i][j].num != 0)
                {
                    SmellMap[i][j].residue = K;
                    
                    int target = SmellMap[i][j].num;
                    SharkList[target] = new Shark(i,j);
                    SharkMap[i][j] = target;
                }
            }
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=M; i++)
        {
            int way = Integer.parseInt(st.nextToken());
            
            SharkList[i].way = way;
        }
        
        for(int i = 1; i<=M; i++)
        {
            int[][] command = new int[5][5];
            for(int j = 1; j<=4; j++)
            {
                st = new StringTokenizer(br.readLine());
                for(int k = 1; k<=4; k++)
                {
                    command[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            
            SharkList[i].Command(command);
            
        }
        
        for(int t = 1; t<=1000; t++)
        {
            SharkMove();
            EraseSmell();
            
            if(OnlyOneShark())
            {
                System.out.println(t);
                return;
            }
        }
        System.out.println(-1);
        
        
    }
}
