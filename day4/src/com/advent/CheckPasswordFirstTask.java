package com.advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CheckPasswordFirstTask {
    private String fileName;
    private int good;
    public CheckPasswordFirstTask(String fileName) throws FileNotFoundException {
        good = 0;
        this.fileName = fileName;
        Scanner sc = new Scanner(new File(fileName));
        while(sc.hasNextLine()){
            String line =sc.nextLine();
            int amount = 0;
            Set<String> set = new HashSet();
            for(String s: line.split(" ")){
                amount++;
                set.add(s);
            }
            if(set.size()==amount) good++;
        }
    }
    public int getGood(){
        return good;
    }
}
