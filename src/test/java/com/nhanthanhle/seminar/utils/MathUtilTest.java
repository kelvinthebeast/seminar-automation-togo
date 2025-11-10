package com.nhanthanhle.seminar.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class MathUtilTest {
    public static Object[][] initData() {
        return new Object[][] {
            {1, 1},
            {2, 2},
            {5, 120},
            {6, 720},
        };
    }

    @ParameterizedTest
    @MethodSource(value = "initData") 
    public void testGetFactorialGivenRightArgsReturnWell(int input, long expected) {
        assertEquals(expected, MathUtil.getFactorial(input));
    }

    // bắt ngoại lệ khi đưa data cà chớn!!
    //@Test(expected = lỗi.class) 
    @Test
    public void testGetFactorialGivenWrongArgsThrowExp() {
        assertThrows(IllegalArgumentException.class, () -> { MathUtil.getFactorial(21);});
    }

    @Test
    void testGetFactorial() {
        assertEquals("6", "6");
    }
}
// DDT