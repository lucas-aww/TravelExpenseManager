// src/gui/MainWindow.java
package gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Travel Expense Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}