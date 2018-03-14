package com.advent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class MazelFirstTask {
    private int stepsToExit;
    private Map<Integer, Integer> stepsMap;

    public MazelFirstTask(String fileName) {
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
        int offset, keyInMap;
        while (element >= 0 && element < stepsMap.size()) {
            offset = stepsMap.get(element);
            keyInMap = element;
            element += offset;
            stepsMap.put(keyInMap, offset + 1);
            stepsToExit++;
        }
        return stepsToExit;
    }
}
