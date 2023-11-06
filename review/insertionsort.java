import java.util.ArrayList;
import java.util.Arrays;

public class insertionsort {
    public static void main(String args[]){
        int test1[] = {1,3,9,4,2,5,7,2};
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(1,3,9,4,2,5,7,2));
        
        System.out.println("array ver :\n"+Arrays.toString(test1));
        insertionSort(test1);
        System.out.println(Arrays.toString(test1));
        System.out.println("arraylist ver :\n"+test2.toString());
        insertionSort(test2);
        System.out.println(test2.toString());
    }
    public static void insertionSort(ArrayList<Integer> array){
        int temp=0;
        for(int i=1;i<array.size();i++){
            temp = array.get(i);
            for(int j=i;j>0;j--){
                if(temp<array.get(j-1)){
                    array.set(j, array.get(j-1));
                }else{
                    array.set(j, temp);
                    break;
                }
            }
        }
    }
    public static void insertionSort(int array[]){
        int temp = 0;
        for(int i=1;i<array.length;i++){
            temp = array[i];
            int j=0;
            for(j=i;j>0;j--){
                if(temp<array[j-1]){
                    array[j]=array[j-1];
                }else{
                    break;
                }
                array[j]=temp;
            }
        }
    }
}
