import java.util.*;

class Solution {
    ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    ArrayList<Queue<Integer>>current_tree = new ArrayList<>();
    int[] visited = new int[101];
    public int TN(int[] target)
    {
        int node_count = target.length;
        
        //1은 조건 만족 , 2는 push작업을 더해야함, 3. -1(불가능)
        int choice = 1;
        for(int i = 0; i<node_count; i++)
        {
            int count = visited[i+1];
            
            if(count*3 < target[i])
            {
                choice = 2;
            }
            
            else if(count > target[i])
            {
                return 3;
            }
            
        }
        
        return choice;
    }
    
    public int Rotation()
    {
        int before_node = 1;
        int node = 1;
        
        while(true)
        {
            if(current_tree.get(node).isEmpty())
            {
                break;
            }
            before_node = node;
            node = current_tree.get(node).peek();
            
            current_tree.get(before_node).add(node);
            current_tree.get(before_node).remove();
            //current_tree.get(node).add(node);
        }
        
        
        return node;
    }
    public int[] solution(int[][] edges, int[] target) {
        int node_count = target.length;
        
        for(int i = 0; i<=node_count; i++)
        {
            tree.add(new ArrayList<>());
            current_tree.add(new LinkedList<>());
        }
        
        for(int i = 0; i<edges.length; i++)
        {
            int parent = edges[i][0];
            int child = edges[i][1];
            
            tree.get(parent).add(child);
        }
        
        for(int i = 1; i<=target.length; i++)
        {
            Collections.sort(tree.get(i));
        }
        
        for(int i = 1; i<=node_count; i++)
        {
            for(int j = 0; j<tree.get(i).size(); j++)
            {
                current_tree.get(i).add(tree.get(i).get(j));
            }
        }
        
        
        ArrayList<Integer>array = new ArrayList<>();
        while(true)
        {
            int TN = TN(target);
            
            if(TN == 1)
            {
                break;
            }
            
            else if(TN == 2)
            {
                int node = Rotation();
                array.add(node);
                visited[node]++;
            }
            
            else
            {
                return new int[]{-1};
            }
        }
    
        int[] answer = new int[array.size()];
        for(int i = 0; i<array.size(); i++)
        {
            int node = array.get(i);
            int token = target[node-1];
            int visit = visited[node];
            if(token-1 <= (visit-1)*3)
            {
                visited[node] -= 1;
                target[node-1] -= 1;
                answer[i] = 1;
            }
            
            else if(token-2 <= (visit-1)*3)
            {
                visited[node] -= 2;
                target[node-1]-=1;
                answer[i] = 2;
            }
            
            else
            {
                visited[node] -= 3;
                target[node-1] -= 1;
                answer[i] = 3;
            }
        }
        
        
        
        //left노드의 방문 횟수만 count 후, target 범위안에 전부 만족하는지만 확인
        //next, count*1 < target < count*3에서, target-3 <= (count-1)*3 만족 시, 1
        //else if(target-2 <= (count-1)*3 만족 시, 2
        //else 3 방출
        return answer;
    }
}
