import java.util.ArrayList;
import java.util.Arrays;

public class insertionsort {
    public static void main(String args[]){
        ArrayList<Integer> test =new ArrayList<Integer>(Arrays.asList(8,6,9,1,3,5,2,7,4));
        for(int i=0;i<test.size();i++){
            System.out.print(test.get(i)+" ");
        }
        System.out.println();   
        for(int i=1;i<test.size();i++){
            int temp = test.get(i);
            int index = 0;
            for(int j=i;j>0;j--){
                if(test.get(j-1)<temp){
                    index = j;
                    break;
                }
                test.set(j, test.get(j-1));
            }
            test.set(index, temp);
            for(int j=0;j<test.size();j++){
                System.out.print(test.get(j)+" ");
                
            }
            //System.out.print("  "+(index==0?"  ":test.get(index-1)+"<")+test.get(index)+(index==test.size()?"  ":"<"+test.get(index+1)));
            System.out.println();   
        }
        for(int i=0;i<test.size();i++){
            System.out.print(test.get(i)+" ");
        }
    }
}
