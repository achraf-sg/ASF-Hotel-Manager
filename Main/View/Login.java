// Login.java
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Models.*;
import Controller.*;

public class Login extends JFrame {
    JButton login, reset;
    JTextField email;
    JPasswordField password;
    JLabel emailLabel, passwordLabel, helloLabel, userTypeLabel;
    JRadioButton adminRadio, employeeRadio;
    ButtonGroup userTypeGroup;
    Hotel hotel; // Reference to the hotel with all employees

    public Login(Hotel hotel) {
        this.hotel = hotel;
        setTitle("Login Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(StyleConfig.SMALL_WINDOW_SIZE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(StyleConfig.PRIMARY_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        helloLabel = new JLabel("Bienvenue");
        helloLabel.setBackground(StyleConfig.ERROR_COLOR);
        helloLabel.setFont(StyleConfig.HEADER_FONT);

        // User type selection
        userTypeLabel = new JLabel("User Type:");
        userTypeLabel.setFont(StyleConfig.TITLE_FONT);

        adminRadio = new JRadioButton("Admin");
        employeeRadio = new JRadioButton("Employee");
        adminRadio.setFont(StyleConfig.NORMAL_FONT);
        employeeRadio.setFont(StyleConfig.NORMAL_FONT);
        adminRadio.setBackground(StyleConfig.PRIMARY_COLOR);
        employeeRadio.setBackground(StyleConfig.PRIMARY_COLOR);

        userTypeGroup = new ButtonGroup();
        userTypeGroup.add(adminRadio);
        userTypeGroup.add(employeeRadio);
        adminRadio.setSelected(true);

        emailLabel = new JLabel("Email:");
        emailLabel.setFont(StyleConfig.TITLE_FONT);
        emailLabel.setBackground(StyleConfig.ERROR_COLOR);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(StyleConfig.TITLE_FONT);

        email = new JTextField(15);
        password = new JPasswordField(15);

        login = new JButton("Login");
        reset = new JButton("Reset");

        // Set background color for components
        email.setBackground(StyleConfig.BACKGROUND_COLOR);
        password.setBackground(StyleConfig.BACKGROUND_COLOR);
        login.setBackground(StyleConfig.SECONDARY_COLOR);
        reset.setBackground(StyleConfig.SECONDARY_COLOR);
        login.setFont(StyleConfig.BUTTON_FONT);
        reset.setFont(StyleConfig.BUTTON_FONT);

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
        radioPanel.setBackground(StyleConfig.PRIMARY_COLOR);
        radioPanel.add(adminRadio);
        radioPanel.add(employeeRadio);
        add(radioPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(emailLabel, gbc);

        gbc.gridx = 1;
        add(email, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        add(password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(login, gbc);

        gbc.gridx = 1;
        add(reset, gbc);

        // Add action listeners
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                email.setText("");
                password.setText("");
            }
        });

        setVisible(true);
    }

    private void authenticateUser() {
        String inputEmail = email.getText();
        String inputPassword = new String(password.getPassword());
        boolean isAdmin = adminRadio.isSelected();

        boolean authenticated = false;
        String role = isAdmin ? "Admin" : "Employee";

        // Use the appropriate controller for authentication
        if (isAdmin) {
            AdminController adminController = new AdminController(hotel);
            authenticated = adminController.authenticateAdmin(inputEmail, inputPassword);
        } else {
            EmployeController employeeController = new EmployeController(hotel);
            authenticated = employeeController.authenticateEmploye(inputEmail, inputPassword);
        }

        if (authenticated) {
            JOptionPane.showMessageDialog(this, "Login successful as " + role, "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            if (isAdmin) {
                AdminController adminController = new AdminController(hotel);
                adminController.showAdminDashboard();
            } else {
                EmployeController employeeController = new EmployeController(hotel);
                employeeController.showEmployeDashboard();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials or user type", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}