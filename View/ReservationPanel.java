package View;

import Models.Hotel;
import Models.Reservation;
import Models.RoomType;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReservationPanel extends JPanel {
    private JTable reservationTable;
    private JTextField startDateField, endDateField, emailField;
    private JButton confirmButton, addReservationButton;
    private JComboBox<String> typeComboBox;
    private JPanel emailPanel;
    private JPanel rightPanel;
    private Hotel hotel;

    public ReservationPanel(Hotel hotel) {
        this.hotel = hotel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Center Panel - Reservation Table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel tableTitle = new JLabel("Reservations");
        tableTitle.setFont(StyleConfig.TITLE_FONT);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        centerPanel.add(tableTitle, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Start Date", "End Date", "Email", "Room Type", "Delete"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        reservationTable = new JTable(model);
        reservationTable.setRowHeight(40);
        reservationTable.setFont(StyleConfig.NORMAL_FONT);
        reservationTable.getTableHeader().setReorderingAllowed(false);
        // Populate table
        populateReservationTable(hotel.getListRes());

        JScrollPane scrollPane = new JScrollPane(reservationTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Right Panel - Add Reservation Form
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel formTitle = new JLabel("Add Reservation");
        formTitle.setFont(StyleConfig.TITLE_FONT);
        formTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(formTitle);
        rightPanel.add(Box.createVerticalStrut(25));

        // Start Date Field
        rightPanel.add(createLabeledField("Start Date (YYYY-MM-DD):", startDateField = new JTextField()));
        rightPanel.add(Box.createVerticalStrut(10));

        // End Date Field
        rightPanel.add(createLabeledField("End Date (YYYY-MM-DD):", endDateField = new JTextField()));
        rightPanel.add(Box.createVerticalStrut(10));

        // Confirm Button
        confirmButton = new JButton("Confirm Dates");
        confirmButton.setFont(StyleConfig.BUTTON_FONT);
        confirmButton.setBackground(StyleConfig.PRIMARY_COLOR);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setMaximumSize(new Dimension(200, 40));
        rightPanel.add(confirmButton);
        rightPanel.add(Box.createVerticalStrut(25));

        // Room Type Dropdown
        JLabel typeLabel = new JLabel("Room Type:");
        typeLabel.setFont(StyleConfig.NORMAL_FONT);
        typeLabel.setVisible(false); // Hide label initially
        rightPanel.add(typeLabel);

        typeComboBox = new JComboBox<>();
        typeComboBox.setFont(StyleConfig.NORMAL_FONT);
        typeComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        typeComboBox.setVisible(false); // Hide dropdown initially
        rightPanel.add(typeComboBox);
        rightPanel.add(Box.createVerticalStrut(25));

        // Email Panel
        emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        emailPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        emailPanel.setVisible(false); // Hide email panel initially
        rightPanel.add(emailPanel);

        // Add Reservation Button
        addReservationButton = new JButton("Add Reservation");
        addReservationButton.setFont(StyleConfig.BUTTON_FONT);
        addReservationButton.setBackground(StyleConfig.PRIMARY_COLOR);
        addReservationButton.setForeground(Color.WHITE);
        addReservationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addReservationButton.setMaximumSize(new Dimension(200, 40));
        rightPanel.add(addReservationButton);

        add(rightPanel, BorderLayout.EAST);

        // Add Action Listener for Room Type Selection
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmailField();
            }
        });
    }

    // Populate the reservation table with reservation data
    public void populateReservationTable(Vector<Reservation> reservations) {
        DefaultTableModel tableModel = (DefaultTableModel) reservationTable.getModel();
        tableModel.setRowCount(0); // Clear the table

        for (Reservation reservation : reservations) {
            tableModel.addRow(new Object[]{
                reservation.getId(),
                reservation.getDateDeb(),
                reservation.getDateFin(),
                reservation.getClientMail(),
                reservation.getType().getName(),
                "Delete" // Placeholder for delete button
            });
        }
    }

    private JPanel createLabeledField(String label, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(StyleConfig.NORMAL_FONT);
        panel.add(jLabel, BorderLayout.NORTH);
        field.setFont(StyleConfig.NORMAL_FONT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    // Update the email field dynamically based on room type selection
    private void updateEmailField() {
        emailPanel.removeAll(); // Clear the email panel
        String selectedType = (String) typeComboBox.getSelectedItem();
        if (selectedType != null) {
            JLabel emailLabel = new JLabel("Client Email:");
            emailLabel.setFont(StyleConfig.NORMAL_FONT);
            emailPanel.add(emailLabel);

            emailField = new JTextField();
            emailField.setFont(StyleConfig.NORMAL_FONT);
            emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            emailPanel.add(emailField);
        }
        emailPanel.revalidate();
        emailPanel.repaint();
    }

    // showError method to display error messages
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // showMessage method to display success messages
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Clear the form fields
    public void clearForm() {
        startDateField.setText("");
        endDateField.setText("");
        if (emailField != null) {
            emailField.setText("");
        }
        typeComboBox.setSelectedIndex(-1); // Reset the dropdown
        
        // Hide the room type components
        for (Component comp : rightPanel.getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getText().equals("Room Type:")) {
                comp.setVisible(false);
            }
        }
        typeComboBox.setVisible(false);
        emailPanel.setVisible(false);
    }

    // Update room type dropdown based on availability
    public void updateRoomTypeDropdown(Vector<RoomType> roomTypes, Hotel hotel, LocalDate startDate, LocalDate endDate) {
        // Show the room type components
        for (Component comp : rightPanel.getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getText().equals("Room Type:")) {
                comp.setVisible(true);
            }
        }
        typeComboBox.setVisible(true);
        emailPanel.setVisible(true);
        
        // Clear and populate the dropdown
        typeComboBox.removeAllItems();
        for (RoomType roomType : roomTypes) {
            int availableRooms = hotel.countChambresDisponibles(roomType.getName(), startDate, endDate);
            if (availableRooms > 0) { // Only add room types with available rooms
                typeComboBox.addItem(roomType.getName() + " (" + availableRooms + " available)");
            }
        }
    }

    // Getters
    public JTextField getStartDate() { return startDateField; }
    public JTextField getEndDate() { return endDateField; }
    public JComboBox<String> getTypeComboBox() { return typeComboBox; }
    public JButton getConfirmDateButton() { return confirmButton; }
    public JButton getAddButton() { return addReservationButton; }
    public JTable getTable() { return reservationTable; }
    public JTextField getEmailField() { return emailField; }
}