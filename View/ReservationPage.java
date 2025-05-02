// package View;

// import javax.swing.*;
// import javax.swing.border.EmptyBorder;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.time.LocalDate;
// import java.util.Vector;
// import Models.Reservation;
// import Models.RoomType;
// import Models.Hotel;
// import Models.DataInitializer;

// public class ReservationPage extends JFrame {
//     private JTable reservationTable;
//     private JTextField startDateField, endDateField, emailField;
//     private JButton confirmButton, addReservationButton;
//     private JComboBox<String> typeComboBox;
//     private JLabel availabilityLabel;
//     private JPanel emailPanel;
//     private JPanel rightPanel;

//     public ReservationPage(Vector<Reservation> reservations, Vector<RoomType> roomTypes) {
//         setTitle("Reservation Management");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(1200, 700);
//         setLocationRelativeTo(null);
//         setLayout(new BorderLayout());
//         getContentPane().setBackground(Color.WHITE);

//         // Header Panel
//         JPanel headerPanel = new JPanel();
//         headerPanel.setBackground(new Color(37, 99, 235));
//         headerPanel.setPreferredSize(new Dimension(800, 100));
//         JLabel titleLabel = new JLabel("Reservation Management", SwingConstants.CENTER);
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//         titleLabel.setForeground(Color.WHITE);
//         headerPanel.add(titleLabel);
//         add(headerPanel, BorderLayout.NORTH);

//         // Center Panel - Reservation Table
//         JPanel centerPanel = new JPanel(new BorderLayout());
//         centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

//         JLabel tableTitle = new JLabel("Reservations");
//         tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
//         tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
//         centerPanel.add(tableTitle, BorderLayout.NORTH);

//         String[] columnNames = {"ID", "Start Date", "End Date", "Email", "Room Type", "Delete"};
//         DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
//             @Override
//             public boolean isCellEditable(int row, int column) {
//                 return false; // Make all cells non-editable
//             }
//         };

//         reservationTable = new JTable(model);
//         reservationTable.setRowHeight(40);
//         reservationTable.setFont(new Font("Arial", Font.PLAIN, 14));

//         // Populate table
//         populateReservationTable(reservations);

//         JScrollPane scrollPane = new JScrollPane(reservationTable);
//         scrollPane.setBorder(BorderFactory.createEmptyBorder());
//         centerPanel.add(scrollPane, BorderLayout.CENTER);
//         add(centerPanel, BorderLayout.CENTER);

//         // Right Panel - Add Reservation Form
//         rightPanel = new JPanel();
//         rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
//         rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

//         JLabel formTitle = new JLabel("Add Reservation");
//         formTitle.setFont(new Font("Arial", Font.BOLD, 18));
//         formTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//         rightPanel.add(formTitle);
//         rightPanel.add(Box.createVerticalStrut(25));

//         // Start Date Field
//         rightPanel.add(createLabeledField("Start Date (YYYY-MM-DD):", startDateField = new JTextField()));
//         rightPanel.add(Box.createVerticalStrut(10));

//         // End Date Field
//         rightPanel.add(createLabeledField("End Date (YYYY-MM-DD):", endDateField = new JTextField()));
//         rightPanel.add(Box.createVerticalStrut(10));

//         // Confirm Button
//         confirmButton = new JButton("Confirm Dates");
//         confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
//         confirmButton.setBackground(new Color(37, 99, 235));
//         confirmButton.setForeground(Color.WHITE);
//         confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//         confirmButton.setMaximumSize(new Dimension(200, 40));
//         rightPanel.add(confirmButton);
//         rightPanel.add(Box.createVerticalStrut(25));

//         //Room Type Dropdown
//         JLabel typeLabel = new JLabel("Room Type:");
//         typeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//         typeLabel.setVisible(false); // Hide label initially
//         rightPanel.add(typeLabel);

//         typeComboBox = new JComboBox<>();
//         typeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
//         typeComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
//         typeComboBox.setVisible(false); // Hide dropdown initially
//         rightPanel.add(typeComboBox);
//         rightPanel.add(Box.createVerticalStrut(25));

//         // Email Panel
//         emailPanel = new JPanel();
//         emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
//         emailPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
//         emailPanel.setVisible(false); // Hide email panel initially
//         rightPanel.add(emailPanel);

//         // Add Reservation Button
//         addReservationButton = new JButton("Add Reservation");
//         addReservationButton.setFont(new Font("Arial", Font.BOLD, 14));
//         addReservationButton.setBackground(new Color(37, 99, 235));
//         addReservationButton.setForeground(Color.WHITE);
//         addReservationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//         addReservationButton.setMaximumSize(new Dimension(200, 40));
//         rightPanel.add(addReservationButton);

