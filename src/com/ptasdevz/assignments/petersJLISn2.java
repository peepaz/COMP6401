package com.ptasdevz.assignments;

public class petersJLISn2 {


    static int[] A = {2, 15, 3, 7, 8, 6, 18,3,3,3,3,3,3,3,3,4,4,7,9,9,100,4,67,67,89,10,79,34};

    static int[] L = new int[A.length];
    static int[] E =  new int[A.length];

    public static void main(String[] args) {

        //initialize all lenghts to 1 	and end points to 0
        for (int i = 0; i < L.length; i++) {
            L[i] =1;
            E[i] =i;
        }
        long millisStrt = System.currentTimeMillis();
        int max = getLis();
        long millisEnd = System.currentTimeMillis();

        System.out.printf("Length is: %d\n",max);
        System.out.printf("LIS: ");
        for (int i = 0; i < L.length; i++) {
            if (max ==L[i]) {
                printPath(i);
                break;
            }
        }
        System.out.printf("\nTime in millis: %d\n",millisEnd - millisStrt);

    }

    public static int getLis(){
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
        if (m>n){
            return m;
        }
        return n;
    }

    //print path recursively from array link list
    public static void printPath(int idxHead){

        if (idxHead == E[idxHead]) System.out.printf("%d ", A[idxHead]);
        else{
            printPath(E[idxHead]);
            System.out.printf("%d ", A[idxHead]);
        }

    }
}
