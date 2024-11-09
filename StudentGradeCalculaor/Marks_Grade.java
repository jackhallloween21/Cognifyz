/*
Percentage of Marks	Grade
Above 90%	         A
80% to 90%	         B
70% to 80%	         C
60% to 70%	         D
Below 60%	         E */
package StudentGradeCalculaor;
import java.util.Scanner;
class InvalidMarksException extends Exception {
    
    InvalidMarksException(String message) {
       super(message);
   }
}


public class Marks_Grade {
    static String calculateGrade(int totalMarks) throws InvalidMarksException {
        if (totalMarks > 100) {
            throw new InvalidMarksException("Total marks cannot exceed 100. You entered: " + totalMarks);
        }

        double percentage = (totalMarks * 100.0) / 100;

        if (percentage > 90) {
            return "Grade A";
        } else if (percentage >= 80) {
            return "Grade B";
        } else if (percentage >= 70) {
            return "Grade C";
        } else if (percentage >= 60) {
            return "Grade D";
        } else {
            return "Grade E";
        }
    }

    public static void main(String[] args) {
        int marks ;
    Scanner in = new Scanner(System.in);
    System.out.println("Enter marks:");
    marks=in.nextInt();
            try {
                String grade = calculateGrade(marks);
                System.out.println("Marks: " + marks + " : " + grade);
            } catch (InvalidMarksException e) {
                System.out.println(e.getMessage());
            }
        }
    }
