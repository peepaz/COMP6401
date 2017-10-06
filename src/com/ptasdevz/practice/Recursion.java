package com.ptasdevz.practice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by jason on 04/10/2017.
 */
public class Recursion {
    static long []memoizeFibStorage;
    public static void main (String [] args){
        Scanner in = new Scanner(System.in);
        String options =
                "1. Calculate nth Fibonacci Sequence (rec)\n" +
                "2. Calculate nth Fibonacci Sequence (memoize rec)\n" +
                "3. Calculate the nth Factorial (rec)\n" +
                "4. Calculate the nth Factorial (memoize rec)\n" +
                "0  Exit\n";
        while (true){

            System.out.printf("Choose from options below: \n");
            System.out.printf(options);
            int option =-1;
            while (in.hasNext()) {
                if (in.hasNextInt()) {
                    option = in.nextInt();
                    break;
                } else {
                    System.out.printf("Invalid Entry\n Please enter a valid entry.\n");
                    in.next();
                }
            }
            switch(option){

                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.printf("Enter the nth position of the fib sequence.\n");
                    if (in.hasNextInt()) {
                        int val = in.nextInt();
                        String startTime = getCurrentTimeStamp();
                        System.out.printf("The %d pos is: %d\n\n",val, fib(val));
                        String endTime = getCurrentTimeStamp();
                        System.out.println("start: " + startTime + " " + "end: " + endTime);
                    }
                    else System.out.printf("Invalid ent1ry.\n");

                    break;
                case 2:
                    System.out.printf("Enter the nth position of the fib sequence.\n");
                    if (in.hasNextLong()) {
                        long val = in.nextLong();
                        memoizeFibStorage = new long[(int) (val+1)];
                        //init fib storage
                        for (int i = 0; i < memoizeFibStorage.length; i++) {
                            memoizeFibStorage[i] = -1;
                        }
                        String startTime = getCurrentTimeStamp();
                        System.out.printf("The %d pos is: %d\n\n",val, memoizeFib(val));
                        String endTime = getCurrentTimeStamp();
                        System.out.println("start: " + startTime + " " + "end: " + endTime);

                    }
                    else System.out.printf("Invalid entry.\n");

                    break;
                case 3:
                    break;
            }

        }
    }

    public static long fib(long n){
        /**
         * fib is defined as fib(n) = fib(n-1) + fib(n-1)
         *
         * Base cases:
         * fib(0) = 0
         * fib(1) = 1
         *
         * Recursive case
         * fib(n) = fib(n-1) + fib(n-2)
         */
        if (n==0) return 0;
        else if (n==1) return 1;
        return fib(n-1) + fib(n-2);

    }

    public static long memoizeFib(long n){
        /**
         * fib is defined as fib(n) = fib(n-1) + fib(n-1)
         *
         * Base cases:
         * fib(0) = 0
         * fib(1) = 1
         *
         * Recursive case:
         * fib(n) = fib(n-1) + fib(n-2)
         *
         * Optimize using memoization: store the work already done
         * by recursive case for a later.
         */
        if (n == 0) return 0;
        else if (n == 1) return 1;
        if (memoizeFibStorage[(int) n] == -1) {

            long sum = memoizeFib(n - 1) + memoizeFib(n - 2);
            memoizeFibStorage[(int) n] = sum;
        }
        return memoizeFibStorage[(int) n];

    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
