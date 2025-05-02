package View;

import Models.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import Controller.*;
import java.util.List;


public class MainFrame extends JFrame {
    private JPanel contentPanel;
    private JPanel leftPanelContainer;
    private List<JButton> navigationButtons = new ArrayList<>();
    private Hotel hotel;
    private Employe currentUser;
    private Color defaultButtonColor = new Color(37, 99, 235);
    private Color selectedButtonColor = new Color(20, 60, 160);

    public MainFrame(Hotel hotel, Employe user) {
        this.hotel = hotel;
        this.currentUser = user;
        
        setTitle("Hotel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Header Panel
        JPanel header = new HeaderPanel("Dashboard", currentUser, hotel);        
        add(header, BorderLayout.NORTH);
        
        // Left Panel Container
        leftPanelContainer = new JPanel(new BorderLayout());
        leftPanelContainer.setPreferredSize(new Dimension(200, 700));
        leftPanelContainer.setBackground(new Color(240, 240, 240));
        add(leftPanelContainer, BorderLayout.WEST);
        
        // Content Panel (will be replaced when navigating)
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        add(contentPanel, BorderLayout.CENTER);
        
        // Create navigation based on user role
        createNavigation();
        
        // Show default panel
        showDefaultPanel();
        
        setVisible(true);
    }
    
    private void createNavigation() {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        navPanel.setBackground(new Color(240, 240, 240));
        
        // Create different navigation options based on user type
        List<String> navOptions = new ArrayList<>();
        
        if (currentUser instanceof Admin) {
            navOptions = Arrays.asList("Dashboard", "Employees", "Reservations", "Rooms", "Products");
        } else if (currentUser instanceof Reception) {
            navOptions = Arrays.asList("Dashboard", "Reservations", "Clients");
        } else if (currentUser instanceof Menage) {
            navOptions = Arrays.asList("Dashboard", "Cleaning");
        }
        
        // Create buttons for each navigation option
        for (String option : navOptions) {
            JButton navButton = createNavButton(option);
            navigationButtons.add(navButton);
            navPanel.add(navButton);
            navPanel.add(Box.createVerticalStrut(10));
        }
        
        leftPanelContainer.add(navPanel, BorderLayout.NORTH);
    }
    
    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(defaultButtonColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setMaximumSize(new Dimension(200, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add action listener
        button.addActionListener(e -> navigateTo(text));
        
        return button;
    }
    
    public void navigateTo(String destination) {
        // Clear content panel
        contentPanel.removeAll();
        
        // Update button highlighting
        updateButtonHighlighting(destination);
        
        // Load the appropriate panel based on destination
        JPanel newPanel = null;
        
        if (destination.equals("Dashboard")) {
            newPanel = createDashboardPanel();
        } else if (destination.equals("Employees") && currentUser instanceof Admin) {
            newPanel = createEmployeePanel();
        } else if (destination.equals("Reservations")) {
            newPanel = createReservationPanel();
        } else if (destination.equals("Rooms") && currentUser instanceof Admin) {
            newPanel = createRoomPanel();
        } else if (destination.equals("Products") && currentUser instanceof Admin) {
            newPanel = createProductPanel();
        } else if (destination.equals("Clients") && currentUser instanceof Reception) {
            newPanel = createClientPanel();
        } else if (destination.equals("Cleaning") && currentUser instanceof Menage) {
            newPanel = createCleaningPanel();
        }
        
        if (newPanel != null) {
            contentPanel.add(newPanel, BorderLayout.CENTER);
        }
        
        // Refresh the frame
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void updateButtonHighlighting(String selectedOption) {
        for (JButton button : navigationButtons) {
            if (button.getText().equals(selectedOption)) {
                button.setBackground(selectedButtonColor);
            } else {
                button.setBackground(defaultButtonColor);
            }
        }
    }
    
    private void showDefaultPanel() {
        navigateTo("Dashboard");
    }
    
    // Content panel creation methods
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        
        JLabel welcomeLabel = new JLabel("Welcome to Hotel Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(welcomeLabel);
        
        return panel;
    }
    
    private JPanel createEmployeePanel() {
        EmployeePanel panel = new EmployeePanel(hotel);
        new EmployeePanelController(hotel, (Admin)currentUser, panel); // Use panel controller
        return panel;
    }
    
    private JPanel createReservationPanel() {
        // Clean up any expired reservations
        hotel.cleanupExpiredReservations();
        
        ReservationPanel panel = new ReservationPanel(hotel);
        new ReservationPanelController(hotel, panel);
        return panel;
    }
    
    private JPanel createRoomPanel() {
        RoomPanel panel = new RoomPanel(hotel);
        new RoomPanelController(hotel, panel); // No Admin parameter
        return panel;
    }
    
    private JPanel createProductPanel() {
        ProduitPanel panel = new ProduitPanel(hotel);
        new ProductPanelController(hotel, panel); // No Admin parameter
        return panel;
    }
    
    private JPanel createClientPanel() {
        ClientPanel panel = new ClientPanel(hotel);
        new ClientPanelController(hotel, panel);
        return panel;
    }
    
    private JPanel createCleaningPanel() {
        MenagePanel panel = new MenagePanel(hotel);
        new CleaningPanelController(hotel, panel);
        return panel;
    }
}