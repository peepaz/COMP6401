package com.ptasdevz.classwork;

/**
 * Created by jason on 14/10/2017.
 */
public class LongestIncreasingSubDy {

   static int[] A = {35,31,22,30,27,29,21,31,24,28};
	static int[] L = new int[A.length];
	static int[] E =  new int[A.length];
	
	public static void main(String[] args) {

		for (int i = 0; i < L.length; i++) {
			L[i] =1;
			E[i] =i;
		}
		
		int max = getLis();
		System.out.printf("A: ");
		for (int i = 0; i < A.length; i++) {
			System.out.printf("%d ",A[i]);
		}
		System.out.println();
		System.out.printf("L: ");
		for (int i = 0; i < L.length; i++) {
			System.out.printf("0%d ",L[i]);
		}
		System.out.println();
		System.out.printf("E: ");
		for (int i = 0; i < E.length; i++) {
			System.out.printf("0%d ",E[i]+1);
		}
		
		System.out.printf("\nmax is: %d\n",max);
		for (int i = 0; i < L.length; i++) {
			if (max ==L[i]) {
				System.out.printf("Sequence: ");
				printPath(i);
				break;
			}
		}
		
	}
	
	public static int getLis(){
		int max = 1;
		for (int i = 1; i < A.length; i++) {
			for (int j = 0; j < i; j++) {
				if (A[i] > A[j]){
					int res = max(1+L[j], L[i]);
					if (res == 1+L[j] && res != L[i]){
						E[i] = j;
					}
					else {
						E[i] = E[i];
					}
					L[i] = res;

				}
			}
		}
		
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
	
	public static void printPath(int idxHead){
		
		if (idxHead == E[idxHead]) System.out.printf("%d ", A[idxHead]);
		else{
			printPath(E[idxHead]);
			System.out.printf("%d ", A[idxHead]);
		}
		
	}
}
