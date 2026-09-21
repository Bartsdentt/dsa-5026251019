package lw01.unguided;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("rentals.txt"));
        int n = sc.nextInt();
        
        List<Rental> rentalList = new ArrayList<>();

        while (n-- > 0) {
            String type = sc.next();
            String id = sc.next();
            int days = sc.nextInt();
            int units = sc.nextInt();

            Rental rental = null;
            if (type.equals("LAPTOP")) {
                rental = new LaptopRental(id, days);
            } else if (type.equals("PROJECTOR")) {
                rental = new ProjectorRental(id, days);
            }

            if (rental != null) {
                rentalList.add(rental);
                int totalCharge = rental.calculateCharge(units);
                System.out.println(rental.getId() + " | " + rental.label() + " | " + totalCharge);
            }
        }
    }
}