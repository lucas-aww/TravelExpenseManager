// src/gui/MainWindow.java
package gui;

import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * La classe MainWindow représente la fenêtre principale de l'application Travel Expense Manager.
 * Elle contient les panneaux pour gérer les voyages, les dépenses et le budget.
 */
public class MainWindow extends JFrame {
    private TripPanel tripPanel;
    private ExpensePanel expensePanel;
    private BudgetPanel budgetPanel;

    /**
     * Constructeur de la classe MainWindow.
     * Initialise la fenêtre principale avec les panneaux de gestion des voyages, des dépenses et du budget.
     */
    public MainWindow() {
        setTitle("Travel Expense Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Définition de la taille de la fenêtre
        setSize(800, 400);

        // Définition du layout en 5 zones (Nord, Sud, Est, Ouest, Centre)
        setLayout(new BorderLayout());

        // Initialisation des panneaux
        tripPanel = new TripPanel();
        budgetPanel = new BudgetPanel();
        expensePanel = new ExpensePanel(tripPanel, budgetPanel);

        // Écouteur d'événement pour la liste des voyages
        tripPanel.getTripList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Trip selectedTrip = tripPanel.getTripList().getSelectedValue();
                if (selectedTrip != null) {
                    budgetPanel.updateBudgetLabels(selectedTrip);
                }
            }
        });

        // Ajout des bordures autour des panneaux
        tripPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        expensePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        budgetPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des panneaux à la fenêtre
        add(tripPanel, BorderLayout.WEST);
        add(expensePanel, BorderLayout.CENTER);
        add(budgetPanel, BorderLayout.EAST);
    }

    /**
     * Méthode principale pour lancer l'application.
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}