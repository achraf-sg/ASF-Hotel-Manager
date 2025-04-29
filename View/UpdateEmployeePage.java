package View;

import Models.Employe;
import Models.Hotel;

import javax.swing.*;
import java.awt.*;

public class UpdateEmployeePage extends JFrame {
    private JTextField nameField, surnameField, addressField, phoneField, emailField;
    private JPasswordField passwordField;
    private JButton saveButton, cancelButton;

    public UpdateEmployeePage(Employe emp, Hotel model) {
        setTitle("Update Employee");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        nameField = new JTextField(emp.getNom(), 20);
        gbc.gridx = 1;
        add(nameField, gbc);

        // Surname Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Surname:"), gbc);
        surnameField = new JTextField(emp.getPrenom(), 20);
        gbc.gridx = 1;
        add(surnameField, gbc);

        // Address Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Address:"), gbc);
        addressField = new JTextField(emp.getAdresse(), 20);
        gbc.gridx = 1;
        add(addressField, gbc);

        // Phone Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Phone:"), gbc);
        phoneField = new JTextField(emp.getTelephone(), 20);
        gbc.gridx = 1;
        add(phoneField, gbc);

        // Email Field
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Email:"), gbc);
        emailField = new JTextField(emp.getEmail(), 20);
        gbc.gridx = 1;
        add(emailField, gbc);

        // Password Field
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(emp.getPassword(), 20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Save Button
        saveButton = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(saveButton, gbc);

        // Cancel Button
        cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        add(cancelButton, gbc);

        setVisible(true);
    }

    // Getters for fields and buttons
    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getSurnameField() {
        return surnameField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Create a dummy Hotel object (replace with actual implementation if needed)
        Hotel hotel = new Hotel("salah", "salah"); // Ensure the Hotel class is properly implemented

        // Create a dummy Employe object for testing
        Employe emp = new Employe("John", "Doe", "123 Main St", "1234567890", "john.doe@example.com", hotel, "password123");

        // Launch the UpdateEmployeePage
        SwingUtilities.invokeLater(() -> new UpdateEmployeePage(emp, hotel));
    }
}
