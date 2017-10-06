package com.ptasdevz.classwork;

public class maxSumTriangleRec {

	static int maxSum =0;
	static int [][] bestPathMap;
//     static int memoSum[][];
    static int memoSum[];

    public static void main(String[] args) {
		
		int size = 5;
		int [][] triangle = new int [size][size];
		int[]  bestPath = new int [size];
		bestPathMap = new int [size][size];
// 		memoSum = new int[size ][size];
	    	memoSum = new int [(triangle[0].length * (triangle.length-1)) + triangle.length-1 +1];// last pos in tri + 1
		for (int[] is : bestPathMap) {
			for (int i = 0; i < is.length; i++) {
				is[i] = -1;
			}
		}
		for (int[] is : memoSum) {
			for (int i = 0; i < is.length; i++) {
				is[i] = -1;
			}
		}
		triangle[0][0] = 7;
		triangle[1][0] = 3;
		triangle[2][0] = 5;
		triangle[3][0] = 2;
		triangle[4][0] = 4;
		triangle[1][1] = 8;
		triangle[2][1] = 6;
		triangle[3][1] = 7;
		triangle[4][1] = 5;
		triangle[2][2] = 0;
		triangle[3][2] = 3;
		triangle[4][2] = 8;
		triangle[3][3] = 9;
		triangle[4][3] = 6;
		triangle[4][4] = 5;
		
		maxSum = getMaxSum(triangle, 0, 0, size-1);
		System.out.printf("Max sum is: %d\n",maxSum);
		
		int j =0;
		System.out.print(triangle[0][0]);
		for (int i=0; i<bestPathMap.length; i++) {

			if (bestPathMap[i][j] == 0) System.out.print(triangle[i+1][j]); //go left

			else if (bestPathMap[i][j] == 1) System.out.print(triangle[i+1][++j]); //go right
			
		}
			
	}
	
	public static int getMaxSum(int[][] tri, int i, int j, int n){
		
		if (i==n) return tri[i][j];

	int memoSumPos = (tri.length*i) + j;
        if (memoSum[memoSumPos] == -1) {

            int left = getMaxSum(tri, i+1, j, n);
            int right = getMaxSum(tri, i+1, j+1, n);
            int apex = tri[i][j];
            int val = max(left,right) + apex;
		
            System.out.printf("stored %d %d => %d\n",i,j,val);
//             memoSum[i][j] = val;
		memoSum[pos] = val;
            if (left > right) {
//			System.out.println(i + " " + j + " => left "+ (left + apex));
                bestPathMap[i][j] =0;
            }
            else {
//			System.out.println(i + " " + j + " => right "+ (right + apex));

                bestPathMap[i][j] = 1;
            }
        }
        else {
            System.out.printf("memo %d %d => %d\n",i,j,memoSum[i][j]);
        }

        return memoSum[pos];


    }

	private static int max(int i, int j) {
		
		if (i>j)return i;
		return j;
	}

}
