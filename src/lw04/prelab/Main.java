package lw04.prelab;

import java.util.*;
import java.io.InputStream;

public class Main {
    public static void insertionSort(int[] arr, int passes) {

        for (int i = 1; i <= passes && i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
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

    public static void main(String[] args) {

        InputStream input = Main.class.getResourceAsStream("branches.txt");

        if (input == null) {
            System.out.println("branches.txt not found");
            return;
        }

        Scanner sc = new Scanner(input);

        int N = sc.nextInt();
        int[] branches = new int[N];

        for (int i = 0; i < N; i++) {
            branches[i] = sc.nextInt();
        }
        int M = sc.nextInt();
        int[] targets = new int[M];

        for (int i = 0; i < M; i++) {
            targets[i] = sc.nextInt();
        }

        insertionSort(branches, 3);

        for (int i = 0; i < N; i++) {
            System.out.print(branches[i]);

            if (i < N - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        for (int i = 0; i < M; i++) {
            int index = linearSearch(branches, targets[i]);

            if (index != -1) {
                System.out.println(targets[i] +
                    ": FOUND at index " + index);
            } else {
                System.out.println(targets[i] +
                    ": NOT FOUND");
            }
        }
        sc.close();
    }
}
