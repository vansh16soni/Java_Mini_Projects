package miniProject.library;

// ==========================================
// 4. SUBCLASS: AudioBook
// ==========================================
class AudioBook extends LibraryItem {
    private double durationHours;

    public AudioBook(String id, String title, double durationHours) {
        super(id, title);
        this.durationHours = durationHours;
    }

    public double getDurationHours() {
        return durationHours;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println(" | Duration: " + durationHours + " hrs (AudioBook)");
    }
}
