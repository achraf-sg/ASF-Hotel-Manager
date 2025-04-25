// Login.java
package View;

import javax.swing.*;
import java.awt.*;
import Models.*;
import Controller.AdminController;

public class Login extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, resetButton;
    private JRadioButton adminRadio, employeeRadio;
    private ButtonGroup userTypeGroup;

    public Login(Hotel hotel) {
        setTitle("Login Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(StyleConfig.SMALL_WINDOW_SIZE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(StyleConfig.BACKGROUND_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Welcome Label
        JLabel helloLabel = new JLabel("Welcome Back!");
        helloLabel.setFont(StyleConfig.HEADER_FONT);

        // User Type Label
        JLabel userTypeLabel = new JLabel("User Type:");
        userTypeLabel.setFont(StyleConfig.TITLE_FONT);

        // Radio Buttons for User Type
        adminRadio = new JRadioButton("Admin");
        employeeRadio = new JRadioButton("Employee");
        adminRadio.setFont(StyleConfig.NORMAL_FONT);
        employeeRadio.setFont(StyleConfig.NORMAL_FONT);
        adminRadio.setBackground(StyleConfig.BACKGROUND_COLOR);
        employeeRadio.setBackground(StyleConfig.BACKGROUND_COLOR);

        userTypeGroup = new ButtonGroup();
        userTypeGroup.add(adminRadio);
        userTypeGroup.add(employeeRadio);
        adminRadio.setSelected(true);

        // Email and Password Labels
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(StyleConfig.TITLE_FONT);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(StyleConfig.TITLE_FONT);

        // Email and Password Fields
        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);

        // Buttons
        loginButton = new JButton("Login");
        loginButton.setFont(StyleConfig.BUTTON_FONT);
        loginButton.setBackground(StyleConfig.SUCCESS_COLOR);

        resetButton = new JButton("Reset");
        resetButton.setFont(StyleConfig.BUTTON_FONT);
        resetButton.setBackground(StyleConfig.ERROR_COLOR);

        // Layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(helloLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(userTypeLabel, gbc);

        gbc.gridx = 1;
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.setBackground(StyleConfig.BACKGROUND_COLOR);
        radioPanel.add(adminRadio);
        radioPanel.add(employeeRadio);
        add(radioPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(emailLabel, gbc);

        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(loginButton, gbc);

        gbc.gridx = 1;
        add(resetButton, gbc);

        // Add Action Listeners
        loginButton.addActionListener(e -> handleLogin(hotel));
        resetButton.addActionListener(e -> resetFields());

        setVisible(true);
    }

    /**
     * Handles the login logic for Admin and Employee.
     */
    private void handleLogin(Hotel hotel) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (adminRadio.isSelected()) {
            // Admin Login
            AdminController adminController = new AdminController(hotel);
            if (!adminController.authenticateAdmin(email, password)) {
                JOptionPane.showMessageDialog(this, "Invalid Admin credentials", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                dispose(); // Close the login window after successful admin login
            }
        } else if (employeeRadio.isSelected()) {
            // Employee Login
            boolean authenticated = false;
            for (Employe emp : hotel.getListEmp()) {
                if (!(emp instanceof Admin) && emp.getEmail().equals(email) && emp.getPassword().equals(password)) {
                    new HomePage(emp, hotel); // Open HomePage for Employee
                    dispose(); // Close the login window after successful employee login
                    authenticated = true;
                    break;
                }
            }
            if (!authenticated) {
                JOptionPane.showMessageDialog(this, "Invalid Employee credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Resets the email and password fields.
     */
    private void resetFields() {
        emailField.setText("");
        passwordField.setText("");
    }
}