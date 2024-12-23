// Adeoluwa Adekoya
// Proj4 Lab Fri 3:30-5:30
// This program assists professors in grading students' exams. It asks the user for their name, WID, first, second, and 
// Final exam scores and provides each student with their average exam score in the class summary.

import java.util.Scanner;

public class Proj4 {
    // Constants
    static final int MAX_STUDENTS = 60;
    static final int NUM_EXAMS = 2;
    static final int MAX_EXAM_SCORE = 50;
    static final int MAX_FINAL_SCORE = 100;

    public static void main(String[] args) {
        // Arrays to store student information
        String[] studentNames = new String[MAX_STUDENTS];
        String[] studentWIDs = new String[MAX_STUDENTS];
        double[] exam1Scores = new double[MAX_STUDENTS];
        double[] exam2Scores = new double[MAX_STUDENTS];
        double[] finalExamScores = new double[MAX_STUDENTS];
        int studentCount = 0;

        
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to the Grading Application!\n");

        //  student information
        while (studentCount < MAX_STUDENTS) {
            // Prompt for student info
            System.out.print("Please enter the name of Student " + (studentCount + 1) + ": ");
            String name = scanner.nextLine().toUpperCase();

            System.out.print("Please enter the WID of Student " + (studentCount + 1) + ": ");
            String wid;
            do {
                wid = scanner.nextLine();
                if (wid.length() != 9 || !wid.matches("\\d{9}")) {
                    System.out.println("**Invalid WID...must be 9-digits");
                    System.out.print("Please re-enter WID: ");
                }
            } while (wid.length() != 9 || !wid.matches("\\d{9}"));

            // Enter exam scores
            double exam1Score;
            do {
                System.out.print("Please enter score for Exam 1: ");
                exam1Score = scanner.nextDouble();
                scanner.nextLine();
                if (exam1Score < 0 || exam1Score > MAX_EXAM_SCORE) {
                    System.out.println("**Invalid score...please enter 0-" + MAX_EXAM_SCORE + " only");
                }
            } while (exam1Score < 0 || exam1Score > MAX_EXAM_SCORE);

            double exam2Score;
            do {
                System.out.print("Please enter score for Exam 2: ");
                exam2Score = scanner.nextDouble();
                scanner.nextLine();
                if (exam2Score < 0 || exam2Score > MAX_EXAM_SCORE) {
                    System.out.println("**Invalid score...please enter 0-" + MAX_EXAM_SCORE + " only");
                }
            } while (exam2Score < 0 || exam2Score > MAX_EXAM_SCORE);

            // Enter final exam score
            double finalExamScore;
            do {
                System.out.print("Please enter score for Final Exam: ");
                finalExamScore = scanner.nextDouble();
                scanner.nextLine();
                if (finalExamScore < 0 || finalExamScore > MAX_FINAL_SCORE) {
                    System.out.println("**Invalid score...please enter 0-" + MAX_FINAL_SCORE + " only");
                }
            } while (finalExamScore < 0 || finalExamScore > MAX_FINAL_SCORE);

            // Store student information
            studentNames[studentCount] = name;
            studentWIDs[studentCount] = wid;
            exam1Scores[studentCount] = exam1Score;
            exam2Scores[studentCount] = exam2Score;
            finalExamScores[studentCount] = finalExamScore;

            studentCount++;

            // Check if maximum number of students entered
            if (studentCount >= MAX_STUDENTS) {
                System.out.println("\nMaximum number of students entered\n");
                break;
            }

            // Prompt to enter another student
            System.out.print("Do you wish to enter another? (y/n): ");
            char continueInput = scanner.next().charAt(0);
            scanner.nextLine(); 
            if (continueInput != 'y' && continueInput != 'Y') {
                break;
            }
        }

        // Display individual student info
        System.out.println("\n***Class Results***\n");

        for (int i = 0; i < studentCount; i++) {
            System.out.println(studentNames[i] + ", " + studentWIDs[i]);

            double examPercentage = calculateExamPercentage(exam1Scores[i], exam2Scores[i], finalExamScores[i]);
            System.out.printf("Exam Percentage: %.1f%%%n", examPercentage);

            System.out.println("Final Grade: " + calculateFinalGrade(examPercentage));

            System.out.print("Press enter to display next student...");
            scanner.nextLine(); 
        }

        // Display class summary
        System.out.println("\n\n***Class Summary***\n");

        System.out.println("Total number of Students: " + studentCount);

        int[] gradeCounts = calculateGradeCounts(exam1Scores, exam2Scores, finalExamScores, studentCount);
        double[] gradePercentages = calculateGradePercentages(gradeCounts, studentCount);

        System.out.println("Total number of A's: " + gradeCounts[0]);
        System.out.println("Total number of B's: " + gradeCounts[1]);
        System.out.println("Total number of C's: " + gradeCounts[2]);
        System.out.println("Total number of D's: " + gradeCounts[3]);
        System.out.println("Total number of F's: " + gradeCounts[4]);

        System.out.println("Individual grade percentages...");
        System.out.println("A: " + String.format("%.1f%%", gradePercentages[0]));
        System.out.println("B: " + String.format("%.1f%%", gradePercentages[1]));
        System.out.println("C: " + String.format("%.1f%%", gradePercentages[2]));
        System.out.println("D: " + String.format("%.1f%%", gradePercentages[3]));
        System.out.println("F: " + String.format("%.1f%%", gradePercentages[4]));

        double averageExamPercentage = calculateAverageExamPercentage(exam1Scores, exam2Scores, finalExamScores, studentCount);
        System.out.printf("Average exam percentage = %.1f%%%n", averageExamPercentage);

        
        scanner.close();
    }

