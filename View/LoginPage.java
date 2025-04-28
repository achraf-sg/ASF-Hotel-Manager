package View;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        setTitle("Login - Gestion Hôtel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // Centre la fenêtre à l'écran

        // Création des composants
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Se connecter");

        // Mise en page
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Mot de passe:"));
        panel.add(passwordField);
        panel.add(new JLabel()); // Cellule vide
        panel.add(loginButton);

        add(panel);
    }

    // Getters pour le contrôleur
    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    // Méthode pour afficher les erreurs
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}