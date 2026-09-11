package miniProject.library;

public abstract class LibraryItem implements Loanable {
    private String id;
    private String title;
    private boolean isIssued;

    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public boolean isIssued() { return isIssued; }

    public boolean issue() {
        if (isIssued) {
            System.out.println("❌ Error: Item is already issued.");
            return false;
        }
        this.isIssued = true;
        return true;
    }

    public boolean returnItem() {
        if (!isIssued) {
            System.out.println("❌ Error: Item was not issued.");
            return false;
        }
        this.isIssued = false;
        return true;
    }

    // Abstract method: every concrete subclass MUST define its own category name
    public abstract String getItemType();

    public void displayDetails() {
        System.out.print("[" + id + "] (" + getItemType() + ") \"" + title +
                "\" | Loan Period: " + getMaxLoanDays() + " days" +
                " | Status: " + (isIssued ? "Issued" : "Available"));
    }
}