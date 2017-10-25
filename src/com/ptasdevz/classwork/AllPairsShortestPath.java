package com.ptasdevz.classwork;

/**
 * Created by 811100537 on 10/24/2017.
 */
public class AllPairsShortestPath {

    static int[][] D;
    static int[][] path;

    public static void main (String [] args){

        D = new int[4][4];
        path = new int [4][4];
        D[1][1] = 0;
        D[1][2] = 2;
        D[1][3] = 9;
        D[2][1] = 8;
        D[2][2] = 0;
        D[2][3] = 6;
        D[3][1] = 1;
        D[3][2] = -1;
        D[3][3] = 0;
        getAllPairsShortestPath();


    }

    public static void getAllPairsShortestPath(){

        for (int k = 1; k < D.length; k++) {

            for (int i = 1; i < D.length; i++) {
                for (int j = 1; j < D.length; j++) {
                    int intermediaryCost = (D[i][k] + D[k][j]);
                    if (intermediaryCost < D[i][j]){
                        D[i][j] = intermediaryCost;
                        path[i][j] = k; // store parent node (intermediary node)
                    }
//                    D[i][j] = min(D[i][j],intermediaryCost);

                }
            }
            System.out.printf("\n");
            printVals(D);
            System.out.printf("\n");
            printVals(path);

        }
    }

    private static int min(int n, int m) {

        if (n<m)return n;
        return m;
    }

    private static void printVals(int[][] a){
        for (int i = 1; i <a.length; i++) {
            for (int j = 1; j < a[i].length; j++) {
                System.out.printf("%d ",a[i][j]);
            }
            System.out.println();
        }
    }
}
