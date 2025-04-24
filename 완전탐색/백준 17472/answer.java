import java.util.*;
import java.io.*;

class XY{
    public int y, x;
    
    public XY(int y, int x){
        this.y = y;
        this.x = x;
    }

}

class Data implements Comparable<Data>{
    public int num,dis;
    
    public Data(int num, int dis){
        this.num = num;
        this.dis = dis;
    }
    
    @Override
    public int compareTo(Data o){
        return this.dis - o.dis;
    }
}

public class Main {
    static int[][] map;
    static List<XY>[] islandList;
    static int[][] graph = new int[7][7];
    static int N,M;
    static int[][] move = {{-1,0},{1,0},{0,-1},{0,1}};
    static int totalIsland = 0;
    
    public static int MakeIsland(){
        boolean[][] visited = new boolean[N][M];
        
        int sequence = 1;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(visited[i][j] == true || map[i][j] == 0){
                    continue;
                }
                
                Queue<XY> q = new LinkedList<>();
                q.add(new XY(i,j));
                visited[i][j] = true;
                islandList[sequence].add(new XY(i,j));


                while(!q.isEmpty()){
                    XY curXY = q.peek();
                    q.remove();
                    
                    map[curXY.y][curXY.x] = sequence;

                    for(int k = 0; k<4; k++){
                        int moveY = curXY.y + move[k][0];
                        int moveX = curXY.x + move[k][1];
                        
                        if(moveY < 0 || moveY >= N || moveX < 0 || moveX>=M || visited[moveY][moveX] == true || map[moveY][moveX] == 0){
                            continue;
                        }
                        
                        visited[moveY][moveX] = true;
                        islandList[sequence].add(new XY(moveY,moveX));
                        q.add(new XY(moveY,moveX));
                            
                        }
                }
                
                sequence++;
                
            }
        }
        
        return sequence-1;
    }
    
    public static void MakeBridge(){
        for(int i = 1; i<=totalIsland; i++){
            int islandNum = i;
            int size = islandList[i].size();
            for(int j = 0; j<size; j++){
                XY curXY = islandList[i].get(j);
                
                for(int k = 0; k<4; k++){
                    int cnt = 0;
                    int moveY = curXY.y;
                    int moveX = curXY.x;
                    
                    while(true){
                        moveY += move[k][0];
                        moveX += move[k][1];
                        if(moveY < 0 || moveY >= N || moveX < 0 || moveX >= M){
                            break;
                        }
                        
                        if(cnt <= 1 && map[moveY][moveX] != 0){
                            break;
                        }
                        
                        if(map[moveY][moveX] == islandNum){
                            break;
                        }
                        
                        if(map[moveY][moveX] != 0 && cnt >= 2){
                            int s = islandNum;
                            int e = map[moveY][moveX];
                            
                            graph[s][e] = Math.min(graph[s][e], cnt);
                            graph[e][s] = Math.min(graph[e][s], cnt);
                            
                            break;
                        }
                        
                        cnt++;
                    }
                    
                }
            }
        }
    }
    
    
    public static void dijkstra(int num, int[] v, int[] arr){
        boolean[] visited = new boolean[totalIsland+1];


        for(int i = 1; i<=totalIsland; i++){
            v[i] = Integer.MAX_VALUE;
            arr[i] = Integer.MAX_VALUE;
        }
        
        arr[num] = 0;
        v[num] = 0;
        
        PriorityQueue<Data> pq = new PriorityQueue<>();
        pq.add(new Data(num,0));
        
        while(!pq.isEmpty()){
            Data data = pq.poll();
            
            if(visited[data.num] == true){
                continue;
            }
            
            visited[data.num] = true;
            for(int i = 1; i<=totalIsland; i++){
                if(graph[data.num][i] == Integer.MAX_VALUE){
                    continue;
                }
                
                if(v[i] <= v[data.num] + graph[data.num][i]){
                    continue;
                }

                arr[i] = graph[data.num][i];
                v[i] = v[data.num] + graph[data.num][i];
                pq.add(new Data(i, v[i]));
                
            }
            
        }
    }
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        islandList = new ArrayList[7];
        
        for(int i = 1; i<=6; i++){
            for(int j = 1; j<=6; j++){
                graph[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for(int i = 1; i<=6; i++){
            islandList[i] = new ArrayList<XY>();
        }
        
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        totalIsland = MakeIsland();

        MakeBridge();

        int result = Integer.MAX_VALUE;
        for(int i = 1; i<=totalIsland; i++){
            int[] v = new int[totalIsland+1];
            int[] arr = new int[totalIsland+1];
            dijkstra(i, v, arr);
            
            int cur = 0;
            for(int j = 1; j<=totalIsland; j++){
                if(arr[j] == Integer.MAX_VALUE){
                    cur = -1;
                    break;
                }
                
                else{
                    cur += arr[j];
                }
            }

            if(cur == -1){
                continue;
            }
            result = Math.min(cur,result);
        }
        
        if(result == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }
    }
}
