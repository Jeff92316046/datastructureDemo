import java.util.ArrayList;
import java.util.Arrays;

public class heapsort {
    public static void main(String args[]){
        int test1[] = {1,3,9,4,2,5,7,2};
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(1,3,9,4,2,5,7,2));
        heapSort(test1);
        heapSort(test2);
    }
    public static void heapify(int array[],int begin,int end){
        int temp = array[begin];
        System.out.println(Arrays.toString(array));
        for(int i=begin*2+1;i<end;i=i*2+1){
            if(i+1<end && array[i]<array[i+1]){
                i++;
            }
            if(array[i]>temp){
                array[begin] = array[i];
                begin = i;
            }else{
                break;
            } 
        }
        array[begin] = temp;
        System.out.println(Arrays.toString(array)+"\n--------");
    }
    public static void heapSort(int array[]){
        for(int i=array.length/2;i>=0;i--){
            heapify(array, i, array.length);
        }
        System.out.println("a"+Arrays.toString(array));
        for(int i=0;i<array.length;i++){
            int temp = array[0];
            array[0] = array[array.length-i-1];
            array[array.length-i-1] = temp;
            heapify(array, 0, array.length-i-1);

        }
        System.out.println(Arrays.toString(array));
    }
    public static void heapify(ArrayList<Integer> array,int start,int length){
        int temp=array.get(start);
        for(int i=start*2+1;i<length;i=i*2+1){
            if(i+1<length && array.get(i)>array.get(i+1)){
                i++;
            }
            if(array.get(i)<temp){
                array.set(start,array.get(i));
                start = i;
            }else{
                break;
            }
            System.out.println(array.toString());
        }
        array.set(start, temp);
    }
    public static void heapSort(ArrayList<Integer> array){
        for(int i=array.size()/2;i>=0;i--){
            heapify(array, i, array.size());
        }
        System.out.println("a"+array.toString());
        for(int i=0;i<array.size();i++){
            array.add(array.get(0));
            array.remove(0);
            heapify(array, 0, array.size()-i-1);
        }
        System.out.println(array.toString());
    }

}
