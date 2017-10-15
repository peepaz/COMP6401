package com.ptasdevz.classwork;

/**
 * Created by jason on 13/10/2017.
 */
public class LongestIncreasingSeqBr {

    static int[] M = {35,31,22,30,27,29,21,32,24,28};
//            static int[] M = {30,56,23,21,12,29};


    static int [] overallPath = new int[M.length];

    public static void main (String[] args){


        int max = getLIS(M,M.length);

        System.out.println("OVERALL IS " + max);
        for (int i = 0; i < overallPath.length; i++) {
            if (overallPath[i] == 1) System.out.printf("%d ",M[i]);
        }


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

                if (bitSeq[i] == 0){
                    bitSeq[i] = 1;
                    zeroCounter =0;
                    break;
                }
                else {
                    bitSeq[i] = 0;
                    zeroCounter++;

                }
            }
            if (zeroCounter == bitSeqSize) break;

            for (int i = 0; i < bitSeq.length; i++) {
                System.out.printf("%d ",bitSeq[i]);
            }
            System.out.println();
            //new sequence ready
            //find first value in sequence
            int i=0;
            for (i = 0; i < bitSeq.length; i++) {
                if (bitSeq[i] == 1) {
                    maxPerSeq++;
                    path[i] = 1;
                    System.out.println(M[i]);
                    break;
                }

            }
            for (int j = i+1; j < bitSeq.length; j++) {
                if (bitSeq[j] == 1){
                    if (M[i] < M[j]){
                        System.out.println(M[j]);
                       maxPerSeq++;
                       path[j] = 1;
                       i = j;
                    }
                }
            }
            System.out.println(maxPerSeq);
            if (maxPerSeq > maxOverall) {
                maxOverall = maxPerSeq;
                for (int j = 0; j < path.length; j++) {
                    overallPath[j] = path[j];
                }

            }
            for (int j = 0; j < path.length; j++) {
                path[j]=0;
            }
            maxPerSeq =0;

        }
        return maxOverall;
    }
}

