package View;

import Models.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Vector;
import java.util.ArrayList;

public class CheckInFormPage extends JFrame {
    private JComboBox<String> roomComboBox;
    private JButton validateButton;
    private JButton cancelButton;
    private Reservation reservation;
    private JPanel clientsPanel;
    private ArrayList<JTextField[]> clientFields; // Stores [name, surname, email, address, phone] fields for each client

    public CheckInFormPage(Reservation reservation, Vector<Chambre> availableRooms) {
        this.reservation = reservation;
        this.clientFields = new ArrayList<>();
        setTitle("Complete Check-In");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(StyleConfig.PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(800, 60));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("Complete Check-In");
        titleLabel.setFont(StyleConfig.HEADER_FONT);
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        // Main Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Reservation Info Panel
        JPanel reservationInfoPanel = new JPanel();
        reservationInfoPanel.setLayout(new GridLayout(4, 2, 10, 10));
        reservationInfoPanel.setBorder(BorderFactory.createTitledBorder("Reservation Details"));

        reservationInfoPanel.add(new JLabel("Reservation ID:"));
        reservationInfoPanel.add(new JLabel(String.valueOf(reservation.getId())));

        reservationInfoPanel.add(new JLabel("Start Date:"));
        reservationInfoPanel.add(new JLabel(reservation.getDateDeb().toString()));

        reservationInfoPanel.add(new JLabel("End Date:"));
        reservationInfoPanel.add(new JLabel(reservation.getDateFin().toString()));

        reservationInfoPanel.add(new JLabel("Room Type:"));
        reservationInfoPanel.add(new JLabel(reservation.getType().getName()));

        contentPanel.add(reservationInfoPanel);
        contentPanel.add(Box.createVerticalStrut(15));

        // Room Selection Panel
        JPanel roomSelectionPanel = new JPanel();
        roomSelectionPanel.setLayout(new BoxLayout(roomSelectionPanel, BoxLayout.Y_AXIS));
        roomSelectionPanel.setBorder(BorderFactory.createTitledBorder("Room Selection"));

        JLabel roomLabel = new JLabel("Select Room:");
        roomLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        roomSelectionPanel.add(roomLabel);

        roomComboBox = new JComboBox<>();
        for (Chambre room : availableRooms) {
            roomComboBox.addItem("Room " + room.getNumero() + " (Floor " + room.getEtage() + ")");
        }
        roomComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        roomSelectionPanel.add(roomComboBox);

        contentPanel.add(roomSelectionPanel);
        contentPanel.add(Box.createVerticalStrut(15));

        // Client Information Panel (will be populated dynamically)
        clientsPanel = new JPanel();
        clientsPanel.setLayout(new BoxLayout(clientsPanel, BoxLayout.Y_AXIS));
        clientsPanel.setBorder(BorderFactory.createTitledBorder("Guest Information"));

        // Generate client fields based on room type max capacity
        generateClientFields(reservation.getType().getMaxPeople());

        JScrollPane clientsScrollPane = new JScrollPane(clientsPanel);
        clientsScrollPane.setBorder(null);
        clientsScrollPane.setPreferredSize(new Dimension(500, 300));
        contentPanel.add(clientsScrollPane);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(StyleConfig.BUTTON_FONT);
        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        buttonsPanel.add(cancelButton);

        validateButton = new JButton("Complete Check-In");
        validateButton.setFont(StyleConfig.BUTTON_FONT);
        validateButton.setBackground(StyleConfig.PRIMARY_COLOR);
        validateButton.setForeground(Color.WHITE);
        buttonsPanel.add(validateButton);

        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(buttonsPanel);

        JScrollPane mainScrollPane = new JScrollPane(contentPanel);
        mainScrollPane.setBorder(null);
        add(mainScrollPane, BorderLayout.CENTER);
    }

