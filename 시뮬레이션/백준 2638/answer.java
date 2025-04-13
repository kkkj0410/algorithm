import java.util.*;
import java.io.*;

class XY{
    public int y, x;
    public XY(int y, int x){
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int N,M;
    static int[][] map;
    static int[][] move = {{1,0},{-1,0},{0,1},{0,-1}};
    
    public static boolean bfs(){
        int[][] copyMap = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        CopyMap(map, copyMap);
        
        Queue<XY> q = new LinkedList<>();
        q.add(new XY(0,0));
        copyMap[0][0] = 2;
        
        boolean TN = true;
        while(!q.isEmpty()){
            XY curXY = q.peek();
            q.remove();
            
            for(int i = 0; i<4; i++){
                int moveY = curXY.y + move[i][0];
                int moveX = curXY.x + move[i][1];
                
                if(moveY < 0 || moveY >= N || moveX < 0 || moveX >= M){
                    continue;
                }
                
                
                if(map[moveY][moveX] == 1){
                    if(visited[moveY][moveX] == true){
                        map[moveY][moveX] = 0;
                        TN = false;
                    }
                    else{
                        visited[moveY][moveX] = true;
                    }
                }

                if(copyMap[moveY][moveX] != 0){
                    continue;
                }

                copyMap[moveY][moveX] = 2;
                q.add(new XY(moveY,moveX));
                
            }
        }
         
        return TN;
    }

    public static void CopyMap(int[][] realMap, int[][] fakeMap){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                fakeMap[i][j] = realMap[i][j];
            }
        }
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    
        map = new int[N][M];
        
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int cnt = 0;
        while(bfs() == false){
            cnt++;
        }
        
        System.out.println(cnt);
        
    }
}
