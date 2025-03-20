// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class Solution
{ // map error
    public int cur_a;
    public int cur_b;
    public Solution(int cur_a, int cur_b)
    {
        this.cur_a = cur_a;
        this.cur_b = cur_b;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        Solution solution = (Solution) obj;
        return cur_a == solution.cur_a && cur_b == solution.cur_b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cur_a, cur_b);
    }
}


public class Main {
    public int a;
    public int b;
    public int object_a;
    public int object_b;

    public Solution Fill(int idx, int cur_a, int cur_b) // a = 0, b = 1
    {
        if(idx == 0)
        {
            cur_a = a;
        }
        else
        {
            cur_b = b;
        }
        
        return new Solution(cur_a,cur_b);
    }

    public Solution Empty(int idx, int cur_a, int cur_b)
    {
        if(idx == 0)
        {
            cur_a = 0;
        }
        else
        {
            cur_b = 0;
        }
        
        return new Solution(cur_a, cur_b);
    }
    
    public Solution Move(int idx, int cur_a, int cur_b)  
    {
        if(idx == 0)
        {
            cur_a += cur_b;
            cur_b = 0;
            if(cur_a > a)
            {
                cur_b += cur_a - a;
                cur_a = a;
                if(cur_b > b)
                {
                    cur_b = b;
                }
            }
        }
        
        else
        {
            cur_b += cur_a;
            cur_a = 0;
            if(cur_b > b)
            {
                cur_a += cur_b - b;
                cur_b = b;
                if(cur_a > a)
                {
                    cur_a = a;
                }
            }
        }
        
        return new Solution(cur_a,cur_b);
    }

    public int bfs()
    {
        Queue<Solution> q = new LinkedList<>();
        
        q.add(new Solution(0,0));
        
        HashMap<Solution, Integer> visited = new HashMap<>();
        
        visited.put(new Solution(0,0), 0);
        while(!q.isEmpty())
        {
            Solution current = q.peek();
            q.remove();
            
            
            int cur_a = current.cur_a;
            int cur_b = current.cur_b;
            
            if(cur_a == object_a && cur_b == object_b)
            {
                //System.out.println("last: "+cur_a+", "+cur_b);
                return visited.get(current);
            }
            
            //System.out.println("answer: "+cur_a+", "+cur_b);
            for(int i = 0; i<=1; i++)
            {
                Solution temp = Fill(i,cur_a,cur_b);
                
                if(!visited.containsKey(temp))
                {
                    visited.put(temp, visited.get(current)+1);
                    q.add(temp);
                }
            }
            for(int i = 0; i<=1; i++)
            {
                Solution temp = Empty(i,cur_a,cur_b);
                
                if(!visited.containsKey(temp))
                {
                    visited.put(temp, visited.get(current)+1);
                    q.add(temp);
                }
            }
            for(int i = 0; i<=1; i++)
            {
                Solution temp = Move(i,cur_a,cur_b);
                
                if(!visited.containsKey(temp))
                {
                    visited.put(temp, visited.get(current)+1);
                    q.add(temp);
                }
            }
        }
        
        return -1;
    }
    public static void main(String[] args) {
        
        Main target = new Main();
        
        Scanner scanner = new Scanner(System.in);
        
        target.a = scanner.nextInt();
        target.b = scanner.nextInt();
        target.object_a = scanner.nextInt();
        target.object_b = scanner.nextInt();
        
        int answer = target.bfs();
        
        System.out.print(answer);
    }
}
