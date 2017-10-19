package com.ptasdevz.classwork;

/**
 * Created by jason on 17/10/2017.
 */
public class LongestCommonSubBR {

    public static void main (String[] args) {

        String X = "ABCBDABC";
        String Y = "BDCABAB";

//        String X = "ABCBDABC";
//        String Y = "ABD";

        int lcsSize = getLCS(X,Y);
        System.out.printf("Longest common subsequence is: %d",lcsSize);

    }

    private static int getLCS(String X, String Y) {

        int lcsSizeMax=0;
        int lcsSize=0;
        String lcs ="";
        String cs="";

        //generate all possibilities of the shorter string
        int shortestSeqLength;
        String shortesSeq;
        int xLength = X.length();
        int yLength = Y.length();
        String longestSeq;
        if (xLength < yLength) {
            shortestSeqLength = xLength;
            shortesSeq = X;
            longestSeq = Y;
        }
        else {
            shortestSeqLength = yLength;
            shortesSeq = Y;
            longestSeq = X;

        }

        int[] bitSeq = new int[shortestSeqLength];
        for (int i = 0; i < bitSeq.length ; i++)bitSeq[i] =0;
        int zeroSeqCounter = 0;
        while (true){

            for (int i = bitSeq.length-1; i >=0; i--) {

                if (bitSeq[i] == 0){
                    bitSeq[i] = 1;
                    zeroSeqCounter =0;
                    break;
                }
                else {
                    bitSeq[i] =0;
                    zeroSeqCounter++;
                }
            }
            //binary sequences available

            //Build string subsequence from binary seqeuence
           String subSeq = "";
           for (int i=0; i<bitSeq.length; i++) {

                if (bitSeq[i] == 1) subSeq +=  shortesSeq.charAt(i);
           }
            int lastSearchSeqPos =0;
            for (int i = 0; i < subSeq.length(); i++) {
                for (int j = lastSearchSeqPos; j < longestSeq.length(); j++) {
                     if (subSeq.charAt(i) == longestSeq.charAt(j)) {
                         lcsSize++;
                         cs += subSeq.charAt(i);
                         lastSearchSeqPos =j; // to be used in the next value of the sequence
                         break;
                     }
                }
            }

            //get the longest common subsequence
           if (lcsSize > lcsSizeMax){
                lcsSizeMax = lcsSize;
                lcs = cs;
           }

           lcsSize = 0;//reset lcs size for next possibility
           cs  = "";
           if (zeroSeqCounter == bitSeq.length) break;// end of sequences
        }
        System.out.println(lcs);
        return lcsSizeMax;
    }


}
