import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class dicisionTree {
    public static void main(String args[]){
        int identityMode = 0;
        ArrayList <String> treeNameList = new ArrayList<>();
        Map <String,ArrayList<judgeAttribute>> treeAttributeList = new HashMap<>();
        Map <String,node> treeNodeMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        for(;;){
            System.out.println("input number\n1.searcher mode\n2.editor mode\n3.exit");
            identityMode = sc.nextInt();
            if(identityMode == 1){
                
            }else if(identityMode == 2){
                int searcher_mode = 0;
                for(;;){
                    System.out.println("input number\n1.new a tree\n2.editor a tree\n3.save a tree\n4.test a tree\n5.back");
                    searcher_mode = sc.nextInt();
                    if(searcher_mode == 1){
                        System.out.println(treeNameList.toString());
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
                        int rootAttributeTemp = sc.nextInt();
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
                            newTreetoken = sc.nextInt();
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
                                    int nowNodeAttributeTemp = sc.nextInt();
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
                    }else if(searcher_mode == 2){
                        if(treeNameList.size()==0){
                            System.out.println("no tree");
                            continue;
                        }
                        for(int i=0;i<treeNameList.size();i++){
                            System.out.println((i+1)+"."+treeNameList.get(i));
                        }
                        int editTreeTemp = sc.nextInt();
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
                            newTreetoken = sc.nextInt();
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
                                    int nowNodeAttributeTemp = sc.nextInt();
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
                    }else if(searcher_mode == 3){
                        if(treeNameList.size()==0){
                            System.out.println("no tree");
                            continue;
                        }
                        for(int i=0;i<treeNameList.size();i++){
                            System.out.println((i+1)+"."+treeNameList.get(i));
                        }
                        int saveTreeTemp = sc.nextInt();
                        ArrayList <node> queue = new ArrayList<>();
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
                        }
                    }else if(searcher_mode == 4){
                        System.out.println(3/2);
                    }else if(searcher_mode == 5){
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
        System.out.println(fileName);
        return f.isFile();
    }
    
}