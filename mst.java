import java.util.ArrayList;
import java.util.Arrays;

public class mst {
    static int max = Integer.MAX_VALUE;
    public static void main(String args[]){
        
        /*int graphMap[][] = {{0,5,7,3,0,6,7,0},
                            {5,0,0,5,0,0,0,9},
                            {7,0,0,0,1,4,14,21},
                            {3,5,0,0,12,4,0,7},
                            {0,0,1,12,0,2,7,0},
                            {6,0,4,4,2,0,0,4},
                            {7,0,14,0,7,0,0,0},
                            {0,9,21,7,0,4,0,0}};*/
         int graphMap[][] = {{0,5,0,0,0,3,0},
                            {5,0,10,0,1,0,4},
                            {0,10,0,5,0,0,8},
                            {0,0,5,0,7,0,9},
                            {0,1,0,7,0,6,2},
                            {3,0,0,0,6,0,0},
                            {0,4,8,9,2,0,0}};
        int pi[] =new int[graphMap.length];
        int key[] =new int[graphMap.length];
        int visited[] = new int[graphMap.length];
        ArrayList<mstEdge> edgesetMST = new ArrayList<>();
        
        ArrayList<mstEdge> increaseWeight = new ArrayList<>();
        printTwoDimensionalArray(graphMap);
        kruskal(graphMap, edgesetMST, increaseWeight);
        //primTraverseKKey(graphMap,pi,key,visited,2);
    }

