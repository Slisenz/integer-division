import com.foxminded.Division;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DivisionTest {

    private final Division division = new Division();

    @Test
    void nullTest() {
        try {
            division.divide(null, null);
            fail("Expected exception not thrown");
        } catch (IllegalArgumentException nullObject) {
            nullObject.printStackTrace();
        }
    }

    @Test
    void zeroTest() {
        try {
            division.divide(0, 0);
            fail("Expected exception not thrown");
        } catch (ArithmeticException zeroDivisor) {
            zeroDivisor.printStackTrace();
        }
    }

    @Test
    void divideTest() {
        String expected =
                """
                        _78945|4
                         4    |-----
                         -    |19736
                        _38
                         36
                         --
                         _29
                          28
                          --
                          _14
                           12
                           --
                           _25
                            24
                            --
                             1
                        """;
        assertEquals(expected, division.divide(78945, 4));
    }

    @Test
    void longDividendTest() {
        String expected =
                """
                        _1000200220|5
                         10        |---------
                         --        |200040044
                            _20
                             20
                             --
                               _22
                                20
                                --
                                _20
                                 20
                                 --
                                  0
                        """;
        assertEquals(expected, division.divide(1000200220, 5));
    }

    @Test
    void impossibleDivision() {
        String expected =
                """
                        _10|3
                          9|-
                          -|3
                          1
                        """;
        assertEquals(expected, division.divide(10, 3));
    }

    @Test
    void divideLessDivisor() {
        String expected =
                """
                        _3|105
                         0|---
                         -|0
                         3
                        """;
        assertEquals(expected, division.divide(3, 105));
    }

    @Test
    void negativeDivisionTest(){
        String expected =
                """
                        _100|10
                         10 |---
                         -- |-10
                          00
                        """;
        assertEquals(expected, division.divide(-100, 10));
    }
}

