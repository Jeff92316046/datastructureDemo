import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.io.IOException;
public class simpleInterpreter {
    public static void main(String args[]) throws IOException{
        String errorlist[] = {"file not exist","instrution wrong","load not yet",
                                "variable undefined 1","variable undefined 2","syntax error"};
        boolean loadFlag = false;
        fileloader fl = null;
        Scanner sc = null;
        boolean swapLineFlag = false;
        Map <String,String> tokenStore = new HashMap<>();
        for(;;){
            if(swapLineFlag){
                System.out.println("");
                swapLineFlag = false;
            }
            System.out.print(">");
            sc = new Scanner(System.in);
            String token = sc.nextLine();
            String splitToken[] = token.split(" ");
            if(splitToken[0].toLowerCase().equals("load")){
                if(splitToken.length == 2){
                    fileloader fl_temp = new fileloader(splitToken[1]);
                    if(fl_temp.existFile()){
                        fl = fl_temp;
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
                    String temp1[] = fl.fileContent.split("\n"); 
                    for(int i=0;i<temp1.length;i++){
                        String temp2[] = temp1[i].toLowerCase().split(";");
                        String printTest[] = temp2[0].split(" ");
                        boolean breakFlag = false;
                        for(int j=0;j<temp2.length;j++){
                            if(temp2[j].contains("=")){
                                String temp3[] = temp2[j].replaceAll(" ", "").split("=");
                                if(temp3[0].toLowerCase().charAt(0)>='a' && temp3[0].toLowerCase().charAt(0)<='z' && temp3.length==2){ 
                                    if(!bracketsCheck(temp3[1])){
                                        System.out.println("line "+(i+1)+" "+errorlist[5]);
                                        break;
                                    }
                                    String temp4[] = temp3[1].replaceAll("[()]", "").split("[\\+\\-\\*\\/]");
                                    for(int k=0;k<temp4.length;k++){
                                        if(isNum(temp4[k])){
                                            continue;
                                        }else if(isAlphabet(temp4[k])){
                                            if(tokenStore.containsKey(temp4[k])){                                               
                                                temp3[1] = temp3[1].replace(temp4[k],"("+tokenStore.get(temp4[k])+")");
                                            }else{
                                                System.out.println("line "+(i+1)+" "+errorlist[4]);
                                                breakFlag = true;
                                                break;
                                            }
                                        }else{
                                            System.out.println("line "+(i+1)+" "+errorlist[5]);
                                            breakFlag = true;
                                            break;
                                        }
                                    }
                                    if(breakFlag)break;
                                    tokenStore.put(temp3[0], temp3[1]);
                                    swapLineFlag = false;
                                }else{
                                    System.out.println("line "+(i+1)+" "+errorlist[3]);
                                    breakFlag = true;
                                    break;
                                }
                                
                            }else if(temp2[j].contains("infixtoprefix")){
                                String temp3[] = temp2[j].split(" ");
                                if(temp3[0].equals("infixtoprefix") && temp3.length==2){
                                    if(!bracketsCheck(temp3[1])){
                                        System.out.println("line "+(i+1)+" "+errorlist[5]);
                                        break;
                                    }
                                    String temp4[] = temp3[1].replaceAll("[()]", "").split("[\\+\\-\\*\\/]");
                                    for(int k=0;k<temp4.length;k++){
                                        if(isNum(temp4[k])){
                                            continue;
                                        }else if(isAlphabet(temp4[k])){
                                            if(tokenStore.containsKey(temp4[k])){                                               
                                                temp3[1] = temp3[1].replace(temp4[k],"("+tokenStore.get(temp4[k])+")");
                                            }else{
                                                System.out.println("line "+(i+1)+" "+errorlist[4]);
                                                breakFlag = true;
                                                break;
                                            }
                                        }else{
                                            System.out.println("line "+(i+1)+" "+errorlist[5]);
                                            breakFlag = true;
                                            break;
                                        }
                                    }
                                    if(breakFlag)break;
                                    Boolean flag = false;

                                    ArrayList<String> str = new ArrayList<String>();
                                    for(int k=0;k<temp3[1].length();k++){
                                        if(temp3[1].charAt(k)=='+' || temp3[1].charAt(k) == '-' || temp3[1].charAt(k) == '*' || temp3[1].charAt(k) == '/' || temp3[1].charAt(k) == '(' || temp3[1].charAt(k) == ')'){
                                            str.add(String.valueOf(temp3[1].charAt(k)));
                                            flag = false;
                                        }else{
                                            if(!flag){
                                                str.add(String.valueOf(temp3[1].charAt(k)));
                                                flag = true;
                                            }else{
                                                str.set(str.size()-1,str.get(str.size()-1).concat(String.valueOf(temp3[1].charAt(k))));
                                            }
                                        }
                                    }
                                    infixtoprefix(str);
                                    swapLineFlag = false;
                                }else{
                                    System.out.println("line "+(i+1)+" "+errorlist[3]);
                                    breakFlag = true;
                                    break;
                                }
                            }else if(temp2[j].contains("infixtopostfix")){
                                String temp3[] = temp2[j].split(" ");
                                if(temp3[0].equals("infixtopostfix") && temp3.length==2){
                                    if(!bracketsCheck(temp3[1])){
                                        System.out.println("line "+(i+1)+" "+errorlist[5]);
                                        break;
                                    }
                                    String temp4[] = temp3[1].replaceAll("[()]", "").split("[\\+\\-\\*\\/]");
                                    for(int k=0;k<temp4.length;k++){
                                        if(isNum(temp4[k])){
                                            continue;
                                        }else if(isAlphabet(temp4[k])){
                                            if(tokenStore.containsKey(temp4[k])){                                               
                                                temp3[1] = temp3[1].replace(temp4[k],"("+tokenStore.get(temp4[k])+")");
                                            }else{
                                                System.out.println("line "+(i+1)+" "+errorlist[4]);
                                                breakFlag = true;
                                                break;
                                            }
                                        }else{
                                            System.out.println("line "+(i+1)+" "+errorlist[5]);
                                            breakFlag = true;
                                            break;
                                        }
                                    }
                                    if(breakFlag)break;
                                    Boolean flag = false;
                                    ArrayList<String> str = new ArrayList<String>();
                                    for(int k=0;k<temp3[1].length();k++){
                                        if(temp3[1].charAt(k)=='+' || temp3[1].charAt(k) == '-' || temp3[1].charAt(k) == '*' || temp3[1].charAt(k) == '/' || temp3[1].charAt(k) == '(' || temp3[1].charAt(k) == ')'){
                                            str.add(String.valueOf(temp3[1].charAt(k)));
                                            flag = false;
                                        }else{
                                            if(!flag){
                                                str.add(String.valueOf(temp3[1].charAt(k)));
                                                flag = true;
                                            }else{
                                                str.set(str.size()-1,str.get(str.size()-1).concat(String.valueOf(temp3[1].charAt(k))));
                                            }
                                        }
                                    }
                                    infixtopostfix(str);
                                    swapLineFlag = false;
                                }else{
                                    System.out.println("line "+(i+1)+" "+errorlist[3]);
                                    breakFlag = true;
                                    break;
                                }
                            }else if(printTest[0].equals("print")){
                                String temp3[] = temp2[j].split(" ");
                                if(temp3[0].equals("print") && temp3.length==2){
                                    if(temp3[1].toLowerCase().charAt(0)>='a' && temp3[1].toLowerCase().charAt(0)<='z'){
                                        if(tokenStore.containsKey(temp3[1])){
                                            
                                        }else{
                                            System.out.println("line "+(i+1)+" "+errorlist[4]);
                                            break;
                                        }
                                    }else{
                                        System.out.println("line "+(i+1)+" "+errorlist[3]);
                                        break;
                                    }
                                }else{
                                    System.out.println("line "+(i+1)+" "+errorlist[3]);
                                    break;
                                }
                                System.out.print(tokenStore.get(temp3[1])+" ");
                                swapLineFlag = true;
                            }else if(printTest[0].equals("println")){
                                String temp3[] = temp2[j].split(" ");
                                if(temp3[0].equals("println") && temp3.length==2 ){
                                    if(temp3[1].toLowerCase().charAt(0)>='a' && temp3[1].toLowerCase().charAt(0)<='z'){
                                        if(tokenStore.containsKey(temp3[1])){
                                            
                                        }else{
                                            System.out.println("line "+(i+1)+" "+errorlist[4]);
                                            break;
                                        }
                                    }else{
                                        System.out.println("line "+(i+1)+" "+errorlist[3]);
                                        break;
                                    }
                                }else{
                                    System.out.println("line "+(i+1)+" "+errorlist[3]);
                                    break;
                                }
                                System.out.println(tokenStore.get(temp3[1]));
                                swapLineFlag = false;
                            }else{
                                System.out.println("line "+(i+1)+" "+errorlist[3]);
                                break;
                            }
                        }
                        if(breakFlag)break;
                    }
                    
                }else{
                    System.out.println(errorlist[2]);
                }
            }else if(splitToken[0].toLowerCase().equals("debug")){
                if(loadFlag){
                    String temp1[] = fl.fileContent.split("\n"); 
                    for(int i=0;i<temp1.length;i++){
                        String temp2[] = temp1[i].toLowerCase().split(";");
                        String printTest[] = temp2[0].split(" ");
                        boolean breakFlag = false;
                        for(int j=0;j<temp2.length;j++){
                            if(temp2[j].contains("=")){
                                String temp3[] = temp2[j].replaceAll(" ", "").split("=");
                                if(temp3[0].toLowerCase().charAt(0)>='a' && temp3[0].toLowerCase().charAt(0)<='z' && temp3.length==2){ 
                                    if(!bracketsCheck(temp3[1])){
                                        System.out.println("line "+(i+1)+" "+errorlist[5]);
                                        break;
                                    }
                                    String temp4[] = temp3[1].replaceAll("[()]", "").split("[\\+\\-\\*\\/]");
                                    for(int k=0;k<temp4.length;k++){
                                        if(isNum(temp4[k])){
                                            continue;
                                        }else if(isAlphabet(temp4[k])){
                                            if(tokenStore.containsKey(temp4[k])){                                               
                                                temp3[1] = temp3[1].replace(temp4[k],"("+tokenStore.get(temp4[k])+")");
                                            }else{
                                                System.out.println("line "+(i+1)+" "+errorlist[4]);
                                                breakFlag = true;
                                                break;
                                            }
                                        }else{
                                            System.out.println("line "+(i+1)+" "+errorlist[5]);
                                            breakFlag = true;
                                            break;
                                        }
                                    }
                                    if(breakFlag)break;
                                    tokenStore.put(temp3[0], temp3[1]);
                                }else{
                                    System.out.println("line "+(i+1)+" "+errorlist[3]);
                                    breakFlag = true;
                                    break;
                                }
                                
                            }else if(temp2[j].contains("infixtoprefix")){
                                String temp3[] = temp2[j].split(" ");
                                if(temp3[0].equals("infixtoprefix") && temp3.length==2){
                                    if(!bracketsCheck(temp3[1])){
                                        System.out.println("line "+(i+1)+" "+errorlist[5]);
                                        break;
                                    }
                                    String temp4[] = temp3[1].replaceAll("[()]", "").split("[\\+\\-\\*\\/]");
                                    for(int k=0;k<temp4.length;k++){
                                        if(isNum(temp4[k])){
                                            continue;
                                        }else if(isAlphabet(temp4[k])){
                                            if(tokenStore.containsKey(temp4[k])){                                               
                                                temp3[1] = temp3[1].replace(temp4[k],"("+tokenStore.get(temp4[k])+")");
                                            }else{
                                                System.out.println("line "+(i+1)+" "+errorlist[4]);
                                                breakFlag = true;
                                                break;
                                            }
                                        }else{
                                            System.out.println("line "+(i+1)+" "+errorlist[5]);
                                            breakFlag = true;
                                            break;
                                        }
                                    }
                                    if(breakFlag)break;
                                }else{
                                    System.out.println("line "+(i+1)+" "+errorlist[3]);
                                    breakFlag = true;
                                    break;
                                }
                            }else if(temp2[j].contains("infixtopostfix")){
                                String temp3[] = temp2[j].split(" ");
                                if(temp3[0].equals("infixtopostfix") && temp3.length==2){
                                    if(!bracketsCheck(temp3[1])){
                                        System.out.println("line "+(i+1)+" "+errorlist[5]);
                                        break;
                                    }
                                    String temp4[] = temp3[1].replaceAll("[()]", "").split("[\\+\\-\\*\\/]");
                                    for(int k=0;k<temp4.length;k++){
                                        if(isNum(temp4[k])){
                                            continue;
                                        }else if(isAlphabet(temp4[k])){
                                            if(tokenStore.containsKey(temp4[k])){                                               
                                                temp3[1] = temp3[1].replace(temp4[k],"("+tokenStore.get(temp4[k])+")");
                                            }else{
                                                System.out.println("line "+(i+1)+" "+errorlist[4]);
                                                breakFlag = true;
                                                break;
                                            }
                                        }else{
                                            System.out.println("line "+(i+1)+" "+errorlist[5]);
                                            breakFlag = true;
                                            break;
                                        }
                                    }
                                    if(breakFlag)break;
                                }else{
                                    System.out.println("line "+(i+1)+" "+errorlist[3]);
                                    breakFlag = true;
                                    break;
                                }
                            }else if(printTest[0].equals("print")){
                                String temp3[] = temp2[j].split(" ");
                                if(temp3[0].equals("print") && temp3.length==2){
                                    if(temp3[1].toLowerCase().charAt(0)>='a' && temp3[1].toLowerCase().charAt(0)<='z'){
                                        if(tokenStore.containsKey(temp3[1])){
                                            continue;
                                        }else{
                                            System.out.println("line "+(i+1)+" "+errorlist[4]);
                                            break;
                                        }
                                    }else{
                                        System.out.println("line "+(i+1)+" "+errorlist[3]);
                                        break;
                                    }
                                }else{
                                    System.out.println("line "+(i+1)+" "+errorlist[3]);
                                    break;
                                }
                            }else if(printTest[0].equals("println")){
                                String temp3[] = temp2[j].split(" ");
                                if(temp3[0].equals("println") && temp3.length==2 ){
                                    if(temp3[1].toLowerCase().charAt(0)>='a' && temp3[1].toLowerCase().charAt(0)<='z'){
                                        if(tokenStore.containsKey(temp3[1])){
                                            continue;
                                        }else{
                                            System.out.println("line "+(i+1)+" "+errorlist[4]);
                                            break;
                                        }
                                    }else{
                                        System.out.println("line "+(i+1)+" "+errorlist[3]);
                                        break;
                                    }
                                }else{
                                    System.out.println("line "+(i+1)+" "+errorlist[3]);
                                    break;
                                }
                            }else{
                                System.out.println("line "+(i+1)+" "+errorlist[3]);
                                break;
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
                String input = "5-1*9*457/88/(28+646)*942";
                Boolean flag = false;
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
        return str.matches("[0-9]+");
    }
    public static boolean isAlphabet(String str){
        return str.matches("[a-z]+");
    }
    public static boolean bracketsCheck(String str){
        int sum=0;
        boolean flag = true;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '('){
                sum++;
            }else if(str.charAt(i) == ')'){
                sum--;
            }else{
                continue;
            }
            if(sum<0){
                flag = false;
                break;
            }
        }
        if(sum!=0)flag = false;
        return flag;
    }
    public static boolean arithmeticCheck(String str){
        boolean temp1 = true;
        String temp2[] = str.replaceAll("[()]", "").split("[\\+\\-\\*\\/]");
        for(int k=0;k<temp2.length;k++){
            if(isNum(temp2[k])){
                continue;
            }else{
                temp1 = false;
                break;
            }
        }
        if(!bracketsCheck(str)) temp1=false;
        return temp1;
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
                if(!st.empty()){
                    if(st.peek().equals("*") || st.peek().equals("/")){
                        temp.add(st.pop());
                    }
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
        System.out.print("postfix : ");
        for(int i=0;i<temp.size();i++){
            System.out.print(temp.get(i)+" ");
        }
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
        System.out.print("prefix : ");
        for(int i=0;i<temp.size();i++){
            System.out.print(temp.get(i)+" ");
        }
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
        System.out.println("\nvalue = "+st.peek());
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
        System.out.println("\nvalue = "+st.peek());
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
