package View;

import Models.Hotel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage(Hotel hotel) {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set window to full screen
        setLayout(new BorderLayout());

        // Load custom font
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Ressources/fonts/KaiseiTokumin-Regular.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("SansSerif", Font.BOLD, 20); // Fallback font
        }

        // Left Blue Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(37, 99, 235));
        leftPanel.setPreferredSize(new Dimension(550, 700));
        add(leftPanel, BorderLayout.WEST);

        // Right Panel (Login Form)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new GridBagLayout());
        add(rightPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Try to load logo
        try {
            ImageIcon logoIcon = new ImageIcon("Ressources/logo.png");
            Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(scaledLogo));
            gbc.gridx = 0;
            gbc.gridy = 0;
            rightPanel.add(logo, gbc);
        } catch (Exception e) {
            // If logo can't be loaded, just continue without it
            System.out.println("Logo could not be loaded: " + e.getMessage());
        }

        // Title
        JLabel title = new JLabel("Log in to your Account", SwingConstants.LEFT);
        title.setFont(customFont.deriveFont(Font.BOLD, 30f));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 2, 10);
        rightPanel.add(title, gbc);

        // Subtitle
        JLabel subtitle = new JLabel("Welcome Back!", SwingConstants.LEFT);
        subtitle.setFont(customFont.deriveFont(Font.PLAIN, 20f));
        subtitle.setForeground(new Color(100, 100, 100));
        gbc.gridy++;
        gbc.insets = new Insets(2, 10, 50, 10);
        rightPanel.add(subtitle, gbc);

        // Username Field with Icon
        JPanel usernamePanel = new JPanel(new BorderLayout());
        usernamePanel.setPreferredSize(new Dimension(300, 40));
        usernamePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        usernamePanel.setBackground(new Color(230, 230, 230));

        // Try to load user icon
        JLabel userLabel = new JLabel();
        try {
            ImageIcon userIcon = new ImageIcon("Ressources/icons/utilisateur.png");
            userLabel = new JLabel(userIcon);
            userLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        } catch (Exception e) {
            userLabel.setText("👤"); // Fallback to emoji if icon not found
            System.out.println("User icon could not be loaded: " + e.getMessage());
        }

        usernameField = new JTextField("Email");
        usernameField.setBorder(BorderFactory.createEmptyBorder());
        usernameField.setForeground(Color.GRAY);
        usernameField.setBackground(new Color(230, 230, 230));
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (usernameField.getText().equals("Email")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Email");
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });

        usernamePanel.add(userLabel, BorderLayout.WEST);
        usernamePanel.add(usernameField, BorderLayout.CENTER);
        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);
        rightPanel.add(usernamePanel, gbc);

        // Password Field with Icon
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setPreferredSize(new Dimension(300, 40));
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        passwordPanel.setBackground(new Color(230, 230, 230));

        // Try to load password icon
        JLabel passwordLabel = new JLabel();
        try {
            ImageIcon passwordIcon = new ImageIcon("Ressources/icons/fermer-a-cle.png");
            passwordLabel = new JLabel(passwordIcon);
            passwordLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        } catch (Exception e) {
            passwordLabel.setText("🔒"); // Fallback to emoji if icon not found
            System.out.println("Password icon could not be loaded: " + e.getMessage());
        }

        passwordField = new JPasswordField("Password");
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setForeground(Color.GRAY);
        passwordField.setBackground(new Color(230, 230, 230));
        passwordField.setEchoChar((char) 0); // Show text instead of masking initially
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('•'); // Mask the password
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("Password");
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setEchoChar((char) 0); // Show text instead of masking
                }
            }
        });

        passwordPanel.add(passwordLabel, BorderLayout.WEST);
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        gbc.gridy++;
        rightPanel.add(passwordPanel, gbc);

        // Login Button
        loginButton = new JButton("Se connecter");
        loginButton.setPreferredSize(new Dimension(300, 40));
        loginButton.setBackground(new Color(37, 99, 235)); // Blue background
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridy++;
        rightPanel.add(loginButton, gbc);
    }

    // Getters for LoginController - preserved from original code
    public JTextField getEmailField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        // Initialize the Hotel model
        Hotel hotel = new Hotel("salah", "salah"); 

        // Launch the LoginPage
        SwingUtilities.invokeLater(() -> {
            LoginPage loginPage = new LoginPage(hotel);
            loginPage.setVisible(true);
        });
    }
}