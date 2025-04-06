import java.util.*;
import java.io.*;

class Tree implements Comparable<Tree>{
    public int y,x,age;
    
    Tree(int y, int x, int age)
    {
        this.y = y;
        this.x = x;
        this.age = age;
    }

}

public class Main {
    public static int N,M,K;
    public static int[][] A;
    public static int[][] map;
    public static Deque<Tree> list = new LinkedList<>();
    public static Queue<Tree>DeadTree = new LinkedList<>();
    public static int[][] move = {{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};

    public static void spring()
    {
        int listSize = list.size();
        for(int i = 0; i<listSize; i++)
        {
            Tree tree = list.pollFirst();
            
            if(map[tree.y][tree.x] >= tree.age)
            {
                map[tree.y][tree.x] -= tree.age;
                tree.age++;
                list.addLast(tree);
            }
            else
            {
                DeadTree.add(tree);
            }
        }
    }
    
    public static void summer()
    {
        while(!DeadTree.isEmpty())
        {
            Tree tree = DeadTree.peek();
            DeadTree.remove();
            
            
            map[tree.y][tree.x] += (tree.age/2);
        }
    }
    
    public static void fall()
    {
        List<Tree> addList = new ArrayList<>();

        int listSize = list.size();
        for(int i = 0; i<listSize; i++)
        {
            Tree tree = list.pollFirst();
            list.addLast(tree);

            if(tree.age % 5 == 0)
            {
                for(int j = 0; j<8; j++)
                {
                    int moveY = tree.y + move[j][0];
                    int moveX = tree.x + move[j][1];
                    
                    if(moveY < 0 || moveY >= N || moveX < 0 || moveX >= N)
                    {
                        continue;
                    }
                    
                    addList.add(new Tree(moveY,moveX,1));
                }
            }

        }

        for(int i = 0; i<addList.size(); i++)
        {
            list.addFirst(addList.get(i));
        }
    }
    
    public static void winter()
    {
        for(int i = 0; i<N; i++)
        {
            for(int j = 0; j<N; j++)
            {
                map[i][j] += A[i][j];
            }
        }
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        A = new int[N][N];
        map = new int[N][N];

        for(int i = 0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++)
            {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0; i<N; i++)
        {
            for(int j = 0; j<N; j++)
            {
                map[i][j] = 5;
            }
        }

        for(int i = 0; i<M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x,y,age;
            
            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            age = Integer.parseInt(st.nextToken());
            
            list.addLast(new Tree(y-1,x-1,age));
        }
        

        for(int i = 0; i<K; i++)
        {
            spring();
            summer();
            fall();
            winter();
            
        }
        
        System.out.println(list.size());
        
        
    }
}
