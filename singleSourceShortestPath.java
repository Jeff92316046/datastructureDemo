import java.util.ArrayList;
import java.util.Arrays;

public class singleSourceShortestPath {
    static int max = Integer.MAX_VALUE;
    public static void main(String args[]){
        int graphMap[][] = {{0,10,8,max,1,3,12,12},
                        {5,0,3,12,11,4,max,max},
                        {7,max,0,0,1,4,14,21},
                        {3,5,3,0,max,4,1,max},
                        {max,max,3,12,0,2,max,max},
                        {6,max,max,12,max,0,max,max},
                        {7,max,6,max,7,max,0,max},
                        {max,9,6,7,max,4,max,0}};
        printTwoDimensionalArray(graphMap);
        singleSourceShortestPathFunc(graphMap, 0);

        
    }
    private static void singleSourceShortestPathFunc(int graphMap[][],int target){
        int distance[] = new int[graphMap.length];
        int pi[] = new int[graphMap.length];
        ArrayList <Integer> Q = new ArrayList<>();
        ArrayList <Integer> S = new ArrayList<>();
        for(int i=0;i<distance.length;i++){
            pi[i] = -1;
            distance[i] = max;
            Q.add(i);
        }
        distance[target] = 0;
        
        for(int i=0;i<graphMap.length;i++){
            System.out.println("step"+i);
            int min=max;
            int minId = 0;
            for(int j=0;j<distance.length;j++){
                if(findQueueNum(Q, j)==-1){
                    continue;
                }
                if(min>distance[j]){
                    min = distance[j];
                    minId = j;
                }
            }
            //System.out.println(minId);
            S.add(minId);
            Q.remove(findQueueNum(Q, minId));
            for(int j=0;j<distance.length;j++){
                if(distance[j]>graphMap[i][j] && findQueueNum(Q, j)!=-1){
                    distance[j] = graphMap[i][j];
                    pi[j]=i;
                }
            }
            System.out.print("distance:");
            System.out.println(Arrays.toString(distance));
            System.out.print("pi      :");
            System.out.println(Arrays.toString(pi));
            System.out.print("S       :");
            System.out.println(S.toString());
            System.out.print("Q       :");
            System.out.println(Q.toString());
        }

    }
    private static int findQueueNum(ArrayList<Integer> array, int target){
        int key = -1;
        for(int i=0;i<array.size();i++){
            if(array.get(i) == target){
                key = i;
                break;
            
            }
        }

        return key;
    }
    private static void printTwoDimensionalArray(int [][]array){
        System.out.printf("%4s","");
        for(int i=0;i<array.length;i++){
            System.out.printf("%4d",i+1);
        }
        System.out.println("");
        for(int i=0;i<array.length;i++){
            System.out.printf("%4d",i+1);
            for(int j=0;j<array[i].length;j++){
                if(array[i][j] == max){
                    System.out.printf("%4s","inf");
                }else{
                    System.out.printf("%4d",array[i][j]);
                }
                
            }
            System.out.println("");
        }
        System.out.println("");
    } 
}
