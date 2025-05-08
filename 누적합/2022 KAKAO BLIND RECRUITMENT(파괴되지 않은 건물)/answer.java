class Solution {
    int N,M;
    int[][] command;
    
    public void setCommand(int[][] board, int[][] skill){
        command = new int[N+1][M+1];
        
        for(int i = 0; i<skill.length; i++){
            int num = skill[i][0];
            int y = skill[i][1];
            int x = skill[i][2];
            int ny = skill[i][3];
            int nx = skill[i][4];
            int value = num == 1 ? -skill[i][5] : skill[i][5];
            
            command[y][x] += value;
            command[ny+1][x] += -value;
            
            command[y][nx+1] += -value;
            command[ny+1][nx+1] += value;
        }
        
        int sum = 0;
        for(int x = 0; x<M; x++){
            for(int y = 0; y<N; y++){
                sum += command[y][x];
                command[y][x] = sum;
            }
            sum = 0;
        }
        
        sum = 0;
        for(int y = 0; y<N; y++){
            for(int x = 0; x<M; x++){
                sum += command[y][x];
                command[y][x] = sum;
            }
            sum = 0;
        }
    }
    
    public void resolve(int[][] board){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                board[i][j] += command[i][j];
            }
        }
    }
    
    public int countBuilding(int[][] board){
        int cnt = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(board[i][j] > 0){
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    public void printBoard(int[][] board){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        N = board.length;
        M = board[0].length;
        
        setCommand(board, skill);
        resolve(board);
        
        //printBoard(board);
        
        answer = countBuilding(board);
        
        
        return answer;
    }
}
