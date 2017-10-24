package com.ptasdevz.classwork;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**,
 * Created by jason on 21/10/2017.
 */
public class LongestIncreasingSequenceDyNLogN {

    static int[] A = {2, 15, 3, 7, 8, 6, 18,3,3,3,3,3,3,3,3,4,4,7,9,9,100,4,67,67,89,10,79,34};

//    static int[] A = {35,31,22,30,27,29,21,32,24,28};
//    static int[] A = {5 ,2,  4,  9,  10,  1,  8,  13,  12,  6,  3,  7,  11};
//    static int[] A = {2, 15, 3, 7, 8, 6, 18};

    static ArrayList<Stack<ElementNode>> heaps;

    public static void main(String[] args){
       heaps = new ArrayList<>();
        long millisStrt = System.currentTimeMillis();
        int lisSize = getLis();
        long millisEnd = System.currentTimeMillis();

        System.out.printf("%d \n",lisSize);
       printLis(heaps.get(heaps.size()-1).peek());
        System.out.printf("\nTime in millis: %d\n",millisEnd - millisStrt);

    }

    public static int getLis(){

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
            if (key >= heapTopVal) s = mid +1;
            else e = mid-1;
        }
        if (s < heaps.size()) return s;
        return -1;//no heap is found
    }

    public static void printLis(ElementNode head){

       if (head.pointer == null ) System.out.printf("%d ",head.value);
       else {
           printLis(head.pointer);
           System.out.printf("%d ",head.value);
       }
    }

}

class ElementNode {

    int value;
    ElementNode pointer;
}
