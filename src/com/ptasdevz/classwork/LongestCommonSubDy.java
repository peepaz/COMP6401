package com.ptasdevz.classwork;

/**
 * Created by 811100537 on 10/18/2017.
 */
public class LongestCommonSubDy {

    static int[][] M;
    static String cs = "";

    public static void main (String [] args){

//        String X = "ABCBDABC";
//        String Y = "BDCABAB";
        char[] Y  = {'A','B','C','B','D','A','B','C'};
        char[] X  = {'B','D','C','A','B','A','B'};

//        char[] Y  = {'0','A','B','C','B','D','A','B','C'};
//        char[] X  = {'0','B','D','C','A','B','A','B'};
        //Declare and Initialize matrix
        M = new int[X.length][Y.length];

        for (int i = 0; i <M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                M[i][j] = 0;
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
        printLCS(X,Y);
    }

    /**
     * Get longest common sub-sequence
     * @return
     * @param X
     * @param Y
     */
    public static int getLCS(char[] X, char[] Y){

        return getLCSHelper(X,Y,X.length-1, Y.length-1);
    }

    private static int getLCSHelper(char[] X, char[] Y, int strPosX, int strPosY){

        if (strPosX == -1 || strPosY== -1){
            return 0; // if anyone goes less than 0 then the other string is being compared with nothing hence 0;
        }
        else {

            char xi = X[strPosX];
            char yi = Y[strPosY];
            int lcsVal;
            if (xi == yi) {

                lcsVal = getLCSHelper(X,Y,strPosX-1, strPosY-1) + 1;
                M[strPosX][strPosY] = lcsVal;
                return lcsVal;
            }
            int valxbased = getLCSHelper(X,Y,strPosX-1, strPosY);
            int valybased = getLCSHelper(X,Y,strPosX,strPosY-1);
            lcsVal = max(valxbased,valybased );
            M[strPosX][strPosY] = lcsVal;
            return lcsVal;
        }
    }

    private static void printLCS(char[]X ,char[] Y){

        //starting from the last column in the array
        //reverse algorithm
         printLCS(X,Y,M.length-1,M[0].length-1);
    }

    private static void printLCS(char[]X ,char[] Y, int xiPos, int yiPos){

        if (xiPos == 0) System.out.printf("%s",X[xiPos]);
        else if (yiPos == 0) System.out.printf("%s",Y[yiPos]);
        else {
            int val1 = M[xiPos][yiPos - 1];
            int val2 = M[xiPos - 1][yiPos];
            if (X[xiPos] == Y[yiPos]) {
                printLCS(X, Y, xiPos - 1, yiPos - 1);
                System.out.printf("%s", X[xiPos]);
            } else if (val1 > val2) {
                printLCS(X, Y, xiPos, yiPos - 1);

            } else printLCS(X, Y, xiPos - 1, yiPos);
        }
    }

    private static  int max(int n, int m){
    if (n>m) return n;
    else return m;
    }


}
