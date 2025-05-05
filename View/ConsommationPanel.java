// package View;

// import Models.Hotel;
// import Models.Sejour;
// import java.awt.*;
// import java.util.Vector;
// import javax.swing.*;
// import javax.swing.border.EmptyBorder;
// import javax.swing.table.DefaultTableModel;

// public class ConsommationPanel extends JPanel {
//     private JTable sejourTable;
//     private JTextField searchField;
//     private JButton searchButton;
//     private Hotel hotel;

//     public ConsommationPanel(Hotel hotel) {
//         this.hotel = hotel;
//         setLayout(new BorderLayout());
//         setBackground(Color.WHITE);

//         // Center Panel - Sejour Table
//         JPanel centerPanel = new JPanel(new BorderLayout());
//         centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

//         JLabel tableTitle = new JLabel("Current Stays");
//         tableTitle.setFont(StyleConfig.TITLE_FONT);
//         tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
//         centerPanel.add(tableTitle, BorderLayout.NORTH);

//         // Define table columns
//         String[] columnNames = {"ID", "Client Name", "Room Number", "Check-in Date", "Check-out Date", "Add Consumption","checkout"};
//         DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
//             @Override
//             public boolean isCellEditable(int row, int column) {
//                 return false; // Make all cells non-editable
//             }
//         };

//         sejourTable = new JTable(model);
//         sejourTable.setRowHeight(40);
//         sejourTable.setFont(StyleConfig.NORMAL_FONT);

//         // Populate table with ongoing sejours
//         showTable(hotel.getOngoingSejours());

//         JScrollPane scrollPane = new JScrollPane(sejourTable);
//         scrollPane.setBorder(BorderFactory.createEmptyBorder());
//         centerPanel.add(scrollPane, BorderLayout.CENTER);
//         add(centerPanel, BorderLayout.CENTER);

//         // Bottom Panel - Search
//         JPanel bottomPanel = new JPanel();
//         bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
//         bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

//         JLabel searchLabel = new JLabel("Search by Email:");
//         searchLabel.setFont(StyleConfig.NORMAL_FONT);
//         bottomPanel.add(searchLabel);

//         searchField = new JTextField();
//         searchField.setFont(StyleConfig.NORMAL_FONT);
//         searchField.setMaximumSize(new Dimension(300, 30));
//         bottomPanel.add(Box.createHorizontalStrut(10));
//         bottomPanel.add(searchField);

//         searchButton = new JButton("Search");
//         searchButton.setFont(StyleConfig.BUTTON_FONT);
//         searchButton.setBackground(StyleConfig.PRIMARY_COLOR);
//         searchButton.setForeground(Color.WHITE);
//         bottomPanel.add(Box.createHorizontalStrut(10));
//         bottomPanel.add(searchButton);

//         add(bottomPanel, BorderLayout.SOUTH);
//     }

//     // Populate the sejour table with data
//     public void showTable(Vector<Sejour> sejours) {
//         DefaultTableModel tableModel = (DefaultTableModel) sejourTable.getModel();
//         tableModel.setRowCount(0); // Clear the table

//         for (Sejour sejour : sejours) {
//             tableModel.addRow(new Object[]{
//                 sejour.getId(),
//                 sejour.getClient().getNom() + " " + sejour.getClient().getPrenom(),
//                 sejour.getChambre().getNumero(),
//                 sejour.getReservation().getDateDeb(),
//                 sejour.getReservation().getDateFin(),
//                 "Add Consumption" // Placeholder for add consumption button
//             });
//         }
//     }

//     // Show error message
//     public void showError(String message) {
//         JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
//     }

//     // Show success message
//     public void showMessage(String message) {
//         JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
//     }

//     // Getters
//     public JTextField getSearchField() {
//         return searchField;
//     }

//     public JButton getSearchButton() {
//         return searchButton;
//     }

//     public JTable getTable() {
//         return sejourTable;
//     }
// }