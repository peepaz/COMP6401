package com.ptasdevz.assignments;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class petersjLIS2n {

    static  int [] A;
    static int [] maxSeqVals;

    public static void main (String[] args){

        int ch;
        String number = "";
        int [] tempStorage = new int[10000];
        try {

            FileReader in = new FileReader("input71.txt");
            int numberCount = 0;
            while (true) {

                ch = in.read();
                if (ch >= '0' && ch <= '9') number += (char) ch; //build integers
                else {
                    if (number.compareTo("") != 0) {
                        int n = Integer.parseInt(number);
                        tempStorage[numberCount] = n;
                        numberCount++;
                    }
                    number = "";
                }
                if (ch == -1)break;
            }
            A = new int[numberCount];
            maxSeqVals = new int[A.length];
            for (int i = 0; i <A.length; i++) {
                A[i] = tempStorage[i];
            }

            long startTime = System.nanoTime();
            int max = getLIS(A, A.length);
            long estimatedTimeNano = System.nanoTime() - startTime;
            long estimatedTimeMillSec = TimeUnit.NANOSECONDS.toMillis(estimatedTimeNano);
            estimatedTimeNano = (estimatedTimeNano - TimeUnit.MILLISECONDS.toNanos(estimatedTimeMillSec));

            writeToFileResult(max, estimatedTimeMillSec, estimatedTimeNano);

        }catch (Exception e){
//            e.printStackTrace();
        }

    }

    private static void writeToFileResult(int max, long estimatedTimeMilliSec, long estimatedTimeNano) {

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));
            out.printf("Length: %d\n",max);
            out.printf("LIS: ");
            for (int i = 0; i < maxSeqVals.length; i++) {
                if (maxSeqVals[i] == 1) out.printf("%d ", A[i]);
            }
            out.printf("\nTotal Execution Time: %d.%s millisecond(s)\n",estimatedTimeMilliSec,String.valueOf(estimatedTimeNano).substring(0,3));
            out.flush();

        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        }
    }

    public static int getLIS(int[] M, int bitSeqSize){

        int lenPerSeq=0;
        int maxSeqLen=0;
        int zeroCounter =0;
        int [] bitSeq = new int[bitSeqSize];
        int [] seqValsPos = new int[bitSeq.length];
        for (int i = 0; i <bitSeq.length; i++) {
            bitSeq[i]=0;
            seqValsPos[i] =0;
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
            //new sequence ready
            //find first value in bit sequence
            int i=0;
            for (i = 0; i < bitSeq.length; i++) {
                if (bitSeq[i] == 1) {
                    lenPerSeq++; //increase longest length count
                    seqValsPos[i] = 1; //store position of value
                    break;
                }

            }
            //find next value in the bit sequence
            for (int j = i+1; j < bitSeq.length; j++) {
                if (bitSeq[j] == 1){
                    if (M[i] < M[j]){ // check to see if it's increasing
                        lenPerSeq++;
                        seqValsPos[j] = 1;
                        i = j; //ensures last increasing value becomes the first for the next check.
                    }
                }
            }

            //Check for max thus far
            if (lenPerSeq > maxSeqLen) {
                maxSeqLen = lenPerSeq;

                //copy new path as longest
                for (int j = 0; j < seqValsPos.length; j++) {
                    maxSeqVals[j] = seqValsPos[j];
                }
            }
            //reset sequence path
            for (int j = 0; j < seqValsPos.length; j++) {
                seqValsPos[j]=0;
            }
            lenPerSeq =0;

        }
        return maxSeqLen;
    }
}
