import java.util.ArrayList;

public class DFS {
    public static void main(String args[]){
        ArrayList <node> nodeList = new ArrayList<>();
        init(nodeList);
        ArrayList <node> stack = new ArrayList<>();

    }
    public static void init(ArrayList <node> nodeList){
        nodeList.add(new node("A", 
        new node("B",
        new node("D", 
        new node("E",null)
        ))));
        nodeList.add(new node("B", //
        new node("A",
        new node("C",null)
        )));
        nodeList.add(new node("C", //
        new node("B",
        new node("E", null
        ))));   
        nodeList.add(new node("D", //
        new node("A",
        new node("E", null)
        )));
        nodeList.add(new node("E", //
        new node("A",
        new node("D", 
        new node("F",
        new node("C",null)
        )))));
        nodeList.add(new node("F", //
        new node("F",null)
        ));
    }
}

class node{
    String name;
    node adjacentNode;
    node(){

    }
    node(String name,node adjacentNode){
        this.name = name;
        this.adjacentNode = adjacentNode;
    } 
}