package queue;

import java.util.Scanner;

public class QueueApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter size of the queue: ");
        int size = scanner.nextInt();
        ArrayQueue theQueue = new ArrayQueue(size);

        int choice;
        do {
            System.out.println("\n--- Queue Menu ---");
            System.out.println("1. Enqueue (Add)");
            System.out.println("2. Dequeue (Remove)");
            System.out.println("3. Peek (View Front)");
            System.out.println("4. Display Queue");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to add: ");
                    int val = scanner.nextInt();
                    theQueue.enqueue(val);
                    break;

                case 2:
                    theQueue.dequeue();
                    break;

                case 3:
                    int frontVal = theQueue.peek();
                    if (frontVal != -1) {
                        System.out.println("Front element is: " + frontVal);
                    }
                    break;

                case 4:
                    theQueue.display();
                    break;

                case 5:
                    System.out.println("Exiting application.");
                    break;

                default:
                    System.out.println("Invalid choice! Please choose between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
