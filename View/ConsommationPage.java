package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;
import Models.Consommation;
import Models.Hotel;

public class ConsommationPage extends JFrame {
  private JTable consommationTable;
  private JTextField productNameField;
  private JTextField quantityField;
  private JButton addButton;
  private JButton confirmButton;

  public ConsommationPage(Hotel hotel) {
    setTitle("Consommation");
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

    JLabel titleLabel = new JLabel("Consommation");
    titleLabel.setFont(StyleConfig.HEADER_FONT);
    titleLabel.setForeground(Color.WHITE);
    headerPanel.add(titleLabel, BorderLayout.WEST);

    add(headerPanel, BorderLayout.NORTH);

    // Main Content Panel
    JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
    contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

    // Left Panel - Table
    JPanel tablePanel = new JPanel(new BorderLayout());
    tablePanel.setBorder(BorderFactory.createTitledBorder("Produits consommés"));

    String[] columnNames = { "ID", "Nom", "Quantité", "Prix Unitaire", "Supprimer" };
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return column == getColumnCount() - 1; // Only the "Supprimer" column is editable
      }
    };

    consommationTable = new JTable(tableModel);
    consommationTable.setRowHeight(40);
    consommationTable.setFont(StyleConfig.NORMAL_FONT);
    JScrollPane tableScrollPane = new JScrollPane(consommationTable);
    tablePanel.add(tableScrollPane, BorderLayout.CENTER);

    contentPanel.add(tablePanel);

    // Right Panel - Form
    JPanel formPanel = new JPanel();
    formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
    formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter un produit"));

    // Product Name Field
    JPanel productNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel productNameLabel = new JLabel("Nom du produit:");
    productNameLabel.setFont(StyleConfig.NORMAL_FONT);
    productNameField = new JTextField(20);
    productNameField.setFont(StyleConfig.NORMAL_FONT);
    productNamePanel.add(productNameLabel);
    productNamePanel.add(productNameField);
    formPanel.add(productNamePanel);

    // Quantity Field
    JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel quantityLabel = new JLabel("Quantité:");
    quantityLabel.setFont(StyleConfig.NORMAL_FONT);
    quantityField = new JTextField(20);
    quantityField.setFont(StyleConfig.NORMAL_FONT);
    quantityPanel.add(quantityLabel);
    quantityPanel.add(quantityField);
    formPanel.add(quantityPanel);

    formPanel.add(Box.createVerticalStrut(20));

    // Buttons
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    addButton = new JButton("Ajouter Produit");
    addButton.setFont(StyleConfig.BUTTON_FONT);
    addButton.setBackground(StyleConfig.PRIMARY_COLOR);
    addButton.setForeground(Color.WHITE);
    buttonsPanel.add(addButton);

    confirmButton = new JButton("Confirmer");
    confirmButton.setFont(StyleConfig.BUTTON_FONT);
    confirmButton.setBackground(Color.GRAY);
    confirmButton.setForeground(Color.WHITE);
    buttonsPanel.add(confirmButton);

    formPanel.add(buttonsPanel);

    contentPanel.add(formPanel);

    add(contentPanel, BorderLayout.CENTER);
  }

  // Method to populate the consommation table
  public void remplirTableConsommation(Vector<Consommation> consommations) {
    DefaultTableModel tableModel = (DefaultTableModel) consommationTable.getModel();
    tableModel.setRowCount(0); // Clear the table

    for (Consommation consommation : consommations) {
      tableModel.addRow(new Object[]{
          consommation.getId(),
          consommation.getProduit().getNom(),
          consommation.getQuantite(),
          String.format("%.2f €", consommation.getProduit().getPrixUnit()),
          "Supprimer" // Placeholder for delete button
      });
    }
  }

  // Getters for components
  public JTable getTable() {
    return consommationTable;
  }

  public JTextField getNameField() {
    return productNameField;
  }

  public JTextField getQuantite() {
    return quantityField;
  }

  public JButton getAddButton() {
    return addButton;
  }

  public JButton getConfirmButton() {
    return confirmButton;
  }

  public void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
}

public void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
}

public void clearForm() {
  productNameField.setText(""); // Clear the product name field
  quantityField.setText("");   // Clear the quantity field
}
}