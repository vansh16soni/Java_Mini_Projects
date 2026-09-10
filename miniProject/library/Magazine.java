package miniProject.library;
// 3. SUBCLASS: Magazine

class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String id, String title, int issueNumber) {
        super(id, title);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println(" | Issue No: #" + issueNumber + " (Magazine)");
    }
}