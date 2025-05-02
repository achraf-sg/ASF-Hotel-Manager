package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Models.*;
import Controller.LoginController;

public class HeaderPanel extends JPanel {
    private JButton logoutButton;
    
    public HeaderPanel(String title, Employe currentUser, Hotel hotel) {
        setLayout(new BorderLayout());
        setBackground(StyleConfig.PRIMARY_COLOR);
        setPreferredSize(new Dimension(800, 80));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Title on the left
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(StyleConfig.HEADER_FONT);
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.WEST);
        
        // Right panel for user info and logout button
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightPanel.setOpaque(false);
        
        // User name label
        JLabel userLabel = new JLabel(currentUser.getNom() + " " + currentUser.getPrenom());
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setForeground(Color.WHITE);
        rightPanel.add(userLabel);
        
        // Logout button
        logoutButton = new JButton("Logout");
        logoutButton.setFont(StyleConfig.BUTTON_FONT);
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(StyleConfig.PRIMARY_COLOR);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        
        // Add action listener to logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current frame
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(HeaderPanel.this);
                currentFrame.dispose();
                
                // Launch the LoginPage
                SwingUtilities.invokeLater(() -> {
                    LoginPage loginPage = new LoginPage(hotel);
                    new LoginController(hotel, loginPage);
                    loginPage.setVisible(true);
                });
            }
        });
        
        rightPanel.add(logoutButton);
        add(rightPanel, BorderLayout.EAST);
    }
    
    public JButton getLogoutButton() {
        return logoutButton;
    }
}
