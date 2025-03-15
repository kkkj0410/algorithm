## 문제 유형

- 다익스트라

## 문제 접근

- 답
    - 1번→N번 지역으로 이동하기 위해서 걸리는 시간(분 단위)
- 주어지는 정보
    - 지역 최대 100,000 (N)
    - 횡단 보도 최대 700,000 (M)
- 후보
    - 완전 탐색
        - N*N 시간 초과 이므로 불가
    - 다익스트라
        - 1→N에 대한 최단 거리를 구하므로 적합
- 풀이
    - 다익스트라 알고리즘으로 1번 지역과 가장 가까운 v[a] 지역을 방문했다고 하자.
    - a 지역 → 2~N 지역에 대한 최단 거리 갱신 방법은?
    //target = a 지역
    //dis = 방문 지역이 초록불이 되는 시간
    
    //if : 초록불이 끝난 다음에 a번 지역에서 b 지역으로 가려는 경우
    if(v[target]%M >= dis){
        comp = v[target] + (dis + (M-v[target]%M));
    }
    //else : 초록불이 지나기 전에 a번 지역에서 b 지역으로 가려는 경우
    else{
        comp = v[target] + (dis - v[target]%M);
    }
    
    //1->a->b 지역으로 가는데 걸린 시간이 b 지역 도착 시간의 최단 시간을 갱신했을 경우
    if(v[arrive] > comp){
        v[arrive] = comp;
        pq.add(new Data(arrive, v[arrive]));
    }





## 틀린 풀이

- 접근 방법
    - 다익스트라
- 틀린 이유
    - 값의 최종값 타입을 int 형으로 했다
        - 값 타입 초과
        - ⇒ 값의 최종값은 long 형태가 가능하므로, long 형태로 바꾸어야 한다
    - 횡단보도에 대한 정보를 배열 형태로 저장했다.
        - 메모리 초과
        - N*N 크기의 배열을 생성한다고 해서 메모리 초과가 일어나진 않는다
        - 하지만 같은 장소에 대한 횡단보도 정보값이 추가로 있으므로, 메모리 초과가 생길 수 있다.
        - ⇒ List<class> 형태로 해서 메모리를 최대한 아껴야한다
    - 우선순위 큐를 사용하지 않았다
        - 시간 초과
        - 제일 우선적은 제일 가까운 target 지역을 뽑는데는 최대 N의 연산이 필요하다
        - 우선순위 큐를 사용하지 않을 시, 기본적으로 N*N의 연산을 사용하게 된다.
        - (N*N = N 지역 모두 방문 * N 지역 중 가장 가까운 지역 찾기)
        - 따라서 시간 초과



import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static List<Integer>[][] map2;
    static int MAX = Integer.MAX_VALUE;
    static int[] v;

    public static void dijkstra(){
        int start = 1;
        boolean[] visited = new boolean[N+1];
        v = new int[N+1];
        
        visited[start] = true;
        v[start] = 0;
        
        for(int i = 2; i<=N; i++){
            if(map2[start][i].size() == 0){
                v[i] = MAX;
                continue;
            }
            v[i] = map2[start][i].get(0);
        }
        
        boolean TN = true;
        while(TN){
            
            int target = -1;
            int compare = MAX;
            
            TN = false;
            for(int i = 1; i<=N; i++){
                if(visited[i] == true){
                    continue;
                }
                
                TN = true;
                if(compare > v[i]){
                    compare = v[i];
                    target = i;
                }
            }
            
            
            if(TN == false){
                break;
            }
            
            
            visited[target] = true;
            for(int i = 1; i<=N; i++){
                if(map2[target][i].size() == 0){
                    continue;
                }
                
                for(int j = 0; j<map2[target][i].size(); j++){
                    int dis = -1;
                    if(v[target]%M >= map2[target][i].get(j)){

                        dis = v[target] + (map2[target][i].get(j) + (M-v[target]%M));
                    }
                    else{
                        dis = v[target] + (map2[target][i].get(j) - v[target]%M);
                    }

                    if(v[i] > dis){
                        v[i] = dis;
                    }
                }

            }
            
        }
        
        
    }
    
    public static void PrintV()
    {
        for(int i = 1; i<=N; i++){
            System.out.println(i + " : " + v[i]);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map2 = new ArrayList[N+1][N+1];

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                map2[i][j] = new ArrayList<Integer>();
            }
        }

        for(int i = 1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            map2[s][e].add(i);
            map2[e][s].add(i);
        }
        
        dijkstra();
        //PrintV();
        System.out.println(v[N]);
    }
}
