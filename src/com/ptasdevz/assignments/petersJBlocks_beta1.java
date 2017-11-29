package com.ptasdevz.assignments;

public class petersJBlocks_beta1 {

    public static void main (String[] args){

        int[][][][] T = new int [2][2][2][2];
        int[][][][] path = new int [2][2][2][2];
//        int[][][][] path = new int [5][4][5][4];

        init(T);
        T[0][0][1][0] = 1;
        T[1][0][1][1] = 1;

//        T[1][1][1][1] = 0;
//        T[1][1][1][2] = 1;
//        T[1][1][2][1] = 1;
//        T[1][2][1][1] = 1;
//        T[1][2][1][2] = 0;
//        T[2][1][1][1] = 1;
//        T[2][1][2][1] = 0;
//        T[2][1][2][2] = 1;
//        T[2][2][2][1] = 1;
//        T[2][2][2][2] = 0;
        getAllPairsShortestPath(T,path);
        
    }

    private static void getAllPairsShortestPath(int[][][][] D, int[][][][] path) {

        for (int k = 0; k < D.length * 2 * 2; k++) {
            for (int l = 0; l < D.length; l++) {//outer rows
                for (int m = 0; m < D[0].length; m++) {//outer columns
                    for (int n = 0; n < D[0][0].length; n++) { //inner row
                        for (int o = 0; o < D[0][0][0].length; o++) { //inner columns


                            int intermediaryCost = (D[l][m][n][o] + D[l][m][n][o]);
                            if (intermediaryCost < D[l][m][n][o] && intermediaryCost >=0){
                                D[l][m][n][o] = intermediaryCost;
                                path[l][m][n][o] = k; // store parent node (intermediary node)
                            }

//                            System.out.printf("%d ",D[l][m][n][o]);
//                            System.out.println(o);

                        }
//                        System.out.printf("\n");

                    }
//                    System.out.printf("\n");
                }
            }
//            System.out.printf("\n");
            System.out.printf("\n");
            printVals(D);
            System.out.printf("\n");
//            printVals(path);
        }


    }

    private static void printVals(int[][][][] a){
        for (int i = 0; i <a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                for (int k = 0; k <a[0][0].length; k++) {
                    for (int l = 0; l < a[0][0][0].length; l++) {
                        System.out.printf("%d ",a[i][j][k][l]);

                    }

                }
                System.out.println();

            }
        }
    }

    private static void init(int[][][][] t){

        for (int l = 0; l < t.length; l++) {//outer rows
            for (int m = 0; m < t[0].length; m++) {//outer columns
                for (int n = 0; n < t[0][0].length; n++) { //inner row
                    for (int o = 0; o < t[0][0][0].length; o++) { //inner columns

                        t[l][m][n][o] = -1;

//                        System.out.printf("%d ",t[l][m][n][0]);
//                            System.out.println(o);

                    }
//                        System.out.printf("\n");

                }
//                System.out.printf("\n");
            }
        }
    }


}
