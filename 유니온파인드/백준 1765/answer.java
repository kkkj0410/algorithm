import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static List<Integer>[] FList;
    static List<Integer>[] EList;
    static int[] unionList;

    static void makeFriendByE(){
        int p, q;
        for(int i = 1; i<=n; i++){
            for(int j = 0; j<EList[i].size()-1; j++){
                for(int k = j+1; k<EList[i].size(); k++){
                    p = EList[i].get(j);
                    q = EList[i].get(k);
                    
                    FList[p].add(q);
                    FList[q].add(p);
                }
                
            }
        }
    }
    
    static int find(int target){
        if(unionList[target] == target){
            return target;
        }

        return unionList[target] = find(unionList[target]);
    }

    static void union(int child, int parent){
        child = find(child);
        parent = find(parent);


        if(child == unionList[child]){
            unionList[child] = parent;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        
        FList = new ArrayList[n+1];
        EList = new ArrayList[n+1];
        unionList = new int[n+1];

        for(int i = 1; i<=n; i++){
            FList[i] = new ArrayList<>();
            EList[i] = new ArrayList<>();
            unionList[i] = i;
        }
        
        char ch;
        int p,q;
        for(int i = 1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            
            ch = st.nextToken().charAt(0);
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            
            if(ch == 'F'){
                FList[p].add(q);
                FList[q].add(p);
            }
            else{
                EList[p].add(q);
                EList[q].add(p);
            }
        }
        makeFriendByE();
        
        for(int i = 1; i<=n; i++){
            for(int j = 0; j<FList[i].size(); j++){
                int a = i;
                int b = FList[i].get(j);

                union(b,a);
            }
        }
        
        int cnt = 0;
        boolean[] visited = new boolean[n+1];
        for(int i = 1; i<=n; i++){
            int target = unionList[i];
            if(visited[target] == true){
                continue;
            }
            visited[target] = true;
            cnt++;
        }

        System.out.println(cnt);
        

        
    
    }
}
