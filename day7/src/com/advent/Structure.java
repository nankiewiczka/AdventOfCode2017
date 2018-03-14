package com.advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Structure {
    private String bottom;
    private Map<String, List<String>> disksLayout; // disc and name's of disc on him
    private Map<String, Integer> disksWeight; // weight of all discs


    public Structure(String fileName) {
        // read data
        bottom = "";
        disksLayout = new HashMap<>();
        disksWeight = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int positionInLineOfData = -1;
                String key = "";
                int weight = 0;
                List<String> list = new LinkedList();
                for (String splitString : line.split("\\s+")) {
                    positionInLineOfData++;
                    switch (positionInLineOfData) {
                        case 0: {
                            key = splitString;
                            break;
                        }
                        case 1: {
                            splitString = splitString.substring(1, splitString.length() - 1);
                            weight = Integer.parseInt(splitString);
                        }
                        case 2: {
                            break;
                        }
                        default: {
                            splitString = deleteComa(splitString);
                            list.add(splitString);
                        }
                    }
                }
                disksLayout.put(key, list);
                disksWeight.put(key, weight);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String findNameOfBottomDisk() {
        /// towerDiscs doesn't contain bottom
        List<String> allDisks = new LinkedList<>(); // all names of discs
        List<String> towerDiscs = new LinkedList<>(); // disc's name which have another disc above
        allDisks.addAll(disksLayout.keySet());

        for (String nameOfDisc : disksLayout.keySet()) {
            towerDiscs.addAll(disksLayout.get(nameOfDisc));
        }

        for (String nameOfDisc : allDisks) {
            if (!towerDiscs.contains(nameOfDisc)) bottom = nameOfDisc;
        }
        return bottom;
    }

    private String deleteComa(String s) {
        if (s.endsWith(",")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;

    }

    private String findWhatToBalance(String s) {
        // return name of disc that isn't balanced
        String originalDiskName = s;
        String nameOfDiscAboveToBalance = "";
        boolean flag = true;
        for(String discName: disksLayout.get(s)){
            if(!isAboveBalanced(discName)){
                flag = false;
                originalDiskName = discName;
                nameOfDiscAboveToBalance = findWhatToBalance(discName);
            }
        }
        if(flag) return originalDiskName;
        else return nameOfDiscAboveToBalance;
    }

    private int weight(String c) {
        // weight = weight of discs + disc on him
        int weightOfDisc = disksWeight.get(c);
        if (!disksLayout.get(c).isEmpty())
            for (String nameOfDisk : disksLayout.get(c)) weightOfDisc += weight(nameOfDisk);

        return weightOfDisc;
    }

    private boolean isAboveBalanced(String s) {
        List<String> children = disksLayout.get(s);
        List<Integer> weightOfChildrenDisks = new LinkedList<>();
        if (!children.isEmpty()) {
            for (String diskName : children)
                weightOfChildrenDisks.add(weight(diskName));
        }
        else
            weightOfChildrenDisks.add(weight(s));

        // check if all have the same value
        Set<Integer> set = new HashSet<>();
        for (int weight : weightOfChildrenDisks)
            set.add(weight);

        if (set.size() == 1)
            return true;
        else
            return false;
    }

    public int balanceWeight() {
        int weightNeedToBe = 0;
        String nameOfDiskToBalance = findWhatToBalance(findNameOfBottomDisk());  // start from bottom
        Map <String, Integer> disksMap = new HashMap<>();
        Set <Integer> diskSet = new HashSet<>();
        for(String diskName: disksLayout.get(nameOfDiskToBalance))
            disksMap.put(diskName, weight(diskName));

        for(int weightDisk: disksMap.values())
            diskSet.add(weightDisk);

        // there should be two elements in set
        String keyMin="";
        String keyMax="";

        int min = Collections.min(diskSet);
        int max = Collections.max(diskSet);
        for(String c: disksMap.keySet()){
            if(disksMap.get(c) == min)
                keyMin=c;
            else
                keyMax=c;
        }
        int diff = max-min;
        int minA = 0;
        int maxA = 0;

        for(int i: disksMap.values()){
            // check which value is only once
            if(i==min)minA++;
            else maxA++;
        }

        if(minA>1) { // minA>maxA so value-keyMax needs to be change
            weightNeedToBe = disksWeight.get(keyMax)-diff;
        }
        else{
            weightNeedToBe = disksWeight.get(keyMin)+diff;
        }
        return weightNeedToBe;

    }
}
