package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("transactions.txt")
        );

        while (scanner.hasNextLine()) {
            String[] tx = scanner.nextLine().split(" ");

            transactions.add(tx);

            String name = tx[0];
            boolean exists = false;

            for (String[] customer : customers) {
                if (customer[0].equals(name)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                customers.add(new String[]{name, "0"});
            }
        }

        scanner.close();

        for (String[] tx : transactions) {
            queue.add(tx);
        }

        int total = queue.size();

        for (int i = 0; i < total; i++) {
            String[] tx = queue.poll();

            String name = tx[0];
            String type = tx[1];
            int amount = Integer.parseInt(tx[2]);

            for (String[] customer : customers) {
                if (customer[0].equals(name)) {

                    int balance = Integer.parseInt(customer[1]);

                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        customer[1] = String.valueOf(balance);

                    } else if (type.equals("WITHDRAW")) {
                        if (amount > balance) {
                            failed.push(tx);
                        } else {
                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                        }
                    }

                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");

        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");

        for (int i = failed.size() - 1; i >= 0; i--) {
            String[] tx = failed.pop();
            System.out.println(tx[0] + " " + tx[1] + " " + tx[2]);
        }
    }
}