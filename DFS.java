import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class dfs {
    public static void main(String args[]){
        
        dfsnode A = init();
        Stack <dfsnode> stack = new Stack<>();
        Map <String,Integer> d = new HashMap<>();
        Map <String,String> pi = new HashMap<>();
        Map <String,Integer> f = new HashMap<>();
        Map <String,Integer> visted = new HashMap<>();
        ArrayList <String> output = new ArrayList<>();
        pi.put(A.name, "null");
        stack.push(A);
        
        for(int j=0;!stack.isEmpty();j++){    
            System.out.println("---------");
            System.out.println("pop->"+stack.peek().name);
            System.out.print("stack : [");
            if(!stack.isEmpty()){
                System.out.print(stack.get(0).name);
            }
            for(int i=1;i<stack.size();i++){
                System.out.print(","+stack.get(i).name);
            }
            System.out.println("]");
            System.out.println("d     : "+d.toString());
            System.out.println("f     : "+f.toString());
            System.out.println("pi    : "+pi.toString());
            System.out.println("output:"+output.toString());  
            dfsnode temp = stack.pop();
            visted.put(temp.name,1);
            output.add(temp.name);
            if(!d.containsKey(temp.name)){
                d.put(temp.name,j);
            }
            if(f.containsKey(temp.name)){
                f.replace(temp.name, j);
            }else{
                f.put(temp.name,j);
            }

            pi.put(temp.adjacentNode.get(0).name,pi.get(temp.name)+1 );
            for(int i=0;i<temp.adjacentNode.size();i++){
                if (!visted.containsKey(temp.adjacentNode.get(i).name) ){

                }else if(visted.get(temp.adjacentNode.get(i).name)==1)continue;
                System.out.println("---------");
                System.out.println("push->"+temp.adjacentNode.get(i).name);
                System.out.print("stack : [");
                if(!stack.isEmpty()){
                    System.out.print(stack.get(0).name);
                }
                for(int k=1;k<stack.size();k++){
                    System.out.print(","+stack.get(k).name);
                }
                System.out.println("]");
                System.out.println("d     : "+d.toString());
                System.out.println("f     : "+f.toString());
                System.out.println("pi    : "+pi.toString());
                System.out.println("output:"+output.toString());
                stack.push(temp.adjacentNode.get(i));
                if(!visted.containsKey(temp.adjacentNode.get(i).name)){
                    visted.put(temp.adjacentNode.get(i).name, 1);
                }
                
                pi.put(temp.adjacentNode.get(i).name, temp.name);
                
                
            }
        }
        System.out.println("---------");
        System.out.println("stack : "+stack.toString());
        System.out.println("d     : "+d.toString());
        System.out.println("f     : "+f.toString());
        System.out.println("pi    : "+pi.toString());
        System.out.println("output:"+output.toString());
    }
    public static dfsnode init(){
        dfsnode A = new dfsnode("A");
        dfsnode B = new dfsnode("B");
        dfsnode C = new dfsnode("C");
        dfsnode D = new dfsnode("D");
        dfsnode E = new dfsnode("E");
        dfsnode F = new dfsnode("F");
        dfsnode G = new dfsnode("G");
        dfsnode H = new dfsnode("H");
        A.adjacentNode.add(B);
        A.adjacentNode.add(D);
        A.adjacentNode.add(E);
        A.adjacentNode.add(G);
        B.adjacentNode.add(A);
        B.adjacentNode.add(C);
        C.adjacentNode.add(B);
        D.adjacentNode.add(A);
        D.adjacentNode.add(E);
        E.adjacentNode.add(A);
        E.adjacentNode.add(F);
        E.adjacentNode.add(C);
        F.adjacentNode.add(E);
        G.adjacentNode.add(A);
        G.adjacentNode.add(F);
        G.adjacentNode.add(H);
        H.adjacentNode.add(G);
        return A;
    }
}

class dfsnode{
    String name ;
    ArrayList <dfsnode> adjacentNode;
    dfsnode(){
    }
    dfsnode(ArrayList <dfsnode> adjacentNode){
        this.adjacentNode = adjacentNode;
    } 
    dfsnode(String name){
        this.name = name;
        adjacentNode = new ArrayList<>();
    }
}