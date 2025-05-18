package View;

import Controller.LoginController;
import Models.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class HeaderPanel extends JPanel {
    private JMenuItem logoutButton;
    private JPopupMenu dropdownMenu;
    
    public HeaderPanel(String title, Employe currentUser, Hotel hotel) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1200, 80));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Logo panel on the left
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        // Try to load logo image with fallback text
        JLabel logoLabel;
        try {
            ImageIcon logoIcon = new ImageIcon("Ressources/logo.png");
            Image scaledLogo = logoIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            logoLabel = new JLabel(new ImageIcon(scaledLogo));
        } catch (Exception e) {
            // Fallback if image loading fails
            logoLabel = new JLabel("🏨");
            logoLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        }
        
        JLabel textLabel = new JLabel("ASF");
        textLabel.setFont(new Font("Arial", Font.BOLD, 20));
        textLabel.setForeground(new Color(37, 99, 235));

        logoPanel.add(logoLabel);
        logoPanel.add(textLabel);
        

        add(logoPanel, BorderLayout.WEST);
        
        // Profile panel on the right
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(Color.WHITE);
        profilePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        // Create rounded container for profile with FlowLayout
        RoundedPanel roundedContainer = new RoundedPanel();
        roundedContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // Simple FlowLayout
        roundedContainer.setBackground(new Color(245, 245, 245));
        roundedContainer.setPreferredSize(new Dimension(220, 50));

        // User name and role panel
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(245, 245, 245));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Even padding all around

        JLabel nameLabel = new JLabel(currentUser.getNom() + " " + currentUser.getPrenom());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));

        // Determine role from user type
        String role = "Employee";
        if (currentUser instanceof Admin) {
            role = "Administrator";
        } else if (currentUser instanceof Reception) {
            role = "Receptionist";
        } else if (currentUser instanceof Menage) {
            role = "Housekeeper";
        }

        JLabel roleLabel = new JLabel(role);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        roleLabel.setForeground(Color.GRAY);

        namePanel.add(nameLabel);
        namePanel.add(roleLabel);

        // Dropdown arrow
        JLabel arrowLabel = new JLabel(" ▼");
        arrowLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        arrowLabel.setForeground(Color.DARK_GRAY);

        // Add components to rounded container
        roundedContainer.add(namePanel);
        roundedContainer.add(arrowLabel);

        // Create more attractive dropdown menu
        dropdownMenu = new JPopupMenu();
        dropdownMenu.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));

        logoutButton = new JMenuItem("Logout");
        logoutButton.setFont(StyleConfig.NORMAL_FONT);

        // Beautify the logout button
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(new Color(37, 99, 235)); // Match the ASF blue color
        logoutButton.setIcon(new ImageIcon("Ressources/logout_icon.png")); // If you have an icon
        // If icon is missing, create a text icon
        if (logoutButton.getIcon() == null) {
            logoutButton.setText("🚪 Logout"); // Door emoji as a visual indicator
        }
        logoutButton.setIconTextGap(10); // Space between icon and text
        logoutButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20)); // Add padding
        logoutButton.setOpaque(true);

        // Add hover effect
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logoutButton.setBackground(new Color(245, 248, 255)); // Light blue on hover
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                logoutButton.setBackground(Color.WHITE);
            }
        });

        // Add action listener to logout button (preserving functionality)
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
        
        dropdownMenu.add(logoutButton);
        
        // Show dropdown menu when clicking on the profile container
        roundedContainer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dropdownMenu.show(roundedContainer, 0, roundedContainer.getHeight());
            }
        });
        roundedContainer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        profilePanel.add(roundedContainer);
        add(profilePanel, BorderLayout.EAST);
    }
    
    public JMenuItem getLogoutButton() {
        return logoutButton;
    }
    
    // Custom rounded panel for the profile container
    private class RoundedPanel extends JPanel {
        public RoundedPanel() {
            setOpaque(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
        }
    }
}
