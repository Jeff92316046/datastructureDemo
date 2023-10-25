import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;



public class InfixToPostfix {
    public static void main(String args[]){
        ArrayList<String> str = new ArrayList<String>(Arrays.asList("(","(","a","+","b",")","*","(","c","+","d",")",")"));//"a","+","b","*","d","+","c","/","d"
        str = itopo(str);
        System.out.println(str.toString());
    }
    public static ArrayList<String> itopo(ArrayList<String> str){
        Stack<String> st = new Stack<String>();
        ArrayList<String> temp = new ArrayList<String>();
        for(int i=0;i<str.size();i++){
            System.out.println(st.toString());
            if(str.get(i).equals("*") || str.get(i).equals("/")){
                System.out.println("*/");
                if(!st.empty()){
                    if(st.peek().equals("*") || st.peek().equals("/")){
                        temp.add(st.pop());
                    }
                }
                st.push(str.get(i));
            }else if(str.get(i).equals("+") || str.get(i).equals("-")){
                System.out.println("+-");
                for(;!st.empty() && !st.peek().equals("(");){
                    System.out.println("out");
                    if(st.peek().equals("*") || st.peek().equals("/") || st.peek().equals("+") || st.peek().equals("-")){
                        temp.add(st.pop());
                        System.out.println("in");
                    }
                }
                st.push(str.get(i));
            }else if(str.get(i).equals("(")){
                System.out.println("(");
                st.push(str.get(i));
            }else if(str.get(i).equals(")")){
                System.out.println(")");
                for(;!st.peek().equals("(");){
                    temp.add(st.pop());
                }
                st.pop();
            }else{
                System.out.println("a");
                temp.add(str.get(i));
            }
        }
        for(;!st.empty();){
            temp.add(st.pop());
        }
        return temp;
    }
}
