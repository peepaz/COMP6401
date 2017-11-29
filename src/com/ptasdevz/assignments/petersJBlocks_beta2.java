package com.ptasdevz.assignments;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class petersJBlocks_beta2 {

    public static void main (String[] args){

        int numberCount = 0;

        try {

            FileReader in = new FileReader("input.txt");
            int [][]M = new int[0][0];
            int [][]path = new int[0][0];
            int[][] memo = new int[0][0];
            int[] M_linear = new int [0];
            int bi=-1,fx=-1,bj=-1,fy=-1, t=-1;
            int bridgeCount = 0;
            int bridgeAddCount=0;
            int ch, blocks=-1,floorsPerBlock=-1,numOfFloors;
            String number = ""; //a positive number to be stored in the 2D-array
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
                            numOfFloors = blocks*floorsPerBlock + 1;
                            M = new int[numOfFloors][numOfFloors];

                            //initialize 2x2 Matrix with time values between floors of blocks
//                            initMatrix(M,floorsPerBlock,blocks,M_linear);
//                            initMatrix(path,floorsPerBlock,blocks);
//
//                            printVals(M);
                        }
                        //get num of bridges
                        else if (numberCount == 3){

                            bridgeCount = Integer.parseInt(number);
                            numOfFloors = blocks*floorsPerBlock + 1;
                            initMatrix(M,floorsPerBlock,blocks);



                        }
                        else if (numberCount == 4) bi = Integer.parseInt(number);
                        else if (numberCount == 5) fx = Integer.parseInt(number);
                        else if (numberCount == 6) bj = Integer.parseInt(number);
                        else if (numberCount == 7) fy = Integer.parseInt(number);
                        else if (numberCount == 8){

                            t = Integer.parseInt(number);

                            //add bridges in a bi-directional fashion
                            int bridgeStartFloorNum = getFloorNumber(floorsPerBlock,bi,fx);
                            int bridgeEndFloorNum = getFloorNumber(floorsPerBlock,bj,fy);
                            M[bridgeStartFloorNum][bridgeEndFloorNum] = t;
                            M[bridgeEndFloorNum][bridgeStartFloorNum] = t;
                            bridgeAddCount++;
                            if (bridgeAddCount != bridgeCount) numberCount =3;
                            else{
//                                    long startTime = System.nanoTime();
//                                    getAllPairsShortestPath(M, path);
//                                    getAllPairsShortestPath1(M,memo);
//                                    long estimatedTimeNano = System.nanoTime() - startTime;
//                                    long estimatedTimeMillSec = TimeUnit.NANOSECONDS.toMillis(estimatedTimeNano);
//                                    estimatedTimeNano = (estimatedTimeNano - TimeUnit.MILLISECONDS.toNanos(estimatedTimeMillSec));
//                                    System.out.printf("\nTotal Execution Time: %d.%s millisecond(s)\n", estimatedTimeMillSec, String.valueOf(estimatedTimeNano).substring(0, 3));
                                }

                        }
                        //get queries
                        else if (numberCount == 9) bi = Integer.parseInt(number);
                        else if (numberCount == 10) fx = Integer.parseInt(number);
                        else if (numberCount == 11) bj = Integer.parseInt(number);
                        else if (numberCount == 12) {
                            fy = Integer.parseInt(number);

                            int queryStartFloorNum = getFloorNumber(floorsPerBlock, bi, fx);
                            int queryEndFloorNum = getFloorNumber(floorsPerBlock, bj, fy);
//                            int res = M[queryStartFloorNum][queryEndFloorNum];
//                            System.out.println(queryStartFloorNum + " "+queryEndFloorNum);
//
                            long startTime = System.nanoTime();
                            int [] dist  = dijkstra(M,queryStartFloorNum);
                            long estimatedTimeNano = System.nanoTime() - startTime;
                            long estimatedTimeMillSec = TimeUnit.NANOSECONDS.toMillis(estimatedTimeNano);
                            estimatedTimeNano = (estimatedTimeNano - TimeUnit.MILLISECONDS.toNanos(estimatedTimeMillSec));
                            System.out.printf("\nTotal Execution Time: %d.%s millisecond(s)\n", estimatedTimeMillSec, String.valueOf(estimatedTimeNano).substring(0, 3));

                            int res = dist[queryEndFloorNum];
                            output += res + "\n";
                            if (bi !=0 ) numberCount = 8;

                        }

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

    private static int getFloorNumber(int floorsPerBlock, int i, int j) {

        return (floorsPerBlock * (i-1))+j;
    }

    private static void initMatrix(int[][] M,int floorsPerBlock, int blocks) {
        int nextBlockFirstFloor = 1;
        int prevBlockFirstFloor = -1;
        int lastBlockFirstFloor =  (blocks *floorsPerBlock) - (floorsPerBlock-1);

        for (int i = 1; i <M.length; i++) {

            for (int j = 1; j < M[i].length; j++) {

                //time values of 1 sec between floors of each block
                if (i==j)M[i][j] =0;
                else if (Math.abs(j-i) == 1 ) {
                    boolean iAsMultiple = isMultiple(i,floorsPerBlock);
                    boolean jAsMultiple = isMultiple(j,floorsPerBlock);

                    if ((iAsMultiple && j>i) || (jAsMultiple && i>j)) {//last floor of each block
                        M[i][j] = 9999;// infinite

                    }
                    else {
                        M[i][j] = 1;
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
                        M[i][j] = 1;

                    }

                    //wrap around from between block 1 first floor and block i first floor
                    else if (i == lastBlockFirstFloor & j==1){
                        M[i][j] = 1;

                        M[j][i] = 1;

                    }
//                    else if (j-i > 1 && i<=j)
//                        break;

                    else  M[i][j] = 9999;// infinite
                }
            }
        }
        printVals(M);
    }

    private static boolean isMultiple(int i, int floorsPerBlock) {
        return i % floorsPerBlock == 0;
    }

    private static void printVals(int[][] a){
        for (int i = 1; i <a.length; i++) {
            for (int j = 1; j < a[i].length; j++) {
                System.out.printf("%d ",a[i][j]);
            }
            System.out.println();
        }
    }

    public static void getAllPairsShortestPath(int [][]D, int [][] path){

        for (int k = 1; k < D.length; k++) {

            for (int i = 1; i < D.length; i++) {
                for (int j = 1; j < D[i].length; j++) {
                    int intermediaryCost = (D[i][k] + D[k][j]);
                    if (intermediaryCost < D[i][j]){
                        D[i][j] = intermediaryCost;
//                        path[i][j] = k; // store parent node (intermediary node)
                    }
//                    D[i][j] = min(D[i][j],intermediaryCost);

                }
            }
//            System.out.printf("\n");
//            printVals(D);
//            System.out.printf("\n");
//            printVals(path);

        }
    }


    public static int min(int n, int m){
        if (n<m) return n;
        return m;
    }

    public static int[] dijkstra(int [][] graph, int src){
        int[] dist = new int[graph.length];
        boolean[] sptSet = new boolean[graph.length];

        //initialize the values of the nodes to infinity
        for (int i = 1; i < graph.length; i++) {
            dist[i] = 9999;
            sptSet[i] = false;
        }

        //set distance of source vertex to itself to 0
        dist[src] =0;

        //shortest path from source to all vertices
        for (int i = 1; i < graph.length-1; i++) {

            //get the index of the next minimum vertex not yet processed
            int u = minDistance(dist,sptSet);

            //mark vertex as processed
            sptSet[u] = true;

            //update distances of other vertexes that are affected "postively" i.e.
            // 1. there is and edge from i to j 2. total weight of path  from src to v through u < curr value of dist[v]
            // 3. not set in sptSet
            for (int v = 1; v < graph.length; v++) {
                int graphVal =  graph[u][v];
                if (!sptSet[v] && //not yet processed
                        graphVal != 0 && // the lowest there can be
                        graphVal != 9999 && // the edge exists
                        dist[u] + graphVal < dist[v]){ //value from src to v through u; dist[u] has the best min from src thus far

                    dist[v] = dist[u] + graphVal;
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

    public static String  printPath(int[][] path, int s, int e){

        int p = path[s][e];
        if (p == 9999 || p == e) return "";
        else {
           return  printPath(path,p,e)  + " " + p;
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
