package stack;

import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        // Create a stack with an initial capacity of 5 for recently returned books
        BookStack recentlyReturned = new BookStack(5);
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("=== Library Information System (Stack Implementation) ===");

        do {
            System.out.println("\nMenu Options:");
            System.out.println("1. Process a Returned Book (Push)");
            System.out.println("2. Shelve Most Recently Returned Book (Pop)");
            System.out.println("3. View Next Book to Shelve (Peek)");
            System.out.println("4. Display All Recently Returned Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number (1-5): ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    Book newBook = new Book(isbn, title, author);
                    recentlyReturned.push(newBook);
                    System.out.println("Success: \"" + title + "\" added to return stack.");
                    break;

                case 2:
                    try {
                        Book shelvedBook = recentlyReturned.pop();
                        System.out.println("Action: Moved \"" + shelvedBook.getTitle() +
                                           "\" from stack to permanent shelves.");
                    } catch (Exception e) {
                        System.out.println("Error: The return stack is empty. No books to shelve.");
                    }
                    break;

                case 3:
                    try {
                        Book nextBook = recentlyReturned.peek();
                        System.out.println("Next up to be shelved:\n " + nextBook);
                    } catch (Exception e) {
                        System.out.println("Error: The return stack is empty.");
                    }
                    break;

                case 4:
                    System.out.println("--- Current Return Stack Status (Top to Bottom) ---");
                    recentlyReturned.displayStack();
                    System.out.println("Total pending books: " + recentlyReturned.size());
                    break;

                case 5:
                    System.out.println("Exiting Library Information System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
	