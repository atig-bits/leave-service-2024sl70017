
package com.example.leave;

import org.junit.Test;
import static org.junit.Assert.*;

public class LeaveCalculatorTest {

    LeaveCalculator calculator = new LeaveCalculator();

    // Test 1: Calculate remaining leave
    @Test
    public void testRemainingLeave() {

        int result =
            calculator.calculateRemainingLeave(30, 10);

        assertEquals(20, result);
    }

    // Test 2: Calculate leave encashment
    @Test
    public void testLeaveEncashment() {

        double result =
            calculator.calculateLeaveEncashment(20, 2000);

        assertEquals(40000.0, result, 0.01);
    }

    // Test 3: Validate invalid leave input
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLeave() {

        calculator.calculateRemainingLeave(30, 40);
    }
}

