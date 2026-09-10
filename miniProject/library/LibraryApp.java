package miniProject.library;

import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Polymorphic Array: stores any subclass of LibraryItem
        final int MAX_CAPACITY = 10;
        LibraryItem[] inventory = new LibraryItem[MAX_CAPACITY];
        int itemCount = 0;

        // Seed initial items of different types
        inventory[itemCount++] = new Book("B101", "Clean Code", "Robert C. Martin");
        inventory[itemCount++] = new Book("B102", "Effective Java", "Joshua Bloch");
        inventory[itemCount++] = new Magazine("M201", "Wired Tech Magazine", 342);
        inventory[itemCount++] = new AudioBook("A301", "Atomic Habits", 5.5);

        boolean running = true;

        // Interactive Menu Loop
        while (running) {
            System.out.println("\n=================================");
            System.out.println("     LIBRARY MANAGEMENT SYSTEM   ");
            System.out.println("=================================");
            System.out.println("1. View All Catalog Items");
            System.out.println("2. Issue an Item");
            System.out.println("3. Return an Item");
            System.out.println("4. Search Items by Title Keyword");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            // Input validation: ensure the user enters an integer
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the bad input buffer
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the trailing newline character left behind by nextInt()

            switch (choice) {
                case 1:
                    // Polymorphism in action: resolves the correct displayDetails() at runtime
                    System.out.println("\n--- Catalog Inventory (" + itemCount + "/" + MAX_CAPACITY + ") ---");
                    if (itemCount == 0) {
                        System.out.println("Inventory is currently empty.");
                    } else {
                        for (int i = 0; i < itemCount; i++) {
                            inventory[i].displayDetails();
                        }
                    }
                    break;

                case 2:
                    System.out.print("\nEnter Item ID to issue: ");
                    String issueId = scanner.nextLine().trim();

                    LibraryItem targetIssue = findItemById(inventory, itemCount, issueId);
                    if (targetIssue != null) {
                        targetIssue.issue();
                    } else {
                        System.out.println("❌ Item with ID [" + issueId + "] was not found.");
                    }
                    break;

                case 3:
                    System.out.print("\nEnter Item ID to return: ");
                    String returnId = scanner.nextLine().trim();

                    LibraryItem targetReturn = findItemById(inventory, itemCount, returnId);
                    if (targetReturn != null) {
                        targetReturn.returnItem();
                    } else {
                        System.out.println("❌ Item with ID [" + returnId + "] was not found.");
                    }
                    break;

                case 4:
                    System.out.print("\nEnter keyword to search: ");
                    String keyword = scanner.nextLine().trim();
                    searchByTitle(inventory, itemCount, keyword);
                    break;

                case 5:
                    System.out.println("\nShutting down the library system. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("❌ Invalid option. Choose between 1 and 5.");
            }
        }

        scanner.close(); // Clean up scanner resource
    }

    // ==========================================
    // HELPER METHODS
    // ==========================================

    // Linear Search by unique identifier
    public static LibraryItem findItemById(LibraryItem[] items, int count, String id) {
        for (int i = 0; i < count; i++) {
            if (items[i].getId().equalsIgnoreCase(id)) {
                return items[i];
            }
        }
        return null;
    }

    // Substring Keyword Search across titles
    public static void searchByTitle(LibraryItem[] items, int count, String keyword) {
        boolean matchFound = false;
        System.out.println("\n--- Search Results for \"" + keyword + "\" ---");

        for (int i = 0; i < count; i++) {
            // Case-insensitive substring matching using .toLowerCase() and .contains()
            if (items[i].getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                items[i].displayDetails();
                matchFound = true;
            }
        }

        if (!matchFound) {
            System.out.println("No matching items found.");
        }
    }
}