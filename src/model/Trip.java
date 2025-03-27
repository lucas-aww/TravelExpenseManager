package model;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    private static int idCounter = 0;
    private int id;
    private String destination;
    private User user;
    private List<Expense> expenses;
    private double initialBudget;

    public Trip(String destination, User user, double initialBudget) {
        this.id = ++idCounter;
        this.destination = destination;
        this.user = user;
        this.expenses = new ArrayList<>();
        this.initialBudget = initialBudget;
    }


    public String getDestination() {
        return destination;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public double getInitialBudget() {
        return initialBudget;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Trip ID: ").append(id).append("\n");
        details.append("Destination: ").append(destination).append("\n");
        details.append("User: ").append(user.getName()).append(" (").append(user.getEmail()).append(")\n");
        details.append("Initial Budget: €").append(initialBudget).append("\n");
        details.append("Expenses:\n");
        for (Expense expense : expenses) {
            details.append(" - ").append(expense.getDescription()).append(": €").append(expense.getAmount()).append("\n");
        }
        return details.toString();
    }

    @Override
    public String toString() {
        return destination;
    }
}