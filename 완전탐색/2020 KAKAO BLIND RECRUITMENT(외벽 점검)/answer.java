import java.util.*;

class Solution {
    
    int[] dist;
    int[][] Weak;
    int N;
    
    public int Brute(int[] weak, int[] dist){
        int L = 0;
        int R = 0;
        int start = weak[0];
        while(L < weak.length && R < dist.length){
            if(start <= weak[L] && weak[L] <= start + dist[R]){
                L++;
            }
            else{
                start = weak[L];
                R++;
            }
        }
        
        if(L == weak.length){
            return R+1;
        }
        
        return Integer.MAX_VALUE;
    }
    
    
    int result = Integer.MAX_VALUE;
    public void dfs(int[] distPer, int cnt, boolean[] visited){
        if(cnt == dist.length){
            for(int[] weak : Weak){
                result = Math.min(Brute(weak,distPer), result);
            }

            return;
        }
        
        for(int i = 0; i<dist.length; i++){
            if(visited[i] == true){
                continue;
            }
            
 
            visited[i] = true;
            distPer[cnt] = dist[i];
            dfs(distPer,cnt+1,visited);
            visited[i] = false;
        }
    }
    
    
    public void makeWeak(int[] weak){
        int weakSize = weak.length;
        Weak = new int[weakSize][weakSize];
        
        for(int i = 0; i<weakSize; i++){
            Weak[0][i] = weak[i];
        }

        for(int i = 1; i<weakSize; i++){
            
            int first = weak[0];
            for(int j = 0; j<weakSize-1; j++){
                weak[j] = weak[j+1];
            }
            
            weak[weakSize-1] = first + N;
 
            
            for(int j = 0; j<weakSize; j++){
                Weak[i][j] = weak[j];
            }
        }
    }
    
    
    public int solution(int n, int[] weak, int[] dist) {
        this.dist = dist;
        int answer = 0;
        N = n;
        makeWeak(weak);
        
        boolean[] visited = new boolean[dist.length];
        int[] distPer = new int[dist.length];
        
        dfs(distPer,0,visited);
        
        
        if(result == Integer.MAX_VALUE){
            answer = -1;
        }
        else{
            answer = result;
        }
        return answer;
    }
}
