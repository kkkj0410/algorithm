import java.util.*;

class Solution {
    int N;
    int answer = 1;
    boolean[] visited;
    
    public void dfs(int[] info,int[][] edges, int sheep, int wolf){
        answer = Math.max(sheep, answer);
        
        for(int[] edge : edges){
            if(visited[edge[0]] == true && visited[edge[1]] == false){
                int nextNode = edge[1];
                int animal = info[edge[1]];
                if(animal == 1 && sheep <= wolf+1){
                    continue;
                }
                
                visited[nextNode] = true;
                if(animal == 1){
                    dfs(info,edges,sheep,wolf+1);
                }
                else{
                    dfs(info,edges,sheep+1,wolf);
                }
                visited[nextNode] = false;
            }
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        N = info.length;
        
        visited = new boolean[N];
        visited[0] = true;
        dfs(info, edges, 1, 0);
        
        
        return answer;
    }
}
