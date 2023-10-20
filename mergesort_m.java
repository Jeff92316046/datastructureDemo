import java.util.ArrayList;
import java.util.Arrays;

public class mergesort_m {
    public static void main(String args[]){
        ArrayList<Integer> test =new ArrayList<Integer>(Arrays.asList(8,6,9,1,3,5,2,7,4));
        ArrayList<Integer> test_1 =new ArrayList<Integer>(mergesort(test));
        for(int i=0;i<test_1.size();i++){
            System.out.print(test_1.get(i)+" ");
        }

    }
    public static ArrayList<Integer> merge(ArrayList<Integer> left ,ArrayList<Integer> right){
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (left.size()!=0 && right.size()!=0){
            if(left.get(0)>right.get(0)){
                result.add(right.get(0));
                right.remove(0);
            }else{
                result.add(left.get(0));
                left.remove(0);
            }
        }
        
        if(left.size()!=0){
            result.addAll(left);
        }
        if(right.size()!=0){
            result.addAll(right);
        }
        return result;
    }
    public static ArrayList<Integer> mergesort(ArrayList<Integer> array){
        if(array.size()<2)return array;
        ArrayList<Integer> left = new ArrayList<Integer>(array.subList(0, array.size()/2));
        ArrayList<Integer> right = new ArrayList<Integer>(array.subList(array.size()/2, array.size()));
        
        return merge(mergesort(left), mergesort(right));
    }
}
