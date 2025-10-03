import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        while (true) {
            System.out.print("Insert amount of elements to heap: ");
            n = scanner.nextInt();
            if (n > 0) break;
            System.out.println("Can't insert 0 elements");
        }

        MaxHeap heap = new MaxHeap(n);

        int choice;
        while (true) {
            System.out.print("Insert elements by yourself (1) or generate them (2)?");
            choice = scanner.nextInt();
            if (choice == 1 || choice == 2) break;
            System.out.println("Pick between 1 or 2");
        }

        if (choice == 1) {
            System.out.println("Insert only " + n + " elements: ");
            int count = 0;
            while (count < n) {
                int val = scanner.nextInt();
                heap.insert(val);
                count++;
                if (count < n) {
                    System.out.println("Didn't insert enough elements. " + (n - count) + " more elements");
                }
            }
        } else {
            Random rnd = new Random();
            System.out.println("Generate " + n + " random elements: ");
            for (int i = 0; i < n; i++) {
                int val = rnd.nextInt(10000);
                System.out.print(val + " ");
                heap.insert(val);
            }
            System.out.println();
        }

        System.out.println("\n Initial heap: ");
        heap.printHeap();

        System.out.println("\nSorted max heap: ");
        while (heap.getSize() > 0) {
            System.out.print(heap.extractMax() + " ");
        }
        System.out.println();

        System.out.println("\nMetrics: " + heap.getMetrics());
    }
}
