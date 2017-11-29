package com.ptasdevz.classwork;

public class BinaryCounter2 {

    static int[] B;
    public static void main (String [] args){
        int n = 3;
        B = new int[n];
        incrCounter(B,n);
        incrCounter(B,n);
        incrCounter(B,n);
        incrCounter(B,n);
        incrCounter(B,n);
        incrCounter(B,n);
        incrCounter(B,n);
        incrCounter(B,n);


    }

    public static void incrCounter(int[] b, int n){
        int i=n-1;
        while (i>=0 && b[i]==1)b[i--]=0;
        if (i>=0)
            b[i] =1;

//        for (int j = 0; j < b.length; j++) {
//            System.out.printf("%d",b[j]);
//        }
//        System.out.print("\n");
    }


}
