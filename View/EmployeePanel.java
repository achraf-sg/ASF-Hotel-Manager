package View;

import Models.Employe;
import Models.Hotel;
import Models.Menage;
import Models.Reception;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class EmployeePanel extends JPanel {
    private JTable employeeTable;
    private JTextField nameField, surnameField, addressField, phoneField, emailField, passwordField;
    private JComboBox<String> functionComboBox;
    private JButton addButton;

    public EmployeePanel(Hotel hotel) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Add 20px margin around the entire panel


        // Center Panel - Employee Table
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


        JLabel tableTitle = new JLabel("Employees");
        tableTitle.setFont(StyleConfig.TITLE_FONT);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0)); // Add padding below the title
        centerPanel.add(tableTitle, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Full Name", "Adress", "Phone Num", "Email", "Function", "Edit", "Delete"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        employeeTable = new JTable(model);
        employeeTable.setRowHeight(40);
        employeeTable.setFont(StyleConfig.NORMAL_FONT);
        employeeTable.setOpaque(false);
        employeeTable.setBackground(new Color(0, 0, 0, 0));

        // Make grid lines transparent
        employeeTable.setShowGrid(false);  // Completely removes grid lines
        employeeTable.setIntercellSpacing(new Dimension(0, 0));  // Remove spacing between cells
        employeeTable.setGridColor(new Color(0, 0, 0, 0));  // Set grid color to transparent (as fallback)

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
        headerRenderer.setHorizontalAlignment(JLabel.LEFT);  // Changed from CENTER to LEFT
        employeeTable.getTableHeader().setDefaultRenderer(headerRenderer);

        // Additional steps to ensure header transparency
        JTableHeader header = employeeTable.getTableHeader();
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
                
                // Special handling for icon columns
                if (column == 6 || column == 7) { // Edit or Delete columns
                    setHorizontalAlignment(JLabel.LEFT);
                    // Use larger font for the icons
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
        employeeTable.setDefaultRenderer(Object.class, centerRenderer);

        // Populate table
        remplirTableEmployes(hotel.getListEmp());

        // Make the scroll pane and its viewport transparent too
        JScrollPane scrollPane = new JScrollPane(employeeTable);
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

        // Right Panel - Add Employee Form
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

        JLabel formTitle = new JLabel("Add Employee");
        formTitle.setFont(StyleConfig.TITLE_FONT);
        formTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        formContainer.add(formTitle);
        formContainer.add(Box.createVerticalStrut(25));

        // Name Fields
        formContainer.add(createLabeledField("Name:", nameField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(10)); 
        formContainer.add(createLabeledField("Surname:", surnameField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(10));
        formContainer.add(createLabeledField("Address:", addressField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(10)); 
        formContainer.add(createLabeledField("Phone:", phoneField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(10));
        formContainer.add(createLabeledField("Email:", emailField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(10));
        formContainer.add(createLabeledField("Password:", passwordField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(10));

        // Function Dropdown - Use similar approach as text fields
        JPanel functionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        functionPanel.setOpaque(false);

        // Create container for label same as other fields
        JPanel functionLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        functionLabelPanel.setOpaque(false);
        JLabel functionLabel = new JLabel("Function:");
        functionLabel.setFont(StyleConfig.NORMAL_FONT);
        functionLabelPanel.add(functionLabel);

        // Create wrapper for alignment
        JPanel functionWrapper = new JPanel();
        functionWrapper.setLayout(new BoxLayout(functionWrapper, BoxLayout.Y_AXIS));
        functionWrapper.setOpaque(false);

        // Configure the combo box with matching styling
        functionComboBox = new JComboBox<>(new String[]{"Receptionist", "Cleaner"});
        functionComboBox.setFont(StyleConfig.NORMAL_FONT);
        functionComboBox.setBackground(Color.WHITE);
        functionComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, functionComboBox.getPreferredSize().height));

        // Apply the same border as the text fields
        Color borderColor = new Color(203, 203, 203);
        functionComboBox.setBorder(new RoundedBorder(borderColor, 10));

        // Custom UI for simple down arrow
        functionComboBox.setUI(new BasicComboBoxUI() {
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
        functionWrapper.add(functionLabelPanel);
        functionWrapper.add(Box.createVerticalStrut(5));
        functionWrapper.add(functionComboBox);
        functionPanel.add(functionWrapper);
        formContainer.add(functionPanel);
        formContainer.add(Box.createVerticalStrut(25));

        // Add Button
        addButton = new JButton("Add Employee");
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

    public void remplirTableEmployes(Vector<Employe> employees) {
        // Get the table model
        DefaultTableModel tableModel = (DefaultTableModel) employeeTable.getModel();
        tableModel.setRowCount(0); // Clear the table

        // Populate the table with employee data
        int id = 1;
        for (Employe emp : employees) {
            String role = emp instanceof Menage ? "Cleaner" : emp instanceof Reception ? "Receptionist" : "Admin";
            tableModel.addRow(new Object[]{
                emp.getId(),
                emp.getNom() + " " + emp.getPrenom(),
                emp.getAdresse(),
                emp.getTelephone(),
                emp.getEmail(),
                role,
                "✏️",
                "🗑️"
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
        
        // Set custom border colorrgb(189, 189, 189) with rounded corners
        Color borderColor = new Color(203, 203, 203); //rgb(203, 203, 203) in RGB
        field.setBorder(new RoundedBorder(borderColor, 10)); // 5px rounded corners
        
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
            g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
            g2.dispose();
        }
        
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 8, 4, 8); // Add some padding inside the field
        }
    }

    // Show an error message in a dialog
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Show a success message in a dialog
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Clear the form fields after adding an employee
    public void clearForm() {
        nameField.setText("");
        surnameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        emailField.setText("");
        passwordField.setText("");
        functionComboBox.setSelectedIndex(0);
    }

    // Getters
    public JTextField getNameField() { return nameField; }
    public JTextField getSurnameField() { return surnameField; }
    public JTextField getAddressField() { return addressField; } 
    public JTextField getPhoneField() { return phoneField; }
    public JTextField getEmailField() { return emailField; }
    public JTextField getPasswordField() { return passwordField; }
    public JComboBox<String> getFunctionComboBox() { return functionComboBox; }
    public JButton getAddButton() { return addButton; }
    public JTable getTable() { return employeeTable; }
}