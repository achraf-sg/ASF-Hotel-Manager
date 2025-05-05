package View;

import Models.Chambre;
import Models.Hotel;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MenagePanel extends JPanel {
    private JTable cleaningTable;
    private JTextField searchField;
    private JButton searchButton;
    private Hotel hotel;

    public MenagePanel(Hotel hotel) {
        this.hotel = hotel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Center Panel - Cleaning Table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel tableTitle = new JLabel("Rooms to Clean");
        tableTitle.setFont(StyleConfig.TITLE_FONT);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        centerPanel.add(tableTitle, BorderLayout.NORTH);

        String[] columnNames = {"Room Number", "Floor", "Is Clean", "Change Clean Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Only the "Change Clean Status" column is editable
            }
        };

        cleaningTable = new JTable(model);
        cleaningTable.setRowHeight(40);
        cleaningTable.setFont(StyleConfig.NORMAL_FONT);
        cleaningTable.getTableHeader().setReorderingAllowed(false);
        // Populate table
        showTable(hotel.getListCham());

        JScrollPane scrollPane = new JScrollPane(cleaningTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel - Search
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel searchLabel = new JLabel("Search Room:");
        searchLabel.setFont(StyleConfig.NORMAL_FONT);
        bottomPanel.add(searchLabel);

        searchField = new JTextField();
        searchField.setFont(StyleConfig.NORMAL_FONT);
        searchField.setMaximumSize(new Dimension(200, 30));
        bottomPanel.add(Box.createHorizontalStrut(10));
        bottomPanel.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setFont(StyleConfig.BUTTON_FONT);
        searchButton.setBackground(StyleConfig.PRIMARY_COLOR);
        searchButton.setForeground(Color.WHITE);
        bottomPanel.add(Box.createHorizontalStrut(10));
        bottomPanel.add(searchButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Populate the cleaning table with only not clean rooms
    public void showTable(Vector<Chambre> rooms) {
        DefaultTableModel tableModel = (DefaultTableModel) cleaningTable.getModel();
        tableModel.setRowCount(0); // Clear the table

        for (Chambre room : rooms) {
            if (!room.isClean()) { // Only add rooms that are not clean
                tableModel.addRow(new Object[]{
                    room.getNumero(),
                    room.getEtage(),
                    "No", // Clean status is always "No" for not clean rooms
                    "Change to Clean" // Button placeholder
                });
            }
        }
    }

    // Method to show an error message
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Method to show a success message
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Getters
    public JTable getTable() {
        return cleaningTable;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}