    static void kruskal(int graphMap[][], ArrayList<mstEdge> edgesetMST,ArrayList<mstEdge> increaseWeight){
        for(int i=0;i<graphMap.length-1;i++){
            for(int j=i+1;j<graphMap.length;j++){
                if(graphMap[i][j]!=0){
                    increaseWeight.add(new mstEdge(i, j, graphMap[i][j]));
                }
            }
        }/* 
        for(int i=0;i<increaseWeight.size();i++){
            System.out.println(increaseWeight.get(i).vertex1 + "->"+increaseWeight.get(i).vertex2+" " + increaseWeight.get(i).weight);
        }*/
        for(int i=1;i<increaseWeight.size();i++){
            mstEdge temp = increaseWeight.get(i);
            increaseWeight.remove(i);
            int index = 0;
            for(int j=i;j>0;j--){
                if(increaseWeight.get(j-1).weight<temp.weight){
                    index = j;
                    break;
                }
            }
            increaseWeight.add(index, temp);
        }
        System.out.println("edge");
        for(int i=0;i<increaseWeight.size();i++){
            System.out.println(increaseWeight.get(i).vertex1 + "->"+increaseWeight.get(i).vertex2+" " + increaseWeight.get(i).weight);
        }
        int []subset = new int[increaseWeight.size()];
        for(int i=0;i<increaseWeight.size();i++){
            subset[i] = -1;
        }
        System.out.println("set");
        for (int i = 0; i < increaseWeight.size(); i++) {
            if (findSetCollapsing(subset, increaseWeight.get(i).vertex1) != findSetCollapsing(subset, increaseWeight.get(i).vertex2)) {
                //邊的兩個點的root是一樣的不一樣的就加進mst
                edgesetMST.add(increaseWeight.get(i)) ;
                //並且將兩個點所在的set做union
                unionSet(subset, increaseWeight.get(i).vertex1, increaseWeight.get(i).vertex2);
            }
            System.out.println("step"+(i+1));
            System.out.println(Arrays.toString(subset));
        }
        System.out.println("mst edge");
        for(int i=0;i<edgesetMST.size();i++){
            System.out.println(edgesetMST.get(i).vertex1+"->"+ edgesetMST.get(i).vertex2 +" "+ edgesetMST.get(i).weight);
        }
    }
    static int findSetCollapsing(int subset[],int i){
        int root;
        for(root=i;subset[root]>=0;root = subset[root]);
        while(i!=root){
            int parent = subset[i];
            subset[i] = root;
            i = parent;
        }
        return root;
    }
    static void unionSet(int subset[],int x,int y){
        int root1 = findSetCollapsing(subset, x);
        int root2 = findSetCollapsing(subset, y);
        if(subset[root1]<=subset[root2]){
            subset[root1]+=subset[root2];
            subset[root2] = root1;
        }else{
            subset[root2]+=subset[root1];
            subset[root1] = root2; 
        }

    }
    static void primTraverseKKey(int graphMap[][],int pi[],int key[],int visited[],int choose){
        for(int i=0;i<pi.length;i++){
            pi[i]=-1;
            key[i]=max;
            visited[i]=0;
        }
        key[choose] = 0;
        System.out.println("start   : "+choose);
        System.out.print("pi      : ");
        printOneDimensionalArray(pi);
        System.out.print("key     : ");
        printOneDimensionalArray(key);
        System.out.print("visited : ");
        printOneDimensionalArray(visited);
        for(int i=0;i<graphMap.length;i++){
            int minKey = max;
            int minKeyId = 0;
            for(int j=0;j<key.length;j++){
                if(minKey >key[j] && visited[j]==0){
                    minKeyId = j;
                    minKey = key[j];
                    //System.out.print("y");
                }
               //System.out.println(minKey+" "+key[j]);
            }
            visited[minKeyId] = 1;
            for(int j=0;j<graphMap[minKeyId].length;j++){
                if(graphMap[minKeyId][j] != 0 && visited[j]==0){
                    if(key[j]>graphMap[minKeyId][j]){
                        key[j] = graphMap[minKeyId][j];
                        pi[j] = minKeyId;
                    }
                }
            }
            System.out.println("step"+(i+1));
            System.out.print("pi      : ");
            printOneDimensionalArray(pi);
            System.out.print("key     : ");
            printOneDimensionalArray(key);
            System.out.print("visited : ");
            printOneDimensionalArray(visited);
        }
    }
    static void primBinaryHeap(int graphMap[][],int pi[],int distance[],int visited[],int choose){
        ArrayList <heapNode> heap = new ArrayList<>();
        
        for(int i=0;i<pi.length;i++){
            pi[i]=-1;
            distance[i]=max;
            visited[i]=0;
            heap.add(new heapNode(i, max));
        }
        
        distance[choose]= 0;
        
        System.out.println("init");
        System.out.print("pi      : ");
        printOneDimensionalArray(pi);
        System.out.print("distance: ");
        printOneDimensionalArray(distance);
        System.out.print("visited : ");
        printOneDimensionalArray(visited);
        System.out.print("before heapify");
        for(int j=0;j<heap.size();j++){
            if(heap.get(j).nodeDistance == max){
                System.out.print("{"+heap.get(j).nodeNum+","+"inf"+"}" );
            }else{
                System.out.print("{"+heap.get(j).nodeNum+","+heap.get(j).nodeDistance+"}" );
            }
        }
        System.out.println("");
        heap.get(findNum(heap, choose)).nodeDistance=0;
        for(int j=heap.size()/2-1;j>=0;j--){
            adjustheap(heap, j, heap.size());
        }
        System.out.print("after heapify");
        for(int j=0;j<heap.size();j++){
            if(heap.get(j).nodeDistance == max){
                System.out.print("{"+heap.get(j).nodeNum+","+"inf"+"}" );
            }else{
                System.out.print("{"+heap.get(j).nodeNum+","+heap.get(j).nodeDistance+"}" );
            }
        }
        System.out.println("");
        for(int i=0;i<graphMap.length;i++){
            int minKeyId = heap.get(0).nodeNum;
            heap.remove(0);
            visited[minKeyId] = 1;
            for(int j=0;j<graphMap[minKeyId].length;j++){
                if(graphMap[minKeyId][j] != 0 && visited[j]==0){
                    if(distance[j]>graphMap[minKeyId][j]){
                        distance[j] = graphMap[minKeyId][j];
                        heap.get(findNum(heap,j)).nodeDistance = graphMap[minKeyId][j];
                        pi[j] = minKeyId;
                        //System.out.print("y");
                        //System.out.println(j+" "+distance[j] +" "+graphMap[minKeyId][j]);
                    }
                    //
                    
                }
                //System.out.println(j+" "+distance[j] +" "+graphMap[minKeyId][j]);
            }
            System.out.println("step"+(i+1));
            System.out.print("pi      : ");
            printOneDimensionalArray(pi);
            System.out.print("distance: ");
            printOneDimensionalArray(distance);
            System.out.print("visited : ");
            printOneDimensionalArray(visited);
            if(heap.size() == 0) continue;
            System.out.print("before heapify");
            
            for(int j=0;j<heap.size();j++){
                if(heap.get(j).nodeDistance == max){
                    System.out.print("{"+heap.get(j).nodeNum+","+"inf"+"}" );
                }else{
                    System.out.print("{"+heap.get(j).nodeNum+","+heap.get(j).nodeDistance+"}" );
                }
            }
            System.out.println("");
            for(int j=heap.size()/2-1;j>=0;j--){
                adjustheap(heap, j, heap.size());
            }
            System.out.print("after heapify");
            for(int j=0;j<heap.size();j++){
                if(heap.get(j).nodeDistance == max){
                    System.out.print("{"+heap.get(j).nodeNum+","+"inf"+"}" );
                }else{
                    System.out.print("{"+heap.get(j).nodeNum+","+heap.get(j).nodeDistance+"}" );
                }
            }
            System.out.println("");
            
        }


    }
    private static int findNum(ArrayList<heapNode> array,int target){
        int num=0;
        for(int i=0;i<array.size();i++){
            if(array.get(i).nodeNum == target){
                num = i;
                break;
            }
        }
        return num;
    }
    private static void adjustheap(ArrayList<heapNode> array,int start,int length){
        int temp = array.get(start).nodeDistance;
        int tempNum = array.get(start).nodeNum;
        for(int i=start*2+1;i<length;i=i*2+1){
            if(i+1<length && array.get(i).nodeDistance>array.get(i+1).nodeDistance){
                i++;
            }
            if(array.get(i).nodeDistance<temp){
                array.get(start).nodeDistance = array.get(i).nodeDistance;
                array.get(start).nodeNum = array.get(i).nodeNum;
                start = i;
            }else{
                break;
            }
        }
        array.get(start).nodeDistance = temp;
        array.get(start).nodeNum = tempNum;
        array.set(start, new heapNode(tempNum, temp));
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
    private static void printOneDimensionalArray(int []array){
        if(array[0] == max){
            System.out.printf("%4s","inf");
        }else{
            System.out.printf("%4d",array[0]);
        }
        for(int i=1;i<array.length;i++){
            if(array[i] == max){
                System.out.printf(",%3s","inf");
            }else{
                System.out.printf(",%3d",array[i]);
            }
        }
        System.out.println("");
    }
    
}
class heapNode{
    int nodeDistance;
    int nodeNum;

    heapNode(){

    }
    heapNode(int nn,int nd){
        this.nodeDistance = nd;
        this.nodeNum = nn;
    }
}
class mstEdge{
    int vertex1;
    int vertex2;
    int weight;
    mstEdge(){

    }
    mstEdge(int v1,int v2,int weight){
        this.vertex1 = v1;
        this.vertex2 = v2;
        this.weight = weight;
    }
}