package com.ptasdevz.classwork;

/**
 * Created by jason on 13/10/2017.
 */
public class LongestIncreasingSeqBr1 {

//  static int[] A = {35,31,22,30,27,29,21,32,24,28};
    static int[] A = {2, 15, 3, 7, 8, 6, 18,3,3,4,4,7,9,9,100,4,67,67,89,10,79,34};
//  static int[] A = {30,56,23,21,12,29};


    static int [] overallPath = new int[A.length];
    static  int [] bitSeq;
    static int zeroCounter = 0;


    public static void main (String[] args){

        long millisStrt = System.currentTimeMillis();
        bitSeq = new int[A.length];
        int max = getLIS(A);
        long millisEnd = System.currentTimeMillis();

        System.out.println("OVERALL IS " + max);
        for (int i = 0; i < overallPath.length; i++) {
            if (overallPath[i] == 1) System.out.printf("%d ", A[i]);
        }
        System.out.printf("\nTime in millis: %d\n",millisEnd - millisStrt);

    }

    public static int getLIS(int[] M){

        int seqIncrCount=0;
        int maxOverall=0;
        int [] seqPath = new int[bitSeq.length];
        for (int i = 0; i <bitSeq.length; i++) {
            bitSeq[i]=0;
            seqPath[i] =0;
        }

        while (true){
            boolean isFirstBitChecked = false;

            //increment to next sequence
            incrCounter();

            //check for all 0's
            if (isZeroSeq()) break;

            //find possibilities that are denoted by 1's
            int i =-1;
            for (int j = 0; j < bitSeq.length; j++) {

                if (bitSeq[j] == 1 && !isFirstBitChecked){
                    isFirstBitChecked = true;
                    seqIncrCount++;
                    seqPath[j]=1;
                    i = j; //keep index of last found increasing sequence
                }
                else if (bitSeq[j] == 1 && isFirstBitChecked){
                    if (M[i] < M[j]){ // check to see if it's increasing
                       seqIncrCount++;
                       seqPath[j] = 1; //location where increase happened
                       i = j; //keep index of last found increasing sequence
                    }
                }
            }

            //Check for max thus far
            if (seqIncrCount > maxOverall) {
                maxOverall = seqIncrCount;

                //copy new path as longest
                for (int j = 0; j < seqPath.length; j++) {
                    overallPath[j] = seqPath[j];
                }
            }
            //reset sequence path and increasing count
            for (int j = 0; j < seqPath.length; j++) {
                seqPath[j]=0;
            }
            seqIncrCount =0;
        }
        return maxOverall;
    }

    public static void incrCounter(){
        int i = bitSeq.length-1;
        while(i>=0 && bitSeq[i] == 1)bitSeq[i--] =0;
        if (i>=0) bitSeq[i] = 1;
    }
    public static boolean isZeroSeq(){
        for (int seqBit : bitSeq) {
            if (seqBit == 1) return false;
        }
        return true;
    }

}

