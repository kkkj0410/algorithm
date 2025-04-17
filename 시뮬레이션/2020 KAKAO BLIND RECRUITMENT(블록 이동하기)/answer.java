import java.util.*;

class Bot{
    public int x1,y1;
    public int x2,y2;
    public int cnt;
    
    public Bot(int y1, int x1, int y2, int x2, int cnt){
        this.y1 = y1;
        this.x1 = x1;
        this.y2 = y2;
        this.x2 = x2;
        this.cnt = cnt;
    }
    
}

class Solution {
    
    int[][][] rotate = {{{1,0},{0,1}},{{0,1},{-1,0}},{{-1,0},{0,-1}},{{0,-1},{1,0}}};
    int N,M;
    
    public boolean escapeMap(int y, int x){
        return y < 0 || y >= N || x < 0 || x>=M;
    }
        
    
    int[][] move = {{1,0},{0,1},{-1,0},{0,-1}};
    
    int[] moveXY = {-1,1};
    public int bfs(int[][] board){
        Queue<Bot> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][N][M];
        q.add(new Bot(0,0,0,1,0));
        visited[0][0][0][1] = true;
        visited[0][1][0][0] = true;
        
        while(!q.isEmpty()){
            Bot bot = q.poll();
            
            if((bot.y1 == N-1 && bot.x1 == M-1) || (bot.y2 == N-1 && bot.x2 == M-1)){
                return bot.cnt;
            }
            
            //가로
            if(Math.abs(bot.x1 - bot.x2) == 1){
                for(int i = 0; i<2; i++){
                    if(escapeMap(bot.y1 + moveXY[i], bot.x1)){
                        continue;
                    }
                    if(board[bot.y1 + moveXY[i]][bot.x1] == 1 || board[bot.y2 + moveXY[i]][bot.x2] == 1){
                        continue;
                    }
                    
                    if(visited[bot.y1][bot.x1][bot.y1 + moveXY[i]][bot.x1] == false){
                        visited[bot.y1][bot.x1][bot.y1 + moveXY[i]][bot.x1] = true;
                        visited[bot.y1 + moveXY[i]][bot.x1][bot.y1][bot.x1] = true;
                        q.add(new Bot(bot.y1, bot.x1, bot.y1 + moveXY[i], bot.x1, bot.cnt+1));
                    }
                    
                    if(visited[bot.y2][bot.x2][bot.y2 + moveXY[i]][bot.x2] == false){
                        visited[bot.y2][bot.x2][bot.y2 + moveXY[i]][bot.x2] = true;
                        visited[bot.y2 + moveXY[i]][bot.x2][bot.y2][bot.x2] = true;
                        q.add(new Bot(bot.y2, bot.x2, bot.y2 + moveXY[i], bot.x2, bot.cnt+1));
                    }

                }
            }
            
            //세로
            else{
                for(int i = 0; i<2; i++){
                    if(escapeMap(bot.y1, bot.x1 + moveXY[i])){
                        continue;
                    }
                    if(board[bot.y1][bot.x1 + moveXY[i]] == 1 || board[bot.y2][bot.x2 + moveXY[i]] == 1){
                        continue;
                    }
                    
                    if(visited[bot.y1][bot.x1][bot.y1][bot.x1 + moveXY[i]] == false){
                        visited[bot.y1][bot.x1][bot.y1][bot.x1 + moveXY[i]] = true;
                        visited[bot.y1][bot.x1 + moveXY[i]][bot.y1][bot.x1] = true;
                        q.add(new Bot(bot.y1, bot.x1, bot.y1, bot.x1 + moveXY[i], bot.cnt+1));
                    }
                    
                    if(visited[bot.y2][bot.x2][bot.y2][bot.x2 + moveXY[i]] == false){
                        visited[bot.y2][bot.x2][bot.y2][bot.x2 + moveXY[i]] = true;
                        visited[bot.y2][bot.x2 + moveXY[i]][bot.y2][bot.x2] = true;
                        q.add(new Bot(bot.y2, bot.x2, bot.y2, bot.x2 + moveXY[i], bot.cnt+1));
                    }

                }
            }
            
            for(int i = 0; i<4; i++){
                int y1 = bot.y1 + move[i][0];
                int x1 = bot.x1 + move[i][1];
                int y2 = bot.y2 + move[i][0];
                int x2 = bot.x2 + move[i][1];
                
                if(escapeMap(y1,x1) || escapeMap(y2,x2)){
                    continue;
                }
                
                if(board[y1][x1] == 1 || board[y2][x2] == 1){
                    continue;
                }
                
                if(visited[y1][x1][y2][x2] == true){
                    continue;
                }
                
                visited[y1][x1][y2][x2] = true;
                visited[y2][x2][y1][x1] = true;
                q.add(new Bot(y1,x1,y2,x2,bot.cnt+1));
            }
        }
        
        return -1;
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        M = board[0].length;
        
        answer = bfs(board);
        
        return answer;
    }
}
