package com.advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class CheckPasswordSecondTask {
    private String fileName;
    private int good;

    public CheckPasswordSecondTask(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            int amount = 0;
            Set<String> set = new HashSet();
            for (String s : line.split(" ")) {
                amount++;
                boolean flag = true;
                for (String p : set) {
                    if (!checkIfWordSatisfyPattern(s, p)) flag = false;
                }
                if (flag) set.add(s);
            }
            if (amount == set.size())
                good++;
        }
    }

    private boolean checkIfWordSatisfyPattern(String word, String pattern) {
        if (word.length() != pattern.length()) return true;
        else {
            List<Character> list = new ArrayList();
            for (char character : word.toCharArray())
                list.add(character);
            for (char character : pattern.toCharArray()) {
                if (list.contains(character))
                    list.remove(list.indexOf(character));
            }

            if (list.isEmpty()) return false;

            else return true;
        }
    }

    public int getGood() {
        return good;
    }

}