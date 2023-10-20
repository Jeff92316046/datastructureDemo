import java.util.ArrayList;
import java.util.Arrays;

public class radixsort {
    public static void main(String args[]){
        ArrayList<Integer> test =new ArrayList<Integer>(Arrays.asList(9644,6994,9805,8906,954,1968,566,4654,6240,5918,10001,1));
        radixSort(test);
        System.out.println(Arrays.toString(test.toArray()));
    }
    public static void radixSort(ArrayList<Integer> array){
        int max=0;
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for(int i=0;i<10;i++){
            temp.add(i, new ArrayList<>());
        }
        for(int i=0;i<array.size();i++){
            max = (getDigits(array.get(i))>max)?getDigits(array.get(i)):max;
        }
        for(int i=0;i<max;i++){
            System.out.println(Arrays.toString(array.toArray()));
            for(int j=0;j<array.size();j++){
                int index = getDigitsNum(array.get(j),i);
                temp.get(index).add(array.get(j));
            }
            array.clear();
            System.out.println(Arrays.toString(temp.toArray()));
            for(ArrayList<Integer> j : temp){
                array.addAll(j);
                j.clear();
            }
            System.out.println("-------");
        }
    }
    public static int getDigits(int d) {
        return (int) Math.log10(d) + 1;
    }
    public static int getDigitsNum(int d,int index){
        String number = String.valueOf(d);
        return number.length()>index?number.charAt(number.length()-index-1)-48:0;
    }
}
