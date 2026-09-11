package miniProject.library;

// 2. SUBCLASS: Book
// Demonstrates Inheritance & Overriding
public class Book extends LibraryItem {
    private String author;

    public Book(String id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String getItemType() {
        return "Book";
    }

    @Override
    public int getMaxLoanDays() {
        return 14; // Books can be kept for 2 weeks
    }

    @Override
    public double calculateFine(int daysOverdue) {
        if (daysOverdue <= 0) return 0.0;
        return daysOverdue * 5.0; // ₹5 per day overdue
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println(" | Author: " + author);
    }
}