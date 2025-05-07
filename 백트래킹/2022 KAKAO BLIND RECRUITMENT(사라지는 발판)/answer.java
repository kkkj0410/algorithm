public class Solution {
    int[][] move = {{-1,0},{1,0},{0,1},{0,-1}};
    boolean[][] visited;
    int answer = -1;
    int row, col;
    public boolean escapeMap(int row, int col){
        
        return row < 0 || row >= this.row || col < 0 || col >= this.col;
    }
    
    public int miniMax(int aY, int aX, int bY, int bX){
        if(visited[aY][aX] == false){
            return 0;
        }
        
        int curTurn = 0;
        for(int i = 0; i<4; i++){
            int moveY = aY + move[i][0];
            int moveX = aX + move[i][1];
            
            if(escapeMap(moveY,moveX) == true){
                continue;
            }
            if(visited[moveY][moveX] == false){
                continue;
            }
            
            visited[aY][aX] = false;
            int nextTurn = miniMax(bY,bX,moveY,moveX) + 1;
            visited[aY][aX] = true;
            
            if(curTurn % 2 == 0 && nextTurn % 2 == 1){
                curTurn = nextTurn;
            }
            //Winner
            //curTurn, nextTurn 모두 같다면 두 턴의 캐릭터 모두 같음
            else if(curTurn % 2 == 1 && nextTurn % 2 == 1){
                curTurn = Math.min(curTurn, nextTurn);
            }
            //Looser
            else if(curTurn % 2 == 0 && nextTurn % 2 == 0){
                curTurn = Math.max(curTurn, nextTurn);
            }
            
        }
        
        return curTurn;
    }
    
    public void setVisited(int[][] board){
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                if(board[i][j] == 1){
                    visited[i][j] = true;
                    continue;
                }
                visited[i][j] = false;
            }
        }
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        row = board.length;
        col = board[0].length;
        
        visited = new boolean[row][col];
        setVisited(board);
        
        answer = miniMax(aloc[0],aloc[1],bloc[0],bloc[1]);
        
        return answer;
    }
}
