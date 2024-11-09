package TemperatureConverter;
import java.util.Scanner;
public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter unit of measurement (C for Celsius, F for Fahrenheit): ");
        char unit = scanner.next().toUpperCase().charAt(0);
        System.out.print("Enter temperature value: ");
        double temperature = scanner.nextDouble();
        if (unit == 'C') {
            double fahrenheit = (temperature * 9/5) + 32;
            System.out.printf("%.2f°C is equal to %.2f°F%n", temperature, fahrenheit);
        } else if (unit == 'F') {
            double celsius = (temperature - 32) * 5/9;
            System.out.printf("%.2f°F is equal to %.2f°C%n", temperature, celsius);
        } else {
            System.out.println("Invalid unit. Please enter 'C' for Celsius or 'F' for Fahrenheit.");
        }
    }
}

