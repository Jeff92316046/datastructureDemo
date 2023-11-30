import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;
import java.io.IOException;
public class simpleInterpreter {
    public static void main(String args[]) throws IOException{
        String errorlist[] = {"file not exist","instrution wrong","load not yet",
                                "variable undefined 1","variable undefined 2","syntax error"};
        boolean loadFlag = false;
        fileloader fl = null;
        Scanner sc = null;
        Map <String,String> tokenStore = new HashMap<>();
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
                        System.out.println(errorlist[0]);
                    }
                }else{
                    System.out.println(errorlist[1]);
                }
            }else if(splitToken[0].toLowerCase().equals("run")){
                if(loadFlag){
                    //String temp[] = fl.fileContent.replace("\n", "").split(";"); 
                    
                }else{
                    System.out.println(errorlist[2]);
                }
            }else if(splitToken[0].toLowerCase().equals("debug")){
                if(loadFlag){
                    String temp1[] = fl.fileContent.split("\n"); 
                    for(int i=0;i<temp1.length;i++){
                        String temp2[] = temp1[i].split(";");
                        boolean breakFlag = false;
                        for(int j=0;j<temp2.length;j++){
                            if(temp2[j].contains("=")){
                                String temp3[] = temp2[j].split("=");
                                if(temp3[0].toLowerCase().charAt(0)>='a' && temp3[0].toLowerCase().charAt(0)<='z' && temp3.length==2){ 
                                    String temp4[] = temp3[1].replaceAll("[()]", "").split("[\\+\\-\\*\\/]");
                                    System.out.println(Arrays.toString(temp4));
                                    for(int k=0;k<temp4.length;k++){
                                        if(isNum(temp4[k])){
                                            continue;
                                        }else if(isAlphabet(temp4[k])){
                                            
                                            if(tokenStore.containsKey(temp4[k])){
                                               
                                                temp3[1] = temp3[1].replace(temp4[k],"("+tokenStore.get(temp4[k])+")");
                                                
                                            }else{
                                                System.out.println("line "+i+" "+errorlist[4]);
                                                breakFlag = true;
                                                break;
                                            }
                                        }else{
                                            System.out.println("line "+i+" "+errorlist[5]);
                                            breakFlag = true;
                                            break;
                                        }
                                    }
                                    if(breakFlag)break;
                                    tokenStore.put(temp3[0], temp3[1]);
                                }else{
                                    System.out.println("line "+i+" "+errorlist[3]);
                                    breakFlag = true;
                                    break;
                                }
                                System.out.println(tokenStore.toString());;
                            }else if(temp2[j].contains("infixtoprefix")){
                                String temp3[] = temp2[j].split(" ");
                                if(temp3[0].toLowerCase().charAt(0)>='a' && temp3[0].toLowerCase().charAt(0)<='z' && temp3.length==2){ 
                                    String temp4[] = temp3[1].replaceAll("[()]", "").split("[\\+\\-\\*\\/]");
                                    System.out.println(Arrays.toString(temp4));
                                    for(int k=0;k<temp4.length;k++){
                                        if(isNum(temp4[k])){
                                            continue;
                                        }else if(isAlphabet(temp4[k])){
                                            System.out.println("alpha1");
                                            if(tokenStore.containsKey(temp4[k])){
                                                System.out.println("alpha2");
                                                temp3[1] = temp3[1].replace(temp4[k],"("+tokenStore.get(temp4[k])+")");
                                                System.out.println(temp3[1]+" "+temp4[k]+" "+tokenStore.get(temp4[k]));
                                            }else{
                                                System.out.println("line "+i+" "+errorlist[4]);
                                                breakFlag = true;
                                                break;
                                            }
                                        }else{
                                            System.out.println("line "+i+" "+errorlist[5]);
                                            breakFlag = true;
                                            break;
                                        }
                                    }
                                    if(breakFlag)break;
                                    tokenStore.put(temp3[0], temp3[1]);
                                }else{
                                    System.out.println("line "+i+" "+errorlist[3]);
                                    breakFlag = true;
                                    break;
                                }
                                System.out.println(tokenStore.toString());;
                            }else if(temp2[j].contains("infixtopostfix")){
                                
                            }else if(temp2[j].contains("Print")){
                                
                            }else if(temp2[j].contains("println")){
                                
                            }else{

                            }
                        }
                        if(breakFlag)break;
                    }
                }else{
                    System.out.println(errorlist[2]);
                }
            }else if(splitToken[0].toLowerCase().equals("dump")){
                if(loadFlag){
                    System.out.println(fl.fileContent);
                }else{
                    System.out.println(errorlist[2]);
                }
            }else if(splitToken[0].toLowerCase().equals("help")){
                System.out.print("load : load 'filename'\n"+
                                    "run : run\n"+
                                    "debug : debug\n"+
                                    "dump : dump\n"+
                                    "exit : exit\n");
            }else if(splitToken[0].toLowerCase().equals("test")){
                String test1 = "aavaaba";
                System.out.println(test1.split("a")[0].equals(""));
            }else{
                if(!splitToken[0].toLowerCase().equals("exit")){
                    System.out.println(errorlist[1]);
                    continue;
                }else{
                    sc.close();
                    break;
                }
            }
            
        }
        
    }
    public static boolean isNum(String str){
        return str.matches("[+-]?\\d*(\\.\\d+)?");
    }
    public static boolean isAlphabet(String str){
        return str.matches("[a-z]*");
    }
    /* public static boolean regex(String str){  
        Pattern pattern1 = Pattern.compile("([a-z])=([0-9]+)");  
        boolean temp1 = true;
        boolean temp2 = true;
        String strTest = str.split("=")[1].replaceAll("[\\(\\)]", "");
        if(strTest.charAt(0)>'z' || strTest.charAt(0)<'a'){
            temp2 = false;
        }
        for(int i=1;i<strTest.length()-1;i+=2){
            if(strTest.charAt(i)=='+' || strTest.charAt(i) == '-' || strTest.charAt(i) == '*' || strTest.charAt(i) == '/' ){
                if(strTest.charAt(i+1)>'z' || strTest.charAt(i+1)<'a'){
                    temp1 = false;
                    break;
                }
            }
        }
        if(str.contains("infixtoprefix")){
            if(str.split(" ")[1].charAt(0)>'z' || str.split(" ")[1].charAt(0)<'a'){
                temp3 = false;
            }
        }
        if(str.contains("infixtopostfix")){
            if(str.split(" ")[1].charAt(0)>'z' || str.split(" ")[1].charAt(0)<'a'){
                temp3 = false;
            }
        }
        return pattern1.matcher(str).matches() || (temp1 && temp2);     
    } */
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
