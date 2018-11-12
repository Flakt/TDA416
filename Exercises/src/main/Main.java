package main;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Exercise1 exercise1 = new Exercise1();
        int[] array = new int[] {1,2,3,4,5,6,7,8,9};
        int[] newArray = exercise1.pushIntToArray(array,11,4);
        System.out.println(Arrays.toString(newArray));

        String str1 = "stefan";
        String str2 = "chan";
        System.out.println(exercise1.weaveStringsTogether(str1, str2, new StringBuilder()));
    }

}