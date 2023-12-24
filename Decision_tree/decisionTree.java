import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class decisionTree {
    public static void main(String args[])throws IOException{
        int identityMode = 0;
        ArrayList <String> treeNameList = new ArrayList<>();
        Map <String,ArrayList<judgeAttribute>> treeAttributeList = new HashMap<>();
        Map <String,node> treeNodeMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        for(;;){
            System.out.println("input number\n1.searcher mode\n2.editor mode\n3.exit");
            String inputTemp0 =  sc.next();
            if(!isNum(inputTemp0)){
                System.out.println("input error");
                continue;
            }
            identityMode = Integer.valueOf(inputTemp0);
            if(identityMode == 1){
                int searcher_mode = 0;
                for(;;){
                    System.out.println("input number\n1.read data from file and output\n2.read data from file and save output into file\n3.back");
                    String inputTemp1 =  sc.next();
                    if(!isNum(inputTemp1)){
                        System.out.println("input error");
                        continue;
                    }
                    searcher_mode = Integer.valueOf(inputTemp1);
                    if(searcher_mode == 1){
                        if(treeNameList.size()==0){
                            System.out.println("no tree");
                            continue;
                        }
                        System.out.println("input number to choose tree");
                        for(int i=0;i<treeNameList.size();i++){
                            System.out.println((i+1)+"."+treeNameList.get(i));
                        }
                        String inputTemp3 =  sc.next();
                        if(!isNum(inputTemp3)){
                            System.out.println("input error");
                            continue;
                        }
                        int testTreeTemp = Integer.valueOf(inputTemp3);
                        System.out.println("input file name");
                        String fileName = sc.next();
                        fileloader fl = new fileloader(fileName);
                        if(fl.existFile()){
                            fl.init();
                            String fileToken[] = fl.fileContent.split("\n");
                            String titleToken[] = fileToken[0].split(" ");
                            for(int i=1;i<fileToken.length;i++){
                                Map <String,Double> testTreeMap = new HashMap<>();
                                String lineToken[] = fileToken[i].split(" ");
                                for(int j=1;j<titleToken.length;j++){
                                    testTreeMap.put(titleToken[j],Double.valueOf(lineToken[j]));
                                }
                                node nowNode = treeNodeMap.get(treeNameList.get(testTreeTemp-1));
                                for(;;){
                                    if(nowNode.endOutput!=null ){
                                        System.out.printf("output%2d:%s\n",i,nowNode.endOutput);
                                        break;
                                    }
                                    if(testTreeMap.get(nowNode.judger.judgeStr)>=nowNode.judgeNum){
                                        nowNode = nowNode.leftNode;
                                    }else{
                                        nowNode = nowNode.rightNode;
                                    }
                                }
                            }
                        }else{
                            System.out.println("file is not find");
                            continue;
                        }
                        
                    }else if(searcher_mode == 2){
                        if(treeNameList.size()==0){
                            System.out.println("no tree");
                            continue;
                        }
                        System.out.println("input number to choose tree");
                        for(int i=0;i<treeNameList.size();i++){
                            System.out.println((i+1)+"."+treeNameList.get(i));
                        }
                        String inputTemp2 =  sc.next();
                        if(!isNum(inputTemp2)){
                            System.out.println("input error");
                            continue;
                        }
                        int testTreeTemp = Integer.valueOf(inputTemp2);
                        System.out.println("input file name");
                        String fileName = sc.next();
                        fileloader fl = new fileloader(fileName);
                        String resultContent = "";
                        if(fl.existFile()){
                            fl.init();
                            String fileToken[] = fl.fileContent.split("\n");
                            String titleToken[] = fileToken[0].split(" ");
                            for(int i=1;i<fileToken.length;i++){
                                Map <String,Double> testTreeMap = new HashMap<>();
                                String lineToken[] = fileToken[i].split(" ");
                                for(int j=1;j<titleToken.length;j++){
                                    testTreeMap.put(titleToken[j],Double.valueOf(lineToken[j]));
                                }
                                node nowNode = treeNodeMap.get(treeNameList.get(testTreeTemp-1));
                                for(;;){
                                    if(nowNode.endOutput!=null ){
                                        resultContent = resultContent + i + " " + nowNode.endOutput + "\n";
                                        System.out.printf("output%2d:%s\n",i,nowNode.endOutput);
                                        break;
                                    }
                                    if(testTreeMap.get(nowNode.judger.judgeStr)>=nowNode.judgeNum){
                                        nowNode = nowNode.leftNode;
                                    }else{
                                        nowNode = nowNode.rightNode;
                                    }
                                }
                            }
                            System.out.println("input result file name");
                            String fwName = sc.next(); 
                            FileWriter fw = new FileWriter(fwName);
                            fw.write(resultContent);
                            fw.flush();
                            fw.close();
                        }else{
                            System.out.println("file is not find");
                            continue;
                        }
                        
                    }else if(searcher_mode == 3){
                        break;
                    }else{
                        System.out.println("input wrong");
                    }
                }
            }else if(identityMode == 2){
                int editor_mode = 0;
                for(;;){
                    System.out.println("input number\n1.new a tree\n2.editor a tree\n3.save a tree\n4.test a tree\n5.read tree from file\n6.back");
                    String inputTemp4 =  sc.next();
                    if(!isNum(inputTemp4)){
                        System.out.println("input error");
                        continue;
                    }
                    editor_mode = Integer.valueOf(inputTemp4);
                    if(editor_mode == 1){
                        String treeNameTemp;
                        System.out.println("input tree name");
                        treeNameTemp = sc.next();
                        treeNameList.add(treeNameTemp);
                        String treeAttributeTemp ;
                        System.out.println("input tree attribute(input 0 to exit)");
                        treeAttributeList.put(treeNameTemp, new ArrayList<judgeAttribute>());
                        for(;;){
                            treeAttributeTemp = sc.next();
                            if(treeAttributeTemp.equals("0"))break;
                            treeAttributeList.get(treeNameTemp).add(new judgeAttribute(treeAttributeTemp));
                        }
                        System.out.println("bulid tree");
                        node nowNode = new node();
                        System.out.println("input root judge attribute");
                        for(int i=0;i<treeAttributeList.get(treeNameTemp).size();i++){
                            System.out.println((i+1)+"."+treeAttributeList.get(treeNameTemp).get(i).judgeStr);
                        }
                        String inputTemp5 =  sc.next();
                        if(!isNum(inputTemp5)){
                            System.out.println("input error");
                            continue;
                        }
                        int rootAttributeTemp = Integer.valueOf(inputTemp5);
                        if(rootAttributeTemp < 1 || rootAttributeTemp>treeAttributeList.get(treeNameTemp).size()){
                            System.out.println("input error");
                            treeNameList.remove(treeNameTemp);
                            continue;
                        }
                        System.out.println("input root judge number");
                        Double rootJudgeNumTemp = sc.nextDouble();
                        nowNode = new node(treeAttributeList.get(treeNameTemp).get(rootAttributeTemp-1), rootJudgeNumTemp,null ,new node(), new node());
                        nowNode.leftNode.fatherNode = nowNode;
                        nowNode.rightNode.fatherNode = nowNode;
                        treeNodeMap.put(treeNameTemp, nowNode);
                        ArrayList<String> nowNodePrompt = new ArrayList<>();
                        nowNodePrompt.add("(root)");
                        int newTreetoken;
                        for(;;){  
                            System.out.print("now node:"+nowNodePrompt.get(0));
                            for(int i=1;i<nowNodePrompt.size();i++){
                                System.out.print("->"+nowNodePrompt.get(i));
                            }
                            System.out.println("");
                            System.out.println("1.switch to father node\n2.switch to right node\n3.switch to left node\n4.edit end output\n5.edit judge state\n6.delete end output\n7.back");
                            String inputTemp6 =  sc.next();
                            if(!isNum(inputTemp6)){
                                System.out.println("input error");
                                continue;
                            }
                            newTreetoken = Integer.valueOf(inputTemp6);
                            if(newTreetoken == 1){
                                if(nowNode.fatherNode==null){
                                    System.out.println("no father node");
                                    continue;
                                }else{
                                    nowNodePrompt.remove(nowNodePrompt.size()-1);
                                    nowNode = nowNode.fatherNode;
                                }
                            }else if(newTreetoken == 2){
                                if(nowNode.rightNode==null){
                                    System.out.println("no right node");
                                    continue;
                                }else{
                                    nowNodePrompt.add("R");
                                    nowNode = nowNode.rightNode;
                                }
                            }else if(newTreetoken == 3){
                                if(nowNode.leftNode==null){
                                    System.out.println("no left node");
                                    continue;
                                }else{
                                    nowNodePrompt.add("L");
                                    nowNode = nowNode.leftNode;
                                }
                            }else if(newTreetoken == 4){
                                
                                if(nowNode.leftNode!=null && nowNode.rightNode!=null){
                                    System.out.println("now node is not leaf node");   
                                }else{
                                    System.out.println("input end output");
                                    String endOutputTemp = sc.next();
                                    nowNode.endOutput =endOutputTemp;
                                }
                            }else if(newTreetoken == 5){
                                if(nowNode.endOutput == null){
                                    for(int i=0;i<treeAttributeList.get(treeNameTemp).size();i++){
                                        System.out.println((i+1)+"."+treeAttributeList.get(treeNameTemp).get(i).judgeStr);
                                    }
                                    String inputTemp7 =  sc.next();
                                    if(!isNum(inputTemp7)){
                                        System.out.println("input error");
                                        continue;
                                    }
                                    int nowNodeAttributeTemp = Integer.valueOf(inputTemp7);
                                    if(nowNodeAttributeTemp < 1 || nowNodeAttributeTemp>treeAttributeList.get(treeNameTemp).size()){
                                        System.out.println("input error");
                                        continue;
                                    }
                                    System.out.println("input root judge number");
                                    Double nowNodeJudgeNumTemp = sc.nextDouble();
                                    nowNode.judgeNum = nowNodeJudgeNumTemp;
                                    nowNode.judger = treeAttributeList.get(treeNameTemp).get(nowNodeAttributeTemp-1);
                                    nowNode.leftNode =new node(nowNode);
                                    nowNode.rightNode = new node(nowNode);
                                }else{
                                    System.out.println("now node is end output");
                                }
                            }else if(newTreetoken == 6){
                                nowNode.endOutput = null;
                            }else if(newTreetoken == 7){
                                break;
                            }
                        }
                    }else if(editor_mode == 2){
                        if(treeNameList.size()==0){
                            System.out.println("no tree");
                            continue;
                        }
                        System.out.println("input number to choose tree");
                        for(int i=0;i<treeNameList.size();i++){
                            System.out.println((i+1)+"."+treeNameList.get(i));
                        }
                        String inputTemp8 =  sc.next();
                        if(!isNum(inputTemp8)){
                            System.out.println("input error");
                            continue;
                        }
                        int editTreeTemp = Integer.valueOf(inputTemp8);
                        ArrayList<String> nowNodePrompt = new ArrayList<>();
                        nowNodePrompt.add("(root)");
                        String treeNameTemp = treeNameList.get(editTreeTemp-1);
                        node nowNode = treeNodeMap.get(treeNameTemp);
                        int newTreetoken;   
                        for(;;){  
                            System.out.print("now node:"+nowNodePrompt.get(0));
                            for(int i=1;i<nowNodePrompt.size();i++){
                                System.out.print("->"+nowNodePrompt.get(i));
                            }
                            System.out.println("");
                            System.out.println("1.switch to father node\n2.switch to right node\n3.switch to left node\n4.edit end output\n5.edit judge state\n6.delete end output\n7.back");
                            String inputTemp9 =  sc.next();
                            if(!isNum(inputTemp9)){
                                System.out.println("input error");
                                continue;
                            }
                            newTreetoken = Integer.valueOf(inputTemp9);
                            if(newTreetoken == 1){
                                if(nowNode.fatherNode==null){
                                    System.out.println("no father node");
                                    continue;
                                }else{
                                    nowNodePrompt.remove(nowNodePrompt.size()-1);
                                    nowNode = nowNode.fatherNode;
                                }
                            }else if(newTreetoken == 2){
                                if(nowNode.rightNode==null){
                                    System.out.println("no right node");
                                    continue;
                                }else{
                                    nowNodePrompt.add("R");
                                    nowNode = nowNode.rightNode;
                                }
                            }else if(newTreetoken == 3){
                                if(nowNode.leftNode==null){
                                    System.out.println("no left node");
                                    continue;
                                }else{
                                    nowNodePrompt.add("L");
                                    nowNode = nowNode.leftNode;
                                }
                            }else if(newTreetoken == 4){
                                
                                if(nowNode.leftNode!=null && nowNode.rightNode!=null){
                                    System.out.println("now node is not leaf node");   
                                }else{
                                    System.out.println("input end output");
                                    String endOutputTemp = sc.next();
                                    nowNode.endOutput =endOutputTemp;
                                }
                            }else if(newTreetoken == 5){
                                if(nowNode.endOutput == null){
                                    for(int i=0;i<treeAttributeList.get(treeNameTemp).size();i++){
                                        System.out.println((i+1)+"."+treeAttributeList.get(treeNameTemp).get(i).judgeStr);
                                    }
                                    String inputTemp10 =  sc.next();
                                    if(!isNum(inputTemp10)){
                                        System.out.println("input error");
                                        continue;
                                    }
                                    int nowNodeAttributeTemp = Integer.valueOf(inputTemp10);
                                    if(nowNodeAttributeTemp < 1 || nowNodeAttributeTemp>treeAttributeList.get(treeNameTemp).size()){
                                        System.out.println("input error");
                                        continue;
                                    }
                                    System.out.println("input root judge number");
                                    Double nowNodeJudgeNumTemp = sc.nextDouble();
                                    nowNode.judgeNum = nowNodeJudgeNumTemp;
                                    nowNode.judger = treeAttributeList.get(treeNameTemp).get(nowNodeAttributeTemp-1);
                                    nowNode.leftNode =new node(nowNode);
                                    nowNode.rightNode = new node(nowNode);
                                }else{
                                    System.out.println("now node is end output");
                                }
                            }else if(newTreetoken == 6){
                                nowNode.endOutput = null;
                            }else if(newTreetoken == 7){
                                break;
                            }
                        }
                    }else if(editor_mode == 3){
                        if(treeNameList.size()==0){
                            System.out.println("no tree");
                            continue;
                        }
                        System.out.println("input number to choose tree");
                        for(int i=0;i<treeNameList.size();i++){
                            System.out.println((i+1)+"."+treeNameList.get(i));
                        }
                        String inputTemp11 =  sc.next();
                        if(!isNum(inputTemp11)){
                            System.out.println("input error");
                            continue;
                        }
                        int saveTreeTemp = Integer.valueOf(inputTemp11);
                        System.out.println("input your file name");
                        String saveTreeFileNameTemp = sc.next();
                        FileWriter fw = new FileWriter(saveTreeFileNameTemp);
                        String tempString = treeNameList.get(saveTreeTemp-1)+"\n";
                        tempString += "|\n";
                        for(int i=0;i<treeAttributeList.get(treeNameList.get(saveTreeTemp-1)).size();i++){
                            tempString += treeAttributeList.get(treeNameList.get(saveTreeTemp-1)).get(i).judgeStr+"\n";
                        }
                        tempString += "|\n";
                        fw.write(tempString + saveTree(treeNodeMap.get(treeNameList.get(saveTreeTemp-1))));
                        fw.flush();
                        fw.close();
                        /* ArrayList <node> queue = new ArrayList<>();
                        queue.add(treeNodeMap.get(treeNameList.get(saveTreeTemp-1)));
                        ArrayList <node> result = new ArrayList<>();
                        for(;!queue.isEmpty();){
                            node temp = queue.get(0);
                            queue.remove(0);
                            result.add(temp);
                            if(temp==null)continue;
                            if(temp.leftNode!=null){
                                queue.add(temp.leftNode);
                            }else{
                                queue.add(null);
                            }
                            if(temp.rightNode!=null){
                                queue.add(temp.rightNode);
                            }else{
                                queue.add(null);
                            }
                        }
                        for(int i=0;i<result.size();i++){
                            System.out.println(result.get(i));
                        } */
                    }else if(editor_mode == 4){
                        if(treeNameList.size()==0){
                            System.out.println("no tree");
                            continue;
                        }
                        System.out.println("input number to choose tree");
                        for(int i=0;i<treeNameList.size();i++){
                            System.out.println((i+1)+"."+treeNameList.get(i));
                        }
                        String inputTemp12 =  sc.next();
                        if(!isNum(inputTemp12)){
                            System.out.println("input error");
                            continue;
                        }
                        int testTreeTemp = Integer.valueOf(inputTemp12);
                        System.out.println("input your test data\nAttribute:");
                        Map <String,Double>testTreeMap = new HashMap<>();
                        for(int i=0;i<treeAttributeList.get(treeNameList.get(testTreeTemp-1)).size();i++){
                            System.out.print(treeAttributeList.get(treeNameList.get(testTreeTemp-1)).get(i).judgeStr+":");
                            Double testTreeTempData = sc.nextDouble();
                            testTreeMap.put(treeAttributeList.get(treeNameList.get(testTreeTemp-1)).get(i).judgeStr, testTreeTempData);
                        }
        
                        node nowNode = treeNodeMap.get(treeNameList.get(testTreeTemp-1));
                        for(;;){
                            
                            if(nowNode.endOutput!=null ){
                                System.out.println("output : "+nowNode.endOutput);
                                break;
                            }
                            if(testTreeMap.get(nowNode.judger.judgeStr)>=nowNode.judgeNum){
                                nowNode = nowNode.leftNode;
                            }else{
                                nowNode = nowNode.rightNode;
                            }
                        }
                    }else if(editor_mode == 5){
                        System.out.println("input your file name");
                        String readTreeFileNameTemp = sc.next();
                        fileloader fl = new fileloader(readTreeFileNameTemp);
                        if(fl.existFile()){
                            fl.init();
                            Stack <String> st = new Stack<>();
                            String readTreeFileContent[] =fl.fileContent.split("\\|");
                            readTreeFileContent[0] = readTreeFileContent[0].replace("\n","").replace(" ","");
                            treeNameList.add(readTreeFileContent[0]);
                            treeNodeMap.put(readTreeFileContent[0], new node());
                            treeAttributeList.put(readTreeFileContent[0], new ArrayList<>());
                            
                            String readTreeFileContentAttribute[] = readTreeFileContent[1].split("\n"); 
                            for(int i=0;i<readTreeFileContentAttribute.length;i++){
                                if(readTreeFileContentAttribute[i].equals("")){
                                    continue;
                                }else{
                                    treeAttributeList.get(readTreeFileContent[0]).add(new judgeAttribute(readTreeFileContentAttribute[i]));
                                }
                            
                            }
                            String readTreeFileContentNode[] =  readTreeFileContent[2].split("\n");
                            node nowNode = treeNodeMap.get(readTreeFileContent[0]);
                            for(int i=0;i<readTreeFileContentNode.length;i++){
                                if(readTreeFileContentNode[i].equals("")){
                                    continue;
                                }else if(readTreeFileContentNode[i].equals("{")){
                                    st.push("{");
                                    if(st.size()==1){
                                        continue;
                                    }
                                    if(nowNode.leftNode==null){
                                        nowNode.leftNode = new node(nowNode);
                                        nowNode = nowNode.leftNode;
                                    }else {
                                        nowNode.rightNode = new node(nowNode);
                                        nowNode = nowNode.rightNode;
                                    }
                                }else if(readTreeFileContentNode[i].equals("}")){
                                    st.pop();
                                    nowNode = nowNode.fatherNode;
                                }else{
                                    String readTreeFileContentNodeData[]  = readTreeFileContentNode[i].split(";"); 
                                    if(readTreeFileContentNodeData.length==2){
                                        nowNode.judgeNum = Double.valueOf(readTreeFileContentNodeData[0]);
                                        for(int j=0;j<treeAttributeList.get(readTreeFileContent[0]).size();j++){
                                            if(treeAttributeList.get(readTreeFileContent[0]).get(j).judgeStr.equals(readTreeFileContentNodeData[1])){
                                                nowNode.judger = treeAttributeList.get(readTreeFileContent[0]).get(j);
                                                break;
                                            }
                                        }
                                    }else{
                                        nowNode.endOutput = readTreeFileContentNodeData[2];
                                    }
                                }   
                            }


                        }else{
                            System.out.println("file not exist");
                        }
                    }else if(editor_mode == 6){
                        break;
                    }else{
                        System.out.println("input wrong");
                    }
                }
            }else{
                if(identityMode == 3){
                    sc.close();
                    break;
                }else{
                    System.out.println("input wrong");
                }
            }
        }
        
    }
    public static String saveTree(node nowNode){
        String temp = "";
        if(nowNode.judgeNum == null){
            temp = temp + ";";
        }else{
            temp = temp + nowNode.judgeNum +";";
        }
        if(nowNode.judger == null){
            temp = temp +";";
        }else{
            temp = temp + nowNode.judger.judgeStr +";";
        }
        if(nowNode.endOutput == null){
            temp = temp + ";\n";
        }else{
            temp = temp + nowNode.endOutput +";\n";
        }
        
        if(nowNode.leftNode == null && nowNode.rightNode == null){
            return "{\n" + temp  +"}\n";
        }else{
            return "{\n" + temp + saveTree(nowNode.leftNode) + saveTree(nowNode.rightNode)  +"}\n";
        }
    }
    public static boolean isNum(String str){
        return str.matches("[0-9]+");
    }
}
class judgeAttribute{
    String judgeStr;
    judgeAttribute(){

    }
    judgeAttribute(String str){
        this.judgeStr = str;
    }
}
class node{
    judgeAttribute judger;
    Double judgeNum;
    node fatherNode;
    node rightNode;
    node leftNode;
    String endOutput;
    node(){

    }
    node(judgeAttribute judger,Double judgeNum,node fatherNode,node leftNode,node rightNode){
        this.judger = judger;
        this.judgeNum = judgeNum;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.fatherNode = fatherNode;
    }
    node(node fatherNode){
        this.fatherNode = fatherNode;
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
        return f.isFile();
    }
    
}