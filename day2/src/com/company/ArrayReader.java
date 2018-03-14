package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ArrayReader {
    private int numberOfRows, numberOfColumns;
    private String fileName;
    private Scanner sc;
    public ArrayReader(String fileName){
        numberOfRows = 0;
        numberOfColumns = 0;
        this.fileName=fileName;
        try {
            sc = new Scanner(new File(fileName));
            while(sc.hasNextLine()){
                sc.nextLine();
                numberOfRows++;
            }
            sc.close();
            sc = new Scanner (new File(fileName));
            int amountNumberInArray = 0;
            while(sc.hasNextInt()){
                sc.next();
                amountNumberInArray++;
            }
            numberOfColumns=amountNumberInArray/numberOfRows;
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    public int [][] getTable(){
        int [][] tab = new int[numberOfRows][numberOfColumns];
        try {
            sc = new Scanner(new File(fileName));
            int i=0, j=0;
            while(sc.hasNextInt()){
                tab[i][j++] = sc.nextInt();
                if((j % numberOfColumns) == 0){
                    i++;
                    j=0;
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return tab;
    }
    public int getRows(){
        return numberOfRows;
    }
    public int getColumns(){
        return numberOfColumns;
    }
}