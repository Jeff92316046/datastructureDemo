import java.util.ArrayList;
import java.util.Arrays;

public class countingsort {
    public static void main(String args[]){
        ArrayList<Integer> test = new ArrayList<Integer>(Arrays.asList(9,8,3,3,4,1,9,2,4,6,3));
        System.out.println(Arrays.toString(test.toArray()));
        test = countingSort(test);
        System.out.println("result:\n"+Arrays.toString(test.toArray()));
    }
    public static ArrayList<Integer> countingSort(ArrayList<Integer> array){
        ArrayList<Integer> temp_array = new ArrayList<>();
        ArrayList<Integer> counting_array = new ArrayList<>();
        int max=0;
        for(int i=0;i<array.size();i++){
            if(array.get(i)>max){
                max = array.get(i);
            }
            temp_array.add(-1);
        }
        for(int i=0;i<=max;i++){
            counting_array.add(0);
        }
        for(int i=0;i<array.size();i++){
            counting_array.set(array.get(i), counting_array.get(array.get(i))+1);
        }
        System.out.println("counting 1st:\n"+Arrays.toString(counting_array.toArray()));
        int temp =0;
        for(int i=0;i<counting_array.size();i++){
            counting_array.set(i, counting_array.get(i)+temp);
            temp = counting_array.get(i);
        }
        System.out.println("counting 2nd:\n"+Arrays.toString(counting_array.toArray()));
        System.out.println("sort:");
        for(int i=array.size()-1;i>=0;i--){
            System.out.println(Arrays.toString(array.toArray()));
            System.out.println(Arrays.toString(temp_array.toArray()));
            System.out.println(Arrays.toString(counting_array.toArray()));
            System.out.println("---------");
            temp_array.set(counting_array.get(array.get(i))-1,array.get(i) );
            counting_array.set(array.get(i),counting_array.get(array.get(i))-1);
        }
        return temp_array;
    }
}
