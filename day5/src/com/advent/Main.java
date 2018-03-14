package com.advent;

public class Main {

    public static void main(String[] args) {
        String filName = "/home/karolina/Documents/AdventOfCode2017/day5/date.txt";
        MazelFirstTask mazelFirstTask = new MazelFirstTask(filName);
        System.out.println(mazelFirstTask.amountOfSteps());
        MazelSecondTask mazelSecondTask = new MazelSecondTask(filName);
        System.out.print(mazelSecondTask.amountOfSteps());
    }
}
