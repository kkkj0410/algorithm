import java.util.*;
class Node
{
    public int node;
    public int distance;
    
    public Node(int node, int distance)
    {
        this.node = node;
        this.distance = distance;
    }
    
}
class Solution {
    
    public boolean isGate(int node, int[] gates)
    {
        for(int gate : gates)
        {
            if(node == gate)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isSummit(int node, int[] summits)
    {
        for(int summit : summits)
        {
            if(node == summit)
            {
                return true;
            }
        }
        return false;
    }
    
    //다익스트라 알고리즘
    public int[] dijkstra(int[] gates, int[] d, ArrayList<ArrayList<Node>> route,int n, int[] summits)
    {
	//d배열은 최적 노선 경로만 남길 거라서 min값을 넣기 위해 MAX 넣음
        for(int i = 1; i<=n; i++)
        {
            d[i] = 1000000000;
        }  
        
        Queue<Node>pq = new LinkedList<>();
        
	//출발 지점은 거리값이 0이므로, 출발 지점 0처리
        for(int gate : gates)
        {
            pq.add(new Node(gate,0));
            d[gate] = 0;
        }
        
        while(!pq.isEmpty())
        {
            int node = pq.peek().node;
            int distance = pq.peek().distance;
            pq.remove();
            
            if(d[node] < distance)
            {
                continue;
            }
            
            for(int i = 0; i < route.get(node).size(); i++)
            {
                Node temp = route.get(node).get(i);
                
                int next_node = temp.node;
                int next_distance = Math.max(d[node], temp.distance);
                
	//새로 갱신된 거리값이, d보다 낮다면 d갱신
                if(d[next_node] > next_distance)
                {
                    d[next_node] = next_distance;
                    pq.add(new Node(next_node, next_distance));
                }
            }
        }
        
        int total_node = 100000000;
        int total_distance = 100000000;
        
	//도착지의 거리 값이 같은 경우, 번호가 낮은 도착지점을 답으로 해야하므로, 정렬
        Arrays.sort(summits);
        
        for(int summit : summits)
        {
            if(d[summit] < total_distance)
            {
                total_node = summit;
                total_distance = d[summit];
            }
        }
        return new int[]{total_node, total_distance};
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        int[] answer = {};
        int[] d = new int[n+1];

        ArrayList<ArrayList<Node>> route = new ArrayList<>();
        
        for(int i = 0; i<=n; i++)
        {
            route.add(new ArrayList<>());
        }
        
        for(int i = 0; i<paths.length; i++)
        {
            int go = paths[i][0];
            int des = paths[i][1];
            int dis = paths[i][2];
            
            //출발 지점, 도착 지점은 단방향(도착 지점은 1번만 방문이 가능하고, 출발 지점은 1번만 방문해도 괜찮기 때문에 단방향임)
            if(isGate(go,gates) || isSummit(des, summits))
            {
                route.get(go).add(new Node(des,dis));
            }
            else if(isGate(des,gates) || isSummit(go, summits) )
            {
                route.get(des).add(new Node(go,dis));
            }
	//출발 지점, 도착 지점외에는 양방향 그래프
            else
            {
                route.get(go).add(new Node(des,dis));
                route.get(des).add(new Node(go,dis));
            }
            
        }
        
        answer = dijkstra(gates, d, route, n, summits);
        
        return answer;
    }
}
