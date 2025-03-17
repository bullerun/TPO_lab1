package bullerun;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TaskOneTests {
    private TaskOne taskOne;

    private static final double PI = 3.141592653589793;
    private final double DELTA = 0.0001;

    @BeforeEach
    public void setUp() {
        taskOne = new TaskOne();
    }

    @Test
    public void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> taskOne.factorial(-1));
    }

    @Test
    public void testFactorialZero() {
        assertEquals(1, taskOne.factorial(0));
    }

    @ParameterizedTest
    @MethodSource("factorialArguments")
    public void testFactorialCorrectness(int input, long expected) {
        assertEquals(expected, taskOne.factorial(input));
    }

    @Test
    public void testCosRange() {
        double cosValue = taskOne.cosTaylor(PI * 1000, 15);
        assertTrue(cosValue >= -1.0 && cosValue <= 1.0, "cos(x) should be in range [-1, 1]");
    }

    @ParameterizedTest
    @MethodSource("cosArguments")
    public void testCosCorrectness(double input, double expected) {
        assertEquals(expected, taskOne.cosTaylor(input, 15), DELTA);
    }

    @Test
    public void testInvalidTerms() {
        assertThrows(IllegalArgumentException.class, () -> taskOne.cosTaylor(0, 0));
        assertThrows(IllegalArgumentException.class, () -> taskOne.cosTaylor(0, -5));
    }

    static Stream<Arguments> factorialArguments() {
        return Stream.of(
                Arguments.of(1, 1L),
                Arguments.of(2, 2L),
                Arguments.of(3, 6L),
                Arguments.of(4, 24L),
                Arguments.of(5, 120L),
                Arguments.of(6, 720L)
        );
    }

    static Stream<Arguments> cosArguments() {
        return Stream.of(
                Arguments.of(0.0, 1.0),
                Arguments.of(PI / 2, 0.0),
                Arguments.of(PI, -1.0),
                Arguments.of((PI * 3) / 2, 0.0),
                Arguments.of(-PI / 3, 0.5),
                Arguments.of(0.5, 0.87758256189)
        );
    }
}