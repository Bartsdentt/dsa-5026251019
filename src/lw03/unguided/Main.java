package lw03.unguided;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> enrollment = new LinkedHashMap<>();
        List<String> checks = new ArrayList<>();
        int rejectedOperations = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");

            String operation = parts[0];
            String course = parts[1];

            if (operation.equals("REGISTER")) {
                int count = Integer.parseInt(parts[2]);
                if (count <= 0) {
                    rejectedOperations++;
                } else if (enrollment.containsKey(course)) {
                    int currentEnrollment = enrollment.get(course);
                    enrollment.put(course, currentEnrollment + count);

                } else {
                    enrollment.put(course, count);
                }
                } else if (operation.equals("WITHDRAW")) {
                int count = Integer.parseInt(parts[2]);

                if(count <= 0) {
                    rejectedOperations++;
                } else if (enrollment.containsKey(course)) {
                    int currentEnrollment = enrollment.get(course);
                    if (currentEnrollment >= count) {
                        enrollment.put(course, currentEnrollment - count);
                    } else {
                        rejectedOperations++;
                    }
                } else {
                    rejectedOperations++;
                }
            } else if (operation.equals("CHECK")) {
                if (enrollment.containsKey(course)) {
                    int currentEnrollment = enrollment.get(course);
                    checks.add(course + ": " + currentEnrollment + " students");
                } else {
                    checks.add(course + ": Not found");
                }
            }

        }
        scanner.close();

        System.out.println("===== Enrolment Checks =====");
        for (String check : checks) {
            System.out.println(check);
        }

        System.out.println("\n===== Final Enrollment =====");
        for (Map.Entry<String, Integer> entry : enrollment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " students");
        }

        System.out.println("\nRejected operations: " + rejectedOperations);
    }
}