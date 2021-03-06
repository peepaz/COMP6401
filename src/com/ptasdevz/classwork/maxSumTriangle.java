package com.ptasdevz.classwork;

public class maxSumTriangle {
	static int maxSum =0;
	static int [] bestPath;
	public static void main (String [] args){
		int size = 5;
		int [][] triangle = new int [size][size];
		bestPath = new int [size];
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
		
		calMaxSumAndPath(triangle);
		
		System.out.printf("Best path is: " );
		for (int i =0; i<bestPath.length; i++){
			System.out.print(bestPath[i]);
		}
		
		System.out.printf("\nMax Sum is: %d", maxSum);
		
	}
	
	public static void calMaxSumAndPath(int [][] triangle){
		
		int [] bitSeq = new int [triangle.length-1];
		int [] path = new int [triangle.length];	
		for (int i =0; i< bitSeq.length; i++) bitSeq[i]=0;
		
		int firstVal = triangle[0][0];
		path[0] = firstVal;
        int l=0, m=0, pathSum=firstVal;
		
		//Brute force 
		while (true){
			
			//use sequence to navigate triangle
			for (int k=0; k<bitSeq.length; k++){
				if (bitSeq[k] == 0) l = l+1; //move  down (left)
				else {

				    //move (down and right) right
					l= l+1;
					m = m+1;
				}
				if (l < triangle.length && m < triangle[0].length){ //keep in bounds
					int val = triangle[l][m]; //apply direction

					pathSum += val;
					path[k+1] = val;
				}
				
			}
			
			//Store current maximum sum and current path 
			if (pathSum > maxSum) {
				maxSum = pathSum;
				for (int n = 0; n < path.length; n++) {
					bestPath[n] = path[n];
				}
			}
			
			//reset variables for next sequence
			pathSum =firstVal;
			l=0; m=0;

            increCounter(bitSeq);
            if (isAllZeros(bitSeq)) break;

		}
		
	}
	public static void increCounter(int[] b){
	    int i = b.length-1;
	    while(i>=0 && b[i]==1)b[i--] = 0;
	    if (i>=0)b[i]=1;
    }

    public static boolean isAllZeros(int[]b){
        for (int bit :b) {
            if (bit == 1) return false;
        }
        return true;
    }


}
