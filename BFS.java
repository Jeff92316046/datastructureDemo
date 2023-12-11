import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BFS {
    public static void main(String args[]){
        
        node A = init();
        ArrayList <node> queue = new ArrayList<>();
        Map <String,String> d = new HashMap<>();
        Map <String,Integer> pi = new HashMap<>();
        ArrayList <String> output = new ArrayList<>();
        d.put(A.name, "null");
        pi.put(A.name, 0);
        output.add(A.name);
        queue.add(A);
        
        for(;!queue.isEmpty();){    
            node temp = queue.get(0);
            queue.remove(0);
            
            for(int i=0;i<temp.adjacentNode.size();i++){
                if(d.containsKey(temp.adjacentNode.get(i).name))continue;
                System.out.println("---------");
                System.out.print("queue : [");
                System.out.print(queue.get(0).name);
                for(int j=1;j<queue.size();j++){
                    System.out.print(","+queue.get(j).name);
                }
                System.out.println("]");
                System.out.println("d     : "+d.toString());
                System.out.println("pi    : "+pi.toString());
                System.out.println("output:"+output.toString());
                queue.add(temp.adjacentNode.get(i));
                d.put(temp.adjacentNode.get(i).name, temp.name);
                pi.put(temp.adjacentNode.get(i).name,pi.get(temp.name)+1 );
                output.add(temp.adjacentNode.get(i).name);
            }
        }
        System.out.println("---------");
        System.out.println("queue : "+queue.toString());
        System.out.println("d     : "+d.toString());
        System.out.println("pi    : "+pi.toString());
        System.out.println("output:"+output.toString());
    }
    public static node init(){
        node A = new node("A");
        node B = new node("B");
        node C = new node("C");
        node D = new node("D");
        node E = new node("E");
        node F = new node("F");
        A.adjacentNode.add(B);
        A.adjacentNode.add(D);
        A.adjacentNode.add(E);
        B.adjacentNode.add(A);
        B.adjacentNode.add(C);
        C.adjacentNode.add(B);
        C.adjacentNode.add(E);
        D.adjacentNode.add(A);
        D.adjacentNode.add(E);
        E.adjacentNode.add(A);
        E.adjacentNode.add(C);
        E.adjacentNode.add(D);
        E.adjacentNode.add(F);
        F.adjacentNode.add(E);
        return A;
    }
}
class node{
    String name ;
    ArrayList <node> adjacentNode;
    node(){
        
    }
    node(ArrayList <node> adjacentNode){
        this.adjacentNode = adjacentNode;
    } 
    node(String name){
        this.name = name;
        adjacentNode = new ArrayList<>();
    }
}
