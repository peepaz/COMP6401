package com.ptasdevz.classwork;

/**
 * Created by jason on 13/10/2017.
 */
public class LongestIncreasingSeqBr {

//    static int[] A = {35,31,22,30,27,29,21,32,24,28};
    static int[] A = {2, 15, 3, 7, 8, 6, 18,3,3,3,3,3,3,3,3,4,4,7,9,9,100,4,67,67,89,10,79,34};
//            static int[] A = {30,56,23,21,12,29};


    static int [] overallPath = new int[A.length];

    public static void main (String[] args){

        long millisStrt = System.currentTimeMillis();
        int max = getLIS(A, A.length);
        long millisEnd = System.currentTimeMillis();

        System.out.println("OVERALL IS " + max);
        for (int i = 0; i < overallPath.length; i++) {
            if (overallPath[i] == 1) System.out.printf("%d ", A[i]);
        }
        System.out.printf("\nTime in millis: %d\n",millisEnd - millisStrt);

    }

    public static int getLIS(int[] M, int bitSeqSize){

        int maxPerSeq=0;
        int maxOverall=0;
        int zeroCounter =0;
        int [] bitSeq = new int[bitSeqSize];
        int [] path = new int[bitSeq.length];
        for (int i = 0; i <bitSeq.length; i++) {
                    bitSeq[i]=0;
                    path[i] =0;
        }
        while (true){

            for (int i = bitSeq.length-1; i >= 0 ; i--) {

                //Add 1 to complete new sequence
                if (bitSeq[i] == 0){
                    bitSeq[i] = 1;
                    zeroCounter =0; //reset counter
                    break;
                }
                else {
                    bitSeq[i] = 0; //start changing sequence
                    zeroCounter++; //zero sequence counter to identify end.

                }
            }
            if (zeroCounter == bitSeqSize) break; // completed all possibilities | the counter restarts

//            for (int i = 0; i < bitSeq.length; i++) {
//                System.out.printf("%d ",bitSeq[i]);
//            }
//            System.out.println();
            //new sequence ready
            //find first value in sequence
            int i=0;
            for (i = 0; i < bitSeq.length; i++) {
                if (bitSeq[i] == 1) {
                    maxPerSeq++; //increase longest length count
                    path[i] = 1; //store position of value
//                    System.out.println(M[i]);
                    break;
                }

            }
            //find subsequent values in the possibility
            for (int j = i+1; j < bitSeq.length; j++) {
                if (bitSeq[j] == 1){
                    if (M[i] < M[j]){ // check to see if it's increasing
//                        System.out.println(M[j]);
                       maxPerSeq++;
                       path[j] = 1;
                       i = j; //ensures last increasing value becomes the first for the next check.
                    }
                }
            }
//            System.out.println(maxPerSeq);

            //Check for max thus far
            if (maxPerSeq > maxOverall) {
                maxOverall = maxPerSeq;

                //copy new path as longest
                for (int j = 0; j < path.length; j++) {
                    overallPath[j] = path[j];
                }

            }
            //reset sequence path
            for (int j = 0; j < path.length; j++) {
                path[j]=0;
            }
            maxPerSeq =0;

        }
        return maxOverall;
    }
}

