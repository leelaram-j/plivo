package com.test;

import java.util.Arrays;

public class NumFinder {

    // write a method which accepts a string as input and parse numbers from the string,
    // sum up the numbers present in the string and return

    //abc121$%324
    public static int findSum(String str) {
        int length = str.length();
        String[] values = new String[100];
        java.util.Arrays.fill(values,"0");
        int j=0;
        int sum=0;
        for(int i=0;i<length-1;i++) {
            Character x = str.charAt(i);
            Character y = str.charAt(i+1);
            if(Character.isDigit(str.charAt(i))) {
                values[j] += String.valueOf(str.charAt(i));
            }
            if(Character.isDigit(str.charAt(i)) && Character.isLetter(y))
                j++;
        }
        if(Character.isDigit(str.charAt(length-1)))
            values[j] += String.valueOf(str.charAt(length-1));

        for(String value:values) {
            sum+=Integer.parseInt(value);
        }
        return sum;
    }


    public static int findSum2(String str) {
        int length = str.length();
        String[] values = new String[100];
        Arrays.fill(values,"0");
        int j=0;
        int sum=0;
        for(int i=0;i<length-1;i++) {
            char x = str.charAt(i);
            char y = str.charAt(i+1);
            if(isNumber(x)) {
                values[j] += String.valueOf(x);
            }
            if(isNumber(x) && isAlphabetOrSymbol(y))
                j++;
        }
        if(isNumber(str.charAt(length-1)))
            values[j] += str.charAt(length-1);

        for(String value:values) {
            sum += Integer.parseInt(value);
        }
        return sum;
    }

    public static boolean isAlphabetOrSymbol(char ch) {
        return ((ch >=32 && ch<=47) || (ch>=58));
    }

    public static  boolean isNumber(char ch) {
        return (ch>=48 && ch<=57);
    }

    public static void main(String[] args) {
        String str = "abc121&*%3290";
        int sum = findSum2(str);
        System.out.println(sum);
    }
}
