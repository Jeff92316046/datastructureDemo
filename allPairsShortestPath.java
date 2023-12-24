public class allPairsShortestPath {
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
        /*int graphMap2[][] = {{0,2,6,8},
                        {max,0,-2,3},
                        {4,max,0,1},
                        {max,max,max,0}};*/
        int distance[][] = graphMap.clone();
        int pi[][] = new int[8][8];
        //int pi2[][] = new int[4][4]
        System.out.println("");
        allPairsShortestPathFunc(graphMap, distance, pi);


        
    }
    public static void allPairsShortestPathFunc(int graphMap[][],int distance[][],int pi[][] ){
        for(int i=0;i<graphMap.length;i++){
            for(int j=0;j<graphMap[0].length;j++){
                if(i==j){
                    pi[i][j] = -1;
                    continue;
                }
                if(graphMap[i][j] == max){
                    pi[i][j] = -1;
                }else{
                    pi[i][j] = i;
                }
            }
        }
        System.out.println("distance init:");
        printTwoDimensionalArray(distance);
        System.out.println("pi init:");
        printTwoDimensionalArray(pi);
        for(int i=0;i<graphMap.length;i++){
            for(int j=0;j<graphMap.length;j++){
                for(int k=0;k<graphMap.length;k++){
                    if((distance[i][j]>distance[i][k]+distance[k][j]) && (distance[i][k]!=max)&& (distance[k][j]!=max)){
                        distance[i][j] = distance[i][k]+distance[k][j];
                        pi[i][j] = pi[k][j];//因為i->k->j所以前行節點從i變k
                    }
                }
            }
            System.out.println("vertex"+(i+1)+ ":");
            System.out.println("distance");
            printTwoDimensionalArray(distance);
            System.out.println("pi");
            printTwoDimensionalArray(pi);
        }
        
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