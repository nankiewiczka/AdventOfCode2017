package com.advent;

public class Main {
    private static String fileName = "/home/karolina/Documents/AdventOfCode2017/day2/data.txt";
    public static void main(String[] args) {
        int rows;
        ArrayReader readerRows = new ArrayReader(fileName);
        rows = readerRows.getRows();
        int [][] tab = readerRows.getTable();
        int i,j;
        int sum = 0;

        for(i=0; i<rows; i++){
            sum+=(maxElement(tab[i]) - minElement(tab[i]));
        }
        System.out.println(sum);

        int sumOfDivisions=0;
        for(i=0; i<rows; i++){
            sumOfDivisions+=sumOfDivision(tab[i]);
        }
        System.out.println(sumOfDivisions);

    }

    static int maxElement(int array[]){
        int max = array[0];
        for(int i=1; i<array.length;i++)
            if(array[i] > max)
                max=array[i];
        return max;

    }

    static int minElement(int array[]){
        int min = array[0];
        for(int i=1; i<array.length;i++)
            if(array[i] < min)
                min=array[i];
        return min;

    }

    static int sumOfDivision(int array[]){
        int sum = 0;
        for(int i=0; i<array.length; i++){
            for(int j=i+1; j<array.length; j++)
                if(array[i]%array[j]==0) sum+= array[i]/array[j];

            for(int j=i-1; j>=0; j--)
                if(array[i]%array[j]==0) sum+= array[i]/array[j];
        }
        return sum;
    }
}




