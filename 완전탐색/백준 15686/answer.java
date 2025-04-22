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
    public static int[][] map;
    public static int N,M;
    public static List<XY> ChickenList = new ArrayList<>();
    public static List<XY> HouseList = new ArrayList<>();
    public static int MAX = 99999999;
    public static int answer = MAX;
    public static boolean[] visited;
    
    public static void Combination(int cnt, int num)
    {
        if(cnt == M)
        {
            answer = Math.min(resolve(), answer);
            return;
        }
        
        for(int i = num; i<ChickenList.size(); i++)
        {
            if(visited[i] == true)
            {
                continue;
            }

            visited[i] = true;
            Combination(cnt+1, i+1);
            visited[i] = false;

        }
    }
    
    public static int resolve()
    {
        int ChickenSize = ChickenList.size();
        int HouseSize = HouseList.size();
        int[] value = new int[HouseSize];
        
        for(int i = 0; i<HouseSize; i++)
        {
            value[i] = MAX;
        }
        
        for(int i = 0; i<ChickenSize; i++)
        {
            if(visited[i] == false)
            {
                continue;
            }

            int ChickenY = ChickenList.get(i).y;
            int ChickenX = ChickenList.get(i).x;
            for(int j = 0; j<HouseSize; j++)
            {
                int HouseY = HouseList.get(j).y;
                int HouseX = HouseList.get(j).x;
                
                int DistanceY = Math.abs(ChickenY - HouseY);
                int DistanceX = Math.abs(ChickenX - HouseX);
                
                int Distance = DistanceY + DistanceX;
                
                if(value[j] > Distance)
                {
                    value[j] = Distance;
                }
            }
        }
        
        int result = 0;
        for(int i = 0 ; i<HouseSize; i++)
        {
            result += value[i];
        }
        
        return result;
    }
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];

        int ChickenMax = 13;
        visited = new boolean[ChickenMax];

        for(int i = 0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                
                if(map[i][j] == 2)
                {
                    ChickenList.add(new XY(i,j));
                }
                
                if(map[i][j] == 1)
                {
                    HouseList.add(new XY(i,j));
                }
            }
        }
        
        Combination(0,0);
        
        System.out.println(answer);
    }
}
