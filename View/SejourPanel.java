package View;

import Models.Hotel;
import Models.Sejour;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SejourPanel extends JPanel {
    private JTable SejourTable;
    private JTextField searchField;
    private JButton searchButton;
    private Hotel hotel;

    public SejourPanel(Hotel hotel) {
        this.hotel = hotel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Center Panel - Reservation Table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel tableTitle = new JLabel("Ongoing Stays");
        tableTitle.setFont(StyleConfig.TITLE_FONT);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        centerPanel.add(tableTitle, BorderLayout.NORTH);

        // Define table columns
        String[] columnNames = {"ID", "Room Number", "Room Type", "Client Email", "Buy Product", "Check Out"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        SejourTable = new JTable(model);
        SejourTable.setRowHeight(40);
        SejourTable.setFont(StyleConfig.NORMAL_FONT);

        // Populate table with upcoming reservations
        //RemplireTableSejour(hotel.getOngoingSejours());

        JScrollPane scrollPane = new JScrollPane(SejourTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel - Search
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel searchLabel = new JLabel("Search by Email:");
        searchLabel.setFont(StyleConfig.NORMAL_FONT);
        bottomPanel.add(searchLabel);

        searchField = new JTextField();
        searchField.setFont(StyleConfig.NORMAL_FONT);
        searchField.setMaximumSize(new Dimension(300, 30));
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

    // Populate the reservation table with reservation data
    public void remplirTableSejours(Vector <Sejour> sejours) {
        DefaultTableModel tableModel = (DefaultTableModel) SejourTable.getModel();
        tableModel.setRowCount(0); // Clear the table
        if (sejours == null) {
            return;
        }
        for (Sejour sejour : sejours) {
                tableModel.addRow(new Object[]{
                    sejour.getId(),
                    sejour.getChambre().getNumero(),
                    sejour.getChambre().getType().getName(),
                    sejour.getClient().getEmail(),
                    "Buy Product",
                    "Check Out"
                });
            }
    }

    // Show error message
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Show success message
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Getters
    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTable getSejoursTable() {
        return SejourTable;
    }
}