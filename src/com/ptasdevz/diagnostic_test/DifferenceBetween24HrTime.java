package com.ptasdevz.diagnostic_test;

/**
 * Created by jason on 20/09/2017.
 */
public class DifferenceBetween24HrTime {

    public static void main(String [] args){

        differenceBetweenTime(20, 30, 6, 15);
    }

    public static void differenceBetweenTime(int hr1, int min1, int hr2, int min2){

        //Given: 1 hr = 60 mins, 24 hrs = 1440 mins = 1 day
        //1. convert both times to mins
        //2. Find the amount of mins remaining in the day for each time
        //3. subtract remaining of the first time from the second time
        //4 if result is negative add 1 day in mins from result
        //4. convert result to hours and mins.

        int time1ToMins = hr1 * 60 + min1;
        int time2ToMins = hr2 * 60 + min2;
        int time1RemMins = 1440 - time1ToMins;
        int time2RemMins = 1440 - time2ToMins;

        int diffBetweenTimes = time1RemMins - time2RemMins;
        if (diffBetweenTimes < 0) diffBetweenTimes = 1440 + diffBetweenTimes;
        int diffBetweenTimeHr = diffBetweenTimes / 60;
        int diffBetweenTimeMin = diffBetweenTimes % 60;

        System.out.printf("%d hours and %d mins", diffBetweenTimeHr, diffBetweenTimeMin);


    }
}
