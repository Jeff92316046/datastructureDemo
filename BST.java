import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class BST {
    public static void main(String args[]){
        node root = new node();
        int list[] = {67,28,39,43,77,33,57,89,66,41,54,24};
        System.out.println(Arrays.toString(list));
        constructBST(root, list);
        bfs(root);
        
    }
    public static void constructBST(node root,int input[]){
        for(int i=0;i<input.length;i++){
            //System.out.print(input[i]+"->"+"(root)");
            for(node temp=root;;){
                if(i==0){
                    temp.value = input[i];
                    break;
                }
                if(input[i]<temp.value){
                    if(temp.leftnode==null){
                        temp.leftnode = new node(null, input[i], null);
                        //System.out.print("->(R)");
                        break;
                    }else{
                        temp = temp.leftnode;
                        //System.out.print("->R");
                    }
                }else if(input[i]>temp.value){
                    if(temp.rightnode==null){
                        temp.rightnode = new node(null, input[i], null);
                        //System.out.print("->(L)");
                        break;
                    }else{
                        temp = temp.rightnode;
                        //System.out.print("->L");
                    }
                }
            }
            //System.out.println("");
            //bfs(root);
            //System.out.println("");
            
        }
    }
    public static void inOrder(node nowNode){
        if(nowNode==null)return;
        inOrder(nowNode.leftnode);
        System.out.print(nowNode.value+" ");
        inOrder(nowNode.rightnode);
    }
    public static void inOrder(node nowNode,int n){
        if(nowNode==null)return;
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("left");
        inOrder(nowNode.leftnode,n+2);
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("  "+nowNode.value+" ");
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("right");
        inOrder(nowNode.rightnode,n+2);
    }

    public static void antiInOrder(node nowNode){
        if(nowNode==null)return;
        antiInOrder(nowNode.rightnode);
        System.out.print(nowNode.value+" ");
        antiInOrder(nowNode.leftnode);
    }
    public static void antiInOrder(node nowNode,int n){
        if(nowNode==null)return;
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("right");
        antiInOrder(nowNode.rightnode,n+2);
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("  "+nowNode.value+" ");
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("left");
        antiInOrder(nowNode.leftnode,n+2);
    }
    public static void preOrder(node nowNode){
        if(nowNode==null)return; 
        System.out.print(nowNode.value+" ");
        preOrder(nowNode.leftnode);
        preOrder(nowNode.rightnode);
    }
    public static void preOrder(node nowNode,int n){
        if(nowNode==null)return;
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("  "+nowNode.value+" ");
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("left");
        preOrder(nowNode.leftnode,n+2);
        
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("right");
        preOrder(nowNode.rightnode,n+2);
    }
    public static void antiPreOrder(node nowNode){
        if(nowNode==null)return; 
        System.out.print(nowNode.value+" ");
        antiPreOrder(nowNode.rightnode);
        antiPreOrder(nowNode.leftnode);
    }
    public static void antiPreOrder(node nowNode,int n){
        if(nowNode==null)return;
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("  "+nowNode.value+" ");
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("right");
        antiPreOrder(nowNode.rightnode,n+2);
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("left");
        antiPreOrder(nowNode.leftnode,n+2);
        
        
    }
    public static void postOrder(node nowNode){
        if(nowNode==null)return; 
        postOrder(nowNode.leftnode);
        postOrder(nowNode.rightnode);
        System.out.print(nowNode.value+" ");
    }
    public static void postOrder(node nowNode,int n){
        if(nowNode==null)return;
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("left");
        postOrder(nowNode.leftnode,n+2);
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("right");
        postOrder(nowNode.rightnode,n+2);
        
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("  "+nowNode.value+" ");
        
    }
    public static void antiPostOrder(node nowNode){
        if(nowNode==null)return; 
        antiPostOrder(nowNode.rightnode);
        antiPostOrder(nowNode.leftnode);
        System.out.print(nowNode.value+" ");
    }
    public static void antiPostOrder(node nowNode,int n){
        if(nowNode==null)return;
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("right");
        antiPostOrder(nowNode.rightnode,n+2);
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("left");
        antiPostOrder(nowNode.leftnode,n+2);
        
        
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
        System.out.println("  "+nowNode.value+" ");
        
    }
    public static void bfs(node root){
        ArrayList <node> queue = new ArrayList<>();
        queue.add(root);
        String str = "";
        System.out.println("queue->");
        for(;!queue.isEmpty();){
            for(int i=0;i<queue.size();i++){
                System.out.print(queue.get(i).value+" ");
            }
            System.out.println("");
            node temp = queue.get(0);
            queue.remove(0);
            str = str + temp.value + " ";
            if(temp.leftnode!=null)queue.add(temp.leftnode);
            if(temp.rightnode!=null)queue.add(temp.rightnode);
        }
        System.out.println("BFS : "+str);
    }
    
}
class node{
    node rightnode;
    node leftnode;
    int value;
    node(){
    }
    node(node l,int v,node r){
        this.leftnode = l;
        this.value = v;
        this.rightnode = r;
    }

}