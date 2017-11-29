package com.ptasdevz.assignments;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by jason on 28/09/2017.
 */
public class petersjThesaurus {

    public static void main (String[] args){

        try {
            //noinspection Since15,Since15
            Scanner in = new Scanner (new FileInputStream("input71.txt"));

            TreeMap<Integer,TreeMap<String,String>> words =  new TreeMap<>();

            int synonymListPos = 0;
            //noinspection Since15
            while (in.hasNextLine()) {

                //noinspection Since15
                String wordListStr = in.nextLine();
                //noinspection Since15
                String[]wordList = wordListStr.trim().replaceAll("\\s+"," ").split(" ");
                HashMap<Integer,Integer> listOfPosToMerge = new HashMap<>();
                TreeMap<String,String> synonymList = new TreeMap<>();

                //insert each word into thesaurus
                for (int i=0; i<wordList.length; i++) {
                    
                    String synonym = wordList[i];
                    if (synonym != null) synonym = synonym.toLowerCase();
                    synonymList.put(synonym,"");

                    //Check to see if word exist already
                    int keyCount =0;
                    for (Map.Entry wordEntry : words.entrySet()) {
                        TreeMap synonymList1 = (TreeMap) wordEntry.getValue();
                        boolean isSetSynonym =  synonymList1.containsKey(synonym);

                        //If the word exist, get the position to add to a list of positions to be merged
                        int key = (Integer) wordEntry.getKey();
                        if (isSetSynonym  && !listOfPosToMerge.containsKey(key)) listOfPosToMerge.put(key,key);
                        keyCount++;
                    }

                }
                //Merge list positions
                 if (listOfPosToMerge.size() > 0){

                     int firstPos = listOfPosToMerge.entrySet().iterator().next().getValue();
                     TreeMap wordsInFirstMergePos = words.get(firstPos);

                     for (Iterator<Integer> it = listOfPosToMerge.keySet().iterator(); it.hasNext(); ) {

                        it.next(); //advance cursor to next position;

                        if (it.hasNext() ) {
                            int nextPos = it.next();
                            TreeMap wordsInNextPos = words.get(nextPos);
                             wordsInFirstMergePos.putAll(wordsInNextPos);
                             wordsInNextPos.clear();
                        }

                     }
                     wordsInFirstMergePos.putAll(synonymList);

                     }
                 else {
                     words.put(synonymListPos,synonymList); //create a new list
                 }
                synonymListPos++;
            }

            writeOutput(words);

        } catch (FileNotFoundException e) {
//            e.printStackTrace();

        }
    }

    public static void writeOutput(TreeMap words){

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));
            out.printf("words%-25ssynonyms\n\n","");
            for (Object wordsEntryObj : words.entrySet()){
                Map.Entry<Integer,TreeMap> wordsEntry = (Map.Entry) wordsEntryObj;

                TreeMap synonymList = (TreeMap)  wordsEntry.getValue();

                int count =0;
                for (Object synonymEntryObj : synonymList.entrySet()) {
                    Map.Entry<String,String> synonymEntry = (Map.Entry) synonymEntryObj;
                    String synonym = (String) synonymEntry.getKey();
                    if (count == 0) {
                        out.printf("%-30s", synonym);
                    }
                    else {
                        out.printf("%s\t", synonym);
                    }
                    out.flush();
                    count++;
                }
                //noinspection Since15
                out.printf("\n");
                out.flush();

            }


        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        }
    }
}



