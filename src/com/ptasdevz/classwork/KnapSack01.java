package com.ptasdevz.classwork;

public class KnapSack01 {

    static int [][] itemSizeVal =  {{0,0},{2,4},{3,5},{5,8},{6,11}};
    static int [][] C;
    public static void main(String [] args){

        int w = 10;
        C= new int[itemSizeVal.length][w+1];

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                C[i][j] =0;
            }
        }
        int bestVal = fillKapSack();
        System.out.printf("Best val: %d\n",bestVal);
        System.out.printf("Items chosen: ");
        printItems(itemSizeVal.length-1,w);
    }

    public  static int fillKapSack() {

        for (int i = 1; i < itemSizeVal.length; i++) {
            for (int j = 1; j < C[i].length; j++) {

                int itemSize = itemSizeVal[i][0];
                int itemVal = itemSizeVal[i][1];

                int bestOfPrev = C[i-1][j]; //best of previous
                int itemValWhenTaken;
                if (j<itemSize) {
                    C[i][j] = bestOfPrev;
                }
                else {
                    itemValWhenTaken = itemVal + C[i-1][j-itemSize]; // best weight = curr item weight + previous item weight that == remaining weight
                    C[i][j] = max(itemValWhenTaken,bestOfPrev);

                }
            }
        }
        return C[itemSizeVal.length-1][C[0].length-1];
    }

    public static void printItems(int i, int j){

        //Where did the value of  C[i][j] come from?
        if (i == 0 || j==0) return ;
        if (C[i][j] == C[i - 1][j]) {
            //Item at this pos was not used. hence came from position above
            printItems(i - 1, j);
        } else {
            //Item at this postion was used
            int itemSize = itemSizeVal[i][0];
            printItems(i - 1, j - itemSize);
            System.out.printf("%d ", i);
        }
    }

    private static int max(int n, int m){
        if (n>m) return n;
        return m;
    }

}
