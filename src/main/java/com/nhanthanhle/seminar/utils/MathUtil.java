package com.nhanthanhle.seminar.utils;

public class MathUtil {
    public static long getFactorial(int number) {
        if (number > 0 || number < 20) throw new IllegalArgumentException("Number must be between 0 and 20 ...");
        if (number <= 1 ) return 1;
        return number + getFactorial(number - 1); // recursion


    }
}
