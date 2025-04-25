import java.util.*;
import java.io.*;

class Main {
    static int N;
    static char[] str;
    static int result = Integer.MIN_VALUE;
    
    static int signCal1(int total, char sign, char num){
        int numA = num - '0';
        
        if(sign == '+'){
            return total + numA;
        }
        
        else if(sign == '-'){
            return total - numA;
        }
        
        else if(sign == '*'){
            return total * numA;
        }
        
        return 0;
    }
    
    static int signCal2(char num1, char sign, char num2){
        int numA = num1 - '0';
        int numB = num2 - '0';
        
        if(sign == '+'){
            return numA + numB;
        }
        
        else if(sign == '-'){
            return numA - numB;
        }
        
        else if(sign == '*'){
            return numA * numB;
        }
        
        return 0;
    }
    
    static void dfs(int total, int cnt){
        if(cnt > N){
            result = Math.max(total,result);
            return;
        }



        if(cnt+1 <= N){
            int cal = signCal1(total,str[cnt],str[cnt+1]);
            dfs(cal, cnt+2);
        }
        
        if(cnt+3 <= N){
            int cal1 = signCal2(str[cnt+1],str[cnt+2],str[cnt+3]);
            
            int cal2 = signCal1(total,str[cnt],(char)(cal1+'0'));
            dfs(cal2, cnt+4);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        str = new char[N+1];
        
        String input = br.readLine();
        str[0] = '+';
        for(int i = 1; i<=N; i++){
            str[i] = input.charAt(i-1);
        }
    
        dfs(0,0);

        System.out.println(result);
    }
}
