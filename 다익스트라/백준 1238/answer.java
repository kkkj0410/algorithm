import java.util.*;
import java.io.*;


public class Main {
    static int N,M,X;
    static int MAX = 1000000;

    public static int[] resolve(int s, int[][] map){

        int[] v = new int[N+1];
        boolean[] visited = new boolean[N+1];

        for(int i = 1; i<=N; i++){
            v[i] = MAX;
        }
        
        for(int i = 1; i<=N; i++){
            if(map[s][i] == 0 || s==i){
                continue;
            }

            v[i] = map[s][i];
        }

        v[s] = 0;
        visited[s] = true;


        boolean TN = true;
        while(TN){
            int target = 0;
            int dis = MAX;

            TN = false;
            for(int i = 1; i<=N; i++){
                if(visited[i] == true){
                    continue;
                }

                TN = true;
                if(dis > v[i]){
                    target = i;
                    dis = v[i];
                }

            }

            if(TN == false){
                break;
            }

            visited[target] = true;
            for(int i = 1; i<=N; i++){
                if(map[target][i] == 0){
                    continue;
                }

                if(v[i] > v[target] + map[target][i]){
                    v[i] = v[target] + map[target][i];
                }
            }

        }

        return v;
    }
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[N+1][N+1];
        int[][] map2 = new int[N+1][N+1];

        for(int i = 1; i<=M; i++){
            
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
        

            map[s][e] = dis;
            map2[e][s] = dis;
        }
        
        int[] v = resolve(X, map);
        int[] v2 = resolve(X,map2);
        
        int result = 0;
        for(int i =1; i<=N; i++){
            if(i == X){
                continue;
            }
            
            int sum = v[i] + v2[i];
            result = Math.max(sum,result);
            
        }
        System.out.println(result);
    }
}
