package lw04.unguided;

import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("cards.txt");

        Scanner sc = new Scanner(input); {
            int n = sc.nextInt();
            int[] cards = new int[n];

            for (int i = 0; i < n; i++) {
                cards[i] = sc.nextInt();
            }
            int m = sc.nextInt();
            int[] requests = new int[m];

            for (int i = 0; i < m; i++) {
                requests[i] = sc.nextInt();
            }

            selectionSort(cards, 2);

            for (int i = 0; i < n; i++) {
                System.out.print(cards[i]);
                if (i < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            for (int i = 0; i < m; i++) {
                int index = linearSearch(cards, requests[i]);
                if (index != -1) {
                    System.out.println(requests[i] +
                            ": FOUND at index " + index);
                } else {
                    System.out.println(requests[i] +
                            ": NOT FOUND");
                }
            }
        }
    }

    public static void selectionSort(int[] arr, int passes) {
        for (int i = 0; i < passes; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
