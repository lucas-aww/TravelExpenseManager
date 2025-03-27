// src/gui/BudgetPanel.java
package gui;

import model.*;

import javax.swing.*;
import java.awt.*;

//déclaration class BudgetPanel
public class BudgetPanel extends JPanel {
    private JLabel totalExpensesLabel;
    private JLabel remainingBudgetLabel;
    private JLabel budgetStatusLabel;

    //constructeur BudgetPanel
    public BudgetPanel() {
        setLayout(new GridLayout(3, 1));

        //définition des labels
        totalExpensesLabel = new JLabel("Total Expenses: €0.0");
        remainingBudgetLabel = new JLabel("Remaining Budget: €0.0");
        budgetStatusLabel = new JLabel("Status: Within Budget");

        //ajout des labels
        add(totalExpensesLabel);
        add(remainingBudgetLabel);
        add(budgetStatusLabel);
    }

    //mise à jour des labels de budget
    public void updateBudgetLabels(Trip trip) {
        double totalExpenses = trip.getExpenses().stream().mapToDouble(Expense::getAmount).sum();
        double remainingBudget = trip.getInitialBudget() - totalExpenses;
        totalExpensesLabel.setText("Total Expenses: €" + totalExpenses);
        remainingBudgetLabel.setText("Remaining Budget: €" + remainingBudget);

        BudgetComparator budgetComparator = new BudgetComparator(trip.getInitialBudget());
        if (budgetComparator.isWithinBudget(trip)) {
            budgetStatusLabel.setText("Status: Within Budget");
        } else {
            budgetStatusLabel.setText("Status: Over Budget");
        }
    }
}