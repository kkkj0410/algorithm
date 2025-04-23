import java.util.*;
import java.io.*;

class Star
{
    public int y,x;
    
    public Star(int y, int x)
    {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int[] star;
    static int[] dp;
    static int N,M,L,K;
    static List<Star> starList = new ArrayList<>();
    
    public static int SetTramp(int y, int x)
    {
        int cnt = 0;
        for(int i = 0; i<K; i++)
        {
            Star star = starList.get(i);
            
            if(star.y >= y && star.y <= y+L && star.x >= x && star.x <= x+L)
            {
                cnt++;
            }
        }
        
        return cnt;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        star = new int[N+1];
        
        for(int i = 0; i<K; i++)
        {
            st = new StringTokenizer(br.readLine());

            int x,y;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            
            starList.add(new Star(y,x));
        }
        
        int result = 0;
        for(int i = 0; i<K; i++)
        {
            Star star1 = starList.get(i);
            for(int j = 0; j<K; j++)
            {
                Star star2 = starList.get(j);
                result = Math.max(result,SetTramp(star2.y,star1.x));
            }

        }
        System.out.println(K-result);
    }
}
