package lw03.prelab;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

//prob 1
        List<String> playlist = new ArrayList<>();

        InputStream playlistFile = Main.class.getResourceAsStream("playlist.txt");
        Scanner scanner1 = new Scanner(playlistFile);

        while (scanner1.hasNextLine()) {
            String line = scanner1.nextLine();

            String[] parts = line.split(" ", 2);
            String operation = parts[0];

            if (operation.equals("ADD")) {
                String song = parts[1];
                playlist.add(song);

            } else if (operation.equals("INSERT")) {
                String[] insertParts = line.split(" ", 3);

                int index = Integer.parseInt(insertParts[1]);
                String song = insertParts[2];

                playlist.add(index, song);

            } else if (operation.equals("REMOVE")) {
                String song = parts[1];

                playlist.remove(song);
            }
        }

        scanner1.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

//prob 2

        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        InputStream participantsFile = Main.class.getResourceAsStream("participants.txt");
        Scanner scanner2 = new Scanner(participantsFile);

        while (scanner2.hasNextLine()) {
            String name = scanner2.nextLine();

            if (participants.contains(name)) {
                duplicateRegistrations++;
            } else {
                participants.add(name);
            }
        }

        scanner2.close();

        System.out.println("\n===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int number = 1;

        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }

        System.out.println("Duplicate registrations: " + duplicateRegistrations);

//prob 3

        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        InputStream inventoryFile = Main.class.getResourceAsStream("inventory.txt");
        Scanner scanner3 = new Scanner(inventoryFile);

        while (scanner3.hasNextLine()) {
            String line = scanner3.nextLine();

            String[] parts = line.split(" ");

            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {

                if (inventory.containsKey(product)) {
                    int currentStock = inventory.get(product);
                    inventory.put(product, currentStock + quantity);
                } else {
                    inventory.put(product, quantity);
                }

            } else if (type.equals("SELL")) {

                if (inventory.containsKey(product)) {
                    int currentStock = inventory.get(product);

                    if (currentStock >= quantity) {
                        inventory.put(product, currentStock - quantity);
                    } else {
                        failedSales++;
                    }

                } else {
                    failedSales++;
                }
            }
        }

        scanner3.close();

        System.out.println("\n===== Problem 3 =====");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Failed sales: " + failedSales);
    }
}