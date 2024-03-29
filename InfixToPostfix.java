import java.util.ArrayList;
import java.util.Stack;



public class InfixToPostfix {
    public static void main(String args[]){
        String input = "5-1*9*457/88/(28+646)*9";
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
                    /* for(;!(input.charAt(i+j)=='+') && !(input.charAt(i+j) == '-') && !(input.charAt(i+j) == '*') && !(input.charAt(i+j) == '/') && !(input.charAt(i+j) == '(') && !(input.charAt(i+j) == ')');j++){
                        System.out.println(i+" "+j);
                        if(i+j+1 == input.length()){
                            break;
                        }
                    }
                    System.out.println(i+" "+j);
                    i += j-1;
                    System.out.println(i+" "+j); */
                }
            }

        }
        System.out.println("question : " + input);
        str = itopo(str);
        System.out.println("result : "+str.toString());
        System.out.println(getValue(str));
    }
    public static ArrayList<String> itopo(ArrayList<String> str){
        Stack<String> st = new Stack<String>();
        ArrayList<String> temp = new ArrayList<String>();
        System.out.printf("%-5s%-16s%-23s\n","now","stack","string");
        for(int i=0;i<str.size();i++){
            System.out.printf("%-5s%-16s%-25s\n",str.get(i),st.toString(),temp.toString());
            if(str.get(i).equals("*") || str.get(i).equals("/")){
                if(!st.empty()){
                    if(st.peek().equals("*") || st.peek().equals("/")){
                        temp.add(st.pop());
                    }
                }
                st.push(str.get(i));
            }else if(str.get(i).equals("+") || str.get(i).equals("-")){
                for(;!st.empty() && !st.peek().equals("(");){
                    if(st.peek().equals("*") || st.peek().equals("/") || st.peek().equals("+") || st.peek().equals("-")){
                        temp.add(st.pop());
                    }
                }
                st.push(str.get(i));
            }else if(str.get(i).equals("(")){
                st.push(str.get(i));
            }else if(str.get(i).equals(")")){
                for(;!st.peek().equals("(");){
                    temp.add(st.pop());
                }
                st.pop();
            }else{
                temp.add(str.get(i));
            }
        }
        System.out.println("left stack element : "+st.toString());
        for(;!st.empty();){
            temp.add(st.pop());
        }
        return temp;
    }
     public static String getValue(ArrayList<String> array){
        Stack<String> temp = new Stack<>();   
        for(String i : array){
            if(i.equals("+") || i.equals("-") || i.equals("*") || i.equals("/")){
                System.out.println(temp.toString() +" "+ i);
                Double num1 = Double.valueOf(temp.pop());
                Double num2 = Double.valueOf(temp.pop());
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
