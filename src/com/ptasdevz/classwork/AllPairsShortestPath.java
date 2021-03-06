package com.ptasdevz.classwork;

/**
 * Created by 811100537 on 10/24/2017.
 */
public class AllPairsShortestPath {

    static int[][] D;
    static int[][] path;

    public static void main (String [] args){

        D = new int[5][5];
        path = new int [5][5];
        D[1][1] = 0;
        D[1][2] = 999;
        D[1][3] = 4;
        D[1][4] = 999;

        D[2][1] = 999;
        D[2][2] = 0;
        D[2][3] = 999;
        D[2][4] = 7;

        D[3][1] = 16;
        D[3][2] = 5;
        D[3][3] = 0;
        D[3][4] = 999;

        D[4][1] = 3;
        D[4][2] = 999;
        D[4][3] = 8;
        D[4][4] = 0;
        init();
        getAllPairsShortestPath();
        System.out.printf("\n");
        printVals(D);
        printAllpaths(3);



    }

    public static void getAllPairsShortestPath(){



        for (int k = 1; k < D.length; k++) {

            for (int i = 1; i < D.length; i++) {
                for (int j = 1; j < D.length; j++) {
                    int intermediaryCost = (D[i][k] + D[k][j]);
                    if (intermediaryCost < D[i][j]){
                        D[i][j] = intermediaryCost;
                        path[i][j] = path[k][j]; // store parent node (intermediary node)
                    }
//                    D[i][j] = min(D[i][j],intermediaryCost);

                }
            }
//            System.out.printf("\n");
//            printVals(D);

        }
//        System.out.printf("\n");
//        printVals(path);
    }

    private static int min(int n, int m) {

        if (n<m)return n;
        return m;
    }

    private static void init(){
        for (int i = 0; i < D.length; i++) {
            for (int j = 0; j < D.length; j++) {
                if (i==j){
                    path[i][j] = 0;
                }
                else if (D[i][j] != 999){
                    path[i][j] = i;
                }
                else {
                    path[i][j] = 0;
                }
            }

        }
    }

    private static void printVals(int[][] a){
        for (int i = 1; i <a.length; i++) {
            for (int j = 1; j < a[i].length; j++) {
                System.out.printf("%d ",a[i][j]);
            }
            System.out.println();
        }
    }

    private static void printAllpaths(int v){

        for (int i = 1; i < D.length; i++) {
            if (i != v){
                System.out.printf("Shortest path to %d:",i);
                printAllpathHelper(v,i);
                System.out.printf("\n");
            }
        }
    }

    private static void printAllpathHelper(int u, int v){

        if (u == v) System.out.printf("%d",v);
        else {
            printAllpathHelper(u, path[u][v]);
            System.out.printf(" -> %d",v);
        }

    }



}
