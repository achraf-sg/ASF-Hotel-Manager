package View;

import Models.Chambre;
import Models.Hotel;
import Models.RoomType;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class RoomPanel extends JPanel {
    private JTable roomTable;
    private JTextField numChamField, etageField;
    private JComboBox<RoomType> typeChamBox;
    private JButton addChamButton;
    private Hotel hotel;

    public RoomPanel(Hotel hotel) {
        this.hotel = hotel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Center Panel - Room Table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel tableTitle = new JLabel("Rooms");
        tableTitle.setFont(StyleConfig.TITLE_FONT);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        centerPanel.add(tableTitle, BorderLayout.NORTH);

        // Updated column names to include cleaning and availability status
        String[] columnNames = {"Number", "Floor", "Type", "Clean", "Available", "Delete"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        roomTable = new JTable(model);
        roomTable.setRowHeight(40);
        roomTable.setFont(StyleConfig.NORMAL_FONT);
        roomTable.getTableHeader().setReorderingAllowed(false);
        // Populate table
        populateRoomTable(hotel.getListCham());

        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Right Panel - Add Room Form
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel formTitle = new JLabel("Add Room");
        formTitle.setFont(StyleConfig.TITLE_FONT);
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
        typeLabel.setFont(StyleConfig.NORMAL_FONT);
        rightPanel.add(typeLabel);
        typeChamBox = new JComboBox<>(hotel.getListTypes());
        typeChamBox.setFont(StyleConfig.NORMAL_FONT);
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
        addChamButton.setFont(StyleConfig.BUTTON_FONT);
        addChamButton.setBackground(StyleConfig.PRIMARY_COLOR);
        addChamButton.setForeground(Color.WHITE);
        addChamButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addChamButton.setMaximumSize(new Dimension(200, 40));
        rightPanel.add(addChamButton);

        add(rightPanel, BorderLayout.EAST);
    }

    // Updated method to include clean and available status
    public void populateRoomTable(Vector<Chambre> rooms) {
        DefaultTableModel tableModel = (DefaultTableModel) roomTable.getModel();
        tableModel.setRowCount(0); // Clear the table

        for (Chambre room : rooms) {
            tableModel.addRow(new Object[]{
                room.getNumero(),
                room.getEtage(),
                room.getType().getName(),
                room.isClean() ? "Yes" : "No",  // Display "Yes" or "No" for clean status
                room.isAvailable() ? "Yes" : "No",  // Display "Yes" or "No" for availability
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
    public JTextField getNumChamField() { return numChamField; }
    public JTextField getEtageField() { return etageField; }
    public JComboBox<RoomType> getTypeChamBox() { return typeChamBox; }
    public JButton getAddChamButton() { return addChamButton; }
    public JTable getTable() { return roomTable; }
}