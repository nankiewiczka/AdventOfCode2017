package com.advent;

public class Main {

    public static void main(String[] args) {
        String fileName = "/home/karolina/Documents/AdventOfCode2017/day6/date.txt";
            Block b = new Block(fileName);
            System.out.println(b.countAmountOfRedistributionCycles());
            System.out.println(b.getNumberOfCyclesInInfiniteLoop());

    }
}