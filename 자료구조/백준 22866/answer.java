import java.util.*;
import java.io.*;

class Building
{
    public int L = 0;
    public int R = 0;
    public int targetIdx=0;
    public int dis=100001;
}

class BuildingState
{
    public int h,idx;

    public BuildingState(int h, int idx)
    {
        this.h = h;
        this.idx = idx;
    }
}

public class Main {
    static int N;
    static int[] map;
    static Building[] arr;
    public static void Resolve()
    {
        Stack<BuildingState> stack = new Stack<>();
        stack.push(new BuildingState(map[0], 0));
        for(int i = 1; i<N; i++)
        {
            while(!stack.isEmpty() && stack.peek().h <= map[i])
            {
                stack.pop();
            }

            if(stack.size() != 0)
            {
                arr[i].L = stack.size();
                int targetIdx = stack.peek().idx;
                arr[i].targetIdx = targetIdx;
                arr[i].dis = i - targetIdx;
            }

            stack.push(new BuildingState(map[i],i));
        }
        stack.clear();

        stack.add(new BuildingState(map[N-1],N-1));
        for(int i = N-1; i>=0; i--)
        {
            while(!stack.isEmpty() && stack.peek().h <= map[i])
            {
                stack.pop();
            }

            if(stack.size() != 0)
            {
                arr[i].R = stack.size();

                int targetIdx = stack.peek().idx;
                if(targetIdx-i < arr[i].dis)
                {
                    arr[i].targetIdx = targetIdx;
                    arr[i].dis = targetIdx-i;
                }
            }

            stack.push(new BuildingState(map[i],i));
        }
    }


    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        map = new int[N];
        arr = new Building[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
        {
            map[i] = Integer.parseInt(st.nextToken());
            arr[i] = new Building();
        }
        
        Resolve();
        
        for(int i = 0; i<N; i++)
        {
            int result = arr[i].L + arr[i].R;
            if(result == 0)
            {
                System.out.println(0);
            }
            else
            {
                int idx = arr[i].targetIdx + 1;
                System.out.println(result + " " + idx);
            }
        }
        
    }
}
