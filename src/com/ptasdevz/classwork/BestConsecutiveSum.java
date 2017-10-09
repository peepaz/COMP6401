package com.ptasdevz.classwork;

/**
 * Created by jason on 06/10/2017.
 */
public class BestConsecutiveSum {

    public static void main (String [] args){

        int[]  seq = {-3,5,6,-7,-2,8,-4,7,-9,3};

        int max = getBestSum2(seq);

        System.out.println(max);

    }
    public static int getBestSum(int[] arr){

        int max = -10;

        for (int i = 0; i <arr.length; i++) {
            int vectorSum =0;
            for (int j = i; j < arr.length; j++) {

                vectorSum += arr[j];
                if (vectorSum > max) max = vectorSum;
            }
        }
        return max;

    }

    public static  int getBestSum2(int[] arr){

        int[] max = new int[arr.length];
        int bestSum = -10;

        for (int i = 0; i < arr.length; i++) {
            if (i==0){
                max[i] = arr[i];
                bestSum = arr[i];
            }
            else {
                if (max[i-1] < 0) max[i] = arr[i];
                else {
                    max[i] = arr[i] + max[i-1];
                }

                if (max[i] > bestSum) bestSum = max[i];
            }

        }
        return bestSum;
    }
}

