package miniProject.library;

// 2. SUBCLASS: Book
// Demonstrates Inheritance & Overriding

class Book extends LibraryItem {
    private String author;

    public Book(String id, String title, String author) {
        super(id, title); // Invoking the superclass constructor
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public void displayDetails() {
        super.displayDetails(); // Reuse base display formatting
        System.out.println(" | Author: " + author + " (Book)");
    }
}
