package com.advent;

import static java.lang.Math.abs;

class MyClass {
    // counting how many steps from particular number it takes to reach 0
    private static int startingPointNumber = 325489;

    public static void main(String[] args) {
        System.out.println(countMoves());
    }
    private static int countMoves() {
        int i = 0, j = 0; // vertex and horizontal steps
        int actualNumber = 0;
        while (actualNumber != startingPointNumber) {
            while (2-j != i) {
                actualNumber++;
                if (actualNumber == startingPointNumber){
                    return (abs(i) + abs(j));
                }
                i++;
            }
            i--;
            j++;
            while (i+1 !=j) {
                actualNumber++;
                if (actualNumber == startingPointNumber){
                    return (abs(i) + abs(j));
                }
                j++;
            }
            j--;
            i--;

            while (-i-1 !=j) {
                actualNumber++;
                if (actualNumber == startingPointNumber){
                    return (abs(i) + abs(j));
                }
                i--;
            }
            i++;
            j--;
            while (j+1!=i) {
                actualNumber++;
                if (actualNumber == startingPointNumber){
                    return (abs(i) + abs(j));
                }
                j--;
            }
            j++;
            i++;
        }
        return (abs(i) + abs(j));
    }
}
