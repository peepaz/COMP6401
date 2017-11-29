package com.ptasdevz.classwork;

public class LogNPowerFunctions {

    public static void main (String[] args){

        System.out.println(powerRec(2,6));
        System.out.println(powerIter(2,6));
    }

    public static int powerRec(int x, int n){
        //base case
        if (n==0) return 1;

        //get answer from subproblem of each log factor
        int y = powerRec(x,n/2);

        //case 1: when n is even
        if (n % 2 == 0) return y * y;

        //case 2: when n is odd
        return x * y * y;
    }

    public static int powerIter(int x, int n){

        int pxn = 1; int xn = x;
        if (n == 0)return  1;
        while (n > 0){
            //power is odd
            if (n%2 == 1) pxn = pxn * xn;
            xn = xn * xn;
            n = n/2;
        }
        return pxn;
    }
}
