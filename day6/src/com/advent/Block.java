package com.advent;

import java.io.File;
import java.io.IOException;
import java.util.*;

class Block {
    private Map<Integer, Integer> mapOfBlocks;

    private int numberOfCyclesInInfiniteLoop;

    public Block(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int blockIndex = 0;
            int blockValue;
            mapOfBlocks = new HashMap();
            while (scanner.hasNextInt()) {
                blockValue = scanner.nextInt();
                mapOfBlocks.put(blockIndex++, blockValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int countAmountOfRedistributionCycles(){
        List<Integer> valuesOfBlocksList= new LinkedList();
        Set <List<Integer>> setOfBlocks = new HashSet<>();
        List <Integer> lastListOfBlocks = new LinkedList(); // to second part
        valuesOfBlocksList.addAll(mapOfBlocks.values());
        setOfBlocks.add(valuesOfBlocksList);
        int numberOfSteps = 0;
        boolean isNotTheSameAsBefore =true;
        while(isNotTheSameAsBefore){
            int maxIndex = indexOfMax();
            redistribution(maxIndex);
            valuesOfBlocksList = new LinkedList();
            valuesOfBlocksList.addAll(mapOfBlocks.values());
            isNotTheSameAsBefore=setOfBlocks.add(valuesOfBlocksList);
            lastListOfBlocks = valuesOfBlocksList; // to second part
            numberOfSteps++;
        }

        // second part
        isNotTheSameAsBefore = true;
        numberOfCyclesInInfiniteLoop = 0;
        setOfBlocks = new HashSet();
        setOfBlocks.add(lastListOfBlocks); // only last set
        while(isNotTheSameAsBefore){
            int indexOfMaxBlock = indexOfMax();
            redistribution(indexOfMaxBlock);
            valuesOfBlocksList = new LinkedList();
            valuesOfBlocksList.addAll(mapOfBlocks.values());
            isNotTheSameAsBefore = setOfBlocks.add(valuesOfBlocksList);
            numberOfCyclesInInfiniteLoop++;
        }
        return numberOfSteps;
    }

    private void redistribution(int indexOfBlockToRedistribution){
        int blockIndex = indexOfBlockToRedistribution;
        int blockValue = mapOfBlocks.get(blockIndex);
        mapOfBlocks.put(blockIndex, 0);
        while(blockValue > 0){
            blockIndex++;
            if(blockIndex == mapOfBlocks.size())
                blockIndex=0;
            int val = mapOfBlocks.get(blockIndex);
            mapOfBlocks.put(blockIndex,val+1);
            blockValue--;
        }
    }

    private int indexOfMax(){
        int maxValueOfBlock = mapOfBlocks.get(0);
        int indexOfBlock = 0;
        for(int i:mapOfBlocks.keySet()){
            int valueOfTestingBlock = mapOfBlocks.get(i);
            if(valueOfTestingBlock>maxValueOfBlock){
                maxValueOfBlock=valueOfTestingBlock;
                indexOfBlock=i;
            }
        }
        return indexOfBlock;
    }

    public int getNumberOfCyclesInInfiniteLoop() {
        return numberOfCyclesInInfiniteLoop;
    }
}