package PasswordStrengthChecker;
import java.util.Scanner;
public class PasswordStrengthChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a password
        System.out.print("Enter a password to check its strength: ");
        String password = scanner.nextLine();

        // Evaluate the password strength
        String strength = checkPasswordStrength(password);

        // Display feedback
        System.out.println("Password strength: " + strength);

        scanner.close();
    }

    private static String checkPasswordStrength(String password) {
        int lengthCriteria = 8;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;
        String specialCharacters = "!@#$%^&*()-_=+<>?";

        // Check each character in the password
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUppercase = true;
            else if (Character.isLowerCase(c)) hasLowercase = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else if (specialCharacters.indexOf(c) != -1) hasSpecialChar = true;
        }

        // Assess strength based on criteria
        if (password.length() >= lengthCriteria && hasUppercase && hasLowercase && hasNumber && hasSpecialChar) {
            return "Strong";
        } else if (password.length() >= lengthCriteria && hasUppercase && hasLowercase && hasNumber) {
            return "Moderate";
        } else if (password.length() >= lengthCriteria && (hasUppercase || hasLowercase) && hasNumber) {
            return "Weak";
        } else {
            return "Very Weak";
        }
    }
}
