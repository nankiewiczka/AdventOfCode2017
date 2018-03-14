package com.advent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String fileName = "/home/karolina/Documents/AdventOfCode2017/day1/data.txt";
    private static List<Integer> list = readIntegerFromData(fileName);
    public static void main(String[] args) {
        System.out.println(sumAllDigitsMatchesNext(list));
        System.out.println(sumAllDigitsMatchesHalfwayDigit(list));
    }

    private static int sumAllDigitsMatchesNext(List<Integer> integerList) {
        int sum = 0;
        try {
           for(int i=0; i<integerList.size()-1; i++){
               if(integerList.get(i) == integerList.get(i+1)) sum+=integerList.get(i);
           }
           if(integerList.get(integerList.size()-1)== integerList.get(0)) sum+=integerList.get(0);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return sum;
    }

    private static int sumAllDigitsMatchesHalfwayDigit(List<Integer> integerList) {
        int sum = 0;
        try {
            int i = 0;
            for (; i < integerList.size() / 2; i++) {
                if (integerList.get(i) == integerList.get(i + integerList.size() / 2)) sum += integerList.get(i);
            }
            int j = 0;
            for (; i < integerList.size(); i++, j++) {
                if (integerList.get(i) == integerList.get(j)) sum += integerList.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sum;
    }

    private static List<Integer> readIntegerFromData(String fileName) {
        List<Integer> list = new ArrayList();
        try {
            Scanner input = new Scanner(new File(fileName));
            input.useDelimiter("");
            while (input.hasNextInt()) {
                list.add(input.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
