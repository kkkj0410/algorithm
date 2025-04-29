class Solution {
    
    //시계 방향(1~3) - 90도*n
    int[][] rotate(int choice, int[][] board){
        if(choice == 0){
            return board;
        }
        
        int n = board.length;
        
        int[][] rotateBoard = new int[n][n];
        
        int i = 0;
        int j = 0;
        if(choice == 1){
            for(int x = 0; x<n; x++){
                for(int y = n-1; y >= 0; y--){
                    rotateBoard[i][j] = (board[y][x] == 1) ? 1 : 0;
                    j++;
                }
                i++;
                j=0;
            }
        }
        
        else if(choice == 2){
            for(int y = n-1; y >= 0; y--){
                for(int x = n-1; x >= 0; x--){
                    rotateBoard[i][j] = (board[y][x] == 1) ? 1 : 0;
                    j++;
                }
                i++;
                j=0;
            }
        }
        
        else if(choice == 3){
            for(int x = n-1; x >= 0; x--){
                for(int y = 0; y < n; y++){
                    rotateBoard[i][j] = board[y][x];
                    j++;
                }
                i++;
                j=0;
            }
        }
        
        return rotateBoard;
    }


    public boolean checked(int[][] key, int[][] lock){
        int n = lock.length;
        int m = key.length;
        
        int s = m-1;
        int e = n - m;
        for(int curY = s; curY<=e; curY++){
            for(int curX = s; curX<=e; curX++){
                if(lock[curY][curX] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    int[][] sizeUpLock(int[][] key, int[][] lock){
        int n = lock.length;
        int m = key.length;
        
        int changeSize = n+(m-1)*2;
        int[][] changeLock = new int[changeSize][changeSize];
        
        int s = m-1;
        int e = m-1 + n-1;
        
        int i = 0;
        int j = 0;
        for(int y = s; y<=e; y++){
            for(int x = s; x<=e; x++){
                changeLock[y][x] = lock[i][j];
                j++;
            }
            j=0;
            i++;
        }
        return changeLock;
    }

    int[][] fetchLock(int[][] key, int[][] lock, int y, int x){
        int n = lock.length;
        int m = key.length;
        
        int[][] newLock = new int[n][n];
        
        int i = 0;
        int j = 0;
        for(int curY = y; curY<y+m; curY++){
            for(int curX = x; curX<x+m; curX++){
                newLock[curY][curX] = key[i][j];
                j++;
            }
            j=0;
            i++;
        }
        
        int s = m-1;
        int e = n - m;
        for(int curY = s; curY<=e; curY++){
            for(int curX = s; curX<=e; curX++){
                if(lock[curY][curX] == 1){
                    //key, lock이 서로 돌기면 0으로 처리
                    newLock[curY][curX] = newLock[curY][curX] == 1 ? 0 : 1;
                }
            }
        }
        
        return newLock;
    }
    
    boolean resolve(int[][] key, int[][] lock){
        int n = lock.length;
        int m = key.length;
        
        for(int y = 0; y<n-(m-1); y++){
            for(int x = 0; x<n-(m-1); x++){
                for(int choice = 0; choice < 4; choice++){
                    int[][] curKey = rotate(choice, key);
                    int[][] curLock = fetchLock(curKey, lock, y, x);
                    if(checked(key, curLock) == true){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    void printBoard(int[][] board){
        int n = board.length;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        
        lock = sizeUpLock(key, lock);
        answer = resolve(key,lock);
        

        return answer;
    }
}
