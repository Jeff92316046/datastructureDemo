import java.util.ArrayList;
import java.util.Arrays;

public class quicksort {
    public static void main(String args[]){
        ArrayList<Integer> test = new ArrayList<Integer>(Arrays.asList(12,10,8,6,9,1,3,11,5,2,7,4));
        System.out.println(Arrays.toString(test.toArray()));
        quickSort(test, 0, test.size()-1);
        System.out.println(Arrays.toString(test.toArray()));
    }
    public static void quickSort(ArrayList<Integer> array, int s,int e){
        if(s<e){
            int pivot = array.get(e);
            int start = s;
            int end = e-1;
            for(;;){
                for(;;){
                    if(array.get(start)>pivot || start>=e)break;
                    start++;
                }
                for(;;){
                    if(array.get(end)<pivot || end<=s ) break;
                    end--;
                }
                if(start>=end){
                    break;   
                }
                
                int temp = array.get(start);
                array.set(start, array.get(end));
                array.set(end, temp);      
            }   
            System.out.println(Arrays.toString(array.toArray()));
            int temp = array.get(start);
            array.set(start, pivot);
            array.set(e, temp);
            quickSort(array, s, start-1);
            quickSort(array,start+1 , e);
        }
        
    }
}