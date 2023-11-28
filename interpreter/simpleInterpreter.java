import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;
import java.io.IOException;
public class simpleInterpreter {
    public static void main(String args[]) throws IOException{
        boolean loadFlag = false;
        fileloader fl = null;
        Scanner sc = null;
        for(;;){
            System.out.print(">");
            sc = new Scanner(System.in);
            String token = sc.nextLine();
            String splitToken[] = token.split(" ");
            if(splitToken[0].toLowerCase().equals("load")){
                if(splitToken.length == 2){
                    fl = new fileloader(splitToken[1]);
                    if(fl.existFile()){
                        fl.init();
                        loadFlag = true;
                        System.out.println("load successfully");
                    }else{
                        System.out.println("file not exist");
                    }
                }else{
                    System.out.println("instrution wrong");
                }
            }else if(splitToken[0].toLowerCase().equals("run")){
                if(loadFlag){
                    //String temp[] = fl.fileContent.replace("\n", "").split(";"); 
                    
                }else{
                    System.out.println("load not yet");
                }
            }else if(splitToken[0].toLowerCase().equals("debug")){
                if(loadFlag){
                    String temp[] = fl.fileContent.replace("\n", "").split(";"); 
                    for(int i=0;i<temp.length;i++){
                        if(regex(temp[i])){
                            System.out.println("ok"+i);
                        }else{
                            System.out.println("no"+i);
                        }
                    }
                }else{
                    System.out.println("load not yet");
                }
            }else if(splitToken[0].toLowerCase().equals("dump")){
                if(loadFlag){
                    System.out.println(fl.fileContent);
                }else{
                    System.out.println("load not yet");
                }
            }else if(splitToken[0].toLowerCase().equals("help")){
                System.out.print("load : load 'filename'\n"+
                                    "run : run\n"+
                                    "debug : debug\n"+
                                    "dump : dump\n"+
                                    "exit : exit\n");
            }else{
                if(!splitToken[0].toLowerCase().equals("exit")){
                    System.out.println("instrution wrong");
                    continue;
                }else{
                    sc.close();
                    break;
                }
            }
            
        }
        
    }
    public static boolean regex(String str){  
        Pattern pattern1 = Pattern.compile("([a-z])=([0-9]+)");  
        boolean temp = true;
        Pattern pattern2 = Pattern.compile("");
        
        //String strTest = str.replace("=", "").replaceAll("[0-9]+", "").replaceAll("[\\+\\-\\*\\/]+", "").replaceAll("[a-z]", "");
        /* int a=0;
        System.out.println(strTest);
        for(int i=0;i<strTest.length();i++){
            if(strTest.charAt(i)=='('){
                a++;
            }else if(strTest.charAt(i)==')'){
                a--;
            }else{
                temp = false;
                break;
            }
            if(a<0){
                temp = false;
                break;
            }
        } */
        return pattern1.matcher(str).matches() || pattern2.matcher(str).matches() ;     
    }
    public static void infixtopostfix(ArrayList<String> array){
        Stack<String> st = new Stack<>();
        ArrayList<String> temp = new ArrayList<>();
        for(int i=0;i<array.size();i++){
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

class fileloader{
    FileReader fr;
    Scanner sc;
    File f;
    String fileContent;
    String fileName;
    fileloader(String fn){
        this.fileName = fn;
    }
    public void init() throws IOException{
        fr = new FileReader(fileName);
        sc = new Scanner(fr);
        for(int i=0;sc.hasNextLine();i++){
            if(i==0){
                fileContent = sc.nextLine();
            }else{
                fileContent = fileContent +"\n"+ sc.nextLine();
            }
        }
        sc.close();
    }
    public boolean existFile() throws IOException{
        f = new File(fileName);
        System.out.println(fileName);
        return f.isFile();
    }
    
}
