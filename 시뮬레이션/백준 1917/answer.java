import java.util.*;
import java.io.*;

class XY{
    public int y,x;
    
    public XY(int y, int x){
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int N = 6;
    static int[][] move = {{1,0},{-1,0},{0,1},{0,-1}};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int curDice = 1; curDice <= 3; curDice++){
            boolean[][] map = new boolean[N][N];
            boolean[] dice = new boolean[N+1];

            for(int y = 0; y<N; y++){
                st = new StringTokenizer(br.readLine());
                for(int x = 0; x<N; x++){
                    if(Integer.parseInt(st.nextToken()) == 1){
                        map[y][x] = true;
                    }else{
                        map[y][x] = false;
                    }
                }
            }

            XY curXY = startXY(map);
            boolean[][] visited = new boolean[N][N];

            dfs(curXY.y, curXY.x,visited ,map, dice);
            if(checkDice(dice) == true){
                System.out.println("yes");
            }
            else{
                System.out.println("no");
            }
        }

    }

    static boolean checkDice(boolean[] dice){
        for(int i = 1; i<=N; i++){
            if(dice[i] == false){
                return false;
            }
        }
        return true;
    }

    static void dfs(int y, int x, boolean[][] visited, boolean[][] map, boolean[] dice){

        dice[3] = true;
        visited[y][x] = true;
        for(int i = 0; i<4; i++){
            int moveY = y + move[i][0];
            int moveX = x + move[i][1];

            if(moveY < 0 || moveY >= N || moveX < 0 || moveX >= N || visited[moveY][moveX] == true || map[moveY][moveX] == false){
                continue;
            }

            int way = i+1;
            rollDice(way, dice);
            dfs(moveY,moveX,visited,map,dice);
            rerollDice(way,dice);
        }
    }

    static void rollDice(int way, boolean[] dice){
        if(way == 1){
            left(dice);
        }

        else if(way == 2){
            right(dice);
        }

        else if(way == 3){
            up(dice);
        }

        else if(way == 4){
            down(dice);
        }
    }

    static void rerollDice(int way, boolean[] dice){
        if(way == 1 || way == 3){
            way++;
        }
        else{
            way--;
        }

        rollDice(way,dice);
    }


    static XY startXY(boolean[][] map){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(map[i][j] == true){
                    return new XY(i,j);
                }
            }
        }
        return new XY(-1,-1);
    }

    static void left(boolean[] dice){
        boolean temp = dice[3];
        dice[3] = dice[2];
        dice[2] = dice[6];
        dice[6] = dice[4];
        dice[4] = temp;
    }


    static void right(boolean[] dice){
        boolean temp = dice[3];
        dice[3] = dice[4];
        dice[4] = dice[6];
        dice[6] = dice[2];
        dice[2] = temp;
    }

    static void up(boolean[] dice){
        boolean temp = dice[3];
        dice[3] = dice[1];
        dice[1] = dice[6];
        dice[6] = dice[5];
        dice[5] = temp;
    }

    static void down(boolean[] dice){
        boolean temp = dice[3];
        dice[3] = dice[5];
        dice[5] = dice[6];
        dice[6] = dice[1];
        dice[1] = temp;
    }



}










