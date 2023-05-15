package com.nttdata.banco.validators;

import java.util.regex.Pattern;

public class PasswordValidator {
     public static boolean isValidPassword(String password) {
        // Check if the password contains only one uppercase letter
        int uppercaseCount = 0;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                uppercaseCount++;
                if (uppercaseCount > 1) {
                    return false;
                }
            }
        }
        if (uppercaseCount != 1) {
            return false;
        }
        
        // Check if the password contains a lowercase letter
        if (!Pattern.compile(".*[a-z].*").matcher(password).matches()) {
            return false;
        }

        // Check if the password contains at least two numbers
        int digitCount = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
                if (digitCount >= 2) {
                    break;
                }
            }
        }
        if (digitCount < 2) {
            return false;
        }

        return true;
    }
}
