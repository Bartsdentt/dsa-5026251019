package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("borrowing.txt")
        );

        while (scanner.hasNextLine()) {
            String[] request = scanner.nextLine().split(" ");
            requests.add(request);

            String name = request[0];

            boolean found = false;

            for (String[] member : members) {
                if (member[0].equals(name)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                members.add(new String[]{name, "0"});
            }
        }

        scanner.close();

        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        for (String[] request : requests) {
            queue.add(request);
        }

        LinkedList<String[]> success = new LinkedList<>();

        while (!queue.isEmpty()) {

            String[] request = queue.poll();

            String name = request[0];
            String title = request[1];

            String[] book = null;
            String[] member = null;

            for (String[] b : books) {
                if (b[0].equals(title)) {
                    book = b;
                    break;
                }
            }

            for (String[] m : members) {
                if (m[0].equals(name)) {
                    member = m;
                    break;
                }
            }

            int stock = Integer.parseInt(book[1]);
            int borrowed = Integer.parseInt(member[1]);

            if (stock > 0 && borrowed < 2) {
                book[1] = String.valueOf(stock - 1);
                member[1] = String.valueOf(borrowed + 1);
                success.add(request);
            } else {
                failed.push(request);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");

        for (String[] request : success) {
            System.out.println(request[0] + " " + request[1]);
        }

        System.out.println("\n=== Remaining Book Stock ===");

        for (String[] book : books) {
            System.out.println(book[0] + " : " + book[1]);
        }

        System.out.println("\n=== Failed Requests ===");

        while (!failed.isEmpty()) {
            String[] request = failed.pop();
            System.out.println(request[0] + " " + request[1]);
        }
    }
}