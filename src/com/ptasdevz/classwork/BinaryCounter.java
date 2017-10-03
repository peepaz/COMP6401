package com.ptasdevz.classwork;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BinaryCounter {

	public static void main(String[] args) {
		
	int[] bitHolder = new int[30];
	initBitSeq(bitHolder);
	String startTime = getCurrentTimeStamp();
	addOne(bitHolder);
	String endTime = getCurrentTimeStamp();
	 System.out.println("start: " + startTime + " " + "end: " + endTime);
	
	
	}
	
	public static void initBitSeq(int[] bitSeq){
		
		for (int i =0; i< bitSeq.length; i++)
			bitSeq[i]=0;
	}
	
public static void addOne(int [] bitSeq){
		
		int zerosCount =0;
		while (true){
			for (int i= bitSeq.length-1; i >=0; i--){
				
				if (bitSeq[i] == 0) {
					bitSeq[i] = 1;
					zerosCount =0;// reset zero counter
					break;
				}
				else {
					bitSeq[i] = 0;
					zerosCount++;
				}
			}
			if (zerosCount == bitSeq.length) break;// binary counter has restarted
			for (int j=0; j<bitSeq.length; j++){
				System.out.print(bitSeq[j]);
			}
			System.out.println();

		}
		
	}
	public static String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}

}
