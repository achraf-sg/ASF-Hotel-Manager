package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Vector;
import Models.Employe;
import Models.Hotel;
import Models.Menage;
import Models.Reception;

public class AdminEmployePage extends JFrame {
    private JTable employeeTable;
    private JTextField nameField, surnameField, phoneField, emailField, passwordField;
    private JComboBox<String> functionComboBox;
    private JButton addButton;

    public AdminEmployePage(Vector<Employe> employees) {
        setTitle("Admin Employee Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(StyleConfig.WINDOW_SIZE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Left Navigation Panel
        JPanel leftPanelContainer = new JPanel(new BorderLayout());
        leftPanelContainer.setBackground(Color.WHITE);
        leftPanelContainer.setBorder(new EmptyBorder(0, 10, 0, 10)); // Add padding around the left panel
        LeftPanel leftPanel = new LeftPanel();
        leftPanelContainer.add(leftPanel, BorderLayout.CENTER);
        add(leftPanelContainer, BorderLayout.WEST);

        // Header Panel
        HeaderPanel headerPanel = new HeaderPanel("Employee Management");
        add(headerPanel, BorderLayout.NORTH);

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
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add 20px padding inside the center panel

        JLabel tableTitle = new JLabel("Employees");
        tableTitle.setFont(StyleConfig.TITLE_FONT);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0)); // Add padding below the title
        centerPanel.add(tableTitle, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Full Name", "Adress", "Phone Num", "Email", "Function", "Action"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        employeeTable = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if (component instanceof JComponent) {
                    ((JComponent) component).setOpaque(false); // Make the cell transparent
                }
                return component;
            }
        };
        employeeTable.setRowHeight(40);
        employeeTable.setFont(StyleConfig.NORMAL_FONT);

        // Customize the table header to make it bold, transparent, and left-aligned
        employeeTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (component instanceof JLabel) {
                    JLabel headerLabel = (JLabel) component;
                    headerLabel.setFont(new Font("SansSerif", Font.BOLD, 14)); // Set bold font for column names
                    headerLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
                    headerLabel.setOpaque(false); // Make the background transparent
                }
                return component;
            }
        });
        employeeTable.getTableHeader().setBackground(new Color(240, 240, 240, 0)); // Transparent background
        employeeTable.getTableHeader().setForeground(Color.BLACK); // Text color
        employeeTable.setShowGrid(false);
        employeeTable.setOpaque(false); // Make the table itself transparent

        // Populate table
        int id = 1;
        for (Employe emp : employees) {
            String fonction = emp instanceof Menage ? "Cleaner" : 
                            emp instanceof Reception ? "Receptionist" : "Unknown";
            model.addRow(new Object[]{
                id++,
                emp.getNom() + " " + emp.getPrenom(),
                emp.getAdresse(),
                emp.getTelephone(),
                emp.getEmail(),
                fonction,
                "✏️ 🗑️"
            });
        }

        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false); // Make the scroll pane transparent
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Wrap the centerPanel in a container with a white background
        JPanel centerPanelContainer = new JPanel(new BorderLayout());
        centerPanelContainer.setBackground(Color.WHITE); // Ensure the background is pure white
        centerPanelContainer.setBorder(new EmptyBorder(0, 10, 0, 10)); // Add padding around the center panel
        centerPanelContainer.add(centerPanel, BorderLayout.CENTER);
        add(centerPanelContainer, BorderLayout.CENTER);

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
        rightPanel.setOpaque(false); // Make the panel fully transparent
        rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add 20px padding inside the right panel

        // Add content to the right panel
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS)); 
        JLabel formTitle = new JLabel("Add Employee");
        formTitle.setFont(StyleConfig.TITLE_FONT);
        formTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(formTitle);
        rightPanel.add(Box.createVerticalStrut(25));

        // Name Fields
        rightPanel.add(createLabeledField("Name:", nameField = new JTextField()));
        rightPanel.add(Box.createVerticalStrut(10)); 
        rightPanel.add(createLabeledField("Surname:", surnameField = new JTextField()));
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(createLabeledField("Phone:", phoneField = new JTextField()));
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(createLabeledField("Email:", emailField = new JTextField()));
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(createLabeledField("Password:", passwordField = new JTextField()));
        rightPanel.add(Box.createVerticalStrut(10));

        // Function Dropdown
        JLabel functionLabel = new JLabel("Function:");
        functionLabel.setFont(StyleConfig.NORMAL_FONT);
        rightPanel.add(functionLabel);
        functionComboBox = new JComboBox<>(new String[]{"Receptionist", "Cleaner"});
        functionComboBox.setFont(StyleConfig.NORMAL_FONT);
        functionComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        rightPanel.add(functionComboBox);
        rightPanel.add(Box.createVerticalStrut(25)); 

        // Add Button
        addButton = new JButton("Add Employee");
        addButton.setFont(StyleConfig.BUTTON_FONT);
        addButton.setBackground(StyleConfig.PRIMARY_COLOR);
        addButton.setForeground(Color.WHITE);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setMaximumSize(new Dimension(200, 40));
        rightPanel.add(addButton);

        // Wrap the rightPanel in a container with a white background
        JPanel rightPanelContainer = new JPanel(new BorderLayout());
        rightPanelContainer.setBackground(Color.WHITE); // Ensure the background is pure white
        rightPanelContainer.setBorder(new EmptyBorder(0, 10, 0, 10)); // Add padding around the right panel
        rightPanelContainer.add(rightPanel, BorderLayout.CENTER);
        add(rightPanelContainer, BorderLayout.EAST);

        setVisible(true);
    }


    private JPanel createLabeledField(String label, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(230, 230, 230)); // Light grey background for the label
        panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add padding around the panel

        JLabel jLabel = new JLabel(label);
        jLabel.setFont(StyleConfig.NORMAL_FONT);
        jLabel.setForeground(new Color(60, 60, 60)); // Dark grey text color
        panel.add(jLabel, BorderLayout.NORTH);

        // Set fixed dimensions for the text field
        field.setPreferredSize(new Dimension(200, 10)); // Fixed width and height for the text field
        field.setFont(StyleConfig.NORMAL_FONT);
        field.setBackground(Color.WHITE); // White background for the text field
        field.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200))); // Light grey border for the text field
        panel.add(field);

        return panel;
    }

    // Getters
    public JTextField getNameField() { return nameField; }
    public JTextField getSurnameField() { return surnameField; }
    public JTextField getPhoneField() { return phoneField; }
    public JTextField getEmailField() { return emailField; }
    public JTextField getPasswordField() { return passwordField; }
    public JComboBox<String> getFunctionComboBox() { return functionComboBox; }
    public JButton getAddButton() { return addButton; }
    public JTable getEmployeeTable() { return employeeTable; }

    // Navigation Panel
    static class LeftPanel extends JPanel {
        public LeftPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(new Color(245, 245, 245));
            setPreferredSize(new Dimension(200, Integer.MAX_VALUE));
            setBorder(new EmptyBorder(20, 15, 20, 15));

            String[] items = {"Employees", "Guests", "Reservations", "Rooms", "Mini bar", "Settings"};
            for (String item : items) {
                JButton btn = new JButton(item);
                btn.setFont(StyleConfig.NORMAL_FONT);
                btn.setAlignmentX(Component.LEFT_ALIGNMENT);
                btn.setBorderPainted(false);
                btn.setContentAreaFilled(false);
                btn.setFocusable(false);
                add(btn);
                add(Box.createVerticalStrut(12));
            }
        }
    }

    // Header Panel
    static class HeaderPanel extends JPanel {
        public HeaderPanel(String title) {
            setLayout(new BorderLayout());
            setBackground(Color.WHITE);
            setBorder(new EmptyBorder(15, 25, 15, 25));
            
            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(StyleConfig.HEADER_FONT);
            titleLabel.setForeground(new Color(60, 60, 60));
            add(titleLabel);
        }
    }

    public static void main(String[] args) {
        // Initialize a sample Hotel object
        Hotel hotel = new Hotel("Grand Hotel", "Paris");

        // Create a sample list of employees
        Vector<Employe> employees = new Vector<>();
        employees.add(new Menage("Marie", "Curie", "12 Rue Pierre", "0123456789", "marie@hotel.com", hotel, "password123"));
        employees.add(new Reception("Jean", "Dupont", "5 Avenue Louis", "0987654321", "jean@hotel.com", hotel, "password456"));

        // Launch the AdminEmployePage
        SwingUtilities.invokeLater(() -> new AdminEmployePage(employees));
    }
}
