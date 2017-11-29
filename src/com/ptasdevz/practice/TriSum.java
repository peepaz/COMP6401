package com.ptasdevz.practice;

/**
 * Created by 811100537 on 11/28/2017.
 */
public class TriSum {

//    static int [] P;
    static String P;
    static int [] B;
    static int [][] M;
    static int maxSum;

    public static void main(String[] args){

        init();
        M[0][0] = 7;
        M[1][0] = 3;
        M[2][0] = 5;
        M[3][0] = 2;
        M[4][0] = 4;
        M[1][1] = 8;
        M[2][1] = 6;
        M[3][1] = 7;
        M[4][1] = 5;
        M[2][2] = 0;
        M[3][2] = 3;
        M[4][2] = 8;
        M[3][3] = 9;
        M[4][3] = 6;
        M[4][4] = 5;
        calTriSum();
        printPath();

    }
    static void init(){
        B = new int [4];
        M = new int [5][5];
    }
    static void calTriSum(){

        while (true){ // while we have  a bit sequence

            int l=0, seqSum=M[0][0];;
            int r=0; String seqPath;
            seqPath =  String.valueOf(M[0][0]);

            for (int i=0; i<B.length; i++) {

              //navigate left if 0 or right if 1
                l= l+1;
                if (B[i] == 1) {
                    r = r+1;
                }
                if (l<M.length && r<M[0].length){//keep inbounds
                    int val = M[l][r];
                    seqSum = seqSum + val;
                    seqPath = seqPath + val;
                }
            }

            //update sum if necessary
            if (seqSum > maxSum){
                maxSum = seqSum;
                P = seqPath;
            }

            //get next sequence
            increCount();
            if (isAllZeros()) break;

        }
    }

    static void increCount(){
        int i = B.length-1;
        while (i>=0 && B[i]==1)B[i--] = 0;
        if (i>=0) B[i] =1;
    }

    static boolean isAllZeros(){
        for (int b : B) {
            if (b==1) return false;
        }
        return true;
    }
    static void printPath(){
        System.out.printf("Max sum is: %d\n",maxSum);
        System.out.printf("Best path is: %s\n",P);
    }

}
