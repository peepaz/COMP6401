package com.ptasdevz.assignments;

import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Name: Jason Peters
 * ID:811100537
 * Method of solution:
 *
 */
public class petersJHuffman {

    static TreeMap<HuffmanTreeNode, Object> huffmanTree; //stores the characters and their frequency count in  sorted manner
    static HashMap<Character,Integer> numberOfDiffCharInFile = new HashMap<>();

    static int numberOfCharInFile =0;
    static int fixLenEncBitSize = 0;
    static int fixLenEncTotalBitCount= 0;
    static int huffmanEncTotalBitCount = 0;
    static double perecntageSavings=0.0;
    static String charAndCode = "";

    public static void main(String[] args) {

        huffmanTree = new TreeMap<>();
        getChars();
        generateHuffmanTree();
        Iterator<Map.Entry<HuffmanTreeNode, Object>> iterator = huffmanTree.entrySet().iterator();
        if (iterator.hasNext()) {
            HuffmanTreeNode huffmanTreeRoot = iterator.next().getKey();
            getCharCodes(huffmanTreeRoot);
            calFixLenCodingBits();
            calPercentageSavings();
            writeToFileResult();
        }
    }

    /**
     * Calculate the percentage savings using huffman encoding
     */
    private static void calPercentageSavings() {
        perecntageSavings = (1- ((double)huffmanEncTotalBitCount / (double) fixLenEncTotalBitCount)) * 100;
    }

    /**
     * Reads the input file, extracts the different characters with its frequency, and insert each character as a
     * leaf node in the huffman tree.
     */
    private static void getChars(){

        try {

            FileReader in = new FileReader("input.txt");
            int ch;

            //get characters and their frequency
            while (true){
                ch = in.read();
                Object charSaved = numberOfDiffCharInFile.get((char)ch);
                if (charSaved != null) {
                    int val = (int) charSaved;
                    val++;
                    numberOfDiffCharInFile.put((char) ch,val);
                    numberOfCharInFile++;
                }

                else numberOfDiffCharInFile.put((char) ch,1);

                if (ch == -1) break;

            }


            for (Map.Entry<Character, Integer> charNFreq: numberOfDiffCharInFile.entrySet()){
                char c = (char) charNFreq.getKey();
                int freq = charNFreq.getValue();
                huffmanTree.put(new HuffmanTreeNode(freq, String.valueOf(c)), null);
            }

        }catch (Exception e){
//            e.printStackTrace();
        }
    }

    /**
     * Calculate the minimum number of bits required to store file as fix-length
     */
    private static void calFixLenCodingBits() {
        int size = numberOfDiffCharInFile.size();
        int bitSize = 0;
        while (size > 0){
            size = size/2;
            bitSize++;
        }
        fixLenEncTotalBitCount =  bitSize * numberOfCharInFile;
    }

    /**
     * Creates the huffman encoding tree
     */
    private static void generateHuffmanTree() {
        while (true) {

            Iterator<Map.Entry<HuffmanTreeNode, Object>> iterator = huffmanTree.entrySet().iterator();
            if (iterator.hasNext()) {
                HuffmanTreeNode huffmanTreeNode1 = iterator.next().getKey();

                if (huffmanTree.size() != 1) {
                    iterator.remove();
                    HuffmanTreeNode huffmanTreeNode2 = iterator.next().getKey();
                    iterator.remove();

                    //merge
                    int sumOfFreq = huffmanTreeNode1.freq + huffmanTreeNode2.freq;
                    String chars = huffmanTreeNode1.chStr + huffmanTreeNode2.chStr;

                    //new branch
                    HuffmanTreeNode huffmanTreeNode = new HuffmanTreeNode(sumOfFreq, chars);
                    huffmanTreeNode.left = huffmanTreeNode1;
                    huffmanTreeNode.right = huffmanTreeNode2;

                    //insert new branch
                    huffmanTree.put(huffmanTreeNode, null);

                } else break;
            }else break;

        }
    }

