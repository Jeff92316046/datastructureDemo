import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DFS {
    public static void main(String args[]){
        
        node A = init();
        Stack <node> stack = new Stack<>();
        Map <String,String> d = new HashMap<>();
        Map <String,Integer> pi = new HashMap<>();
        ArrayList <String> output = new ArrayList<>();
        d.put(A.name, "null");
        pi.put(A.name, 0);
        stack.push(A);
        
        for(;!stack.isEmpty();){    
            System.out.println("---------");
            System.out.println("pop->"+stack.peek().name);
            System.out.print("stack : [");
            if(!stack.isEmpty()){
                System.out.print(stack.get(0).name);
            }
            for(int j=1;j<stack.size();j++){
                System.out.print(","+stack.get(j).name);
            }
            System.out.println("]");
            System.out.println("d     : "+d.toString());
            System.out.println("pi    : "+pi.toString());
            System.out.println("output:"+output.toString());  
            node temp = stack.pop();
            output.add(temp.name);
            pi.put(temp.adjacentNode.get(i).name,pi.get(temp.name)+1 );
            for(int i=0;i<temp.adjacentNode.size();i++){
                if(d.containsKey(temp.adjacentNode.get(i).name))continue;
                System.out.println("---------");
                System.out.println("push->"+temp.adjacentNode.get(i).name);
                System.out.print("stack : [");
                if(!stack.isEmpty()){
                    System.out.print(stack.get(0).name);
                }
                for(int j=1;j<stack.size();j++){
                    System.out.print(","+stack.get(j).name);
                }
                System.out.println("]");
                System.out.println("d     : "+d.toString());
                System.out.println("pi    : "+pi.toString());
                System.out.println("output:"+output.toString());
                stack.push(temp.adjacentNode.get(i));
                d.put(temp.adjacentNode.get(i).name, temp.name);
                
                
            }
        }
        System.out.println("---------");
        System.out.println("stack : "+stack.toString());
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
        E.adjacentNode.add(D);
        E.adjacentNode.add(F);
        E.adjacentNode.add(C);
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