class Solution {
    public void ShiftRow(int[][] rc)
    {
        int RowMax = rc.length;
        
        int[] temp = rc[RowMax-1];
        for(int i = RowMax-1; i>0; i--)
        {
            rc[i] = rc[i-1];
        }
        rc[0] = temp;
    }
    
    public void Rotate(int[][] rc)
    {
        int RowMax = rc.length;
        int ColMax = rc[0].length;
        
        int temp = rc[0][0];

        //first_col move
        for(int i = 0; i<RowMax-1; i++)
        {
            rc[i][0] = rc[i+1][0];
        }
        
        //last_row move
        for(int i = 0; i<ColMax-1; i++)
        {
            rc[RowMax-1][i] = rc[RowMax-1][i+1];
        }
        
        //last_col move
        for(int i = RowMax-1; i>0; i--)
        {
            rc[i][ColMax-1] = rc[i-1][ColMax-1];
        }
        
        //first_row move
        for(int i = ColMax-1; i>0; i--)
        {
            rc[0][i] = rc[0][i-1];
        }
        
        rc[0][1] = temp;
    }
    public int[][] solution(int[][] rc, String[] operations) {
        int[][] answer = {};
        
        for(int i = 0; i<operations.length; i++)
        {
            String command = operations[i];
            if(command.equals("Rotate"))
            {
                Rotate(rc);
            }
            
            else if(command.equals("ShiftRow"))
            {
                ShiftRow(rc);
            }
        }
        
        answer = rc;
        return answer;
    }
}
