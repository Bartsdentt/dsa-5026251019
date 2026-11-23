package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree bst = new BinarySearchTree();

        String[] numbers = read.nextLine().split(" ");

        for(String number : numbers){
            int data = Integer.parseInt(number);
            bst.insert(data);
        }

        int target = Integer.parseInt(read.nextLine().trim());

        System.out.print("Preorder : ");
        bst.preOrder();

        System.out.print("Inorder : ");
        bst.inOrder();

        System.out.print("Postorder : ");
        bst.postOrder();

        System.out.println("Search " + target + " : " + bst.search(target));

        System.out.println("Minimum : " + bst.findMin());
        System.out.println("Maximum : " + bst.findMax());

        read.close();
    }
}
