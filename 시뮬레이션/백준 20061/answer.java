import java.util.*;
import java.io.*;

public class Main {
    public static boolean[][] BlueMap = new boolean[4][6];
    public static boolean[][] GreenMap = new boolean[6][4];
    
    public static void BlueInsert(int t, int y, int x)
    {
        if(t == 1)
        {
            for(int i = 1; i<=5; i++)
            {
                if(BlueMap[y][i] == true)
                {
                    BlueMap[y][i-1] = true;
                    break;
                }

                if(i==5)
                {
                    BlueMap[y][5] = true;
                }
            }


        }
        
        else if(t==2)
        {
            for(int i = 2; i<=5; i++)
            {
                if(BlueMap[y][i] == true)
                {
                    BlueMap[y][i-1] = true;
                    BlueMap[y][i-2] = true;
                    break;
                }

                if(i==5)
                {
                    BlueMap[y][5] = true;
                    BlueMap[y][4] = true;
                }
            }


        }
        
        //t=3
        else
        {
            for(int i = 1; i<=5; i++)
            {
                if(BlueMap[y][i] == true || BlueMap[y+1][i] == true)
                {
                    BlueMap[y][i-1] = true;
                    BlueMap[y+1][i-1] = true;
                    break;
                }

                if(i==5)
                {
                    BlueMap[y][5] = true;
                    BlueMap[y+1][5] = true;
                }
            }

        }
    }
    
    
    public static void GreenInsert(int t, int y, int x)
    {
        if(t == 1)
        {
            for(int i = 1; i<=5; i++)
            {
                if(GreenMap[i][x] == true)
                {
                    GreenMap[i-1][x] = true;
                    break;
                }
                if(i == 5)
                {
                    GreenMap[5][x] = true;
                }
            }

        }
        
        else if(t==2)
        {
            for(int i = 1; i<=5; i++)
            {
                if(GreenMap[i][x] == true || GreenMap[i][x+1] == true)
                {
                    GreenMap[i-1][x] = true;
                    GreenMap[i-1][x+1] = true;
                    break;
                }
                if(i == 5)
                {
                    GreenMap[5][x] = true;
                    GreenMap[5][x+1] = true;
                }
            }

        }
        
        //t=3
        else
        {
            for(int i = 2; i<=5; i++)
            {
                if(GreenMap[i][x] == true)
                {
                    GreenMap[i-1][x] = true;
                    GreenMap[i-2][x] = true;
                    break;
                }

                if(i == 5)
                {
                    GreenMap[5][x] = true;
                    GreenMap[4][x] = true;
                }
            }

        }
    }
    
    public static void BlueMove(int x, int gap)
    {
        for(int nx = x-gap; nx>=0; nx--)
        {
            for(int ny = 0; ny<4; ny++)
            {
                BlueMap[ny][nx+gap] = BlueMap[ny][nx]; 
            }
            
        }
        
        for(int ny = 0; ny<4; ny++)
        {
            BlueMap[ny][0] = false;
        }
        
        if(gap == 2)
        {
            for(int ny = 0; ny<4; ny++)
            {
                BlueMap[ny][1] = false;
            }
        }
        
    }
    
    public static int BluePoint()
    {
        int result = 0;
        
        for(int x = 2; x<6; x++)
        {
            boolean TN = true;
            for(int y = 0; y<4; y++)
            {
                if(BlueMap[y][x] == false)
                {
                    TN = false;
                    break;
                }
            }
            
            if(TN == true)
            {
                BlueMove(x,1);
                result++;
            }
        }
        
        return result;
    }
    
    public static void GreenMove(int y, int gap)
    {

        for(int ny = y-gap; ny>=0; ny--)
        {
            for(int nx = 0; nx<4; nx++)
            {
                GreenMap[ny+gap][nx] = GreenMap[ny][nx];
            }
        }
        
        for(int nx = 0; nx<4; nx++)
        {
            GreenMap[0][nx] = false;
        }
        
        if(gap==2)
        {
            for(int nx = 0; nx<4; nx++)
            {
                GreenMap[1][nx] =false;
            }
        }
    }
    
    public static int GreenPoint()
    {
        int result = 0;
        
        for(int ny = 2; ny < 6; ny++)
        {
            boolean TN = true;
            for(int nx = 0; nx < 4; nx++)
            {
                if(GreenMap[ny][nx] == false)
                {
                    TN = false;
                    break;
                }
            }
            
            if(TN == true)
            {
                GreenMove(ny,1);
                result++;
            }
        }
        
        return result;
    }
    
    public static void LightBlueCheck()
    {
        boolean TN1 = false;
        boolean TN2 = false;
        
        //check
        for(int ny = 0; ny<4; ny++)
        {
            if(BlueMap[ny][0] == true)
            {
                TN1 = true;
            }
            
            if(BlueMap[ny][1] == true)
            {
                TN2 = true;
            }
        }
        
        
        //move
        if(TN1 == true && TN2 == true)
        {
            BlueMove(5,2);
        }
        
        else if(TN2 == true)
        {
            BlueMove(5,1);
        }
    }
    
    
    public static void LightGreenCheck()
    {
        boolean TN1 = false;
        boolean TN2 = false;
        
        //check
        for(int nx = 0; nx<4; nx++)
        {
            if(GreenMap[0][nx] == true)
            {
                TN1 = true;
            }
            
            if(GreenMap[1][nx] == true)
            {
                TN2 = true;
            }
        }
        
        
        //move
        if(TN1 == true && TN2 == true)
        {
            GreenMove(5,2);
        }
        
        else if(TN2 == true)
        {
            GreenMove(5,1);
        }
    }

    public static int TotalTile()
    {

        //blue

        int blueTile = 0;
        for(int nx = 2; nx <6; nx++)
        {
            for(int ny = 0; ny<4; ny++)
            {
                if(BlueMap[ny][nx] == true)
                {
                    blueTile++;
                }
            }
        }

        //green
        int greenTile = 0;
        for(int ny = 2; ny<6; ny++)
        {
            for(int nx = 0; nx<4; nx++)
            {
                if(GreenMap[ny][nx] == true)
                {
                    greenTile++;
                }
            }
        }

        return blueTile + greenTile;
    }

    public static void BlueMapPrint()
    {
        System.out.println("------BlueMap------");
        for(int ny = 0; ny<4; ny++)
        {
            for(int nx = 0; nx<6; nx++)
            {
                if(BlueMap[ny][nx] == true)
                {
                    System.out.print(1 + " ");
                    continue;
                }
                System.out.print(0 + " ");
            }
            System.out.println();
        }
    }


    public static void GreenMapPrint()
    {
        System.out.println("------GreenMap------");
        for(int ny = 0; ny<6; ny++)
        {
            for(int nx = 0; nx<4; nx++)
            {
                if(GreenMap[ny][nx] == true)
                {
                    System.out.print(1 + " ");
                    continue;
                }
                System.out.print(0 + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());

        int pointResult = 0;
        for(int i = 0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int t,y,x;
            
            t = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            
            
            BlueInsert(t,y,x);
            GreenInsert(t,y,x);
            
            int bluePoint = 0;
            int greenPoint = 0;
            
            bluePoint = BluePoint();
            greenPoint = GreenPoint();
            
            if(bluePoint < 2)
            {
                LightBlueCheck();
            }
            
            if(greenPoint < 2)
            {
                LightGreenCheck();
            }
            
            pointResult += (bluePoint + greenPoint);

            //BlueMapPrint();
            //GreenMapPrint();
        }
        
        System.out.println(pointResult);
        System.out.println(TotalTile());
    }
}
