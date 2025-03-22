import java.util.*;
import java.io.*;
class Data
{
    public int[][] board;
    public int cnt;
    
    public Data(int[][] arr, int cnt)
    {
        board = new int[arr.length][arr[0].length];
        copy(arr, board);
        this.cnt = cnt;
    }
    
    //arr->brr;
    public void copy(int[][] arr, int[][] brr)
    {
        for(int i = 0; i<arr.length; i++)
        {
            for(int j = 0; j<arr.length; j++)
            {
                brr[i][j] = arr[i][j];
            }
        }
    }
}

public class Main {

    public static int N;
    
    
    public static void Rotate(int[][] arr, int direction)
    {
        Queue<Integer> q = new LinkedList<>();
        
        //north
        if(direction == 0)
        {
            for(int x = 0; x<N; x++)
            {
                for(int y = 0; y<N; y++)
                {

                    if(arr[y][x] != 0)
                    {
                        q.add(arr[y][x]);
                    }
                }
                
                int cnt = 0;
                while(!q.isEmpty())
                {
                    int cur = q.peek();
                    q.remove();
                    
                    if(cur != 0 && !q.isEmpty() &&cur == q.peek())
                    {
                        cur = cur*2;
                        q.remove();
                    }
                    
                    arr[cnt++][x] = cur;
                }
                for(int cur = cnt; cur<N; cur++)
                {
                    arr[cur][x] = 0;
                }
            }
        }
        
        else if(direction == 1)
        {
            for(int y = 0; y<N; y++)
            {
                for(int x = N-1; x>=0; x--)
                {
                    if(arr[y][x] != 0)
                    {
                        q.add(arr[y][x]);
                    }
                }
                
                int cnt = N-1;
                while(!q.isEmpty())
                {
                    int cur = q.peek();
                    q.remove();
                    
                    if(cur != 0 && !q.isEmpty() &&cur == q.peek())
                    {
                        cur = cur*2;
                        q.remove();
                    }
                    
                    arr[y][cnt--] = cur;
                }
                for(int cur = cnt; cur>=0; cur--)
                {
                    arr[y][cur] = 0;
                }
            }
        }
        
        else if(direction == 2)
        {
            for(int x = 0; x<N; x++)
            {
                for(int y = N-1; y>=0; y--)
                {
                    if(arr[y][x] != 0)
                    {
                        q.add(arr[y][x]);
                    }
                }
                
                int cnt = N-1;
                while(!q.isEmpty())
                {
                    int cur = q.peek();
                    q.remove();
                    
                    if(cur != 0 && !q.isEmpty() &&cur == q.peek())
                    {
                        cur = cur*2;
                        q.remove();
                    }
                    
                    arr[cnt--][x] = cur;
                }
                for(int cur = cnt; cur>=0; cur--)
                {
                    arr[cur][x] = 0;
                }
            }
        }
        
        else if(direction == 3)
        {
            for(int y = 0; y<N; y++)
            {
                for(int x = 0; x<N; x++)
                {
                    if(arr[y][x] != 0)
                    {
                        q.add(arr[y][x]);
                    }
                }
                
                int cnt = 0;
                while(!q.isEmpty())
                {
                    int cur = q.peek();
                    q.remove();
                    
                    if(cur != 0 && !q.isEmpty() &&cur == q.peek())
                    {
                        cur = cur*2;
                        q.remove();
                    }
                    
                    arr[y][cnt++] = cur;
                }
                for(int cur = cnt; cur<N; cur++)
                {
                    arr[y][cur] = 0;
                }
            }
        }
    }
    
    public static void Print(int[][] arr)
    {
        for(int i = 0; i<N; i++)
        {
            for(int j = 0; j<N; j++)
            {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static int Find_max(int[][] arr)
    {
        int max = 0;
        for(int i = 0; i<arr.length; i++)
        {
            for(int j = 0; j<arr[0].length; j++)
            {
                max = Math.max(arr[i][j], max);
            }
        }
        return max;
    }
    
    public static int bfs(int[][] arr)
    {
        Queue<Data> q = new LinkedList<>();
        
        q.add(new Data(arr,0));
        
        int MAX = 0;
        while(!q.isEmpty())
        {
            Data cur = q.peek();
            q.remove();
            
            if(cur.cnt == 5)
            {
                MAX = Math.max(Find_max(cur.board), MAX);
                continue;
            }
            
            for(int i = 0; i<4; i++)
            {
                Data next_cur = new Data(cur.board,cur.cnt+1);
                
                Rotate(next_cur.board, i);
                
                q.add(next_cur);
            }
        }
        
        return MAX;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];
        
        for(int y = 0; y<N; y++)
        {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x<N; x++)
            {
                arr[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        
        System.out.println(bfs(arr));
        
        
    }
}
