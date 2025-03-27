// src/gui/ExpensePanel.java
package gui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//déclaration class ExpensePanel
public class ExpensePanel extends JPanel {

    //déclaration des variables
    private DefaultListModel<Expense> expenseListModel;//liste des dépenses
    private JList<Expense> expenseList;//liste des dépenses
    private JTextArea expenseDetailsArea;//détails des dépenses
    private JTextField descriptionField;//champ de saisie de la description
    private JTextField amountField;//champ de saisie du montant
    private TripPanel tripPanel;//panneau de voyage
    private BudgetPanel budgetPanel;//panneau de budget

    //constructeur ExpensePanel
    public ExpensePanel(TripPanel tripPanel, BudgetPanel budgetPanel) {
        this.tripPanel = tripPanel;
        this.budgetPanel = budgetPanel;

        //définition du layout
        setLayout(new BorderLayout());

        //Gestion de la liste des dépenses
        expenseListModel = new DefaultListModel<>();//modèle de liste des dépenses
        expenseList = new JList<>(expenseListModel);//liste des dépenses
        expenseDetailsArea = new JTextArea();//zone de texte pour les détails des dépenses
        expenseDetailsArea.setEditable(false);//zone de texte pour les détails des dépenses non modifiable

        //Gestion des détails des dépenses
        expenseList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Expense selectedExpense = expenseList.getSelectedValue();
                if (selectedExpense != null) {
                    expenseDetailsArea.setText(selectedExpense.toString());
                }
            }
        });

        //Disposition des champs de saisie en grille
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        descriptionField = new JTextField();
        amountField = new JTextField();

        inputPanel.add(new JLabel("Expense Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Expense Amount:"));
        inputPanel.add(amountField);

        //Bouton pour ajouter une dépense
        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.addActionListener(e -> {
            //Ajout de la description et du montant de la dépens
            try {
                String description = descriptionField.getText();
                double amount = Double.parseDouble(amountField.getText());

                Expense newExpense = new Expense(description, amount);
                Trip selectedTrip = tripPanel.getTripList().getSelectedValue();
                if (selectedTrip != null) {
                    selectedTrip.addExpense(newExpense);
                    expenseListModel.addElement(newExpense);
                    budgetPanel.updateBudgetLabels(selectedTrip);
                }

                // Clear input fields after adding the expense
                descriptionField.setText("");
                amountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ExpensePanel.this, "Please enter a valid number for amount.");
            }
        });

        //Bouton pour supprimer une dépense selectionnée
        JButton deleteExpenseButton = new JButton("Delete Expense");
        deleteExpenseButton.addActionListener(e -> {
            int selectedIndex = expenseList.getSelectedIndex();
            if (selectedIndex != -1) {
                Expense selectedExpense = expenseListModel.getElementAt(selectedIndex);
                Trip selectedTrip = tripPanel.getTripList().getSelectedValue();
                if (selectedTrip != null) {
                    selectedTrip.getExpenses().remove(selectedExpense);
                    expenseListModel.remove(selectedIndex);
                    budgetPanel.updateBudgetLabels(selectedTrip);
                }
            } else {
                JOptionPane.showMessageDialog(ExpensePanel.this, "Please select an expense to delete.");
            }
        });

        //Disposition des boutons dans différentes zones du BorderLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addExpenseButton);
        buttonPanel.add(deleteExpenseButton);

        add(new JScrollPane(expenseList), BorderLayout.WEST);//la liste des dépenses
        add(new JScrollPane(expenseDetailsArea), BorderLayout.CENTER);//La zone de texte affichant les détails de la dépense sélectionnée
        add(inputPanel, BorderLayout.NORTH);//Le panneau de saisie pour les informations de la dépense
        add(buttonPanel, BorderLayout.SOUTH);//Les boutons pour ajouter et supprimer des dépenses
    }
}