package com.nhanthanhle.seminar.utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class SumUtilTest {
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",       // test 1 + 2 = 3
        "5, 5, 10",      // test 5 + 5 = 10
        "0, 0, 0",       // test 0 + 0 = 0
        "-1, 1, 0",      // test -1 + 1 = 0
        "-5, -5, -10",    // test -5 + -5 = -10
    
    })
    void testSumNumbers(int num1, int num2, int expectedRes) {
        int actualRes = SumUtil.sumNumbers(num1, num2);
        Assertions.assertEquals(expectedRes, actualRes);

    }
}
