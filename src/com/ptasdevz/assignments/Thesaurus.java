package com.ptasdevz.assignments;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by jason on 28/09/2017.
 */
public class Thesaurus {

    public static void main (String[] args){
        try {
            //noinspection Since15,Since15
            Scanner in = new Scanner (new FileInputStream("input.txt"));
            Map<String,FreqAndPos> wordFreqCountandPos = new TreeMap<>();
            ArrayList<ArrayList<String>> words =  new ArrayList<>();
            int listPos = 0;
            //noinspection Since15
            while (in.hasNextLine()) {

                //noinspection Since15
                String wordListStr = in.nextLine();
                //noinspection Since15
                String[]wordList = wordListStr.split(" ");
                ArrayList<String> synonymList = null;
                synonymList = new ArrayList<>();

//
//                System.out.println(wordList[0]);
//                System.exit(0);

                for (String synonym : wordList) {

                    if (synonym != null) synonym = synonym.toLowerCase();
                    insertInOrder(synonym,synonymList);

                    boolean isExistWord = wordFreqCountandPos.containsKey(synonym);

                    if (isExistWord) {
                        wordFreqCountandPos.get(synonym).freq++;
                        wordFreqCountandPos.get(synonym).listPos.add(listPos);
                    } else {
                        FreqAndPos freqAndPos = new FreqAndPos();
                        freqAndPos.freq++;
                        freqAndPos.listPos.add(listPos);
                        wordFreqCountandPos.put(synonym, freqAndPos);

                    }
                }
                words.add(synonymList);
                listPos++;
            }

            for (Map.Entry entry: wordFreqCountandPos.entrySet()){

                FreqAndPos freqAndPos = (FreqAndPos) entry.getValue();
                if (freqAndPos.freq > 1){
                    mergeSynList(words,freqAndPos);
                }
            }

            System.out.println(words);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void mergeSynList( ArrayList<ArrayList<String>> synonymList, FreqAndPos freqAndPos){

        for (int pos = 0; pos < freqAndPos.listPos.size(); pos++) {

            ArrayList<String> synlist1 =  synonymList.get(freqAndPos.listPos.get(pos));

            if (pos + 1 < freqAndPos.listPos.size()){
                int nextPos = freqAndPos.listPos.get(pos + 1);
                ArrayList<String> synlistNext =  synonymList.get(nextPos);

                for (int  syn1pos =0; syn1pos<synlist1.size(); syn1pos++) {
                    String synlist1Val = synlist1.get(syn1pos);
                    if (!synlistNext.contains(synlist1Val)){
                        insertInOrder(synlist1Val,synlistNext);
                    }
                }
                synlist1.clear();

            }
        }
    }

    public static void insertInOrder(String s, ArrayList<String> synonyms){

        int insPos = 0;
        for(int i=0; i<synonyms.size(); i++){
            if (s.compareToIgnoreCase(synonyms.get(i)) > 0){
                insPos = i+1;
                break;
            }
            else continue;

        }
        synonyms.add(insPos,s);
    }

}

class FreqAndPos {

    public int freq = 0;
    public ArrayList<Integer> listPos;

    public FreqAndPos(){
        this.listPos = new ArrayList<>();
    }

}


