import java.util.ArrayList;
import java.util.Arrays;

public class heapsort {
    public static void main(String args[]){
        ArrayList<Integer> test =new ArrayList<Integer>(Arrays.asList(12,10,8,6,9,1,3,11,5,2,7,4));
        heapSort(test);
    }
    public static void adjustheap(ArrayList<Integer> array,int start,int length){
        int temp = array.get(start);
        for(int i=start*2+1;i<length;i=i*2+1){
            if(i+1<length && array.get(i)<array.get(i+1)){
                i++;
            }
            if(array.get(i)>temp){
                array.set(start, array.get(i));
                start = i;
            }else{
                break;
            }
        }
        array.set(start, temp);
        System.out.println(Arrays.toString(array.toArray()));
    }
    public static void heapSort(ArrayList<Integer> array){
        int temp=0;
        System.out.println("heapify");
        for(int i=array.size()/2-1;i>=0;i--){
            adjustheap(array, i, array.size());
        }
        System.out.println(Arrays.toString(array.toArray()));
        System.out.println("heapsort");
        for(int i=array.size()-1;i>0;i--){
            temp = array.get(i);
            array.set(i,array.get(0));
            array.set(0,temp);
            adjustheap(array, 0, i);
        }
        System.out.println(Arrays.toString(array.toArray()));
    }
}
