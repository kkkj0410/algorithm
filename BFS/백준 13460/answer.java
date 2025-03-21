import java.util.*;
import java.io.*;

class Ball
{
    public int rx,ry;
    public int bx,by;
    public int cnt;
    
    Ball(int rx, int ry, int bx, int by, int cnt)
    {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.cnt = cnt;
    }
}

public class Main {
    static int R,C;
    static char[][] board;
    static int[][] move = {{-1,0},{0,-1},{1,0},{0,1}};
    
    public static void print_board()
    {
        for(int i = 0; i<R; i++)
        {
            for(int j = 0; j<C; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    
    
    static int result = -1;
    public static int bfs(int rx, int ry, int bx, int by)
    {
        Queue<Ball>q = new LinkedList<>();
        q.add(new Ball(rx,ry,bx,by,0));
        
        boolean[][][][] visited = new boolean[11][11][11][11];
        visited[rx][ry][bx][by] = true;
        
        while(!q.isEmpty())
        {
            Ball ball = q.peek();
            q.remove();
            
            //판을 10번 넘게 움직이면 실패
            if(ball.cnt > 10)
            {
                return -1;
            }
            
            //빨간공이 O에 들어가면 성공
            if(board[ball.ry][ball.rx] == 'O')
            {
                return ball.cnt;
            }
            
            //파란공이 O에 들어가면 실패
            else if(board[ball.by][ball.bx] == 'O')
            {
                continue;
            }
            
            for(int i = 0; i<4; i++)
            {
                int red_x = ball.rx;
                int red_y = ball.ry;
                int red_distance = 0;
                
                //빨간공 움직이기
                while(board[red_y + move[i][0]][red_x + move[i][1]] != '#')
                {
                    red_distance++;
                    red_y += move[i][0];
                    red_x += move[i][1];
                    
                    //공이 O에 도달하면 움직일 수 없음
                    if(board[red_y][red_x] == 'O')
                    {
                        break;
                    }
                    
                }
                
                //파란공 움직이기
                int blue_x = ball.bx;
                int blue_y = ball.by;
                int blue_distance = 0;
                while(board[blue_y + move[i][0]][blue_x + move[i][1]] != '#')
                {
                    blue_distance++;
                    blue_y += move[i][0];
                    blue_x += move[i][1];
                    
                    if(board[blue_y][blue_x] == 'O')
                    {
                        break;
                    }
                }
                
                //공 2개가 겹치면 뒤늦게 도착한 공이 밀려남
                if(red_x == blue_x && red_y == blue_y)
                {
                    if(board[red_y][red_x] == 'O')
                    {
                        continue;
                    }


                    if(red_distance > blue_distance)
                    {
                        red_y -= move[i][0];
                        red_x -= move[i][1];
                    }
                    else
                    {
                        blue_y -= move[i][0];
                        blue_x -= move[i][1];
                    }
                }
                
                if(visited[red_x][red_y][blue_x][blue_y] == true)
                {
                    continue;
                }
                
                visited[red_x][red_y][blue_x][blue_y] = true;
                q.add(new Ball(red_x,red_y,blue_x,blue_y,ball.cnt+1));
            }
        }
        
        return -1;
        
    }
    
    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R+1][C+1];
        
        //make board
        for(int i = 0; i<R; i++)
        {
            String s = br.readLine();
            for(int j = 0; j<C; j++)
            {
                board[i][j] = s.charAt(j);
            }
        }
        
        
        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;
        //collect ball
        for(int i = 0; i<R; i++)
        {
            for(int j = 0; j<C; j++)
            {
                if(board[i][j] == 'R')
                {
                    rx = j;
                    ry = i;
                    board[i][j] = '.';
                }
                else if(board[i][j] == 'B')
                {
                    bx = j;
                    by = i;
                    board[i][j] = '.';
                }
            }
        }

        
        System.out.println(bfs(rx,ry,bx,by));
        
    }
}
