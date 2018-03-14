package com.advent;

import java.io.FileNotFoundException;

public class Main {
    private static String fileName = "/home/karolina/Documents/AdventOfCode2017/day4/date.txt";

    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/home/karolina/Documents/AdventOfCode2017/day4/date.txt";
        CheckPasswordFirstTask cp = new CheckPasswordFirstTask(fileName);
        System.out.println(cp.getGood());
        CheckPasswordSecondTask  cp2 = new CheckPasswordSecondTask (fileName);
        System.out.println(cp2.getGood());
    }
}
