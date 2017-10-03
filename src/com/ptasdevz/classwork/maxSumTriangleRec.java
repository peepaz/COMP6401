package com.ptasdevz.classwork;

public class maxSumTriangleRec {

	static int maxSum =0;
	static int [][] bestPathMap;
	
	public static void main(String[] args) {
		
		int size = 5;
		int [][] triangle = new int [size][size];
		int[]  bestPath = new int [size];
		bestPathMap = new int [size][size];
		for (int[] is : bestPathMap) {
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
		for (int i=1; i<bestPathMap.length; i++) {
			
//			if (j<bestPathMap[i].length){
			if (bestPathMap[i][j] == 1)System.out.print(triangle[i][j+1]);
			else System.out.print(triangle[i][j]);
			
		}
		
			
	}
	
	public static int getMaxSum(int[][] tri, int i, int j, int n){
		
		if (i==n) return tri[i][j];

		int left = getMaxSum(tri, i+1, j, n);
		int right = getMaxSum(tri, i+1, j+1, n);
		int apex = tri[i][j];
		
		if (left > right) {
			System.out.println(i + " " + j + " => left "+ (left + apex));
			bestPathMap[i][j] =0;
		}
		else {
			System.out.println(i + " " + j + " => right "+ (right + apex));

			bestPathMap[i][j] = 1;
		}
		
		return max(left,right) + apex; 

	}

	private static int max(int i, int j) {
		
		if (i>j)return i;
		return j;
	}

}
