package com.ptasdevz.classwork;

public class DisjointSets {

    public static void main (String [] args){

        int P[] = new int[10];
        int R[] = new int[10];

        for (int i = 0; i < P.length; i++) {
            System.out.printf("%d ",P[i]);
//            System.out.printf("%d ",R[i]);
        }
        System.out.printf("\n");

        union(P,R,1,2);
        union(P,R,3,4);
        union(P,R,5,6);
        union(P,R,7,8);
        union(P,R,2,4);
        union(P,R,8,9);
        union(P,R,4,8);
        union(P,R,6,4);

        System.out.println(find(P,5));
        System.out.println(find(P,1));
        System.out.println(find(P,7));
        System.out.println(find(P,9));

        for (int i = 0; i < P.length; i++) {
            System.out.printf("%d ",P[i]);
//            System.out.printf("%d ",R[i]);
        }
    }

    public static void union(int [] P, int[] R, int x, int y){

        int xRoot = find(P,x);
        int yRoot = find(P,y);

        if (R[xRoot] < R[yRoot]){ // make y's root the parent of x
            P[x] = yRoot;
        }
        else if (R[yRoot] < R[xRoot]){ //make x's root the parent of y
            P[y] = xRoot;
        }
        else { //if same set the second root as the parent of the first
            P[x] = yRoot;
            R[yRoot]++;

        }
    }

    public static int find(int[] P , int x){
        if (P[x] == 0) return x;
        P[x] = find(P,P[x]); //apply path compression by updating the root of the notde
        return P[x];
    }
}
