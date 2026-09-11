package miniProject.library;

public interface Loanable {
    int getMaxLoanDays();
    double calculateFine(int daysOverdue);
}