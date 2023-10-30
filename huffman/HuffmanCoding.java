import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HuffmanCoding {
    static Map <Character,String> bitMapCtoS = new HashMap<>();  
    static Map <String,Character> bitMapStoC = new HashMap<>();
    public static void main(String args[]){
        String str = "to be or not to be";
        ArrayList<Node> nodeSet = new ArrayList<>();  
        Map<Character,Integer> countMap = new HashMap<>();
        LinkedHashMap<Character, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println(str);
        for(int i=0;i<str.length();i++){
            if(countMap.containsKey(str.charAt(i))){
                countMap.put(str.charAt(i),countMap.get(str.charAt(i))+1);
            }else{
                countMap.put(str.charAt(i),1);
            }
        }
        
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list); 
        for (int num : list) {
            for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        for(Character i:sortedMap.keySet()){
            nodeSet.add(new Node(null,null,countMap.get(i),i));
        }
        System.out.println("frequency:");
        for(Node i:nodeSet){
            System.out.println(i.content+":"+i.number);
        }
        System.out.println("priority queue:");
        for(;nodeSet.size()>1;){
            int sum =  nodeSet.get(0).number+nodeSet.get(1).number;
            for(int i=0;i<nodeSet.size();i++){
                System.out.print(nodeSet.get(i).number);
                System.out.print(" ");
            }
            System.out.println();
            Node temp = new Node(nodeSet.get(0),nodeSet.get(1),sum);
            int flag = 0;
            nodeSet.remove(0);
            nodeSet.remove(0);
            for(int i=0;i<nodeSet.size()-1;i++){
                if(sum == nodeSet.get(i).number || (sum >nodeSet.get(i).number && sum <nodeSet.get(i+1).number)){
                    nodeSet.add(i+1,temp);
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                nodeSet.add(temp);
            }
        }
        traverseTree(nodeSet.get(0), "");
        System.out.println(bitMapCtoS.toString());
        String encodeStr = "";
        for(int i=0;i<str.length();i++){
            encodeStr += bitMapCtoS.get(str.charAt(i));
            System.out.print(bitMapCtoS.get(str.charAt(i)));
        }
        System.out.println("");
        for(int i=0;i<encodeStr.length();){
            for(int j=1;;j++){
                if(bitMapStoC.get(encodeStr.substring(i,j+i))==null){
                    continue;
                }else{
                    System.out.printf("%-4s => %2s\n",encodeStr.substring(i,j+i),bitMapStoC.get(encodeStr.substring(i,j+i)));
                    i += (j);
                    break;
                }
            }
        }
    }   
    public static void traverseTree(Node now,String bit){

        if(now.leftNode!=null){
            String temp = "";
            for(int i=0;i<bit.length();i++){
                temp += bit.substring(i, i+1);
            }
            temp += "1";
            
            traverseTree(now.leftNode,temp);
        }
        if(now.rightNode!=null){
            String temp = "";
            for(int i=0;i<bit.length();i++){
                temp += bit.substring(i, i+1);
            }
            temp += "0";

            traverseTree(now.rightNode,temp);

        }
        if(now.rightNode==null && now.leftNode==null){
            bitMapCtoS.put(now.content,bit);
            bitMapStoC.put(bit,now.content);
        }
    } 
}

class Node{
    Node rightNode ;
    Node leftNode ;
    int number;
    char content ;

    Node(Node r,Node l,int number,char content){
        this.leftNode = l;
        this.rightNode = r;
        this.number = number;
        this.content = content;
    }
    Node(Node r,Node l,int number){
        this.leftNode = l;
        this.rightNode = r;
        this.number = number;
    }
}
