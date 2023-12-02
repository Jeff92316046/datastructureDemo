import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class BST {
    public static void main(String args[]){
        node root = new node();
        int list[] = {67,28,39,43,77,33,57,89,66,41,54,24};
        constructBST(root, list);
        inOrder(root);
        System.out.println("");
        preOrder(root);
        System.out.println("");
        bfs(root);
    }
    public static void constructBST(node root,int input[]){
        for(int i=0;i<input.length;i++){
            for(node temp=root;;){
                if(i==0){
                    temp.value = input[i];
                    break;
                }
                if(input[i]<temp.value){
                    if(temp.leftnode==null){
                        temp.leftnode = new node(null, input[i], null);
                        break;
                    }else{
                        temp = temp.leftnode;
                    }
                }else if(input[i]>temp.value){
                    if(temp.rightnode==null){
                        temp.rightnode = new node(null, input[i], null);
                        break;
                    }else{
                        temp = temp.rightnode;
                    }
                }
            }
            
            
        }
    }
    public static void inOrder(node nowNode){
        if(nowNode==null)return;
        inOrder(nowNode.leftnode);
        System.out.print(nowNode.value+" ");
        inOrder(nowNode.rightnode);
    }
    public static void antiInOrder(node nowNode){
        if(nowNode==null)return;
        antiInOrder(nowNode.rightnode);
        System.out.print(nowNode.value+" ");
        antiInOrder(nowNode.leftnode);
    }
    public static void preOrder(node nowNode){
        if(nowNode==null)return; 
        System.out.print(nowNode.value+" ");
        preOrder(nowNode.leftnode);
        preOrder(nowNode.rightnode);
    }
    public static void antiPreOrder(node nowNode){
        if(nowNode==null)return; 
        System.out.print(nowNode.value+" ");
        antiPreOrder(nowNode.rightnode);
        antiPreOrder(nowNode.leftnode);
    }
    public static void postOrder(node nowNode){
        if(nowNode==null)return; 
        postOrder(nowNode.leftnode);
        postOrder(nowNode.rightnode);
        System.out.print(nowNode.value+" ");
    }
    public static void antiPostOrder(node nowNode){
        if(nowNode==null)return; 
        antiPostOrder(nowNode.rightnode);
        antiPostOrder(nowNode.leftnode);
        System.out.print(nowNode.value+" ");
    }
    public static void bfs(node root){
        ArrayList <node> queue = new ArrayList<>();
        queue.add(root);
        for(;!queue.isEmpty();){
            node temp = queue.get(0);
            queue.remove(0);
            System.out.print(temp.value+" ");
            if(temp.leftnode!=null)queue.add(temp.leftnode);
            if(temp.rightnode!=null)queue.add(temp.rightnode);
        }
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