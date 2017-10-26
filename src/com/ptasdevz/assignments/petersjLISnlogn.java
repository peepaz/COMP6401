package com.ptasdevz.assignments;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class petersjLISnlogn {

    static int[] A;

    static ArrayList<Stack<ElementNode>> heaps;

    public static void main(String[] args){

        int ch;
        String number = "";
        int [] tempStorage = new int[10000];
        try {

            FileReader in = new FileReader("input.txt");
            int numberCount = 0;
            while (true) {

                ch = in.read();
                if (ch >= '0' && ch <= '9') number += (char) ch; //build integers
                else {
                    if (number.compareTo("") != 0) {
                        int n = Integer.parseInt(number);
                        tempStorage[numberCount] = n;
                        numberCount++;
                    }
                    number = "";
                }
                if (ch == -1)break;
            }

            A = new int[numberCount];
            for (int i = 0; i <A.length; i++) {
                A[i] = tempStorage[i];
            }

            heaps = new ArrayList<>();
            long startTime = System.nanoTime();
            int max = getLIS();
            long estimatedTimeNano = System.nanoTime() - startTime;
            long estimatedTimeMillSec = TimeUnit.NANOSECONDS.toMillis(estimatedTimeNano);
            estimatedTimeNano = (estimatedTimeNano - TimeUnit.MILLISECONDS.toNanos(estimatedTimeMillSec));

            ElementNode node;
            if (heaps.size() > 0) {
                 node =heaps.get(heaps.size()-1).peek();
                writeToFileResult(max,estimatedTimeMillSec,estimatedTimeNano,node);

            }

        }catch (Exception e){
//            e.printStackTrace();
        }

    }

    public static int getLIS(){

        for (int i = 0; i < A.length; i++) {

            if (i==0){ //create first heap and push first value onto it
                Stack<ElementNode> heap = new Stack<>();
                ElementNode elementNode = new ElementNode();
                elementNode.value = A[i];
                elementNode.pointer = null;
                heap.push(elementNode);
                heaps.add(heap);
            }
            else {

                // locate next heap to place next value
                int heapPos = binSearchHeap(A[i]);
                ElementNode elementNode = new ElementNode();

                if (heapPos != -1) { //add to existing heap
                    Stack<ElementNode> heap = heaps.get(heapPos);
                    if (heapPos == 0) {
                        elementNode.value = A[i];
                        elementNode.pointer = null;
                    } else {
                        Stack<ElementNode> prevHeap = heaps.get(heapPos-1);
                        elementNode.value= A[i];
                        elementNode.pointer= prevHeap.peek();
                    }
                    heap.push(elementNode);
                }
                else { //start new heap
                    heapPos = heaps.size()-1;
                    Stack<ElementNode> heap = new Stack<>();
                    Stack<ElementNode> prevHeap = heaps.get(heapPos);
                    elementNode.value = A[i];
                    elementNode.pointer = prevHeap.peek();
                    heap.push(elementNode);
                    heaps.add(heap);

                }
            }
        }

        return heaps.size();
    }

    public static int binSearchHeap(int key){

        int s = 0;
        int e = heaps.size()-1;
        while (s <= e){
            int mid = (s + e)/2;
            int heapTopVal = heaps.get(mid).peek().value;
            if (key > heapTopVal) s = mid +1;
            else e = mid-1;
        }
        if (s < heaps.size()) return s;
        return -1;//no heap is found
    }

    private static void writeToFileResult(int max, long estimatedTimeMilliSec, long estimatedTimeNano, ElementNode node) {

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));
            out.printf("Length: %d\n",max);
            out.printf("LIS: ");
            out.write(printLis(node));
            out.printf("\nTotal Execution Time: %d.%s millisecond(s)\n",estimatedTimeMilliSec,
                    String.valueOf(estimatedTimeNano).substring(0,3));
            out.flush();

        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        }
    }

    public static String printLis(ElementNode head){

        if (head.pointer == null ) return String.valueOf(head.value);
            return printLis(head.pointer) + " "+ head.value;
    }
}

class ElementNode {

    int value;
    ElementNode pointer;
}

