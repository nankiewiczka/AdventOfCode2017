package com.nankiewicz;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    private static String fileName = "/home/karolina/Documents/AdventOfCode2017/day12/date.txt";
    private static Map<Integer, List<Integer>> readedDate;
    public static void main(String[] args) {
        readedDate = readDateToMap();
        System.out.println(groupOfProgramContainsID(0).size());

        Set<Set<Integer>> allGroups = new HashSet<>();
        for(Integer i : readedDate.keySet()) {
            allGroups.add(groupOfProgramContainsID(i));
        }

        System.out.println(allGroups.size());

    }

    private static Map<Integer, List<Integer>> readDateToMap() {
        Map<Integer, List<Integer>> data = new HashMap<>();
        try {
            Scanner input = new Scanner(new File(fileName));
            while(input.hasNextLine()) {
                String[] array = input.nextLine().split(" ");
                List<Integer> listOfID = new LinkedList<>();
                for(int i=2; i<array.length; i++) {
                    listOfID.add(Integer.parseInt(deleteComma(array[i])));
                }
                data.put(Integer.parseInt(array[0]), listOfID);
            }
        } catch(IOException e) {
            System.out.println("Not find a file");
        }

        return data;

    }

    private static String deleteComma(String word) {
        String wordToChange = word;
        if(word.endsWith(",")) {
            wordToChange = word.substring(0, word.length()-1);
        }
        return wordToChange;
    }

    private static Set<Integer> groupOfProgramContainsID(int ID) {
        Set<Integer> programWithID = new HashSet<>();
        programWithID.addAll(readedDate.get(ID));
        boolean isUnchanged;

        do {
            isUnchanged = false;
            Set<Integer> temporarySet = new HashSet<>(programWithID);
            for(Integer i : programWithID) {
                if (temporarySet.addAll(readedDate.get(i)))
                    isUnchanged = true;
            }
            programWithID= new HashSet<>(temporarySet);
        } while(isUnchanged);

        return programWithID;
    }

}
