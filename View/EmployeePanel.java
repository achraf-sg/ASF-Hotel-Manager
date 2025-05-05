package View;

import Models.Employe;
import Models.Hotel;
import Models.Menage;
import Models.Reception;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class EmployeePanel extends JPanel {
    private JTable employeeTable;
    private JTextField nameField, surnameField, addressField, phoneField, emailField, passwordField;
    private JComboBox<String> functionComboBox;
    private JButton addButton;

    public EmployeePanel(Hotel hotel) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

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
         employeeTable.getTableHeader().setReorderingAllowed(false); 
        // Customize table rendering
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        employeeTable.setDefaultRenderer(Object.class, centerRenderer);

        // Populate table
        remplirTableEmployes(hotel.getListEmp());

        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Right Panel - Add Employee Form
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

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
        rightPanel.add(createLabeledField("Address:", addressField = new JTextField()));
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

        add(rightPanel, BorderLayout.EAST);
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
                "Edit",
                "Delete"
            });
        }
    }

    private JPanel createLabeledField(String label, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(StyleConfig.NORMAL_FONT);
        panel.add(jLabel, BorderLayout.NORTH);
        field.setFont(StyleConfig.NORMAL_FONT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(field, BorderLayout.CENTER);
        return panel;
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