package miniProject.library;

public class Patron {
    private String memberId;
    private String name;
    private LibraryItem[] borrowedItems;
    private int borrowedCount;
    private static final int MAX_BORROW_LIMIT = 3; // Business rule: Max 3 items

    public Patron(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedItems = new LibraryItem[MAX_BORROW_LIMIT];
        this.borrowedCount = 0;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public int getBorrowedCount() { return borrowedCount; }

    // Check if patron can borrow another item
    public boolean canBorrow() {
        return borrowedCount < MAX_BORROW_LIMIT;
    }

    // Add an item to the patron's borrowed list
    public boolean addBorrowedItem(LibraryItem item) {
        if (!canBorrow()) {
            System.out.println("❌ " + name + " has reached the maximum borrowing limit of " + MAX_BORROW_LIMIT + " items.");
            return false;
        }
        borrowedItems[borrowedCount++] = item;
        return true;
    }

    // Remove an item from the patron's borrowed list on return
    public boolean removeBorrowedItem(String itemId) {
        for (int i = 0; i < borrowedCount; i++) {
            if (borrowedItems[i].getId().equalsIgnoreCase(itemId)) {
                // Shift remaining items left to close the gap in the array
                for (int j = i; j < borrowedCount - 1; j++) {
                    borrowedItems[j] = borrowedItems[j + 1];
                }
                borrowedItems[--borrowedCount] = null; // Clear duplicate reference
                return true;
            }
        }
        return false;
    }

    public void displayPatronInfo() {
        System.out.println("\nPatron ID: [" + memberId + "] Name: " + name + " | Borrowed (" + borrowedCount + "/" + MAX_BORROW_LIMIT + ")");
        if (borrowedCount == 0) {
            System.out.println("  (No items currently borrowed)");
        } else {
            for (int i = 0; i < borrowedCount; i++) {
                System.out.println("  -> " + borrowedItems[i].getTitle() + " [" + borrowedItems[i].getId() + "]");
            }
        }
    }
}
