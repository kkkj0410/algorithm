import java.util.*;
import java.io.*;

class Coin
{
    public int value, cnt;

    public Coin(int value, int cnt)
    {
        this.value = value;
        this.cnt = cnt;
    }
}

public class Main {
    static int[] N = new int[3];

    public static void Resolve(int coinCnt, Coin[] list)
    {
        boolean[] visited = new boolean[100001];
        int total = 0;
        for(int i = 0; i<coinCnt; i++)
        {
            int coin = list[i].value;
            int cnt = list[i].cnt;
            
            for(int j = 1; j<=cnt; j++)
            {
                int CurCoin = coin * j;
                visited[CurCoin] = true;
            }

            total += coin * cnt;
        }

        if(total % 2 == 1)
        {
            System.out.println(0);
            return;
        }
        else if(visited[total/2] == true)
        {
            System.out.println(1);
            return;
        }


        int half = total/2;
        for(int i = 0; i<coinCnt; i++)
        {
            int coin = list[i].value;
            int cnt = list[i].cnt;
            
            boolean[] visited2 = new boolean[coin*cnt+1];
            for(int j = 1; j<=cnt; j++)
            {
                visited2[coin*j] = true;
            } 

            for(int curMoney = half; curMoney>=0; curMoney--)
            {
                if(visited[curMoney] == true)
                {
                    if(curMoney <= coin*cnt)
                    {
                        if(visited2[curMoney] == true)
                        {
                            continue;
                        }
                    }
                    for(int j = 1; j<=cnt; j++)
                    {
                        int cur = curMoney + (coin*j);
                        if(cur > half)
                        {
                            break;
                        }

                        visited[cur] = true;
                    }
                }
            }
        }

        if(visited[half] == true)
        {
            System.out.println(1);
        }
        
        else
        {
            System.out.println(0);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for(int i = 0; i<3; i++)
        {
            st = new StringTokenizer(br.readLine());
            N[i] = Integer.parseInt(st.nextToken());

            int coinCnt = N[i];
            Coin[] list = new Coin[coinCnt];

            for(int j = 0; j<coinCnt; j++)
            {
                st = new StringTokenizer(br.readLine());
                int coin = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                
                list[j] = new Coin(coin,cnt);
            }

            Resolve(coinCnt,list);
        }
    }
}
