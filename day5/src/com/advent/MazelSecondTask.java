package com.advent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class MazelSecondTask {
    private int stepsToExit;
    private Map<Integer, Integer> stepsMap;

    public MazelSecondTask(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            stepsMap = new HashMap<>();
            int numberInSequenceOfSteps = 0;
            while (scanner.hasNextInt()) {
                int valueOfStep = scanner.nextInt();
                stepsMap.put(numberInSequenceOfSteps++, valueOfStep);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int amountOfSteps() {
        stepsToExit = 0;
        int element = 0;
        int offset, q;
        while (element >= 0 && element < stepsMap.size()) {
            offset = stepsMap.get(element);
            q = element;
            element += offset;
            if(offset>=3) stepsMap.put(q, offset-1);
            else stepsMap.put(q, offset+1);
            stepsToExit++;
        }
        return stepsToExit;
    }
}