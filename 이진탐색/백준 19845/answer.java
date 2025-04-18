import java.util.*;
import java.io.*;

class Main {
    static int N, Q;
    static int[] arr;
    
    public static int simul(int x, int y) {
        int amount_y = 0;
        int amount_x = 0;
        
        if (arr[y] < x) {
            return 0;
        }
        
        // Calculate y
        int s = y;
        int e = N;
        while (s < e) {
            int m = (s + e) / 2;
            
            if (arr[m] >= x) {
                s = m + 1;
            } else {
                e = m;
            }
        }
        amount_y = s - y + 1;
        if (s + 1 <= N && arr[s + 1] >= x) {
            amount_y++;
        }
        if (s - 1 >= y && arr[s] < x) {
            amount_y--;
        }
        
        // Calculate x
        int total_x = arr[y];
        if (total_x >= x) {
            amount_x = total_x - x;
        }
        
        return amount_y + amount_x;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        
        arr = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int amount = Integer.parseInt(st.nextToken());
            arr[i] = amount;
        }
        
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            int result = simul(x, y);
            System.out.println(result);
        }
    }
}
