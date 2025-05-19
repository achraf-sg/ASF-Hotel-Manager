package View;

import Models.Hotel;
import Models.Produit;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ProduitPanel extends JPanel {
    private JTable produitTable;
    private JTextField nomField, prixField, quantiteField;
    private JButton addButton;
    private Hotel hotel;

    public ProduitPanel(Hotel hotel) {
        this.hotel = hotel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Add 20px margin around the entire panel

        // Center Panel - Product Table with rounded transparent styling
        JPanel centerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(189, 189, 189, 20)); // Semi-transparent grey background
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Rounded corners with 30px radius
            }
        };
        centerPanel.setOpaque(false); // Make the panel fully transparent
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 40)); // Increased right padding to 40px

        JLabel tableTitle = new JLabel("Products");
        tableTitle.setFont(StyleConfig.TITLE_FONT);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0)); // Add padding below the title
        centerPanel.add(tableTitle, BorderLayout.NORTH);

        String[] columnNames = {"Name", "Price", "Quantity", "Update", "Delete"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        produitTable = new JTable(model);
        produitTable.setRowHeight(40);
        produitTable.setFont(StyleConfig.NORMAL_FONT);
        produitTable.setOpaque(false);
        produitTable.setBackground(new Color(0, 0, 0, 0));

        // Make grid lines transparent
        produitTable.setShowGrid(false);  // Completely removes grid lines
        produitTable.setIntercellSpacing(new Dimension(0, 0));  // Remove spacing between cells
        produitTable.setGridColor(new Color(0, 0, 0, 0));  // Set grid color to transparent (as fallback)

        // Create custom header renderer to make headers transparent and bold
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel)super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(0, 0, 0, 0));
                label.setOpaque(false);
                label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                label.setFont(label.getFont().deriveFont(Font.BOLD));
                return label;
            }
        };
        headerRenderer.setHorizontalAlignment(JLabel.LEFT);
        produitTable.getTableHeader().setDefaultRenderer(headerRenderer);

        // Additional steps to ensure header transparency
        JTableHeader header = produitTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 0, 0, 0));
        header.setBorder(BorderFactory.createEmptyBorder());

        // Customize table rendering with proper icon handling
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(0, 0, 0, 0));
                
                // Special handling for icon columns
                if (column == 3 || column == 4) { // Update or Delete columns
                    setHorizontalAlignment(JLabel.LEFT);
                    setFont(new Font("SansSerif", Font.PLAIN, 20));
                } else {
                    setHorizontalAlignment(JLabel.LEFT);
                    setFont(StyleConfig.NORMAL_FONT);
                    if (isSelected) {
                        c.setForeground(Color.BLUE);
                    } else {
                        c.setForeground(Color.BLACK);
                    }
                }
                return c;
            }
        };
        produitTable.setDefaultRenderer(Object.class, centerRenderer);

        // Populate table
        populateProduitTable(hotel.getListProd());

        // Make the scroll pane and its viewport transparent too
        JScrollPane scrollPane = new JScrollPane(produitTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Create a wrapper panel for the right side that includes spacing
        JPanel rightSideWrapper = new JPanel(new BorderLayout());
        rightSideWrapper.setOpaque(false);

        // Add a spacer panel with fixed width
        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(20, 0)); // 20px wide spacer
        spacerPanel.setOpaque(false);
        rightSideWrapper.add(spacerPanel, BorderLayout.WEST);

        // Right Panel - Add Product Form
        JPanel rightPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(189, 189, 189, 20)); // Semi-transparent grey background
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Rounded corners with 30px radius
            }
        };
        rightPanel.setOpaque(false);
        rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create a container panel to hold the form components with BoxLayout
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setOpaque(false); // Make this container transparent too

        JLabel formTitle = new JLabel("Add Product");
        formTitle.setFont(StyleConfig.TITLE_FONT);
        formTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        formContainer.add(formTitle);
        formContainer.add(Box.createVerticalStrut(25));

        // Product fields
        formContainer.add(createLabeledField("Name:", nomField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(10));
        formContainer.add(createLabeledField("Price:", prixField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(10));
        formContainer.add(createLabeledField("Quantity:", quantiteField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(25));

        // Add Button
        addButton = new JButton("Add Product");
        addButton.setFont(StyleConfig.BUTTON_FONT);
        addButton.setBackground(StyleConfig.PRIMARY_COLOR);
        addButton.setForeground(Color.WHITE);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setMaximumSize(new Dimension(200, 40));
        formContainer.add(addButton);

        // Add the form container to the right panel
        rightPanel.add(formContainer, BorderLayout.CENTER);
        rightSideWrapper.add(rightPanel, BorderLayout.CENTER);
        add(rightSideWrapper, BorderLayout.EAST);
    }

    // Update populate method to use emoji icons
    public void populateProduitTable(Vector<Produit> produits) {
        DefaultTableModel tableModel = (DefaultTableModel) produitTable.getModel();
        tableModel.setRowCount(0); // Clear the table

        for (Produit produit : produits) {
            tableModel.addRow(new Object[]{
                produit.getNom(),
                produit.getPrixUnit(),
                produit.getQuantite(),
                "✏️", // Update icon
                "🗑️"  // Delete icon
            });
        }
    }

    private JPanel createLabeledField(String label, JTextField field) {
        // Use FlowLayout instead of BorderLayout
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setOpaque(false);
        
        // Create a container for the label to ensure it's on top
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelPanel.setOpaque(false);
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(StyleConfig.NORMAL_FONT);
        labelPanel.add(jLabel);
        
        // Create a wrapper panel to stack label above field
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setOpaque(false);
        
        // Configure the text field with custom border color and rounded corners
        field.setFont(StyleConfig.NORMAL_FONT);
        field.setColumns(20); // Set number of columns instead of dimension
        
        // Set custom border color with rounded corners
        Color borderColor = new Color(203, 203, 203);
        field.setBorder(new RoundedBorder(borderColor, 10));
        
        // Add components with spacing between label and field
        wrapper.add(labelPanel);
        wrapper.add(Box.createVerticalStrut(5)); // Add 5 pixels of vertical space
        wrapper.add(field);
        panel.add(wrapper);
        
        return panel;
    }

    // Custom rounded border class
    private class RoundedBorder extends AbstractBorder {
        private Color color;
        private int radius;
        
        RoundedBorder(Color color, int radius) {
            this.color = color;
            this.radius = radius;
        }
        
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
            g2.dispose();
        }
        
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 8, 4, 8);
        }
    }

    // Getters
    public JTextField getNomField() { return nomField; }
    public JTextField getPrixField() { return prixField; }
    public JTextField getQuantiteField() { return quantiteField; }
    public JButton getAddButton() { return addButton; }
    public JTable getTable() { return produitTable; }

    // Other methods remain the same
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void clearForm() {
        nomField.setText("");
        prixField.setText("");
        quantiteField.setText("");
    }
}