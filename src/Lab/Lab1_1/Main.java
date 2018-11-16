package main;

import main.lab1.MaxSum;

public class Main {

    public static void main(String[] args) {

        MaxSum sum = new MaxSum();
        int intSum = sum.maxSubSum1(new int[] {1,2,3,4,5});
        System.out.println(intSum);

    }

}