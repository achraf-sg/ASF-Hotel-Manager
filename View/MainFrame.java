package View;

import Controller.*;
import Models.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel contentPanel;
    private LeftPanel leftPanel;
    private Hotel hotel;
    private Employe currentUser;
    private Color defaultButtonColor = new Color(37, 99, 235);
    private Color selectedButtonColor = new Color(20, 60, 160);

    public MainFrame(Hotel hotel, Employe user) {
        this.hotel = hotel;
        this.currentUser = user;

        setTitle("Hotel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set window to full screen
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel header = new HeaderPanel("Dashboard", currentUser, hotel);
        add(header, BorderLayout.NORTH);

        // Initialize components
        initComponents();

        // Show default panel
        showDefaultPanel();

        setVisible(true);
    }

    private void initComponents() {
        // Content Panel (will be replaced when navigating)
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        add(contentPanel, BorderLayout.CENTER);

        // Create navigation items and their actions
        List<String> navOptions = new ArrayList<>();
        if (currentUser instanceof Admin) {
            navOptions = Arrays.asList("Dashboard", "Employees", "Reservations", "Rooms", "Products");
        } else if (currentUser instanceof Reception) {
            navOptions = Arrays.asList("Dashboard", "Reservations", "CheckIn", "Clients","Stays");
        } else if (currentUser instanceof Menage) {
            navOptions = Arrays.asList("Dashboard", "Cleaning");
        }
        

        // Create navigation actions
        Runnable[] actions = new Runnable[navOptions.size()];
        for (int i = 0; i < navOptions.size(); i++) {
            final String destination = navOptions.get(i);
            actions[i] = () -> navigateTo(destination);
        }

        // Create and add the left panel
        leftPanel = new LeftPanel(navOptions, actions);
        add(leftPanel, BorderLayout.WEST);
    }

    public void navigateTo(String destination) {
        // Clear content panel
        contentPanel.removeAll();

        // Update the active item in the sidebar
        leftPanel.setActiveItem(destination);

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
        } else if (destination.equals("CheckIn") && currentUser instanceof Reception) {
            newPanel = createCheckInPanel();
        } else if (destination.equals("Stays") && currentUser instanceof Reception) {
            newPanel = creatSejourPanel();
        } 
        
        if (newPanel != null) {
            contentPanel.add(newPanel, BorderLayout.CENTER);
        }

        // Refresh the frame
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showDefaultPanel() {
        navigateTo("Dashboard");
    }

    // Content panel creation methods
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        String welcomeMessage = "Welcome, " + currentUser.getPrenom() + " " + currentUser.getNom();
        JLabel welcomeLabel = new JLabel(welcomeMessage , JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(welcomeLabel);

        return panel;
    }

    private JPanel createEmployeePanel() {
        EmployeePanel panel = new EmployeePanel(hotel);
        new EmployeePanelController(hotel, (Admin) currentUser, panel); // Use panel controller
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

    private JPanel createCheckInPanel() {
        CheckInPanel panel = new CheckInPanel(hotel);
        new CheckInController(hotel, panel);
        return panel;
    }
    private JPanel creatSejourPanel() {
        SejourPanel panel = new SejourPanel(hotel);
        new SejourPanelController(hotel, panel);
        return panel;
    }
}