package miniProject.library;

// ==========================================
// 4. SUBCLASS: AudioBook
// ==========================================
public class AudioBook extends LibraryItem {
    private double durationHours;

    public AudioBook(String id, String title, double durationHours) {
        super(id, title);
        this.durationHours = durationHours;
    }

    public double getDurationHours() {
        return durationHours;
    }

    @Override
    public String getItemType() {
        return "AudioBook";
    }

    @Override
    public int getMaxLoanDays() {
        return 7; // 7 days loan duration
    }

    @Override
    public double calculateFine(int daysOverdue) {
        if (daysOverdue <= 0) {
            return 0.0;
        }

        double baseFine = daysOverdue * 2.0; // ₹2/day rate

        if (daysOverdue > 5) {
            return baseFine + 25.0; // Flat penalty added on top
        }

        return baseFine;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println(" | Duration: " + durationHours + " hrs");
    }
}