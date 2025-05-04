import java.util.*;
import java.io.*;

public class Main {
    static int[] preList = new int[1001];
    static int[] inList = new int[1001];

    static int n;
    
    public static void printTree(int preIdx, int s, int e){
        if(s > e){
            return;
        }

        for(int i = 1; i<=n; i++){
            if(preList[preIdx] == inList[i]){
                printTree(preIdx+1, s,i-1);
                printTree(preIdx+(i-s+1), i+1, e);
                
                int curValue = preList[preIdx];
                System.out.print(curValue + " ");
                return;
            }
        }
        
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        for(int i = 1; i<=T; i++){
            Arrays.fill(preList, 0);
            Arrays.fill(inList, 0);
            
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=n; j++){
                preList[j] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=n; j++){
                inList[j] = Integer.parseInt(st.nextToken());
            }
            
            printTree(1,1,n);
            System.out.println();
        }
    }
}
