package com.ptasdevz.assignments;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class petersJLISn2 {


    static int[] A;
    static int[] L;
    static int[] E;

    public static void main(String[] args) {

        int ch;
        String number = "";
        int [] tempStorage = new int[10000];
        try {

            FileReader in = new FileReader("input.txt");
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
            L = new int[A.length];
            E =  new int[A.length];

            //initialize all lenghts to 1 	and end points to 0
            for (int i = 0; i < A.length; i++) {
                L[i] =1;
                E[i] =i;
                A[i] = tempStorage[i];
            }
            long startTime = System.nanoTime();
            int max = getLIS();
            long estimatedTimeNano = System.nanoTime() - startTime;
            long estimatedTimeMillSec = TimeUnit.NANOSECONDS.toMillis(estimatedTimeNano);
            estimatedTimeNano = (estimatedTimeNano - TimeUnit.MILLISECONDS.toNanos(estimatedTimeMillSec));
            writeToFileResult(max, estimatedTimeMillSec, estimatedTimeNano);


        }catch (Exception e){
//            e.printStackTrace();
        }

    }

    public static int getLIS(){
        int max = 1;
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]){
                    int res = max(1+L[j], L[i]);
                    if (res == 1+L[j] && res != L[i]){// second condition avoids changing index if result == to val at i
                        E[i] = j; //max length came from previous
                    }
                    //else remain as is
                    L[i] = res;

                }
            }
        }

        //get max value
        for (int i = 0; i < L.length; i++) {
            if (L[i] > max) max = L[i];
        }
        return max;

    }

    public static int max(int m,int n){
        if (m>n)return m;
        return n;
    }

    private static void writeToFileResult(int max, long estimatedTimeMilliSec, long estimatedTimeNano) {

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));
            out.printf("Length: %d\n",max);
            out.printf("LIS: ");
            for (int i = 0; i < L.length; i++) {
                if (max ==L[i]) {
                    out.write(printPath(i));
                    break;
                }
            }
            out.printf("\nTotal Execution Time: %d.%s millisecond(s)\n",estimatedTimeMilliSec,
                    String.valueOf(estimatedTimeNano).substring(0,3));
            out.flush();

        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        }
    }

    //print path recursively from array link list
    public static String printPath(int idxHead){

        if (idxHead == E[idxHead]) return String.valueOf(A[idxHead]);
           return printPath(E[idxHead]) +" "+ A[idxHead];
    }

}