//         add(rightPanel, BorderLayout.EAST);

//         // Add Action Listener for Room Type Selection
//         typeComboBox.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 updateEmailField();
//             }
//         });

//         setVisible(true);
//     }

//     // Populate the reservation table with reservation data
//     public void populateReservationTable(Vector<Reservation> reservations) {
//         DefaultTableModel tableModel = (DefaultTableModel) reservationTable.getModel();
//         tableModel.setRowCount(0); // Clear the table

//         for (Reservation reservation : reservations) {
//             tableModel.addRow(new Object[]{
//                 reservation.getId(),
//                 reservation.getDateDeb(),
//                 reservation.getDateFin(),
//                 reservation.getClientMail(),
//                 reservation.getType().getName(),
//                 "Delete" // Placeholder for delete button
//             });
//         }
//     }

//     private JPanel createLabeledField(String label, JTextField field) {
//         JPanel panel = new JPanel(new BorderLayout());
//         JLabel jLabel = new JLabel(label);
//         jLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//         panel.add(jLabel, BorderLayout.NORTH);
//         field.setFont(new Font("Arial", Font.PLAIN, 14));
//         field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
//         panel.add(field, BorderLayout.CENTER);
//         return panel;
//     }

//     // Update the email field dynamically based on room type selection
//     private void updateEmailField() {
//         emailPanel.removeAll(); // Clear the email panel
//         String selectedType = (String) typeComboBox.getSelectedItem();
//         if (selectedType != null) {
//             JLabel emailLabel = new JLabel("Client Email:");
//             emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//             emailPanel.add(emailLabel);

//             emailField = new JTextField();
//             emailField.setFont(new Font("Arial", Font.PLAIN, 14));
//             emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
//             emailPanel.add(emailField);
//         }
//         emailPanel.revalidate();
//         emailPanel.repaint();
//     }

//     // showError method to display error messages
//     public void showError(String message) {
//         JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
//     }

//     // showMessage method to display success messages
//     public void showMessage(String message) {
//         JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
//     }

//     // Clear the form fields
//     public void clearForm() {
//         startDateField.setText("");
//         endDateField.setText("");
//         emailField.setText("");
//         typeComboBox.setSelectedIndex(-1); // Reset the dropdown
//     }

//     // Getters
//     public JTextField getStartDate() {
//         return startDateField;
//     }

//     public JTextField getEndDate() {
//         return endDateField;
//     }

//     public JComboBox<String> getTypeComboBox() {
//         return typeComboBox;
//     }

//     public JButton getConfirmDateButton() {
//         return confirmButton;
//     }

//     public JButton getAddButton() {
//         return addReservationButton;
//     }

//     public JTable getReservationTable() {
//         return reservationTable;
//     }

//     public JLabel getAvailabilityLabel() {
//         return availabilityLabel;
//     }

//     public JTextField getEmailField() {
//         return emailField;
//     }

//     public JTable getTable() {
//         return reservationTable;
//     }

//     public void updateRoomTypeDropdown(Vector<RoomType> roomTypes, Hotel hotel, LocalDate startDate, LocalDate endDate) {
//         // Show the room type components
//         for (Component comp : rightPanel.getComponents()) {
//             if (comp instanceof JLabel && ((JLabel) comp).getText().equals("Room Type:")) {
//                 comp.setVisible(true);
//             }
//         }
//         typeComboBox.setVisible(true);
//         emailPanel.setVisible(true);
        
//         // Clear and populate the dropdown
//         typeComboBox.removeAllItems();
//         for (RoomType roomType : roomTypes) {
//             int availableRooms = hotel.countChambresDisponibles(roomType.getName(), startDate, endDate);
//             if (availableRooms > 0) { // Only add room types with available rooms
//                 typeComboBox.addItem(roomType.getName() + " (" + availableRooms + " available)");
//             }
//         }
//     }

//     public static void main(String[] args) {
//         // Initialize the hotel using DataInitializer
//         Hotel hotel = DataInitializer.initializeHotel();

//         // Get the list of reservations and room types from the hotel
//         Vector<Reservation> reservations = hotel.getListRes();
//         Vector<RoomType> roomTypes = hotel.getListTypes();

//         // Launch the ReservationPage
//         SwingUtilities.invokeLater(() -> new ReservationPage(reservations, roomTypes));
//     }
// }
