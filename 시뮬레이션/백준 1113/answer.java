import java.util.*;
import java.io.*;

class XY{
    public int y,x;
    
    public XY(int y, int x){
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int N,M;
    static int[][] map;
    static int[][] move = {{1,0},{-1,0},{0,1},{0,-1}};
    
    public static int pourWater(int y, int x, int height){
        Queue<XY>q = new LinkedList<>();
        q.add(new XY(y,x));
        
        int point = 0;
        map[y][x] = height+1;
        point++;
        boolean TN = true;
        while(!q.isEmpty()){
            XY curXY = q.peek();
            q.remove();
            
            for(int i = 0; i<4; i++){
                int moveY = curXY.y + move[i][0];
                int moveX = curXY.x + move[i][1];
                
                
                if(moveY<0 ||moveY >= N || moveX < 0 || moveX >= M || map[moveY][moveX] < height){
                    TN = false;
                    continue;
                }

                if(map[moveY][moveX] != height){
                    continue;
                }

                point++;
                map[moveY][moveX] = height+1;

                q.add(new XY(moveY, moveX));
            }
        }
        
        if(TN == false) return 0;
        return point;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        
        int minHeight = 10;
        int maxHeight = 0;
        for(int i = 0; i<N; i++){
            String str = br.readLine();
            for(int j = 0; j<M; j++){
                map[i][j] = str.charAt(j) - '0';

                minHeight = Math.min(minHeight, map[i][j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }
        
        int point = 0;
        for(int height = minHeight; height<maxHeight; height++){
            for(int y = 1; y<N-1; y++){
                for(int x = 1; x<M-1; x++){
                    if(map[y][x] != height){
                        continue;
                    }
                    point += pourWater(y,x,height);
                }
            }
        }
        
        
        System.out.println(point);
    }
}










