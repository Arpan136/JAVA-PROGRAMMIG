import java.util.*;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String[] names = new String[numStudents];
        int[][] grades = new int[numStudents][];
        double[] averages = new double[numStudents];
        int[] highest = new int[numStudents];
        int[] lowest = new int[numStudents];

        for (int i = 0; i < numStudents; i++) {
            System.out.print("\nEnter name of student " + (i + 1) + ": ");
            names[i] = scanner.nextLine();

            System.out.print("Enter number of grades for " + names[i] + ": ");
            int gradeCount = scanner.nextInt();
            grades[i] = new int[gradeCount];

            int sum = 0;
            highest[i] = Integer.MIN_VALUE;
            lowest[i] = Integer.MAX_VALUE;

            for (int j = 0; j < gradeCount; j++) {
                System.out.print("Enter grade #" + (j + 1) + ": ");
                grades[i][j] = scanner.nextInt();
                sum += grades[i][j];
                if (grades[i][j] > highest[i]) highest[i] = grades[i][j];
                if (grades[i][j] < lowest[i]) lowest[i] = grades[i][j];
            }

            averages[i] = (double) sum / gradeCount;
            scanner.nextLine(); // Consume newline
        }

        // Display summary
        System.out.println("\n📊 STUDENT SUMMARY REPORT:");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nName: " + names[i]);
            System.out.print("Grades: ");
            for (int g : grades[i]) {
                System.out.print(g + " ");
            }
            System.out.printf("\nAverage Score: %.2f\n", averages[i]);
            System.out.println("Highest Score: " + highest[i]);
            System.out.println("Lowest Score: " + lowest[i]);
        }

        scanner.close();
    }
}