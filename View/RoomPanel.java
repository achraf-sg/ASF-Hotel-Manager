package View;

import Models.Chambre;
import Models.Hotel;
import Models.RoomType;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Add 20px margin around the entire panel

        // Center Panel - Room Table with rounded transparent styling
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

        JLabel tableTitle = new JLabel("Rooms");
        tableTitle.setFont(StyleConfig.TITLE_FONT);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0)); // Add padding below the title
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
        roomTable.setOpaque(false);
        roomTable.setBackground(new Color(0, 0, 0, 0));

        // Make grid lines transparent
        roomTable.setShowGrid(false);  // Completely removes grid lines
        roomTable.setIntercellSpacing(new Dimension(0, 0));  // Remove spacing between cells
        roomTable.setGridColor(new Color(0, 0, 0, 0));  // Set grid color to transparent (as fallback)

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
        roomTable.getTableHeader().setDefaultRenderer(headerRenderer);

        // Additional steps to ensure header transparency
        JTableHeader header = roomTable.getTableHeader();
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

        // Customize table rendering with proper icon handling
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(0, 0, 0, 0));
                
                // Special handling for icon column
                if (column == 5) { // Delete column
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
        roomTable.setDefaultRenderer(Object.class, centerRenderer);

        // Populate table
        populateRoomTable(hotel.getListCham());

        // Make the scroll pane and its viewport transparent too
        JScrollPane scrollPane = new JScrollPane(roomTable);
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

        // Right Panel - Add Room Form
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

        JLabel formTitle = new JLabel("Add Room");
        formTitle.setFont(StyleConfig.TITLE_FONT);
        formTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        formContainer.add(formTitle);
        formContainer.add(Box.createVerticalStrut(25));

        // Room Number Field
        formContainer.add(createLabeledField("Room Number:", numChamField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(10));

        // Floor Field
        formContainer.add(createLabeledField("Floor:", etageField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(10));

        // Room Type Dropdown - Use similar approach as text fields
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        typePanel.setOpaque(false);

        // Create container for label same as other fields
        JPanel typeLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        typeLabelPanel.setOpaque(false);
        JLabel typeLabel = new JLabel("Room Type:");
        typeLabel.setFont(StyleConfig.NORMAL_FONT);
        typeLabelPanel.add(typeLabel);

        // Create wrapper for alignment
        JPanel typeWrapper = new JPanel();
        typeWrapper.setLayout(new BoxLayout(typeWrapper, BoxLayout.Y_AXIS));
        typeWrapper.setOpaque(false);

        // Configure the combo box with matching styling
        typeChamBox = new JComboBox<>(hotel.getListTypes());
        typeChamBox.setFont(StyleConfig.NORMAL_FONT);
        typeChamBox.setBackground(Color.WHITE);
        typeChamBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, typeChamBox.getPreferredSize().height));

        // Apply the same border as the text fields
        Color borderColor = new Color(203, 203, 203);
        typeChamBox.setBorder(new RoundedBorder(borderColor, 10));

        // Custom renderer to show room type name
        typeChamBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // Change from getTableCellRendererComponent to getListCellRendererComponent
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof RoomType) {
                    setText(((RoomType) value).getName());
                }
                return this;
            }
        });

        // Custom UI for simple down arrow
        typeChamBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public void paint(Graphics g) {
                        int width = getWidth();
                        int height = getHeight();
                        Graphics2D g2 = (Graphics2D)g;
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(Color.DARK_GRAY);
                        
                        // Draw simple down arrow
                        int x = width/2;
                        int y = height/2;
                        int size = 8;
                        int[] xPoints = {x-size/2, x+size/2, x};
                        int[] yPoints = {y-size/3, y-size/3, y+size/3};
                        g2.fillPolygon(xPoints, yPoints, 3);
                    }
                    
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(20, 20);
                    }
                };
            }
        });

        // Add components with same spacing pattern
        typeWrapper.add(typeLabelPanel);
        typeWrapper.add(Box.createVerticalStrut(5));
        typeWrapper.add(typeChamBox);
        typePanel.add(typeWrapper);
        formContainer.add(typePanel);
        formContainer.add(Box.createVerticalStrut(25));

        // Add Button
        addChamButton = new JButton("Add Room");
        addChamButton.setFont(StyleConfig.BUTTON_FONT);
        addChamButton.setBackground(StyleConfig.PRIMARY_COLOR);
        addChamButton.setForeground(Color.WHITE);
        addChamButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addChamButton.setMaximumSize(new Dimension(200, 40));
        formContainer.add(addChamButton);

        // Add the form container to the right panel
        rightPanel.add(formContainer, BorderLayout.CENTER);
        rightSideWrapper.add(rightPanel, BorderLayout.CENTER);
        add(rightSideWrapper, BorderLayout.EAST);
    }

    // Updated method to include clean and available status with emoji icons
    public void populateRoomTable(Vector<Chambre> rooms) {
        DefaultTableModel tableModel = (DefaultTableModel) roomTable.getModel();
        tableModel.setRowCount(0); // Clear the table

        for (Chambre room : rooms) {
            tableModel.addRow(new Object[]{
                room.getNumero(),
                room.getEtage(),
                room.getType().getName(),
                room.isClean() ? "Yes" : "No",
                room.isAvailable() ? "Yes" : "No",
                "🗑️" // Trash bin emoji for delete
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

    // Existing methods remain the same
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

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