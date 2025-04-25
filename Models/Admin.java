package Models;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends Employe {

    public Admin(String nom, String prenom, String adresse, String telephone, String email, Hotel hotel,  String password) {
        super(nom, prenom, adresse, telephone, email, hotel,  password);
    }

    // Authenticate admin
    public boolean authenticate(String email, String password) {
        return this.getEmail().equals(email) && this.getPassword().equals(password);
    }

    // Show admin dashboard
    public void showDashboard() {
        JOptionPane.showMessageDialog(null, "Welcome to Admin Dashboard", "Admin Access", JOptionPane.INFORMATION_MESSAGE);
    }

    // ActionListener for login
    public ActionListener getLoginActionListener(JTextField emailField, JPasswordField passwordField, Hotel hotel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                for (Employe emp : hotel.getListEmp()) {
                    if (emp instanceof Admin && ((Admin) emp).authenticate(email, password)) {
                        showDashboard();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
    }
}