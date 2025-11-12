package com.nhanthanhle.seminar.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class MathUtilTest {
    public static Object[][] initData() {
        return new Object[][]{
            {-1, 0L, IllegalArgumentException.class}, // sai -> ném exception
            {0, 1L, null},                     
            {1, 1L, null},
            
            {9,  362880L, null},
            {10, 3628800L, null},
            {11, 39916800L, null},

            {19, 121645100408832000L, null},
            {20, 2432902008176640000L, null},  
            {21, 0L, IllegalArgumentException.class}, // sai -> ném exception
            
    };
    }

    @ParameterizedTest
    @MethodSource("initData")
    public void testGetFactorial_AllCases(int input, long expected, Class<? extends Exception> expectedException) {
        if (expectedException == null) {
            // ✅ Trường hợp hợp lệ
            assertEquals(expected, MathUtil.getFactorial(input));
        } else {
            // ⚠️ Trường hợp ném exception
            assertThrows(expectedException, () -> MathUtil.getFactorial(input));
        }
    }
}
// DDT