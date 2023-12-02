import java.util.ArrayList;
import java.util.Arrays;

public class mergesortR {
    public static void main(String args[]){
        int test1[] = {1,3,9,4,2,5,7,2};
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(1,3,9,4,2,5,7,2));
        test2 = mergeSort(test2);
        System.out.println(test2.toString());
        System.out.println(Arrays.toString(mergeSort(test1)));

    }
    public static ArrayList<Integer> merge(ArrayList<Integer> leftArray,ArrayList<Integer> rightArray){
        ArrayList<Integer> temp = new ArrayList<>();
        for(;!leftArray.isEmpty() && !rightArray.isEmpty();){
            
            if(leftArray.get(0)<rightArray.get(0)){
                temp.add(leftArray.get(0));
                leftArray.remove(0);
            }else{
                temp.add(rightArray.get(0));
                rightArray.remove(0);
            }
        }
        temp.addAll(rightArray.isEmpty()?leftArray:rightArray);

        return temp; 
    }
    public static ArrayList<Integer> mergeSort(ArrayList<Integer> array){
        if(array.size()==1)return array;
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        left.addAll(array.subList(0, array.size()/2));
        right.addAll(array.subList(array.size()/2, array.size()));
        return merge(mergeSort(left), mergeSort(right));
    }
    public static int[] merge(int[] leftArray,int[] rightArray){
        int temp[] = new int[leftArray.length+rightArray.length];
        int i=0,j=0;
        for(i=0,j=0;leftArray.length!=i&&rightArray.length!=j;){
            if(leftArray[i]>rightArray[j]){
                temp[i+j] = rightArray[j];
                j++;
            }else{
                temp[i+j] = leftArray[i];
                i++;
            }
        }
        for(;leftArray.length!=i;i++){
            temp[i+j] = leftArray[i];
        }
        for(;rightArray.length!=j;j++){
            temp[i+j] = rightArray[j];
        }
        return temp;  
    }
    public static int[] mergeSort(int[] array){
        if(array.length==1) return array;
        int right[] = new int[array.length-array.length/2];
        int left[] = new int[array.length/2];
        for(int i=0;i<array.length/2;i++){
            left[i] = array[i];
        }
        for(int j=0;j<array.length-array.length/2;j++){
            right[j] = array[j+array.length/2];
        }
        return merge(mergeSort(left), mergeSort(right));
    }
}
