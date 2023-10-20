import java.util.ArrayList;
import java.util.Arrays;

public class mergesort{
    public static void main(String args[]){
        ArrayList<Integer> test =new ArrayList<Integer>(Arrays.asList(8,6,9,1,3,5,2,7,4));
        for(int i=0;i<test.size();i++){
            System.out.print(test.get(i)+" ");
        }
        ArrayList<Integer> result = new ArrayList<Integer>(mergeSort(test));
        System.out.println();
        for(int i=0;i<result.size();i++){
            System.out.print(result.get(i)+" ");
        }
        System.out.println();
    }
    public static ArrayList<Integer> merge(ArrayList<Integer> left,ArrayList<Integer> right){
        ArrayList<Integer> result = new ArrayList<>();
        System.out.println();
        for(int i=0;i<left.size();i++){
            System.out.print(left.get(i)+" ");
        }
        System.out.print("|");
        for(int i=0;i<right.size();i++){
            System.out.print(right.get(i)+" ");
        }
        
        while(left.size()!=0 && right.size()!=0){
            if(left.get(0)<right.get(0)){
                result.add(left.get(0));
                left.remove(0);
            }else{
                result.add(right.get(0));
                right.remove(0);
            }

        }
        result.addAll(left.size()!=0?left:right);
        System.out.print(" => ");
        
        for(int i=0;i<result.size();i++){
            System.out.print(result.get(i)+" ");
        }
        return result;
    }
    public static ArrayList<Integer> mergeSort(ArrayList<Integer> array){
        if(array.size()<2)return array;
        int mid = array.size()/2;
        ArrayList<Integer> left = new ArrayList<>(array.subList(0, mid));
        ArrayList<Integer> right = new ArrayList<>(array.subList(mid, array.size()));
        System.out.println();
        for(int i=0;i<left.size();i++){
            System.out.print(left.get(i)+" ");
        }
        System.out.println();
        for(int i=0;i<right.size();i++){
            System.out.print(right.get(i)+" ");
        }
        return merge(mergeSort(left), mergeSort(right));
    }
}