package com.ptasdevz.assignments;


/**
 * Name: Jason Peters
 * ID:811100537
 *
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class petersJBlocks {
    public static int floorsPerBlock =-1;
    public static int blocks =-1;
    public static int numOfFloors =0;
    public static int[] floorVals = new int[0];
    public static HashMap<Integer,Integer> bridgesAndCircularRoad = new HashMap<>();


    public static void main (String[] args){

        int numberCount = 0;

        try {

            FileReader in = new FileReader("input.txt");
            int bi=-1,fx=-1,bj=-1,fy=-1, t=-1;
            int bridgeCount = 0;
            int bridgeAddCount=0;
            int ch;
            String number = "";
            String output ="";
            while (true){
                ch = in.read();
                if (ch >= '0' && ch <='9')number += (char)ch; //build positive integers
                else {

                    if (number.compareTo("") != 0) {
                        numberCount++;
                        if (numberCount == 1) blocks = Integer.parseInt(number); //get num of blocks
                        else if (numberCount == 2) {//get number of per block floors
                            floorsPerBlock = Integer.parseInt(number);

                        }
                        //get num of bridges
                        else if (numberCount == 3){

                            bridgeCount = Integer.parseInt(number);
                            numOfFloors = blocks*floorsPerBlock;

                            //maximize on storage  by converting band matrix to a 1D-array
                            floorVals = new int[(3*numOfFloors -2)+1];
                            initFloorBridgeVals();

                        }
                        else if (numberCount == 4) bi = Integer.parseInt(number);
                        else if (numberCount == 5) fx = Integer.parseInt(number);
                        else if (numberCount == 6) bj = Integer.parseInt(number);
                        else if (numberCount == 7) fy = Integer.parseInt(number);
                        else if (numberCount == 8){

                            t = Integer.parseInt(number);

                            //add bridges in a bi-directional fashion
                            int bridgeStartFloorNum = getFloorNumber(bi,fx);
                            int bridgeEndFloorNum = getFloorNumber(bj,fy);

                            setBridgeCircularRoadVal(bridgeStartFloorNum,bridgeEndFloorNum,t);
                            setBridgeCircularRoadVal(bridgeEndFloorNum,bridgeStartFloorNum,t);

                            bridgeAddCount++;
                            if (bridgeAddCount != bridgeCount) numberCount =3;

                        }

                        //get queries
                        else if (numberCount == 9) bi = Integer.parseInt(number);
                        else if (numberCount == 10) fx = Integer.parseInt(number);
                        else if (numberCount == 11) bj = Integer.parseInt(number);
                        else if (numberCount == 12) {
                            fy = Integer.parseInt(number);

                            int queryStartFloorNum = getFloorNumber(bi, fx);
                            int queryEndFloorNum = getFloorNumber(bj, fy);

//                            long startTime = System.nanoTime();
                            int [] dist  = ssspDijkstra(queryStartFloorNum);
//                            long estimatedTimeNano = System.nanoTime() - startTime;
//                            long estimatedTimeMillSec = TimeUnit.NANOSECONDS.toMillis(estimatedTimeNano);
//                            estimatedTimeNano = (estimatedTimeNano - TimeUnit.MILLISECONDS.toNanos(estimatedTimeMillSec));
//                            System.out.printf("\nTotal Execution Time: %d.%s millisecond(s)\n", estimatedTimeMillSec, String.valueOf(estimatedTimeNano).substring(0, 3));

                            int res = dist[queryEndFloorNum];
                            output += res + "\n";
                            if (bi !=0 ) numberCount = 8;

                        }
//                        printFloorVals();
//                        System.out.println();
//                        printBridgeVals();

                        number = "";
                    }
                    if (ch == -1)break;
                }
            }
            writeToFileResult(output);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static int getFloorNumber(int i, int j) {

        return (floorsPerBlock * (i-1))+j;
    }

    private static void initFloorBridgeVals() {
        int nextBlockFirstFloor = 1;
        int prevBlockFirstFloor = -1;
        int lastBlockFirstFloor =  (blocks *floorsPerBlock) - (floorsPerBlock-1);
        int numOfFloors = blocks *floorsPerBlock;
        for (int i = 1; i <=numOfFloors; i++) {

            for (int j = 1; j <= numOfFloors; j++) {
                //time values of 1 sec between floors of each block
                if (i==j) setFloorVal(i, j, 0);
                else if (Math.abs(j-i) == 1 ) {
                    boolean iAsMultiple = isMultiple(i,floorsPerBlock);
                    boolean jAsMultiple = isMultiple(j,floorsPerBlock);

                    if ((iAsMultiple && j>i) || (jAsMultiple && i>j)) {//last floor of each block
                        setFloorVal(i, j, Integer.MAX_VALUE);
                    }
                    else {
                        setFloorVal(i, j, 1);
                    }
                }
                else {
                    //Circular road on first floor of each block
                    if ((i == nextBlockFirstFloor || i== prevBlockFirstFloor) && Math.abs(j-i) == floorsPerBlock){
                        if (i==nextBlockFirstFloor) { // only update next and prev block if an nextBlock is encountered
                            if (prevBlockFirstFloor == -1) prevBlockFirstFloor = 1;
                            else prevBlockFirstFloor = nextBlockFirstFloor;
                            nextBlockFirstFloor = nextBlockFirstFloor + floorsPerBlock;
                        }
                        setBridgeCircularRoadVal(i,j,1);

                    }

                    //wrap around from between block 1 first floor and block i first floor
                    else if (i == lastBlockFirstFloor & j==1){
                        setBridgeCircularRoadVal(i,j,1);

                        setBridgeCircularRoadVal(j,i,1);

                    }
//                    else if (j-i > 1 && j-i > 4 && i != nextBlockFirstFloor && i!=nextBlockFirstFloor && j !=1)
//                        break;


//                    else setBridgeCircularRoadVal(i,j,Integer.MAX_VALUE);

                }
            }
        }
//        printFloorVals();
//        System.out.println();
//        printBridgeVals();
    }

    private static boolean isMultiple(int i, int floorsPerBlock) {
        return i % floorsPerBlock == 0;
    }

    private static void printFloorVals(){

        for (int i = 0; i < floorVals.length; i++) {
            System.out.printf("%d => %d\n",i,floorVals[i]);

        }
    }
    private static void printBridgeVals() {

        for (Map.Entry<Integer,Integer> entry:bridgesAndCircularRoad.entrySet()) {
            System.out.printf("%d => %d\n",entry.getKey(),entry.getValue());

        }
    }

    public static int[] ssspDijkstra(int srcNode){

        int[] dist = new int[numOfFloors +1];
        boolean[] sptSet = new boolean[numOfFloors +1];

        //initialize the values of the nodes to infinity
        for (int i = 1; i < numOfFloors+1; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        //set distance of source vertex to itself to 0
        dist[srcNode] =0;

        //shortest path from source to all vertices
        for (int i = 1; i < numOfFloors; i++) {

            //get the index of the next minimum vertex not yet processed
            int u = minDistance(dist,sptSet);

            //mark vertex as processed
            sptSet[u] = true;

            //update distances of other vertexes that are affected "postively" i.e.
            // 1. there is and edge from i to j 2. total weight of path  from src to v through u < curr value of dist[v]
            // 3. not set in sptSet
            for (int v = 1; v < numOfFloors+1; v++) {

                int val = getFloorOrBridgeVal(u, v);
                if (!sptSet[v] && //not yet processed
                       val != 0 && // the lowest there can be
                       val != Integer.MAX_VALUE && // the edge exists
                       dist[u] + val < dist[v]){ //value from src to v through u; dist[u] has the best min from src thus far

                    dist[v] = dist[u] + val;
                }

            }
        }
//        printSol(dist);
        return dist;

    }

    public static void printSol(int[] dist) {
        for (int i = 1; i < dist.length; i++) {

            System.out.println(i+" tt "+dist[i]);
        }
    }

    public static int minDistance(int[]dist, boolean []sptSet){

        int min = Integer.MAX_VALUE;
        int min_index = -1;
        for (int i = 1; i < dist.length; i++) {
            if (sptSet[i] == false && dist[i] <= min){
                min = dist[i];
                min_index = i;
            }
        }
        return min_index;
    }


    public static int getFloorOrBridgeVal(int i, int j){

        if (Math.abs(i-j) == 0) return 0;
        int key = 2 * (i - 1) + j;
        if (Math.abs(i-j) <= 1) {//check in band matrix
            return floorVals[key];
        }else {
            key = (blocks*floorsPerBlock * i)+j;
            Object val = bridgesAndCircularRoad.get(key);
            if (val == null) return Integer.MAX_VALUE;
            return (int) val;
        }
    }

    public static void setFloorVal(int i, int j, int val){
        int key = 2*(i-1)+j;
        if (Math.abs(i-j) <= 1) { //ensure value is in band
             floorVals[key] = val;
         }
    }

    public static void setBridgeCircularRoadVal(int i, int j, int val){
        if (Math.abs(i-j) >1) { //ensure value is out of band
            int key = (numOfFloors * i)+j;
            bridgesAndCircularRoad.put(key,val);
        }
    }

    private static void writeToFileResult(String output) {

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));
            out.printf("%s\n",output);
            out.flush();

        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        }
    }


}
