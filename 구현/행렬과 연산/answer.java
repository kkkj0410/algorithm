import java.util.*;

class Solution {
    private Deque<Deque<Integer>>middle = new LinkedList<>();
    private Deque<Integer>left = new LinkedList<>();
    private Deque<Integer>right = new LinkedList<>();
    
    public void ShiftRow()
    {
        //left
        left.addLast(left.getFirst());
        left.removeFirst();
        
        
        //right
        right.addLast(right.getFirst());
        right.removeFirst();
        
        //middle
        middle.addLast(middle.getFirst());
        middle.removeFirst();
    }
    
    public void Rotate()
    {
        //[0][0]부터 시계방향으로 값 삽입/추가
        
        //1
        middle.getLast().addLast(left.getLast());
        left.removeLast();
        
        //2
        right.addLast(middle.getLast().getFirst());
        middle.getLast().removeFirst();
        
        //3
        middle.getFirst().addFirst(right.getFirst());
        right.removeFirst();
        
        //4
        left.addFirst(middle.getFirst().getLast());
        middle.getFirst().removeLast();
            
    }
    
    void Print(int[][] rc, int[][] answer)
    {
        int RowMax = rc.length;
        int ColMax = rc[0].length;
        
        //left
        for(int i = 0; i<RowMax; i++)
        {
            answer[i][0] = left.getLast();
            left.removeLast();
        }
        
        //right
        for(int i = 0; i<RowMax; i++)
        {
            answer[i][ColMax-1] = right.getLast();
            right.removeLast();
        }
        
        //middle
        for(int i = 0; i<RowMax; i++)
        {
            for(int j = 1; j<ColMax-1; j++)
            {
                answer[i][j] = middle.getLast().getLast();
                middle.getLast().removeLast();
            }
            middle.removeLast();
        }
    }
    
    public int[][] solution(int[][] rc, String[] operations) {
        int RowMax = rc.length;
        int ColMax = rc[0].length;
        
        int[][] answer = new int[RowMax][ColMax];
        
        //left
        for(int i = 0; i<RowMax; i++)
        {
            left.addFirst(rc[i][0]);
        }
        
        //right
        for(int i = 0; i<RowMax; i++)
        {
            right.addFirst(rc[i][ColMax-1]);
        }
        
        //middle
        for(int i = 0; i<RowMax; i++)
        {
            Deque<Integer>temp = new LinkedList<>();
            for(int j = 1; j<ColMax-1; j++)
            {
                temp.addFirst(rc[i][j]);
            }
            middle.addFirst(temp);
        }
    
        for(String command : operations)
        {
            if(command.equals("Rotate"))
            {
                Rotate();
            }
            else if(command.equals("ShiftRow"))
            {
                ShiftRow();
            }
        }
        
        Print(rc,answer);
        
        return answer;
    }
}