    // The rest of the methods (generateClientFields, createLabeledField, getters, etc.) remain unchanged

    
    /**
     * Generates fields for the specified number of clients
     * @param maxClients Maximum number of clients for this room type
     */
    public void generateClientFields(int maxClients) {
        clientsPanel.removeAll();
        clientFields.clear();
        
        JLabel infoLabel = new JLabel("Please enter details for each guest. First guest is the primary guest.");
        infoLabel.setFont(StyleConfig.NORMAL_FONT);
        clientsPanel.add(infoLabel);
        clientsPanel.add(Box.createVerticalStrut(10));
        
        for (int i = 0; i < maxClients; i++) {
            JPanel clientPanel = new JPanel();
            clientPanel.setLayout(new BoxLayout(clientPanel, BoxLayout.Y_AXIS));
            clientPanel.setBorder(BorderFactory.createTitledBorder("Guest " + (i+1) + 
                    (i == 0 ? " (Primary)" : "")));
            
            JTextField[] fields = new JTextField[5]; // name, surname, email, address, phone
            
            // Name field
            JPanel namePanel = createLabeledField("First Name:");
            fields[0] = new JTextField(20);
            fields[0].setFont(StyleConfig.NORMAL_FONT);
            namePanel.add(fields[0]);
            clientPanel.add(namePanel);
            
            // Surname field
            JPanel surnamePanel = createLabeledField("Last Name:");
            fields[1] = new JTextField(20);
            fields[1].setFont(StyleConfig.NORMAL_FONT);
            surnamePanel.add(fields[1]);
            clientPanel.add(surnamePanel);
            
            // Email field
            JPanel emailPanel = createLabeledField("Email:");
            fields[2] = new JTextField(20);
            fields[2].setFont(StyleConfig.NORMAL_FONT);
            fields[2].setEditable(false);
            if (i == 0 && reservation.getClientMail() != null) {
                // Pre-fill email for primary guest if available from reservation
                fields[2].setText(reservation.getClientMail());
            }
            emailPanel.add(fields[2]);
            clientPanel.add(emailPanel);
            
            // Phone field
            JPanel phonePanel = createLabeledField("Phone:");
            fields[4] = new JTextField(20);
            fields[4].setFont(StyleConfig.NORMAL_FONT);
            phonePanel.add(fields[4]);
            clientPanel.add(phonePanel);
            
            // Store the fields array
            clientFields.add(fields);
            
            // Add client panel to main panel
            clientsPanel.add(clientPanel);
            clientsPanel.add(Box.createVerticalStrut(10));
        }
        
        clientsPanel.revalidate();
        clientsPanel.repaint();
    }
    
    private JPanel createLabeledField(String labelText) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        label.setFont(StyleConfig.NORMAL_FONT);
        label.setPreferredSize(new Dimension(100, 25));
        panel.add(label);
        return panel;
    }
    
    // Show error message
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    // Show success message
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Getters for form elements
    public JComboBox<String> getRoomComboBox() {
        return roomComboBox;
    }
    
    public JButton getValidateButton() {
        return validateButton;
    }
    
    public JButton getCancelButton() {
        return cancelButton;
    }
    
    public JTextField getClientNameField(int index) {
        if (index < 0 || index >= clientFields.size()) {
            return null;
        }
        return clientFields.get(index)[0];
    }
    
    public JTextField getClientSurnameField(int index) {
        if (index < 0 || index >= clientFields.size()) {
            return null;
        }
        return clientFields.get(index)[1];
    }
    
    public JTextField getClientEmailField(int index) {
        if (index < 0 || index >= clientFields.size()) {
            return null;
        }
        return clientFields.get(index)[2];
    }
    
    public JTextField getClientAddressField(int index) {
        if (index < 0 || index >= clientFields.size()) {
            return null;
        }
        return clientFields.get(index)[3];
    }
    
    public JTextField getClientPhoneField(int index) {
        if (index < 0 || index >= clientFields.size()) {
            return null;
        }
        return clientFields.get(index)[4];
    }
}