package com.advent;

public class Main {

    public static void main(String[] args) {
        String fileName = "/home/karolina/Documents/AdventOfCode2017/day7/date.txt";
        Structure structure = new Structure(fileName);
        System.out.println(structure.findNameOfBottomDisk());
        System.out.println(structure.balanceWeight());

    }
}