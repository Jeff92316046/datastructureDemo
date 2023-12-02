import java.util.ArrayList;
import java.util.Arrays;

public class radixsortR {
    public static void main(String args[]){
        System.out.println(maxDigit(124));
        System.out.println(nowDigit(124,0)+" "+nowDigit(124,1)+" "+nowDigit(124,2));
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(4067,97092,63751,34957,30886,95467,72167,96174,3915,27520));
        radixSort(test2);
        int test1[] = {4067,97092,63751,34957,30886,95467,72167,96174,3915,27520};
        radixSort(test1);
    }
    public static void radixSort(ArrayList<Integer> array){
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        int max = 0;
        for(int i=0;i<array.size();i++){
            max = max>maxDigit(array.get(i))?max:maxDigit(array.get(i));
        }
        for(int i=0;i<10;i++){
            temp.add(new ArrayList<Integer>());
        }
        for(int i=0;i<max;i++){
            for(int j=0;j<array.size();j++){
                temp.get(nowDigit(array.get(j), i)).add(array.get(j));
            }
            array.clear();
            System.out.println(temp.toString());
            for(int j=0;j<temp.size();j++){
                array.addAll(temp.get(j));
                temp.get(j).clear();
            }
        }
        System.out.println(array.toString());
    }
    public static void radixSort(int array[]){
        int temp[][] = new int[10][array.length];
        int tempIndex[] = new int[10];
        int max = 0;
        for(int i=0;i<array.length;i++){
            max = max>maxDigit(array[i])?max:maxDigit(array[i]);
        }
        for(int i=0;i<max;i++){
            for(int j=0;j<array.length;j++){
                temp[nowDigit(array[j],i)][tempIndex[nowDigit(array[j],i)]] = array[j];
                System.out.println(array.length);
                System.out.println(nowDigit(array[j],i)+" "+tempIndex[nowDigit(array[j],i)]+" "+array[j]);
                tempIndex[nowDigit(array[j],i)]++;
            }
            int index = 0;
            for(int j=0;j<10;j++){
                for(int k=0;k<tempIndex[j];k++){
                    array[index] = temp[j][k];
                    index++;
                }
            }
            tempIndex = new int[10];

        }
        System.out.println(Arrays.toString(array));;
    }
    public static int maxDigit(int num){
        String temp = String.valueOf(num);
        return temp.length();
    }
    public static int nowDigit(int num,int index){
        String temp = String.valueOf(num);
        return index<temp.length()?temp.charAt(temp.length()-1-index)-48:0;
    }
}
