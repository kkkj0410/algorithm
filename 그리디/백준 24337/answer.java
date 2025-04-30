import java.util.*;
import java.io.*;

class Main {
    static int N,a,b;
    static List<Integer> map = new LinkedList<>();
    
    public static boolean Resolve()
    {
        if(a+b > N+1)
        {
            return false;
        }

        for(int i = 1; i<a; i++)
        {
            map.add(i);
        }

        map.add(Math.max(a,b));

        for(int i = b-1; i>=1; i--)
        {
            map.add(i);
        }


        while(a==1 && map.size() < N)
        {
            map.add(1,1);
        }

        while(a!=0 && map.size() < N)
        {
            map.add(0,1);
        }

        return map.size() == N;
    }
    

    
    public static void Print()
    {   
        StringBuilder sb = new StringBuilder();
		for (int n: map)
			sb.append(n).append(' ');
        System.out.println(sb);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        

        if(Resolve())
        {
            Print();
        }
        else
        {
            System.out.println(-1);
        }
    }
}
