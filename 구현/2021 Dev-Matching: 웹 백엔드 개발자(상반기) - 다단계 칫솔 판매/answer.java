import java.util.*;

class Node{
    public String parent;
    public String name;
    public int value;
    
    public Node(String parent, String name){
        this.parent = parent;
        this.name = name;
        this.value = 0;
    }
}

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        int n = enroll.length;
        
        Map<String, Node> map = new HashMap<>();
        
        for(int i = 0; i<n; i++){
            String parent = referral[i];
            String child = enroll[i];
            map.put(child, new Node(parent, child));
        }
        
        int brush = 100;
        for(int i = 0; i<seller.length; i++){
            String name = seller[i];
            int value = amount[i] * brush;
            
            map.get(name).value += value;
            while(value/10 >= 1){
                value /= 10;
                map.get(name).value -= value;
                
                String parent = map.get(name).parent;
                if(parent.equals("-")){
                    break;
                }

                map.get(parent).value += value;
                
                name = parent;
            }
        }
        
        int[] values = new int[n];
        for(int i = 0; i<n; i++){
            String name = enroll[i];
            values[i] = map.get(name).value;
        }
    
        return values;
    }
}
