// src/gui/TripPanel.java
package gui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//déclaration class TripPanel
public class TripPanel extends JPanel {

    //déclaration des variables
    private DefaultListModel<Trip> tripListModel;
    private JList<Trip> tripList;
    private JTextArea tripDetailsArea;
    private JTextField destinationField;
    private JTextField userNameField;
    private JTextField userEmailField;
    private JTextField budgetField;

    //constructeur TripPanel
    public TripPanel() {
        setLayout(new BorderLayout());
        //Gestion de la listes des voyages
        tripListModel = new DefaultListModel<>();
        tripList = new JList<>(tripListModel);
        //Gestion des détails des voyages
        tripDetailsArea = new JTextArea();
        tripDetailsArea.setEditable(false);

        //
        tripList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Trip selectedTrip = tripList.getSelectedValue();
                if (selectedTrip != null) {
                    tripDetailsArea.setText(selectedTrip.getDetails());
                }
            }
        });

        //Gestion des champs de saisie
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        destinationField = new JTextField();
        userNameField = new JTextField();
        userEmailField = new JTextField();
        budgetField = new JTextField();

        //ajout des champs de saisie
        inputPanel.add(new JLabel("Destination:"));
        inputPanel.add(destinationField);
        inputPanel.add(new JLabel("User Name:"));
        inputPanel.add(userNameField);
        inputPanel.add(new JLabel("User Email:"));
        inputPanel.add(userEmailField);
        inputPanel.add(new JLabel("Initial Budget:"));
        inputPanel.add(budgetField);

        //Bouton pour ajouter un voyage
        JButton addTripButton = new JButton("Add Trip");
        addTripButton.addActionListener(e -> {
            try {
                String destination = destinationField.getText();
                String userName = userNameField.getText();
                String userEmail = userEmailField.getText();
                double initialBudget = Double.parseDouble(budgetField.getText());

                User user = new User(userName, userEmail);
                Trip newTrip = new Trip(destination, user, initialBudget);

                //Une fois le voyage ajouté, on l'ajoute à la liste des voyages
                tripListModel.addElement(newTrip);
                tripDetailsArea.setText(newTrip.getDetails());

                //On vide les champs de saisie
                destinationField.setText("");
                userNameField.setText("");
                userEmailField.setText("");
                budgetField.setText("");
            }
            //Gestion des exceptions
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TripPanel.this, "Please enter valid numbers for budget.");
            }
        });

        //Bouton pour supprimer un voyage
        JButton deleteTripButton = new JButton("Delete Trip");
        deleteTripButton.addActionListener(e -> {
            int selectedIndex = tripList.getSelectedIndex();
            if (selectedIndex != -1) {
                tripListModel.remove(selectedIndex);
                tripDetailsArea.setText("");
            } else {
                JOptionPane.showMessageDialog(TripPanel.this, "Please select a trip to delete.");
            }
        });

        //Gestion des boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addTripButton);
        buttonPanel.add(deleteTripButton);


        add(new JScrollPane(tripList), BorderLayout.WEST);//ajout de la liste des voyages

        add(new JScrollPane(tripDetailsArea), BorderLayout.CENTER);//ajout des détails des voyages

        add(inputPanel, BorderLayout.NORTH);//ajout des champs de saisie

        add(buttonPanel, BorderLayout.SOUTH);//ajout des boutons
    }

    //getter de la liste des voyages
    public JList<Trip> getTripList() {
        return tripList;
    }
}