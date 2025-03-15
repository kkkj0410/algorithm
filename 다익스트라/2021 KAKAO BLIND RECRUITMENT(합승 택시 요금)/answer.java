import java.util.*;

class Node implements Comparator<Node>{
    public int node,value;
    
    public Node(){
        
    }
    
    public Node(int node, int value){
        this.node = node;
        this.value = value;
    }
    
    @Override
    public int compare(Node node1, Node node2){
        return node1.value - node2.value;
    }
    
}

class Solution {
    int N;
    public int[] dijkstra(int start, int[][] graph){
        int[] v = new int[N+1];
        for(int i = 1; i<=N; i++){
            v[i] = Integer.MAX_VALUE;
        }
        
        v[start] = 0;
        boolean[] visited = new boolean[N+1];
        
        PriorityQueue<Node> pq = new PriorityQueue<>(new Node());
        pq.add(new Node(start, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int curValue = cur.value;
            int curNode = cur.node;
            
            if(visited[curNode] == true){
                continue;
            }
            
            visited[curNode] = true;
            
            for(int i = 0; i<graph[curNode].length; i++){
                if(graph[curNode][i] == 0 || visited[i] == true){
                    continue;
                }
                
                int node = i;
                int value = v[curNode] + graph[curNode][i];
                
                if(v[node] > value){
                    v[node] = value;
                    pq.add(new Node(node, value));
                }
            }
            
        }
        
        return v;
    }
    
    public int findPrice(int s, int a, int b, int[][] graph){
        int total = Integer.MAX_VALUE;
        
        int[] v = dijkstra(s,graph);
        
        for(int i = 1; i<=N; i++){
            if(v[i] == Integer.MAX_VALUE){
                continue;
            }
            
            int value = v[i];
            
            int[] curV = dijkstra(i, graph);
            value += curV[a];
            value += curV[b];
            
            
            //System.out.println("현재 " + i + " : " + v[i] + ", " + curV[a] + ", " +curV[b]);
            
            total = Math.min(value, total);
        }
        
        return total;
    }
    
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        N = n;
        
        int[][] graph = new int[N+1][N+1];
        for(int i = 0; i<fares.length; i++){
            int node1 = fares[i][0];
            int node2 = fares[i][1];
            int value = fares[i][2];
            
            graph[node1][node2] = value;
            graph[node2][node1] = value;
        }
        
        dijkstra(s,graph);
        answer = findPrice(s,a,b,graph);
        
        
        return answer;
    }
}
