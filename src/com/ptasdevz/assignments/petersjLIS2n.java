package com.ptasdevz.assignments;

public class petersjLIS2n {

    static int[] A = {2, 15, 3, 7, 8, 6, 18,3,3,3,3,3,3,3,3,4,4,7,9,9,100,4,67,67,89,10,79,34};

    static int [] maxSeqVals = new int[A.length];

    public static void main (String[] args){

        long millisStart = System.currentTimeMillis();
        int max = getLIS(A, A.length);
        long millisEnd = System.currentTimeMillis();

        System.out.printf("Length: %d\n",max);
        System.out.printf("LIS: ");
        for (int i = 0; i < maxSeqVals.length; i++) {
            if (maxSeqVals[i] == 1) System.out.printf("%d ", A[i]);
        }
        System.out.printf("\nTime in millis: %d\n",millisEnd - millisStart);

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
