import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class infix {
    public static void main(String args[]){
        String input = "5-1*9*457/88/(28+646)*942";
        boolean flag = false;
        ArrayList<String> str = new ArrayList<String>();//"a","+","b","*","d","+","c","/","d"Arrays.asList("(","(","a","+","b",")","*","(","c","+","d",")",")")
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)=='+' || input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '/' || input.charAt(i) == '(' || input.charAt(i) == ')'){
                str.add(String.valueOf(input.charAt(i)));
                flag = false;
            }else{
                if(!flag){
                    str.add(String.valueOf(input.charAt(i)));
                    flag = true;
                }else{
                    str.set(str.size()-1,str.get(str.size()-1).concat(String.valueOf(input.charAt(i))));
                }
            }
        }
        infixtopostfix(str);
        infixtoprefix(str);
    }
    public static void infixtopostfix(ArrayList<String> array){
        Stack<String> st = new Stack<>();
        ArrayList<String> temp = new ArrayList<>();
        for(int i=0;i<array.size();i++){
            System.out.println(array.get(i));
            System.out.println(temp.toString());
            System.out.println(st.toString()+"\n-------");
            if(array.get(i).equals("+") || array.get(i).equals("-")){
                for(;!st.empty() && !st.peek().equals("(");){
                    temp.add(st.pop());
                }
                st.push(array.get(i));
            }else if(array.get(i).equals("*") || array.get(i).equals("/")){
                if(st.peek().equals("*") || st.peek().equals("/")){
                    temp.add(st.pop());
                }
                st.push(array.get(i));
            }else if(array.get(i).equals("(")){
                st.push(array.get(i));
            }else if(array.get(i).equals(")")){
                for(;!st.peek().equals("(");){
                    temp.add(st.pop());
                }
                st.pop();
            }else {
                temp.add(array.get(i));
            }
        }
        for(;!st.empty();){
            temp.add(st.pop());
        }
        System.out.println(temp.toString());
        postfixtovalue(temp);
    }
    public static void infixtoprefix(ArrayList<String> array){
        Stack<String> st = new Stack<>();
        ArrayList<String> temp = new ArrayList<>();
        for(int i=array.size()-1;i>=0;i--){
            System.out.println(array.get(i));
            System.out.println(temp.toString());
            System.out.println(st.toString()+"\n-------");
            if(array.get(i).equals("+") || array.get(i).equals("-")){
                if(st.peek().equals("*") || st.peek().equals("/")){
                    for(;!st.empty() && !st.peek().equals(")");){
                        temp.add(st.pop());
                    }
                }
                st.push(array.get(i));
            }else if(array.get(i).equals("*") || array.get(i).equals("/")){
                st.push(array.get(i));
            }else if(array.get(i).equals(")")){
                st.push(array.get(i));
            }else if(array.get(i).equals("(")){
                for(;!st.peek().equals(")");){
                    temp.add(st.pop());
                }
                st.pop();
            }else {
                temp.add(array.get(i));
            }
        }
        for(;!st.empty();){
            temp.add(st.pop());
        }
        Collections.reverse(temp);
        System.out.println(temp.toString());
        prefixtovalue(temp);
    }
    static void prefixtovalue(ArrayList <String> array){
        Stack<String> st = new Stack<>();
        Double temp1,temp2;
        
        for(int i=array.size()-1;i>=0;i--){
            System.out.println(st.toString());
            System.out.println(array.get(i)+"\n-----");
            if(array.get(i).equals("+") || array.get(i).equals("-") || array.get(i).equals("*") || array.get(i).equals("/")){
                temp1 = Double.valueOf(st.pop());
                temp2 = Double.valueOf(st.pop());
                if(array.get(i).equals("+")){
                    st.push(String.valueOf(temp1+temp2));
                }else if(array.get(i).equals("-")){
                    st.push(String.valueOf(temp1-temp2));
                }else if(array.get(i).equals("*")){
                    st.push(String.valueOf(temp1*temp2));
                }else if(array.get(i).equals("/")){
                    st.push(String.valueOf(temp1/temp2));
                }
            }else{
                st.push(array.get(i));
            }
        }
        System.out.println(st.peek());
    }
    static void postfixtovalue(ArrayList <String> array){
        Stack<String> st = new Stack<>();
        Double temp1,temp2;
        
        for(int i=0;i<array.size();i++){
            System.out.println(st.toString());
            System.out.println(array.get(i)+"\n-----");
            if(array.get(i).equals("+") || array.get(i).equals("-") || array.get(i).equals("*") || array.get(i).equals("/")){
                temp2 = Double.valueOf(st.pop());
                temp1 = Double.valueOf(st.pop());
                if(array.get(i).equals("+")){
                    st.push(String.valueOf(temp1+temp2));
                }else if(array.get(i).equals("-")){
                    st.push(String.valueOf(temp1-temp2));
                }else if(array.get(i).equals("*")){
                    st.push(String.valueOf(temp1*temp2));
                }else if(array.get(i).equals("/")){
                    st.push(String.valueOf(temp1/temp2));
                }
            }else{
                st.push(array.get(i));
            }
        }
        System.out.println(st.peek());
    }
}
