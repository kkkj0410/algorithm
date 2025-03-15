import java.util.*;
import java.io.*;

class Data implements Comparable<Data>{
    public int n;
    public long dis;

    public Data(int n, long dis){
        this.n = n;
        this.dis = dis;
    }

    @Override
    public int compareTo(Data o) {
        return Long.compare(this.dis, o.dis);
    }
}

public class Main {
    static int N, M;
    static List<Data>[] map;
    static long MAX = Long.MAX_VALUE;
    static long[] v;

    public static void dijkstra(){
        int start = 1;
        boolean[] visited = new boolean[N+1];
        v = new long[N+1];
        
        v[start] = 0;
        
        for(int i = 2; i<=N; i++){
            v[i] = MAX;
        }

        PriorityQueue<Data> pq = new PriorityQueue<>();
        pq.add(new Data(1,0));
        while(!pq.isEmpty()){
        
            Data data = pq.poll();
            if(visited[data.n] == true){
                continue;
            }
            visited[data.n] = true;

            int target = data.n;

            for(int i = 0; i<map[target].size(); i++){
                Data curData = map[target].get(i);
                int arrive = curData.n;
                long dis = curData.dis;

                
                long comp = -1;
                if(v[target]%M >= dis){
                    comp = v[target] + (dis + (M-v[target]%M));
                }
                else{
                    comp = v[target] + (dis - v[target]%M);
                }

                if(v[arrive] > comp){
                    v[arrive] = comp;
                    pq.add(new Data(arrive, v[arrive]));
                }
            }

        }
        
        
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new ArrayList[N+1];

        for(int i = 1; i<=N; i++){
            map[i] = new ArrayList<Data>();
        }


        for(int i = 1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());


            map[s].add(new Data(e,i));
            map[e].add(new Data(s,i));

        }
        
        dijkstra();
        System.out.println(v[N]);
    }
}
