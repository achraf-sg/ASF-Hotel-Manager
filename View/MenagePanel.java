package View;

import Models.Chambre;
import Models.Hotel;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class MenagePanel extends JPanel {
    private JTable cleaningTable;
    private JTextField searchField;
    private JButton searchButton;
    private Hotel hotel;

    public MenagePanel(Hotel hotel) {
        this.hotel = hotel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Add 20px margin around entire panel

        // Center Panel - Cleaning Table with transparent styling
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
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel tableTitle = new JLabel("Rooms to Clean");
        tableTitle.setFont(StyleConfig.TITLE_FONT);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0)); // Add padding below the title
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
        cleaningTable.setOpaque(false);
        cleaningTable.setBackground(new Color(0, 0, 0, 0));

        // Make grid lines transparent
        cleaningTable.setShowGrid(false);  // Completely removes grid lines
        cleaningTable.setIntercellSpacing(new Dimension(0, 0));  // Remove spacing between cells
        cleaningTable.setGridColor(new Color(0, 0, 0, 0));  // Set grid color to transparent (as fallback)

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
        cleaningTable.getTableHeader().setDefaultRenderer(headerRenderer);

        // Additional steps to ensure header transparency
        JTableHeader header = cleaningTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 0, 0, 0));
        header.setBorder(BorderFactory.createEmptyBorder());

        // Force completely transparent table header
        header.setUI(new javax.swing.plaf.basic.BasicTableHeaderUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                // Skip background painting, just paint the header contents
                super.paint(g, c);
            }
        });

        // Customize table rendering 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(0, 0, 0, 0));
                
                // Special handling for action column
                if (column == 3) { // Change Clean Status column
                    setHorizontalAlignment(JLabel.LEFT);
                    setText("🧹"); // Broom emoji for cleaning
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
        cleaningTable.setDefaultRenderer(Object.class, centerRenderer);

        // Populate table
        showTable(hotel.getListCham());

        // Make the scroll pane and its viewport transparent too
        JScrollPane scrollPane = new JScrollPane(cleaningTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel - Search with improved styling
        JPanel bottomPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(189, 189, 189, 20)); // Semi-transparent grey background
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Rounded corners with 30px radius
            }
        };
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel searchLabel = new JLabel("Search Room:");
        searchLabel.setFont(StyleConfig.NORMAL_FONT);
        bottomPanel.add(searchLabel);

        searchField = new JTextField();
        searchField.setFont(StyleConfig.NORMAL_FONT);
        searchField.setPreferredSize(new Dimension(300, 30));
        
        // Apply consistent styling to search field
        Color borderColor = new Color(203, 203, 203);
        searchField.setBorder(new RoundedBorder(borderColor, 10));
        
        bottomPanel.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setFont(StyleConfig.BUTTON_FONT);
        searchButton.setBackground(StyleConfig.PRIMARY_COLOR);
        searchButton.setForeground(Color.WHITE);
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
                    "🧹" // Broom emoji for cleaning action
                });
            }
        }
    }

    // Custom rounded border class to match other panels
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