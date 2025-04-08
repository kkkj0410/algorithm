import java.util.*;
import java.io.*;

class Chess
{
    public int y,x,way;
    
    Chess(int y, int x, int way)
    {
        this.y = y;
        this.x = x;
        this.way = way;
    }
}

public class Main {
    public static int N,K;
    public static int[][] ColorMap;
    public static int[][][] ChessMap;
    public static int[][] move = {{0,0},{0,1},{0,-1},{-1,0},{1,0}};
    public static List<Chess> ChessList = new ArrayList<>();
    
    public static int FindChessHeight(Chess chess, int ChessOrder)
    {
        int Height = 0;
        for(int j = 0; j<4; j++)
        {
            if(ChessMap[chess.y][chess.x][j] == ChessOrder)
            {
                Height = j;
                break;
            }
        }
        
        return Height;
    }
    
    public static int FindChessBlank(int y, int x)
    {
        int Height = 0;
        for(int j = 0; j<4; j++)
        {
            if(ChessMap[y][x][j] == 0)
            {
                Height = j;
                break;
            }
        }
        return Height;
    }
    
    public static boolean Move()
    {
        for(int i = 0; i<K; i++)
        {
            Chess chess = ChessList.get(i);
            int ChessOrder = i+1;
            int MoveY = chess.y + move[chess.way][0];
            int MoveX = chess.x + move[chess.way][1];
            
            int CurHeight = 0;
            CurHeight = FindChessHeight(chess, ChessOrder);
            
            
            //wall
            if(MoveY < 0 || MoveY >= N || MoveX < 0 || MoveX >= N || ColorMap[MoveY][MoveX] == 2)
            {
                if(chess.way == 1 || chess.way == 3)
                {
                    chess.way = chess.way+1;
                }
                
                else
                {
                    chess.way = chess.way-1;
                }
                
                int ny = chess.y + move[chess.way][0];
                int nx = chess.x + move[chess.way][1];
                
                if(ny < 0 || ny >= N || nx < 0 ||nx >=N || ColorMap[ny][nx] == 2)
                {
                    continue;
                }
                
                
                MoveY = ny;
                MoveX = nx;
            }
            
            
            //white and red
            if(ColorMap[MoveY][MoveX] == 0 || ColorMap[MoveY][MoveX] == 1)
            {
                int CurChessIndex = i; 
                Chess CurChess = ChessList.get(CurChessIndex);
                int CurY = CurChess.y;
                int CurX = CurChess.x;
                
                
                int NextHeight = 0;
                NextHeight = FindChessBlank(MoveY,MoveX);
                
                //white
                if(ColorMap[MoveY][MoveX] == 0)
                {
                    for(int j = CurHeight; j<4; j++)
                    {
                        if(ChessMap[CurY][CurX][j] == 0)
                        {
                            break;
                        }
                        

                        CurChessIndex = ChessMap[CurY][CurX][j] - 1;
                        CurChess = ChessList.get(CurChessIndex);
                        CurChess.y = MoveY;
                        CurChess.x = MoveX;
                        
                        ChessMap[MoveY][MoveX][NextHeight++] = ChessMap[CurY][CurX][j];
                        ChessMap[CurY][CurX][j] = 0;
                        
                        //end
                        if(NextHeight == 4)
                        {
                            return true;
                        }
                    }
                }
                
                //red
                else
                {
                    for(int j = 3; j>=CurHeight; j--)
                    {
                        if(ChessMap[CurY][CurX][j] == 0)
                        {
                            continue;
                        }
                        
                        CurChessIndex = ChessMap[CurY][CurX][j] - 1;
                        CurChess = ChessList.get(CurChessIndex);
                        CurChess.y = MoveY;
                        CurChess.x = MoveX;
                        
                        ChessMap[MoveY][MoveX][NextHeight++] = ChessMap[CurY][CurX][j];
                        ChessMap[CurY][CurX][j] = 0;

                        //end
                        if(NextHeight == 4)
                        {
                            return true;
                        }
                    }
                }
                
            }
            
        }
        
        return false;
    }
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        
         N = Integer.parseInt(st.nextToken());
         K = Integer.parseInt(st.nextToken());
         
         ColorMap = new int[N][N];
         ChessMap = new int[N][N][4];
         
         for(int i = 0; i<N; i++)
         {
             st = new StringTokenizer(br.readLine());
             for(int j = 0; j<N; j++)
             {
                 ColorMap[i][j] = Integer.parseInt(st.nextToken());
             }
         }
         
         for(int i = 0; i<K; i++)
         {
             st = new StringTokenizer(br.readLine());
             int y = Integer.parseInt(st.nextToken());
             int x = Integer.parseInt(st.nextToken());
             int way = Integer.parseInt(st.nextToken());
             
             ChessList.add(new Chess(y-1,x-1,way));
             
             ChessMap[y-1][x-1][0] = i+1;
         }
         
         
         int t;
         for(t = 1; t<=1000; t++)
         {
             if(Move() == true)
             {
                 System.out.println(t);
                 break;
             }
         }
         
         if(t > 1000)
         {
             System.out.println(-1);
         }
        

    }
}
