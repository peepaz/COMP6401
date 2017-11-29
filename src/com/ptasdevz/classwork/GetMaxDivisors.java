package com.ptasdevz.classwork;

public class GetMaxDivisors {

    public static void main(String [] args){

        int m =3;
        int n =50;
        int num = getMaxDivisors1(m,n);
        System.out.printf("%d \n",num);

    }

    private static int getMaxDivisors(int m, int n) {
        int maxDivisors = 0;
        int num =0;
        for (int i = m; i <=n; i++) {
            int divisors=0;
            for (int j = 2; j <=i/2; j++) {
                if (i%j == 0){
                    divisors = divisors + 1;
                }
            }
            if (divisors +1 > maxDivisors){
                maxDivisors = divisors;
                num = i;
            }
        }
        return num;
    }

    private static int getMaxDivisors1(int m, int n) {
        int maxDivisors = 0;
        int num =0;
        for (int i = m; i <=n; i++) {
            int divisors=0;
            int d = 2;
            while (d*d<i){ //up to root m
                if (i%d == 0){
                    divisors = divisors + 2;
                }
                ++d;
            }
            if (d*d == n) divisors++;
            if (divisors > maxDivisors){

                maxDivisors = divisors;
                num = i;
            }
        }
        return num;
    }

}
