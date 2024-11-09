package RandomPasswordGenerator;
import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Prompt user for password length
        System.out.print("Enter the desired length of the password: ");
        int length = scanner.nextInt();

        // Prompt user for character options
        System.out.print("Include numbers? (y/n): ");
        boolean includeNumbers = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include lowercase letters? (y/n): ");
        boolean includeLowercase = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include uppercase letters? (y/n): ");
        boolean includeUppercase = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include special characters? (y/n): ");
        boolean includeSpecial = scanner.next().equalsIgnoreCase("y");

        // Define character pools based on user preferences
        String numbers = "0123456789";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialCharacters = "!@#$%^&*()-_=+<>?";

        // Build the character set based on user preferences
        StringBuilder characterSet = new StringBuilder();
        if (includeNumbers) characterSet.append(numbers);
        if (includeLowercase) characterSet.append(lowercase);
        if (includeUppercase) characterSet.append(uppercase);
        if (includeSpecial) characterSet.append(specialCharacters);

        // Check if character set is empty
        if (characterSet.length() == 0) {
            System.out.println("You must select at least one character type for the password.");
            return;
        }

        // Generate random password
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            password.append(characterSet.charAt(index));
        }

        // Display generated password
        System.out.println("Generated Password: " + password.toString());

        scanner.close();
    }
}
