package miniProject.library;

public class Library {
    // Composition: Library "has-a" collection of items and patrons
    private LibraryItem[] catalog;
    private int catalogCount;

    private Patron[] members;
    private int memberCount;

    public Library(int maxItems, int maxMembers) {
        this.catalog = new LibraryItem[maxItems];
        this.catalogCount = 0;
        this.members = new Patron[maxMembers];
        this.memberCount = 0;
    }

    // Register an item
    public void addItem(LibraryItem item) {
        if (catalogCount < catalog.length) {
            catalog[catalogCount++] = item;
        } else {
            System.out.println("❌ Catalog capacity full.");
        }
    }

    // Register a patron
    public void registerMember(Patron patron) {
        if (memberCount < members.length) {
            members[memberCount++] = patron;
        } else {
            System.out.println("❌ Member directory full.");
        }
    }

    // Lookup helpers
    public LibraryItem findItem(String id) {
        for (int i = 0; i < catalogCount; i++) {
            if (catalog[i].getId().equalsIgnoreCase(id)) return catalog[i];
        }
        return null;
    }

    public Patron findMember(String memberId) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getMemberId().equalsIgnoreCase(memberId)) return members[i];
        }
        return null;
    }

    // Transaction: Borrow an item
    public boolean issueItemToMember(String itemId, String memberId) {
        LibraryItem item = findItem(itemId);
        Patron patron = findMember(memberId);

        if (item == null) {
            System.out.println("❌ Item not found.");
            return false;
        }
        if (patron == null) {
            System.out.println("❌ Member not found.");
            return false;
        }
        if (item.isIssued()) {
            System.out.println("❌ Item is already issued to someone else.");
            return false;
        }
        if (!patron.canBorrow()) {
            System.out.println("❌ Member has reached their borrowing limit.");
            return false;
        }

        // Execute transaction
        item.issue();
        patron.addBorrowedItem(item);
        System.out.println("✅ Successfully issued \"" + item.getTitle() + "\" to " + patron.getName() + ".");
        return true;
    }

    // Transaction: Return an item with fine calculation
    public boolean returnItemFromMember(String itemId, String memberId, int daysKept) {
        LibraryItem item = findItem(itemId);
        Patron patron = findMember(memberId);

        if (item == null || patron == null) {
            System.out.println("❌ Invalid Item ID or Member ID.");
            return false;
        }

        boolean removed = patron.removeBorrowedItem(itemId);
        if (!removed) {
            System.out.println("❌ This member does not have this item checked out.");
            return false;
        }

        item.returnItem();

        // Connect getMaxLoanDays() and calculateFine()
        int allowedDays = item.getMaxLoanDays();
        int overdueDays = daysKept - allowedDays;

        System.out.println("✅ \"" + item.getTitle() + "\" returned successfully.");
        if (overdueDays > 0) {
            double fine = item.calculateFine(overdueDays);
            System.out.println("⚠️ OVERDUE by " + overdueDays + " days! Fine assessed: ₹" + fine);
        } else {
            System.out.println("Item returned on time. No fine.");
        }
        return true;
    }

    public void displayCatalog() {
        System.out.println("\n--- Library Catalog ---");
        for (int i = 0; i < catalogCount; i++) {
            catalog[i].displayDetails();
        }
    }

    public boolean reportLostItem(String itemId, String memberId, double replacementFee) {
        LibraryItem item = findItem(itemId);
        Patron patron = findMember(memberId);

        if (item == null || patron == null) {
            System.out.println("❌ Invalid Item ID or Member ID.");
            return false;
        }

        // Remove the lost item from the member's account
        boolean removed = patron.removeBorrowedItem(itemId);
        if (!removed) {
            System.out.println("❌ Member does not have this item checked out.");
            return false;
        }

        // Do NOT mark it as available using returnItem()
        // It remains unreturned or can be flagged as permanently lost
        System.out.println("⚠️ Item \"" + item.getTitle() + "\" reported LOST.");
        System.out.println("Patron: " + patron.getName());
        System.out.println("Replacement fee assessed: ₹" + replacementFee);

        return true;
    }
}