    // Method to calculate the total exam percentage
    public static double calculateExamPercentage(double exam1Score, double exam2Score, double finalExamScore) {
        double totalExamScore = exam1Score + exam2Score + finalExamScore;
        return (totalExamScore / (NUM_EXAMS * MAX_EXAM_SCORE + MAX_FINAL_SCORE)) * 100;
    }

    // Method to calculate the final grade
    public static String calculateFinalGrade(double examPercentage) {
        if (examPercentage >= 90) {
            return "A";
        } else if (examPercentage >= 80) {
            return "B";
        } else if (examPercentage >= 70) {
            return "C";
        } else if (examPercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Method to calculate the count of each grade
    public static int[] calculateGradeCounts(double[] exam1Scores, double[] exam2Scores, double[] finalExamScores, int studentCount) {
        int[] gradeCounts = new int[5];
        for (int i = 0; i < studentCount; i++) {
            double examPercentage = calculateExamPercentage(exam1Scores[i], exam2Scores[i], finalExamScores[i]);
            String grade = calculateFinalGrade(examPercentage);
            switch (grade) {
                case "A":
                    gradeCounts[0]++;
                    break;
                case "B":
                    gradeCounts[1]++;
                    break;
                case "C":
                    gradeCounts[2]++;
                    break;
                case "D":
                    gradeCounts[3]++;
                    break;
                case "F":
                    gradeCounts[4]++;
                    break;
            }
        }
        return gradeCounts;
    }

    // Method to calculate the percentage of each grade
    public static double[] calculateGradePercentages(int[] gradeCounts, int studentCount) {
        double[] gradePercentages = new double[5];
        for (int i = 0; i < gradeCounts.length; i++) {
            gradePercentages[i] = (double) gradeCounts[i] / studentCount * 100;
        }
        return gradePercentages;
    }

    // Method to calculate the average exam percentage
    public static double calculateAverageExamPercentage(double[] exam1Scores, double[] exam2Scores, double[] finalExamScores, int studentCount) {
        double totalExamPercentage = 0;
        for (int i = 0; i < studentCount; i++) {
            totalExamPercentage += calculateExamPercentage(exam1Scores[i], exam2Scores[i], finalExamScores[i]);
        }
        return totalExamPercentage / studentCount;
    }
}
