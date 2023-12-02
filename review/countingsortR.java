import java.util.ArrayList;
import java.util.Arrays;

public class countingsortR {
    public static void main(String args[]){
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(1,1,4,5,4,2,5,2,7,3,4,3,2));
        countingSort(test2);
        int test1[] = {1,1,4,5,4,2,5,2,7,3,4,3,2} ;
        countingSort(test1);
    }
    public static void countingSort(ArrayList<Integer> array){
        int max = 0;
        for(int i=0;i<array.size();i++){
            max = max>array.get(i)?max:array.get(i);
        }
        ArrayList<Integer> count = new ArrayList<>();
        for(int i=0;i<max;i++){
            count.add(0);
        }
        System.out.println(count.toString());
        for(int i=0;i<array.size();i++){
            count.set(array.get(i)-1, count.get(array.get(i)-1)+1);
        }
        for(int i=1;i<count.size();i++){
            count.set(i, count.get(i-1)+count.get(i));
        }
        System.out.println(count.toString());
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i=0;i<array.size();i++){
            temp.add(0);
        }
        System.out.println(temp.toString());
        for(int i=array.size()-1;i>=0;i--){
            temp.set(count.get(array.get(i)-1)-1, array.get(i));
            count.set(array.get(i)-1, count.get(array.get(i)-1)-1);
        }
        System.out.println(temp.toString());
    }
    public static void countingSort(int array[]){
        int max=0;
        for(int i=0;i<array.length;i++){
            max = max>array[i]?max:array[i];
        }
        int count[] = new int[max];
        for(int i=0;i<array.length;i++){
            count[array[i]-1]++;
        }
        System.out.println(Arrays.toString(count));
        for(int i=1;i<count.length;i++){
            count[i] += count[i-1];
        }
        System.out.println(Arrays.toString(count));
        int temp[] = new int[array.length];
        System.out.println(Arrays.toString(temp));
        for(int i=array.length-1;i>=0;i--){
            temp[count[array[i]-1]-1] = array[i];
            count[array[i]-1]--;
        }
        System.out.println(Arrays.toString(temp));
    }
}