    /**
     * Get the prefix code of each character in the huffman tree
     * @param huffmanTreeNode
     */
    public static void getCharCodes(HuffmanTreeNode huffmanTreeNode) {

        if (huffmanTreeNode == null) charAndCode += ("empty tree\n");
        else if (huffmanTreeNode.left == null) charAndCode += String.format("%s \n", huffmanTreeNode.chStr);
        else if (huffmanTreeNode.right == null) charAndCode += String.format("%s \n", huffmanTreeNode.chStr);
        else {
            getCharCodesHelper(huffmanTreeNode.left,"0",1);
            getCharCodesHelper(huffmanTreeNode.right,"1",1);
        }
    }

    /**
     * A helper to get the prefix code of each character in the huffman tree
     * @param huffmanTreeNode
     * @param bitStr
     */
    private static void getCharCodesHelper(HuffmanTreeNode huffmanTreeNode, String  bitStr, int bitCount) {

        if (huffmanTreeNode == null) charAndCode += String.format("empty tree\n");
        else if (huffmanTreeNode.left == null){
//            charAndCode += String.format("'%s' -> %s\n", huffmanTreeNode.chStr, bitStr);
//            charAndCode += String.format("%s (%-2d): %s\n", huffmanTreeNode.chStr, huffmanTreeNode.freq, bitStr);
//            if (bitCount < 4) {
                if (huffmanTreeNode.chStr.charAt(0) == '\n') {
                    charAndCode += String.format("%s -> %s\n", "\\n", bitStr);
                } else if (huffmanTreeNode.chStr.charAt(0) == '\r') {
                    charAndCode += String.format("%s -> %s\n", "\\r", bitStr);
                } else if (huffmanTreeNode.chStr.charAt(0) == -1) {
                    charAndCode += String.format("%s -> %s\n", "-1", bitStr);
                } else charAndCode += String.format("%s -> %s\n", huffmanTreeNode.chStr, bitStr);
//            }
                huffmanEncTotalBitCount += huffmanTreeNode.freq * bitCount;
        }
        else if (huffmanTreeNode.right == null){
//            charAndCode += String.format("%s (%-2d): %s\n", huffmanTreeNode.chStr, huffmanTreeNode.freq, bitStr);
//            if (bitCount < 4) {
                if (huffmanTreeNode.chStr.charAt(0) == '\n') {
                    charAndCode += String.format("%s -> %s\n", "\\n", bitStr);
                } else if (huffmanTreeNode.chStr.charAt(0) == '\r') {
                    charAndCode += String.format("%s -> %s\n", "\\r", bitStr);
                } else if (huffmanTreeNode.chStr.charAt(0) == -1) {
                    charAndCode += String.format("%s -> %s\n", "-1", bitStr);
                } else charAndCode += String.format("%s -> %s\n", huffmanTreeNode.chStr, bitStr);
//            }

            huffmanEncTotalBitCount += huffmanTreeNode.freq * bitCount;
        }
        else {
            bitCount++;
            getCharCodesHelper(huffmanTreeNode.left,bitStr + " " +"0",bitCount);
            getCharCodesHelper(huffmanTreeNode.right,bitStr + " " +"1",bitCount);
        }

    }

    /**
     * Saves results to file
     */
    private static void writeToFileResult() {

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));
            out.printf("Total characters in file: %d\n", numberOfCharInFile);
            out.printf("Number of unique characters in file: %d\n", numberOfDiffCharInFile.size());
            out.printf("char -> prefix_code\n");
            out.println(charAndCode);
            out.printf("Bits required for fix-length encoding of file: %d\n", fixLenEncTotalBitCount);
            out.printf("Bits required for huffman encoding of file: %d\n", huffmanEncTotalBitCount);
            out.printf("Percentage Savings: %3.1f",perecntageSavings);
            out.flush();

        } catch (FileNotFoundException e) {
//            e.printStackTrace();
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


