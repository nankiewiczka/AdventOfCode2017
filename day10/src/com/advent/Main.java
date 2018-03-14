package com.advent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int MAX_SIZE = 256;
    private static String fileName = "/home/karolina/Documents/AdventOfCode2017/day10/date.txt";
    private static ArrayList<Integer> lengths;
    private static ArrayList<Integer> list;
    public static void main(String[] args) {
        lengths = readDateToArray();
        if(lengths != null) {
            list = createListFrom0To255();
            ArrayList<Integer> finalList = expectedList();
            System.out.println(finalList.get(0)*finalList.get(1));
        }
    }

    public static ArrayList<Integer> readDateToArray() {
        ArrayList<Integer> readList = new ArrayList<>();
        try {
            Scanner input = new Scanner(new File(fileName));
            for(String length : input.nextLine().split(","))
                readList.add(Integer.parseInt(length));

            return readList;
        } catch (IOException e) {
            return null;
        }
    }

    public static ArrayList<Integer> createListFrom0To255() {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<MAX_SIZE; i++)
            list.add(i);

        return list;
    }

    public static ArrayList<Integer> expectedList() {
        ArrayList<Integer> originalList = new ArrayList<>(list);
        int skipSize = 0;
        int currentPosition = 0;
        for(Integer length : lengths) {
            if(length > 1)
                originalList = getReversedArray(currentPosition, length, originalList);

            currentPosition = currentPosition + length + skipSize;
            if(currentPosition > MAX_SIZE)
                currentPosition = currentPosition - MAX_SIZE;
            skipSize++;
        }
    return originalList;
   }

    private static ArrayList<Integer> getReversedArray(int currentPosition, int length, List<Integer> originalList) {
        ArrayList<Integer> reversedArray = new ArrayList<>();
        ArrayList<Integer> sublistToRevers = new ArrayList<>(); 
        ArrayList<Integer> originalArray = new ArrayList<>(originalList);
        int endPoint = currentPosition + length;

        if(endPoint > MAX_SIZE) {
            sublistToRevers.addAll(originalArray.subList(currentPosition, MAX_SIZE));
            sublistToRevers.addAll(originalArray.subList(0, endPoint - MAX_SIZE));
            Collections.reverse(sublistToRevers);
            reversedArray.addAll(sublistToRevers.subList(MAX_SIZE - currentPosition, sublistToRevers.size()));
            reversedArray.addAll(originalArray.subList(endPoint - MAX_SIZE, currentPosition));
            reversedArray.addAll(sublistToRevers.subList(0, MAX_SIZE - currentPosition));
        } else {
            if(currentPosition != 0) {
                reversedArray.addAll(originalArray.subList(0, currentPosition));
            }
            sublistToRevers.addAll(originalArray.subList(currentPosition, endPoint));
            Collections.reverse(sublistToRevers);
            reversedArray.addAll(sublistToRevers);
            reversedArray.addAll(originalArray.subList(endPoint, MAX_SIZE));
        }

        return reversedArray;
    }
}
