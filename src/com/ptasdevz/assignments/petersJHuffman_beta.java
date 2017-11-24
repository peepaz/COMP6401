package com.ptasdevz.assignments;

import java.util.*;

public class petersJHuffman_beta {

    static TreeMap<HuffmanTreeNode, Object> huffmanTree; //stores the characters and their frequency count in  sorted manner

    public static void main(String[] args) {

        huffmanTree = new TreeMap<>();

        huffmanTree.put(new HuffmanTreeNode(13, "d"), null);
        huffmanTree.put(new HuffmanTreeNode(5, "a"), null);
        huffmanTree.put(new HuffmanTreeNode(12, "c"), null);
        huffmanTree.put(new HuffmanTreeNode(45, "f"), null);
        huffmanTree.put(new HuffmanTreeNode(9, "b"), null);
        huffmanTree.put(new HuffmanTreeNode(16, "e"), null);


        generateHuffmanTree();
        Iterator<Map.Entry<HuffmanTreeNode, Object>> iterator = huffmanTree.entrySet().iterator();
        HuffmanTreeNode huffmanTreeRoot = iterator.next().getKey();
        printCode(huffmanTreeRoot);

    }

    private static void generateHuffmanTree() {
        while (true) {
            Iterator<Map.Entry<HuffmanTreeNode, Object>> iterator = huffmanTree.entrySet().iterator();
            HuffmanTreeNode huffmanTreeNode1 = iterator.next().getKey();

            if (huffmanTree.size() != 1) {
                iterator.remove();
                HuffmanTreeNode huffmanTreeNode2 = iterator.next().getKey();
                iterator.remove();
                int sumOfFreq = huffmanTreeNode1.freq + huffmanTreeNode2.freq;
                String chars = huffmanTreeNode1.chStr + huffmanTreeNode2.chStr;
                HuffmanTreeNode huffmanTreeNode = new HuffmanTreeNode(sumOfFreq, chars);
                huffmanTreeNode.left = huffmanTreeNode1;
                huffmanTreeNode.right = huffmanTreeNode2;
                huffmanTree.put(huffmanTreeNode, null);

            } else break;

        }

//        Iterator<Map.Entry<HuffmanTreeNode, Object>> iterator = huffmanTree.entrySet().iterator();
//        HuffmanTreeNode huffmanTreeNode1 = iterator.next().getKey();
//        System.out.println(huffmanTreeNode1.toString());
    }

    public static void printCode(HuffmanTreeNode huffmanTreeNode) {

        if (huffmanTreeNode == null) System.out.println("empty tree");
        else if (huffmanTreeNode.left == null) System.out.printf("%s \n", huffmanTreeNode.chStr);
        else if (huffmanTreeNode.right == null) System.out.printf("%s \n", huffmanTreeNode.chStr);
        else {
            printCodeHelper(huffmanTreeNode.left,"0");
            printCodeHelper(huffmanTreeNode.right,"1");
        }

    }
    private static void printCodeHelper(HuffmanTreeNode huffmanTreeNode, String  codeVal) {

        if (huffmanTreeNode == null) System.out.println("empty tree");
        else if (huffmanTreeNode.left == null) System.out.printf("%s : %s\n", huffmanTreeNode.chStr,codeVal);
        else if (huffmanTreeNode.right == null) System.out.printf("%s : %s \n", huffmanTreeNode.chStr,codeVal);
        else {
            printCodeHelper(huffmanTreeNode.left,codeVal + " " +"0");
            printCodeHelper(huffmanTreeNode.right,codeVal + " " +"1");
        }

    }
}

class HuffmanTreeNode implements Comparable {

    public int freq;
    public String chStr;
    public HuffmanTreeNode left;
    public HuffmanTreeNode right;


    public HuffmanTreeNode(int freq, String chStr){
        this.freq =freq;
        this.chStr = chStr;
    }

    @Override
    public int compareTo(Object o) {
        HuffmanTreeNode huffmanTreeNode = (HuffmanTreeNode) o;
        int val = this.freq - huffmanTreeNode.freq;
        if (val == 0) return 1;
        return val;
    }

    @Override
    public String toString() {
        return "HuffmanTreeNode{" +
                "freq=" + freq +
                ", chStr='" + chStr + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}


