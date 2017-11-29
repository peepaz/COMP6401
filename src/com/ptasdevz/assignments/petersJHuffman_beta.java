package com.ptasdevz.assignments;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class petersJHuffman_beta {

    static TreeMap<HuffmanTreeNodeBeta, Object> huffmanTree; //stores the characters and their frequency count in  sorted manner
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
        Iterator<Map.Entry<HuffmanTreeNodeBeta, Object>> iterator = huffmanTree.entrySet().iterator();
        if (iterator.hasNext()) {
            HuffmanTreeNodeBeta huffmanTreeRoot = iterator.next().getKey();
            getCharCodes(huffmanTreeRoot);
            calFixLenCodingBits();
            calPercentageSavings();
            writeToFileResult();
        }
    }

    private static void calPercentageSavings() {
        perecntageSavings = (1- ((double)huffmanEncTotalBitCount / (double) fixLenEncTotalBitCount)) * 100;
    }

    /**
     * Reads the input file, extracts the different characters with its frequency, and insert each character as a
     * leaf node in the huffman tree.
     */
    private static void getChars(){

        try {

            FileReader in = new FileReader("input8.txt");
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
                else if (ch != -1){
                    numberOfCharInFile++;
                    numberOfDiffCharInFile.put((char) ch,1);
                }
//                else numberOfDiffCharInFile.put((char) ch,1);

                if (ch == -1) break;

            }


            for (Map.Entry<Character, Integer> charNFreq: numberOfDiffCharInFile.entrySet()){
                char c = (char) charNFreq.getKey();
                int freq = charNFreq.getValue();
                huffmanTree.put(new HuffmanTreeNodeBeta(freq, String.valueOf(c)), null);
            }


//            System.out.println(numberOfDiffCharInFile.toString());
//            System.out.println(numberOfCharInFile);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

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

            Iterator<Map.Entry<HuffmanTreeNodeBeta, Object>> iterator = huffmanTree.entrySet().iterator();
            if (iterator.hasNext()) {
                HuffmanTreeNodeBeta huffmanTreeNodeBeta1 = iterator.next().getKey();


                if (huffmanTree.size() != 1) {
                    iterator.remove();
                    HuffmanTreeNodeBeta huffmanTreeNodeBeta2 = iterator.next().getKey();
                    iterator.remove();

                    //merge
                    int sumOfFreq = huffmanTreeNodeBeta1.freq + huffmanTreeNodeBeta2.freq;
                    String chars = huffmanTreeNodeBeta1.chStr + huffmanTreeNodeBeta2.chStr;

                    //new branch
                    HuffmanTreeNodeBeta huffmanTreeNodeBeta = new HuffmanTreeNodeBeta(sumOfFreq, chars);
                    huffmanTreeNodeBeta.left = huffmanTreeNodeBeta1;
                    huffmanTreeNodeBeta.right = huffmanTreeNodeBeta2;

                    //insert new branch
                    huffmanTree.put(huffmanTreeNodeBeta, null);

                } else break;
            }else break;

        }
    }

    /**
     * Get the prefix code of each character in the huffman tree
     * @param huffmanTreeNodeBeta
     */
    public static void getCharCodes(HuffmanTreeNodeBeta huffmanTreeNodeBeta) {

        if (huffmanTreeNodeBeta == null) charAndCode += ("empty tree\n");
        else if (huffmanTreeNodeBeta.left == null) charAndCode += String.format("%s \n", huffmanTreeNodeBeta.chStr);
        else if (huffmanTreeNodeBeta.right == null) charAndCode += String.format("%s \n", huffmanTreeNodeBeta.chStr);
        else {
            getCharCodesHelper(huffmanTreeNodeBeta.left,"0",1);
            getCharCodesHelper(huffmanTreeNodeBeta.right,"1",1);
        }
    }

    /**
     * A helper to get the prefix code of each character in the huffman tree
     * @param huffmanTreeNodeBeta
     * @param bitStr
     */
    private static void getCharCodesHelper(HuffmanTreeNodeBeta huffmanTreeNodeBeta, String  bitStr, int bitCount) {

        if (huffmanTreeNodeBeta == null) charAndCode += String.format("empty tree\n");
        else if (huffmanTreeNodeBeta.left == null){
            charAndCode += String.format("%s : %s\n", huffmanTreeNodeBeta.chStr,bitStr);
            huffmanEncTotalBitCount += huffmanTreeNodeBeta.freq * bitCount;
        }
        else if (huffmanTreeNodeBeta.right == null){
            charAndCode += String.format("s : %s \n", huffmanTreeNodeBeta.chStr,bitStr);
            huffmanEncTotalBitCount += huffmanTreeNodeBeta.freq * bitCount;
        }
        else {
            bitCount++;
            getCharCodesHelper(huffmanTreeNodeBeta.left,bitStr + " " +"0",bitCount);
            getCharCodesHelper(huffmanTreeNodeBeta.right,bitStr + " " +"1",bitCount);
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
            out.printf(charAndCode);
            out.printf("Bits required for fix-length encoding: %d\n", fixLenEncTotalBitCount);
            out.printf("Bits required for huffman encoding: %d\n", huffmanEncTotalBitCount);
            out.printf("Percentage Savings: %3.1f",perecntageSavings);
            out.flush();

        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        }
    }
}

class HuffmanTreeNodeBeta implements Comparable {

    public int freq;
    public String chStr;
    public HuffmanTreeNodeBeta left;
    public HuffmanTreeNodeBeta right;


    public HuffmanTreeNodeBeta(int freq, String chStr){
        this.freq =freq;
        this.chStr = chStr;
    }

    @Override
    public int compareTo(Object o) {
        HuffmanTreeNodeBeta huffmanTreeNodeBeta = (HuffmanTreeNodeBeta) o;
        int val = this.freq - huffmanTreeNodeBeta.freq;
        if (val == 0) return 1;
        return val;
    }

    @Override
    public String toString() {
        return "HuffmanTreeNodeBeta{" +
                "freq=" + freq +
                ", chStr='" + chStr + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}


