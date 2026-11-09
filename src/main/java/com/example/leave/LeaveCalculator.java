
package com.example.leave;

public class LeaveCalculator {

    // Calculate remaining leave balance
    public int calculateRemainingLeave(
            int totalLeave, int leaveTaken) {

        if (totalLeave < 0 || leaveTaken < 0) {
            throw new IllegalArgumentException(
                "Leave values cannot be negative");
        }

        if (leaveTaken > totalLeave) {
            throw new IllegalArgumentException(
                "Leave taken cannot exceed allocated leave");
        }

        return totalLeave - leaveTaken;
    }

    // Calculate leave encashment amount
    public double calculateLeaveEncashment(
            int remainingLeave, double dailySalary) {

        if (remainingLeave < 0 || dailySalary < 0) {
            throw new IllegalArgumentException(
                "Values cannot be negative");
        }

        return remainingLeave * dailySalary;
    }
}

