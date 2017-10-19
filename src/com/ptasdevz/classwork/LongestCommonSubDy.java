package com.ptasdevz.classwork;

/**
 * Created by 811100537 on 10/18/2017.
 */
public class LongestCommonSubDy {

    static int[][] M;
    static String cs = "";

    public static void main (String [] args){

        String X = "ABCBDABC";
        String Y = "NMAB";


        //Declare and Initialize matrix
        M = new int[X.length()][Y.length()];
        for (int i = 0; i <M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                M[i][j] = -1;
            }
        }

        //Get the longest common sub-sequence
        int lcs = getLCS(X,Y);

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                System.out.printf("%d",M[i][j]);
            }
            System.out.printf("\n");
        }
        System.out.printf("Lcs is: %d\n",lcs);
        System.out.println(cs);

    }

    /**
     * Get longest common sub-sequence
     * @return
     */
    public static int getLCS(String X, String Y){


        return getLCSHelper(X,Y,X.length()-1, Y.length()-1);
    }

    private static int getLCSHelper(String X,String Y, int strPosX, int strPosY){

//
        if (strPosX < 0 || strPosY < 0){

            return 0; // if anyone goes less than 0 then the other string is being compared with nothing hence 0;
        }
        else {

            char xi = X.charAt(strPosX);
            char yi = Y.charAt(strPosY);
            int lcsVal;
            if (xi == yi) {
                lcsVal = getLCSHelper(X,Y,strPosX-1, strPosY-1) + 1;
                M[strPosX][strPosY] = lcsVal;
                return lcsVal;

            }
            int valxbased = getLCSHelper(X,Y,strPosX-1, strPosY);
            int valybased = getLCSHelper(X,Y,strPosX,strPosY-1);
            System.out.println(strPosX + "," +strPosY);
            lcsVal = max(valxbased,valybased );
            M[strPosX][strPosY] = lcsVal;
            return lcsVal;
        }
    }

    private static  int max(int n, int m){
        if (n>m) return n;
        else return m;
    }


}
