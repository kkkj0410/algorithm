import java.io.*;
import java.util.*;


class Main {
    public static int[][] gear = new int[5][9];
    
    
    public static void Rotate(int num, int rotate)
    {
        //CW(Clockwise)
        if(rotate == 1)
        {
            int temp = gear[num][8];
            for(int i = 8; i>1; i--)
            {
                gear[num][i] = gear[num][i-1];
            }
            gear[num][1] = temp;
        }
        
        //ACW
        else if(rotate == -1)
        {
            int temp = gear[num][1];
            for(int i = 1; i<8; i++)
            {
                gear[num][i] = gear[num][i+1];
            }
            gear[num][8] = temp;
        }
        
    }
    
    
    public static void L_rotate(int num, int rotate)
    {
        if(num == 1)
        {
            return;
        }
        
        int cur = num;
        int target = num-1;
        
        int cur_value = gear[cur][7];
        int target_value = gear[target][3];
        
        if(cur_value == target_value)
        {
            return;
        }
        
        
        L_rotate(target, -rotate);

        Rotate(target, -rotate);
    }
    
    public static void R_rotate(int num, int rotate)
    {
        if(num == 4)
        {
            return;
        }
        
        int cur = num;
        int target = num+1;
        
        int cur_value = gear[cur][3];
        int target_value = gear[target][7];
        
        if(cur_value == target_value)
        {
            return;
        }
        
        R_rotate(target, -rotate);
        
        Rotate(target, -rotate);
    }
    
    public static void Point_print()
    {
        int result = 0;
        for(int i = 1; i<=4; i++)
        {
            int point = (int)Math.pow(2,i-1);
            if(gear[i][1] == 1)
            {
                result += point;
            }
            
        }
        
        System.out.println(result);
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        for(int i = 1; i<=4; i++)
        {
            String str = br.readLine();
            for(int j = 1; j<=8; j++)
            {
                gear[i][j] = str.charAt(j-1) - '0';
            }
        }
        
        
        int K;
        int num, rotate;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i<K; i++)
        {
            st = new StringTokenizer(br.readLine());
            
            num = Integer.parseInt(st.nextToken());
            rotate = Integer.parseInt(st.nextToken());
            
            L_rotate(num,rotate);
            R_rotate(num,rotate);
            Rotate(num,rotate);
        }
        
        Point_print();
        
        
    }
}
