// package View;

// import javax.swing.*;
// import javax.swing.border.EmptyBorder;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.util.Vector;
// import Models.Chambre;
// import Models.DataInitializer;
// import Models.Hotel;

// public class MenagePage extends JFrame {
//     private JTable cleaningTable;
//     private JTextField searchField;
//     private JButton searchButton;

//     public MenagePage(Vector<Chambre> rooms) {
//         setTitle("Room Cleaning Management");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(1200, 700);
//         setLocationRelativeTo(null);
//         setLayout(new BorderLayout());
//         getContentPane().setBackground(Color.WHITE);

//         // Header Panel
//         JPanel headerPanel = new JPanel();
//         headerPanel.setBackground(new Color(37, 99, 235));
//         headerPanel.setPreferredSize(new Dimension(800, 100));
//         JLabel titleLabel = new JLabel("Room Cleaning Management", SwingConstants.CENTER);
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//         titleLabel.setForeground(Color.WHITE);
//         headerPanel.add(titleLabel);
//         add(headerPanel, BorderLayout.NORTH);

//         // Center Panel - Cleaning Table
//         JPanel centerPanel = new JPanel(new BorderLayout());
//         centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

//         JLabel tableTitle = new JLabel("Rooms to Clean");
//         tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
//         tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
//         centerPanel.add(tableTitle, BorderLayout.NORTH);

//         String[] columnNames = {"Room Number", "Floor", "Is Clean", "Change Clean Status"};
//         DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
//             @Override
//             public boolean isCellEditable(int row, int column) {
//                 return column == 3; // Only the "Change Clean Status" column is editable
//             }
//         };

//         cleaningTable = new JTable(model);
//         cleaningTable.setRowHeight(40);
//         cleaningTable.setFont(new Font("Arial", Font.PLAIN, 14));

//         // Populate table
//         showTable(rooms);

//         JScrollPane scrollPane = new JScrollPane(cleaningTable);
//         scrollPane.setBorder(BorderFactory.createEmptyBorder());
//         centerPanel.add(scrollPane, BorderLayout.CENTER);

//         add(centerPanel, BorderLayout.CENTER);

//         // Bottom Panel - Search
//         JPanel bottomPanel = new JPanel();
//         bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
//         bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

//         JLabel searchLabel = new JLabel("Search Room:");
//         searchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//         bottomPanel.add(searchLabel);

//         searchField = new JTextField();
//         searchField.setFont(new Font("Arial", Font.PLAIN, 14));
//         searchField.setMaximumSize(new Dimension(200, 30));
//         bottomPanel.add(Box.createHorizontalStrut(10));
//         bottomPanel.add(searchField);

//         searchButton = new JButton("Search");
//         searchButton.setFont(new Font("Arial", Font.BOLD, 14));
//         searchButton.setBackground(new Color(37, 99, 235));
//         searchButton.setForeground(Color.WHITE);
//         bottomPanel.add(Box.createHorizontalStrut(10));
//         bottomPanel.add(searchButton);

//         add(bottomPanel, BorderLayout.SOUTH);

//         setVisible(true);
//     }

//     // Populate the cleaning table with only not clean rooms
//     public void showTable(Vector<Chambre> rooms) {
//         DefaultTableModel tableModel = (DefaultTableModel) cleaningTable.getModel();
//         tableModel.setRowCount(0); // Clear the table

//         for (Chambre room : rooms) {
//             if (!room.isClean()) { // Only add rooms that are not clean
//                 tableModel.addRow(new Object[]{
//                     room.getNumero(),
//                     room.getEtage(),
//                     "No", // Clean status is always "No" for not clean rooms
//                     "Change to Clean" // Button placeholder
//                 });
//             }
//         }
//     }

//     // Method to show an error message
//     public void showError(String message) {
//         JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
//     }

//     // Method to show a success message
//     public void showMessage(String message) {
//         JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
//     }

//     // Getters
//     public JTable getTable() {
//         return cleaningTable;
//     }

//     public JTextField getSearchField() {
//         return searchField;
//     }

//     public JButton getSearchButton() {
//         return searchButton;
//     }

//     public static void main(String[] args) {
//         // Initialize the hotel using DataInitializer
//         Hotel hotel = DataInitializer.initializeHotel();

//         // Get the list of rooms from the hotel
//         Vector<Chambre> rooms = hotel.getListCham();

//         // Launch the MenagePage
//         SwingUtilities.invokeLater(() -> new MenagePage(rooms));
//     }
// }
