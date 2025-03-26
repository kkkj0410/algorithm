import java.util.*;
import java.io.*;

class Cow{
    public int y, x;
    
    public Cow(int y, int x){
        this.y = y;
        this.x = x;
    }
    
}

public class Main {
    static int N,K,R;
    static int[][] map;
    static boolean[][][][] bridge;
    static Cow[] cowList;
    static int[][] move = {{0,0},{1,0},{-1,0},{0,1},{0,-1}};
    
    
    static int bfs(){
        int result = 0;
        
        Queue<Cow> q = new LinkedList<>();
        for(int i = 1; i<=K; i++){
            Cow startCow = cowList[i];
            boolean[][] visited = new boolean[N+1][N+1];
            
            q.add(startCow);
            visited[startCow.y][startCow.x] = true;
            
            while(!q.isEmpty()){
                Cow cow = q.peek();
                q.remove();
                
                int y = cow.y;
                int x = cow.x;
                for(int j = 1; j<=4; j++){
                    int moveY = y + move[j][0];
                    int moveX = x + move[j][1];
                    
                    if(moveY < 1 || moveY > N || moveX < 1 || moveX > N || visited[moveY][moveX] == true || bridge[y][x][moveY][moveX] == true){
                        continue;
                    }
                    
                    visited[moveY][moveX] = true;
                    q.add(new Cow(moveY, moveX));
                }
            }

            result += findCow(i, visited);
        }
        
        return result;
    }
    
    static int findCow(int num, boolean[][] visited){
        int cnt = 0;
        for(int i = num+1; i<=K; i++){
            Cow cow = cowList[i];

            if(visited[cow.y][cow.x] == false){
                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        bridge = new boolean[N+1][N+1][N+1][N+1];
        map = new int[N+1][N+1];
        cowList = new Cow[N+1];
        
        for(int i = 1; i<=R; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int rr = Integer.parseInt(st.nextToken());
            int cc = Integer.parseInt(st.nextToken());
            
            bridge[r][c][rr][cc] = true;
            bridge[rr][cc][r][c] = true;
        }
        
        for(int i = 1; i<=K; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            
            map[y][x] = i;
            cowList[i] = new Cow(y,x);
        }
        
        
        System.out.println(bfs());
    }
}
