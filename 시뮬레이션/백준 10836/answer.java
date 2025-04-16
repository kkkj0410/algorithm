import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] map;
    static int[] growth;

    static void targetOne(){
        for(int i = N-1; i>=0; i--){
            map[i][0] = 1;
            map[0][i] = 1;
        }
    }

    static void targetGrowth(){
        int sum = 1;
        for(int i = N-1; i>=0; i--){
            sum += growth[N - (i+1)];
            map[i][0] = sum;
        }
        
        for(int i = 1; i<N; i++){
            sum += growth[N + (i-1)];
            map[0][i] = sum;
        }
    }
    
    static void mapGrowth(){
        for(int y = 1; y<N; y++){
            for(int x = 1; x<N; x++){
                map[y][x] = map[y-1][x];
            }
        }
    }
    
    static void printMap(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        targetOne();

        growth = new int[2*N];

        int zero,one,two;
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            
            zero = Integer.parseInt(st.nextToken());
            one = Integer.parseInt(st.nextToken());
            two = Integer.parseInt(st.nextToken());
            
            int startOne = zero;
            int startTwo = zero + one;

            growth[startOne]++;
            growth[startTwo]++;
        }
        targetGrowth();
        
        mapGrowth();
        
        printMap();
        
    }
}
