import java.util.ArrayList;
import java.util.Arrays;

public class quicksort {
    public static void main(String args[]){
        int test1[] = {1,3,9,4,2,5,7,2};
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(6,1,3,9,4,2,5,7,2));
        quickSort(test2, 0, test2.size()-1);
        System.out.println(test2.toString()+"\n-------");
        quickSort(test1, 0, test1.length-1);
        System.out.println(Arrays.toString(test1));
    }
    public static void quickSort(ArrayList<Integer> array,int begin,int end){
        int pivot = begin;
        int p1 = begin+1;
        int p2 = end;
        int temp;
        if(begin>=end)return;
        for(;;){
            for(;;){
                if(array.get(p1)>array.get(pivot) || p1>=end)break;
                p1++;
            }
            for(;;){
                if(array.get(p2)<array.get(pivot) || p2<=begin)break;
                p2--;
            }
            if(p1>=p2)break;
            temp = array.get(p1);
            array.set(p1,array.get(p2));
            array.set(p2,temp);
        }
        temp = array.get(p2);
        array.set(p2,array.get(pivot));
        array.set(pivot,temp);
        System.out.println(begin+" "+end);
        System.out.println(p1+" "+p2+" "+pivot);
        System.out.println(array.toString());
        quickSort(array, begin,p2-1);
        quickSort(array, p2+1, end);
    }


    public static void quickSort(int array[],int start,int end){
        if(start>=end)return;
        int p1 = start;
        int p2 = end-1;
        int pivot = end;
        int temp;
        for(;;){
            for(;;){
                if(array[p1]>array[pivot] || p1>=end)break;
                p1++;
            }
            for(;;){
                if(array[p2]<array[pivot] || p2<=start)break;
                p2--;
            }
            if(p1>=p2)break;
            temp = array[p1];
            array[p1] = array[p2];
            array[p2] = temp;
        }
        System.out.println(p1+ " " + p2 + " " + pivot);
        temp = array[p1];
        array[p1] = array[pivot];
        array[pivot] = temp;
        quickSort(array, start, p1-1);
        quickSort(array, p1+1, end);
    }





}
