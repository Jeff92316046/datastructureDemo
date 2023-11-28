import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;



public class InfixToPrefix {    
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
        System.out.println("question : " + input);
        str = itopre(str);
        
        System.out.println("result : "+str.toString());
        System.out.println(getValue(str));
    }
    public static ArrayList<String> itopre(ArrayList<String> str){
        Stack<String> st = new Stack<String>();
        ArrayList<String> temp = new ArrayList<String>();
        System.out.printf("%-5s%-16s%-23s\n","now","stack","string");
        for(int i=str.size()-1;i>=0;i--){
            System.out.printf("%-5s%-16s%-25s\n",str.get(i),st.toString(),temp.toString());
            if(str.get(i).equals("*") || str.get(i).equals("/")){
                st.push(str.get(i));
            }else if(str.get(i).equals("+") || str.get(i).equals("-")){
                for(;!st.empty() && !st.peek().equals(")") && !st.peek().equals("+")&&!st.peek().equals("-");){
                    if(st.peek().equals("*") || st.peek().equals("/")){
                        temp.add(0,st.pop());
                    }
                }
                st.push(str.get(i));
            }else if(str.get(i).equals("(")){
                for(;!st.peek().equals(")");){
                    temp.add(0,st.pop());
                }
                st.pop();
            }else if(str.get(i).equals(")")){
                st.push(str.get(i));
                
            }else{
                temp.add(0,str.get(i));
            }
        }
        System.out.println("left stack element : "+st.toString());
        for(;!st.empty();){
            temp.add(0,st.pop());
        }
        return temp;
    }
     public static String getValue(ArrayList<String> array){
        Stack<String> temp = new Stack<>();   
        System.out.println(array.toString());
        Collections.reverse(array);
        for(String i : array){
            System.out.println(temp.toString() +" "+ i);
            if(i.equals("+") || i.equals("-") || i.equals("*") || i.equals("/")){
                
                Double num2 = Double.valueOf(temp.pop());
                Double num1 = Double.valueOf(temp.pop());
                
                if(i.equals("+")){
                    temp.push(String.valueOf((num2+num1)));
                }else if(i.equals("-")){
                    temp.push(String.valueOf((num2-num1)));
                }else if(i.equals("*")){
                    temp.push(String.valueOf((num2*num1)));
                }else if(i.equals("/")){
                    temp.push(String.valueOf((num2/num1)));
                }
            }else{
                temp.push(i);
            }
        }
        return temp.peek();
     }
}
