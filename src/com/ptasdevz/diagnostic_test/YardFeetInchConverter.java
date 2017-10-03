package com.ptasdevz.diagnostic_test;

/**
 * Created by jason on 20/09/2017.
 */
public class YardFeetInchConverter {

    public static void main (String [] args){

        yardFeetInchesConverter(100);

    }

    public static void yardFeetInchesConverter(int ins){

        //Given 1 yard = 3 feet, 1 foot = 12 ins

        //1.Convert ins to yards  1 yrd = (3x12ins) = 36 ins
        //2. Take remainder after getting yards and convert to feet.
        //3. remainder after feet will be ins
        //4. Print yards feet and ins

        int yards = ins/36;
        ins = ins % 36;
        int feet = ins / 12;
        ins = ins % 12;

        System.out.printf("Yard = %d, Feet= %d, Ins= %d ", yards, feet, ins );
    }
}


