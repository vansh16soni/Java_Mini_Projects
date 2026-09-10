package java.library;

class LibraryItem {
    // Encapsulation: fields are private to prevent unauthorized modification
    private String id;
    private String title;
    private boolean isIssued;

    // Constructor: enforces proper initialization
    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false; // By default, new items are available
    }

    // Getters: Read-only access to encapsulated data
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    // Business Logic: State transitions with validation
    public boolean issue() {
        if (isIssued) {
            System.out.println("❌ Error: Item is already issued.");
            return false;
        }
        this.isIssued = true;
        System.out.println("✅ Success: Item issued successfully.");
        return true;
    }

    public boolean returnItem() {
        if (!isIssued) {
            System.out.println("❌ Error: Item was not issued.");
            return false;
        }
        this.isIssued = false;
        System.out.println("✅ Success: Item returned successfully.");
        return true;
    }

    // Base display method intended to be extended by subclasses
    public void displayDetails() {
        System.out.print("[" + id + "] \"" + title + "\" | Status: " + (isIssued ? "Issued" : "Available"));
    }
}