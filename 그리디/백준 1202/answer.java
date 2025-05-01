import java.util.*;
import java.io.*;

class Gem implements Comparable<Gem>{
    public int M,V;
    
    public Gem(){
        
    }
    
    public Gem(int M, int V){
        this.M = M;
        this.V = V;
    }
    

    //보석 크기 오름차순
    //크기가 같으면 보석 가치 내림차순 
    @Override
    public int compareTo(Gem o){
        if(o.M == this.M){
            return o.V - this.V;
        }

        return this.M - o.M;
    }

}

public class Main {
    static int N,K;
    static Gem[] gemList;
    static int[] bagList;
    static boolean[] visitedBagList;
    
    public static Long Resolve(){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Long result = 0L;

        int j = 0;
        for(int i = 0; i<K; i++){
            while(j < N && bagList[i] >= gemList[j].M){
                pq.add(gemList[j++].V);
            }

            if(!pq.isEmpty()){
                result += pq.poll();
            }
        }


        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        gemList = new Gem[N];
        bagList = new int[K];
        visitedBagList = new boolean[K];
        
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            gemList[i] = new Gem(M,V);
        }
        
        for(int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            bagList[i] = Integer.parseInt(st.nextToken());
        }
        
        
        Arrays.sort(gemList);
        Arrays.sort(bagList);
    
        System.out.println(Resolve());
    }
}
