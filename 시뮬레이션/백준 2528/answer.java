import java.util.Scanner;

class bar
{
    public int dis,d;
    
    bar(int dis, int d)
    {
        this.dis = dis;
        this.d = d;
    }
}


public class Main {
    public static boolean TN(bar a, bar b, int cnt, int MAX_L)
    {
        //예외 처리
        if(a.dis == MAX_L || b.dis == MAX_L)
        {
            return true;
        }
        
        //s = 시작점, e = 끝점
        int s1,e1;
        int s2,e2;
        
        int a_remain = MAX_L - a.dis;
        int b_remain = MAX_L - b.dis;
        
        // x_move = 실제 움직이는 횟수(왕복 횟수 없앰)
        int a_move = cnt % (a_remain*2);
        int b_move = cnt % (b_remain*2);
        
        //a의 막대기가 벽에 부딪히는 경우
        if(a_move > a_remain)
        {
            a_move = a_move % a_remain;
            
            if(a.d == 0)
            {
                e1 = MAX_L - a_move;
                s1 = MAX_L - (a.dis + a_move);
            }
            else
            {
                s1 = a_move;
                e1 = a.dis + a_move;
            }
        }
        
        else
        {
            if(a.d == 0)
            {
                s1 = a_move;
                e1 = a.dis + a_move;
            }
            else
            {
                e1 = MAX_L - a_move;
                s1 = MAX_L - (a.dis + a_move);
            }
        }
        
        //b의 막대기가 벽에 부딪히는 경우
        if(b_move > b_remain)
        {
            b_move = b_move % b_remain;
            
            if(b.d == 0)
            {
                e2 = MAX_L - b_move;
                s2 = MAX_L - (b.dis + b_move);
            }
            else
            {
                s2 = b_move;
                e2 = b.dis + b_move;
            }
        }
        
        else
        {
            if(b.d == 0)
            {
                s2 = b_move;
                e2 = b.dis + b_move;
            }
            else
            {
                e2 = MAX_L - b_move;
                s2 = MAX_L - (b.dis + b_move);
            }
        }
        
        //a, b의 막대기가 겹치지 않는 경우
        if(s1 > e2 || s2 > e1)
        {
            return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        int N, L;
        Scanner scanner = new Scanner(System.in);
        
        N = scanner.nextInt();
        L = scanner.nextInt();
        
        bar[] arr = new bar[N+1];
        
        for(int i = 1; i<=N; i++)
        {
            int dis, d;
            
            dis = scanner.nextInt();
            d = scanner.nextInt();
            
            arr[i] = new bar(dis, d);
        }
        
        int cnt = 0;
        for(int i = 1; i<N; i++)
        {
            bar a = arr[i];
            bar b = arr[i+1];
            
            while(true)
            {
                if(TN(a,b,cnt,L))
                {
                    break;
                }
                
                //해당 층에서 못 올라가면 시간을 1초 늘림 
                cnt++;
            }
            
        }

        System.out.print(cnt);
        
    }
}
