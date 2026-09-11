package miniProject.library;

// 3. SUBCLASS: Magazine
public class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String id, String title, int issueNumber) {
        super(id, title);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    @Override
    public String getItemType() {
        return "Magazine";
    }

    @Override
    public int getMaxLoanDays() {
        return 3; // Short loan period
    }

    @Override
    public double calculateFine(int daysOverdue) {
        if (daysOverdue <= 0) return 0.0;
        return daysOverdue * 10.0; // Higher penalty: ₹10 per day
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println(" | Issue No: #" + issueNumber);
    }
}