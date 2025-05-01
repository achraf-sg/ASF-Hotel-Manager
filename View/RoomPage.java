package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;
import Models.Chambre;
import Models.RoomType;
import Models.Hotel;
import Models.DataInitializer;

public class RoomPage extends JFrame {
    private JTable roomTable;
    private JTextField numChamField, etageField;
    private JComboBox<RoomType> typeChamBox;
    private JButton addChamButton;

    public RoomPage(Vector<Chambre> rooms, Vector<RoomType> roomTypes) {
        setTitle("Room Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(37, 99, 235));
        headerPanel.setPreferredSize(new Dimension(800, 100));
        JLabel titleLabel = new JLabel("Room Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Center Panel - Room Table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel tableTitle = new JLabel("Rooms");
        tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        centerPanel.add(tableTitle, BorderLayout.NORTH);

        String[] columnNames = {"Number", "Floor", "Type", "Delete"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        roomTable = new JTable(model);
        roomTable.setRowHeight(40);
        roomTable.setFont(new Font("Arial", Font.PLAIN, 14));

        // Populate table
        populateRoomTable(rooms);

        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Right Panel - Add Room Form
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel formTitle = new JLabel("Add Room");
        formTitle.setFont(new Font("Arial", Font.BOLD, 18));
        formTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(formTitle);
        rightPanel.add(Box.createVerticalStrut(25));

        // Room Number Field
        rightPanel.add(createLabeledField("Room Number:", numChamField = new JTextField()));
        rightPanel.add(Box.createVerticalStrut(10));

        // Floor Field
        rightPanel.add(createLabeledField("Floor:", etageField = new JTextField()));
        rightPanel.add(Box.createVerticalStrut(10));

        // Room Type Dropdown
        JLabel typeLabel = new JLabel("Room Type:");
        typeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        rightPanel.add(typeLabel);
        typeChamBox = new JComboBox<>(roomTypes);
        typeChamBox.setFont(new Font("Arial", Font.PLAIN, 14));
        typeChamBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        // Custom renderer to show room type name
        typeChamBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof RoomType) {
                    setText(((RoomType) value).getName());
                }
                return this;
            }
        });

        rightPanel.add(typeChamBox);
        rightPanel.add(Box.createVerticalStrut(25));

        // Add Button
        addChamButton = new JButton("Add Room");
        addChamButton.setFont(new Font("Arial", Font.BOLD, 14));
        addChamButton.setBackground(new Color(37, 99, 235));
        addChamButton.setForeground(Color.WHITE);
        addChamButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addChamButton.setMaximumSize(new Dimension(200, 40));
        rightPanel.add(addChamButton);

        add(rightPanel, BorderLayout.EAST);

        setVisible(true);
    }

    // Populate the room table with room data
    public void populateRoomTable(Vector<Chambre> rooms) {
        DefaultTableModel tableModel = (DefaultTableModel) roomTable.getModel();
        tableModel.setRowCount(0); // Clear the table

        for (Chambre room : rooms) {
            tableModel.addRow(new Object[]{
                room.getNumero(),
                room.getEtage(),
                room.getType().getName(),
                "Delete" // Placeholder for delete button
            });
        }
    }

    private JPanel createLabeledField(String label, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(jLabel, BorderLayout.NORTH);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    // method show error message
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // method show success message
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // method to clear the input fields
    public void clearForm() {
        numChamField.setText("");
        etageField.setText("");
        typeChamBox.setSelectedIndex(0);
    }

    // Getters
    public JTextField getNumChamField() {
        return numChamField;
    }

    public JTextField getEtageField() {
        return etageField;
    }

    public JComboBox<RoomType> getTypeChamBox() {
        return typeChamBox;
    }

    public JButton getAddChamButton() {
        return addChamButton;
    }

    public JTable getTable() {
        return roomTable;
    }

    public static void main(String[] args) {
        // Initialize the hotel using DataInitializer
        Hotel hotel = DataInitializer.initializeHotel();

        // Get the list of rooms and room types from the hotel
        Vector<Chambre> rooms = hotel.getListCham();
        Vector<RoomType> roomTypes = hotel.getListTypes();

        // Launch the RoomPage
        SwingUtilities.invokeLater(() -> new RoomPage(rooms, roomTypes));
    }
}
