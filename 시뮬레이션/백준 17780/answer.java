import java.util.*;
import java.io.*;

class Chess{
    public int y, x, way, h;
    
    public Chess(int y, int x, int way, int h){
        this.y = y;
        this.x = x;
        this.way = way;
        this.h = h;
    }
}

public class Main {
    static int N,K;
    static int[][] colorMap;
    static int[][][] chessMap;
    static Chess[] chessList;
    static int[][] move = {{0,0},{0,1},{0,-1},{-1,0},{1,0}};
    static boolean TN = false;

    public static void moveChess(){
        for(int i = 1; i<=K; i++){

            Chess chess = chessList[i];
            
            if(chess.h != 1){
                continue;
            }
            
            int moveY = chess.y + move[chess.way][0];
            int moveX = chess.x + move[chess.way][1];
            
            
            if(escapeMap(moveY,moveX) == true || colorMap[moveY][moveX] == 2){
                if(chess.way == 1 || chess.way == 3){
                    chess.way++;
                }
                else{
                    chess.way--;
                }
                
                int nextMoveY = chess.y + move[chess.way][0];
                int nextMoveX = chess.x + move[chess.way][1];
                if(escapeMap(nextMoveY,nextMoveX) == true || colorMap[nextMoveY][nextMoveX] == 2){
                    continue;
                }
                
                else{
                    moveGroup(chess);
                }
                
            }
            
            else{
                moveGroup(chess);
            }
        }
    }
    
    public static void moveGroup(Chess chess){
        if(chess.h != 1){
            return;
        }
        
        int y = chess.y;
        int x = chess.x;
        int moveY = chess.y + move[chess.way][0];
        int moveX = chess.x + move[chess.way][1];
        
        int curHeight = 0;
        for(int i = 1; i<=K && chessMap[y][x][i] != 0; i++){
            curHeight++;
        }

        int oponentHeight = 0;
        for(int i = 1; i<=K && chessMap[moveY][moveX][i] != 0; i++){
            oponentHeight++;
        }
        
        if(curHeight + oponentHeight >= 4){
            TN = true;
        }

        if(colorMap[moveY][moveX] == 1){
            int buildHeight = oponentHeight + 1;
            for(int i = curHeight; i>=1; i--){
                int chessIdx = chessMap[y][x][i];
                Chess curChess = chessList[chessIdx];
                
                chessMap[y][x][i] = 0;
                chessMap[moveY][moveX][buildHeight] = chessIdx;
                curChess.h = buildHeight++;
                curChess.y = moveY;
                curChess.x = moveX;
            }
            

        }
        
        else{
            int buildHeight = oponentHeight + 1;
            for(int i = 1; i<=curHeight; i++){
                int chessIdx = chessMap[y][x][i];
                Chess curChess = chessList[chessIdx];
                
                chessMap[y][x][i] = 0;
                chessMap[moveY][moveX][buildHeight] = chessIdx; 
                curChess.h = buildHeight++;
                curChess.y = moveY;
                curChess.x = moveX;
            }

        }
    }
    
    public static boolean escapeMap(int y, int x){
        if(y < 1 || y > N || x < 1 || x > N){
            return true;
        }
        return false;
    }
    
    public static boolean sucess(){
        for(int i = 1; i<=K; i++){
            Chess chess = chessList[i];
            
            if(chess.h == K){
                return true;
            }
            
        }
        return false;
    }
    
    static void printChessMap(){

        System.out.println("-------chessMap--------");
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                System.out.print(chessMap[i][j][1] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        colorMap = new int[N+1][N+1];
        chessMap = new int[N+1][N+1][K+1];
        chessList = new Chess[K+1];
        
        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++){
                colorMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 1; i<=K; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int way = Integer.parseInt(st.nextToken());
            
            chessList[i] = new Chess(y,x,way,1);
            chessMap[y][x][1] = i;
        }
        
        
        int cnt = 1;
        while(cnt <=1000){
            moveChess();
            if(TN == true){
                System.out.println(cnt);
                break;
            }

            cnt++;
        }
        if(TN == false){
            System.out.println(-1);
        }
        
    }
}
