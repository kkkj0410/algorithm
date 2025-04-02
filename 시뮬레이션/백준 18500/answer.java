import java.util.*;

class xy
{
    public int x,y;
    xy(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}

public class HelloWorld {
    static int R,C;
    static int N;
    static char[][] arr;
    static int[] attack;
    static int[][] Move = {{0,-1},{-1,0},{0,1},{1,0}};
    
    //입력
    public static void input()
    {
        Scanner scanner = new Scanner(System.in);
        
        R = scanner.nextInt();
        C = scanner.nextInt();
        
        arr = new char[R][C];
        
        for(int i = 0; i<R; i++)
        {
            String str = scanner.next();
            for(int j = 0; j<str.length(); j++)
            {
                arr[i][j] = str.charAt(j);
            }
        }
        
        N = scanner.nextInt();
        attack = new int[N];
        
        for(int i = 0; i<N; i++)
        {
            attack[i] = scanner.nextInt();
        }
        
    }
    
    
    //input : 막대기로 인해 사라진 미네랄의 동서남북 x,y 좌표
    //output : 미네랄 집합이 바닥에 붙어있으면 true,
    //         미네랄 집합이 공중에 떠있으면 false
    public static boolean bfs(int x, int y)
    {
        Queue<xy> q = new LinkedList<>();
        q.add(new xy(x,y));
        
        boolean[][] visited = new boolean[R][C];
        visited[y][x] = true;
        
        ArrayList<xy>list = new ArrayList<>();
        
        while(!q.isEmpty())
        {
            xy cur = q.peek();
            q.remove();
            list.add(cur);
            
            if(cur.y == R-1)
            {
                return true;
            }
            
            for(int i = 0; i<4; i++)
            {
                int xx = cur.x + Move[i][0];
                int yy = cur.y + Move[i][1];
                
                if(yy >= 0 && yy<R && xx>=0 && xx<C)
                {
                    if(arr[yy][xx] == 'x' && visited[yy][xx] == false)
                    {
                        visited[yy][xx] = true;
                        q.add(new xy(xx,yy));
                    }
                }
            }
            
        }
        
        for(int i = 0; i<list.size(); i++)
        {
            xy temp = list.get(i);
            arr[temp.y][temp.x] = '.';
        }
        Drop(list);
        return false;
    }
    
    //input : 공중에 떠있는 미네랄의 전체 좌표 list
    //output : X (미네랄을 바닥에 가라앉히고 지도에 반영함)
    public static void Drop(ArrayList<xy> list)
    {
        
        boolean TN = true;
        
        
        while(TN)
        {
            for(int i = 0; i<list.size(); i++)
            {
            //cur.y는 가져야할 위치보다 +1만큼 더 아래의 값을 갖는다.
                xy cur = list.get(i);
                cur.y = cur.y+1;
                
                if(cur.y == R || arr[cur.y][cur.x] == 'x')
                {
                    TN = false;
                }
                list.set(i,new xy(cur.x,cur.y));
                
            }
        }
        
        for(int i = 0; i<list.size(); i++)
        {
        //cur.y는 가져야할 위치보다 +1만큼 더 아래에 있으므로, cur.y-1로 반영
            xy cur = list.get(i);
            arr[cur.y-1][cur.x] = 'x';
        }
    }
    
    //막대기를 던진 후 미네랄을 삭제.
    //미네랄이 삭제된 좌표의 동서남북 좌표를 bfs함수에 할당
    //bfs함수는 미네랄 집합이 공중에 떠있을 시, 미네랄 전체 좌표를 Drop함수에 할당
    //Drop함수는 미네랄을 바닥에 배치하고 반영
    public static void move_attack()
    {
        for(int i = 0; i<attack.length; i++)
        {
            int attack_r = R - attack[i];
            int target_x = -1;
            if(i % 2 == 0)
            {
                for(int j = 0; j<C; j++)
                {
                    if(arr[attack_r][j] == 'x')
                    {
                        target_x = j;
                        break;
                    }
                }
            }
            
            else
            {
                for(int j = C-1; j>=0; j--)
                {
                    if(arr[attack_r][j] == 'x')
                    {
                        target_x = j;
                        break;
                    }
                }
            }
            if(target_x == -1)
            {
                continue;
            }
            
            arr[attack_r][target_x] = '.';
            
            
            for(int j = 0; j<4; j++)
            {
                int xx = target_x + Move[j][0];
                int yy = attack_r + Move[j][1];
                
                if(yy >= 0 && yy<R && xx>=0 && xx<C && arr[yy][xx] == 'x')
                {
                    bfs(xx,yy);
                }
            }
        }
    }
    public static void main(String[] args) {
        input();
        move_attack();
        
        

        for(int i = 0; i<R; i++)
        {
            for(int j = 0; j<C; j++)
            {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        
        
    }
}
