import java.util.*;

class Build{
    public int x,y,fabric;
    
    public Build(int x, int y, int fabric){
        this.x=x;
        this.y=y;
        this.fabric=fabric;
    }
}

class Solution {
    int N;
    int[][][] map;
    boolean[][] columns;
    boolean[][] beams;
    
    public void pop(int[] build){
        int y = build[1];
        int x = build[0];
        int fabric = build[2];
        
        boolean[][] targetArr;
        if(fabric == 0){
            targetArr = columns;
        }
        else{
            targetArr = beams;
        }
        
        targetArr[y][x] = false;
        for(int i = 0; i<=N; i++){
            for(int j = 0; j<=N; j++){
                if((columns[i][j] && checkColumn(i,j) == false) || (beams[i][j] && checkBeam(i,j) == false)){
                    targetArr[y][x] = true;
                }
            }
        }
    }
    
    public void push(int[] build){
        int y = build[1];
        int x = build[0];
        int fabric = build[2];
        
        if(fabric == 0 && checkColumn(y,x) == true){
            columns[y][x] = true;
        }
        else if(fabric == 1 && checkBeam(y,x) == true){
            beams[y][x] = true;
        }
    }
    
    public int[][] setAnswer(){
        Queue<Build>q = new LinkedList<>();
        
        
        for(int x = 0; x<=N; x++){
            for(int y = 0; y<=N; y++){
                if(columns[y][x] == true){
                    q.add(new Build(x,y,0));
                }
                if(beams[y][x] == true){
                    q.add(new Build(x,y,1));
                }

            }
        }
        
        int[][] answer = new int[q.size()][3];
        int cnt = 0;
        while(!q.isEmpty()){
            Build build = q.poll();
            
            answer[cnt][0] = build.x;
            answer[cnt][1] = build.y;
            answer[cnt][2] = build.fabric;
            
            cnt++;
        }
        
        return answer;
    }
    
    public boolean checkColumn(int y, int x){
        //바닥
        if(y == 0){
            return true;
        }
        //아래에 기둥 존재
        else if(y-1 >= 0 && columns[y-1][x] == true){
            return true;
        }
        //같은 위치에 보 존재
        else if(beams[y][x] == true){
            return true;
        }
        //왼쪽에 보 존재
        else if(x-1 >= 0 && beams[y][x-1] == true){
            return true;
        }
        return false;
    }
    
    public boolean checkBeam(int y, int x){
        //보의 한쪽 끝 부분이 기둥
        if(y-1 >= 0 && (columns[y-1][x] == true || (x+1 <= N && columns[y-1][x+1] == true))){
            return true;
        }
        //보의 양쪽 끝이 보
        else if((x-1 >= 0 && beams[y][x-1] == true) && (x+1 <= N && beams[y][x+1] == true)){
            return true;
        }
        
        return false;
    }
    
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer;
        N = n;
        map = new int[n+1][n+1][2];
        columns = new boolean[n+1][n+1];
        beams = new boolean[n+1][n+1];
        
        for(int[] build : build_frame){
            int y = build[1];
            int x = build[0];
            int fabric = build[2];
            int choice = build[3];
            
            if(choice == 1){
                push(build);
            }
            else{
                pop(build);
            }
        }
        answer = setAnswer();
        
        return answer;
    }
}
