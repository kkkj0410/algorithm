import java.util.*;
import java.io.*;


class XY
{
    public int x;
    public int y;
    
    public XY(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static int[][] map;
    public static boolean[][] visited;
    public static int N,M;
    public static int[][] move = {{1,0},{0,1},{-1,0},{0,-1}};
    public static int save_cnt = 0;
    public static List<XY> list = new ArrayList<>();
    public static int result = 0;
    
    
    //바이러스가 퍼졌을 때 안전 영역의 개수
    public static int FindSaveValue()
    {
        boolean[][] visited2 = new boolean[N][M];
        int cur_save = save_cnt;
        
        for(XY cur : list)
        {
            Queue<XY> q = new LinkedList<>();
            
            q.add(cur);
            
            while(!q.isEmpty())
            {
                XY target = q.peek();
                q.remove();
                for(int i = 0; i<4; i++)
                {
                    int moveX = target.x + move[i][0];
                    int moveY = target.y + move[i][1];
                    
                    if(moveX < 0 || moveX >= M || moveY<0 || moveY >= N || visited2[moveY][moveX] == true)
                    {
                        continue;
                    }
                    
                    if(map[moveY][moveX] == 0 && visited2[moveY][moveX] == false)
                    {
                        cur_save--;
                        visited2[moveY][moveX] = true;
                        q.add(new XY(moveX,moveY));
                    }
                }
            }
        }
        
        return cur_save;
    }
    
    //벽 3개 배치
    public static void dfs(int y, int cnt)
    {
        if(cnt == 3)
        {
            result = Math.max(result, FindSaveValue());
            return;
        }
        
        
        for(int i = y; i<N; i++)
        {
            for(int j = 0; j<M; j++)
            {
                if(map[i][j] == 0)
                {
                    map[i][j] = 1;
                    dfs(i,cnt+1);
                    map[i][j] = 0;
                }
            }
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
                
                if(map[i][j] == 0)
                {
                    save_cnt++;
                }
                
                if(map[i][j] == 2)
                {
                    list.add(new XY(j,i));
                }
            }
        }
        //사용자는 벽 3개 추가 설치
        //따라서 0의 개수는 3개 감소
        save_cnt -= 3;

        dfs(0,0);

        System.out.println(result);
        
        

        
        
    }
}
