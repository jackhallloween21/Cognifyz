package FileEncryptDecrypt;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileEncryptDecrypt {

    // Shift value for the Caesar Cipher
    private static final int SHIFT = 4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Choose an option (1 for Encryption, 2 for Decryption): ");
        int choice = scanner.nextInt();

        String newFileName = (choice == 1) ? "encrypted.txt" : "decrypted.txt";

        try {
            // Read file contents
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Process content based on choice
            String processedContent = (choice == 1) ? encrypt(content) : decrypt(content);

            // Write processed content to new file
            writeFile(newFileName, processedContent);

            System.out.println("Operation successful. Output saved to " + newFileName);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        scanner.close();
    }

    // Encryption method using Caesar Cipher
    private static String encrypt(String text) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) (c + SHIFT);
                if (Character.isUpperCase(c) && shifted > 'Z' || Character.isLowerCase(c) && shifted > 'z') {
                    shifted -= 26;
                }
                encrypted.append(shifted);
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    // Decryption method using Caesar Cipher
    private static String decrypt(String text) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) (c - SHIFT);
                if (Character.isUpperCase(c) && shifted < 'A' || Character.isLowerCase(c) && shifted < 'a') {
                    shifted += 26;
                }
                decrypted.append(shifted);
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }

    // Method to write content to a file
    private static void writeFile(String fileName, String content) throws IOException {
        FileWriter writer = new FileWriter(new File(fileName));
        writer.write(content);
        writer.close();
    }
}
