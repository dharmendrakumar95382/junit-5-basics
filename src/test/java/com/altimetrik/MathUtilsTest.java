package com.altimetrik;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * There are separate Instances for each of the test method.
 * Default -  @TestInstance(TestInstance.Lifecycle.PER_METHOD)
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("When running MathUtils ")
class MathUtilsTest {

    MathUtils utils;

    @BeforeAll
    static void beforeAllInit(){
        System.out.println("This needs to run before all.");
    }
    @BeforeEach
    void init(){
        utils = new MathUtils();
    }

    @AfterEach
    void cleanUp(){
        System.out.println("Clean up ..");
    }

    @Nested
    @DisplayName("Add methods")
    class AddTest{
        @Test
        void testAddPositive() {
            int a = 10;
            int b = 20 ;
            assertEquals(30,utils.add(a,b));
        }

        @Test
        @DisplayName("When adding two negative value")
        void testAddNegative(){
            assertNotEquals(40, utils.add(10,20));
        }
    }


    @Test
    void testDivide(){
        boolean isSeverUp = false;
        assumeTrue(isSeverUp);
        assertEquals(2,utils.divide(4,2));
    }

    @Test
    @EnabledOnOs(OS.MAC)   // Test run based on operating system
    @EnabledOnJre(JRE.JAVA_11) // Test run based on Jre
    void testDivideNegative(){
        assertThrows(ArithmeticException.class,()-> utils.divide(4,0));
    }

    // if anyone one of them is failed it will fail
    @Test
    void testMultiply(){
        assertAll(
                ()-> assertEquals(2,utils.multiply(1,2)),
                ()-> assertEquals(-2,utils.multiply(-1,2)),
                ()-> assertEquals(8,utils.multiply(4,2))
        );
    }

    @Test
    void lambdaExpressions() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        assertTrue(numbers.stream()
                           .mapToInt(Integer::intValue)
                           .sum() > 5, () -> "Sum should be greater than 5");
    }

    @Test
    @Disabled
    @DisplayName("TDD method. should not run")
    void testDisabled(){
        fail("This test should be disabled.");
    }

    @Test
    void groupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll("numbers",
                  () -> assertEquals(numbers[0], 0),
                  () -> assertEquals(numbers[3], 3),
                  () -> assertEquals(numbers[4], 4)
        );
    }

    @Test
    void shouldThrowException() {
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Not supported");
        });
        assertEquals("Not supported", exception.getMessage());
    }

}