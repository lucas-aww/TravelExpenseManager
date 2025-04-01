// src/gui/MainWindow.java
package gui;

import model.*;

import javax.swing.*;
import java.awt.*;

//déclaration class MainWindow
public class MainWindow extends JFrame {
    private TripPanel tripPanel;
    private ExpensePanel expensePanel;
    private BudgetPanel budgetPanel;

    //constructeur MainWindow
    public MainWindow() {
        setTitle("Travel Expense Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //définition de la taille de la fenêtre
        setSize(800, 400);

        //définition du layout en 5 zones (Nord, Sud, Est, Ouest, Centre)
        setLayout(new BorderLayout());

        //objets de la classe TripPanel, ExpensePanel et BudgetPanel
        tripPanel = new TripPanel();
        budgetPanel = new BudgetPanel();
        expensePanel = new ExpensePanel(tripPanel, budgetPanel);

        //écouteur d'événement pour la liste des voyages
        tripPanel.getTripList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Trip selectedTrip = tripPanel.getTripList().getSelectedValue();
                if (selectedTrip != null) {
                    budgetPanel.updateBudgetLabels(selectedTrip);
                }
            }
        });

        //ajout des bordures autour des panneaux
        tripPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        expensePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        budgetPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //ajout des panneaux à la fenêtre
        add(tripPanel, BorderLayout.WEST);
        add(expensePanel, BorderLayout.CENTER);
        add(budgetPanel, BorderLayout.EAST);
    }

    //main
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}