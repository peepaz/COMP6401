package com.ptasdevz.assignments;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created by jason on 07/10/2017.
 */
public class petersjAssignment2 {

    static int[][] GRID;
    static int [][] BEST_MIN_PATH;
    static String memoizedMinSum[];

    public static void main(String [] args){

        int numberCount = 0, gridElementCount =0;

        try {
            //Create 2D-array and populate with elements from file
            FileReader in = new FileReader("input4.txt");
            int ch, n=-1,m=-1;
            String number = "";
            while (true){
                ch = in.read();
                if (ch >= '0' && ch <='9' || ch == '-')number += (char)ch; //build both negative and positive integers
                else {

                    if (number.compareTo("") != 0) {
                        numberCount++;
                        if (numberCount == 1) m = Integer.parseInt(number);
                        else if (numberCount == 2) {
                            n = Integer.parseInt(number);
                            GRID = new int[m][n];
                        }
                        else {
                            int i = gridElementCount / n;
                            int j = gridElementCount % n;
                            GRID[i][j] = Integer.parseInt(number);
                            gridElementCount++;
                        }
                        number = "";
                    }
                    if (ch == -1)break;
                }
            }

            int steps = GRID[0].length-1;
            BEST_MIN_PATH = new int[m][n];
            memoizedMinSum = new String [(n* (m-1)) + n];//calculate index for last pos of the Grid +1

            int bestMin=0;
            int bestStartPos = -1;
            for (int l = 0; l < GRID.length; l++) {

                int minSum = getMinSum(GRID,l,0,steps);
                if (l==0){
                    bestMin = minSum;
                    bestStartPos = l;
                }
                else if (minSum < bestMin){
                    bestMin = minSum;
                    bestStartPos = l;
                }
            }
            writeOutput(bestMin,bestStartPos);

        }catch (Exception e){
//                e.printStackTrace();
        }

    }

    public static int getMinSum(int[][] GRID, int i, int j, int steps){

        if (j==steps) return GRID[i][j];

        int memoSumPos = (GRID.length*i) + j;

        if (memoizedMinSum[memoSumPos] == null) {

            int diagUpSum = -1;
            int diagDownSum = -1;
            int horizontialSum = -1;

            //check for wrap around conditions
            if (i - 1 < 0) diagUpSum = getMinSum(GRID, GRID.length - 1, j + 1, steps);
            else diagUpSum = getMinSum(GRID, i - 1, j + 1, steps);

            horizontialSum = getMinSum(GRID, i, j + 1, steps);

            if (i + 1 > GRID.length - 1) diagDownSum = getMinSum(GRID, GRID.length - 1, j + 1, steps);
            else diagDownSum = getMinSum(GRID, i + 1, j + 1, steps);


            //get next best min sum step value and store path
            int bestMinSum = min(min(diagUpSum, diagDownSum), horizontialSum);

            if (bestMinSum == diagUpSum) BEST_MIN_PATH[i][j] = 1;
            else if (bestMinSum == horizontialSum) BEST_MIN_PATH[i][j] = 2;
            else BEST_MIN_PATH[i][j] = 3;

            memoizedMinSum[memoSumPos] = String.valueOf(bestMinSum +GRID[i][j]);
        }

        return  Integer.parseInt(memoizedMinSum[memoSumPos]);

    }

    private static int min(int i, int j) {

       if (i<j) return i;
       return j;
    }

    public static void writeOutput(int minSum,int startPos) {

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));
            out.printf("%d\n",minSum);
            int rowIdx =startPos;
            int colIdx =0;
            int rowCount = BEST_MIN_PATH.length;
            int colCount = BEST_MIN_PATH[0].length;
            int pathStep =-1;
            out.printf("%d ", rowIdx + 1); //first step
            while (true){

                pathStep = BEST_MIN_PATH[rowIdx][colIdx];

                //move Diagonal Up
                if (pathStep == 1) {
                    rowIdx = rowIdx - 1;
                    if (rowIdx < 0) rowIdx = rowCount - 1;
                }
                //move Diagonal Down
                else if (pathStep == 3) {
                    rowIdx = rowIdx + 1;
                    if (rowIdx > BEST_MIN_PATH.length -1) rowIdx = 0;

                }
                else{}; //move horizontal

                out.printf("%d ", rowIdx + 1);
                out.flush();

                colIdx++;
                if (colIdx >= colCount-1) break;

            }
            out.flush();
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        }
    }
}
