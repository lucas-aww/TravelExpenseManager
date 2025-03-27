package model;

public class BudgetComparator {
    private double initialBudget;

    public BudgetComparator(double initialBudget) {
        this.initialBudget = initialBudget;
    }

    public boolean isWithinBudget(Trip trip) {
        double totalExpenses = trip.getExpenses().stream().mapToDouble(Expense::getAmount).sum();
        return totalExpenses <= initialBudget;
    }
}