package View;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import Models.Hotel;
import Models.RoomType;
import Models.Reservation;
import java.util.Vector;

public class ReservationPanel extends JPanel {
    private JTable reservationTable;
    private JTextField startDateField, endDateField, emailField;
    private JButton confirmButton, addReservationButton;
    private JComboBox<String> typeComboBox;
    private JPanel emailPanel;
    private JPanel rightPanel;
    private Hotel hotel;
    private JPanel formContainer;

    public ReservationPanel(Hotel hotel) {
        this.hotel = hotel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Add 20px margin around the entire panel

        // Center Panel - Reservation Table with transparent styling
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

        JLabel tableTitle = new JLabel("Reservations");
        tableTitle.setFont(StyleConfig.TITLE_FONT);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0)); // Add padding below the title
        centerPanel.add(tableTitle, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Start Date", "End Date", "Email", "Room Type", "Delete"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        reservationTable = new JTable(model);
        reservationTable.setRowHeight(40);
        reservationTable.setFont(StyleConfig.NORMAL_FONT);
        reservationTable.setOpaque(false);
        reservationTable.setBackground(new Color(0, 0, 0, 0));

        // Make grid lines transparent
        reservationTable.setShowGrid(false);  // Completely removes grid lines
        reservationTable.setIntercellSpacing(new Dimension(0, 0));  // Remove spacing between cells
        reservationTable.setGridColor(new Color(0, 0, 0, 0));  // Set grid color to transparent (as fallback)

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
        reservationTable.getTableHeader().setDefaultRenderer(headerRenderer);

        // Additional steps to ensure header transparency
        JTableHeader header = reservationTable.getTableHeader();
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
        reservationTable.setDefaultRenderer(Object.class, centerRenderer);

        // Populate table
        populateReservationTable(hotel.getListRes());

        // Make the scroll pane and its viewport transparent too
        JScrollPane scrollPane = new JScrollPane(reservationTable);
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

        // Right Panel - Add Reservation Form with transparent styling
        rightPanel = new JPanel(new BorderLayout()) {
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
        formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setOpaque(false); // Make this container transparent too

        JLabel formTitle = new JLabel("Add Reservation");
        formTitle.setFont(StyleConfig.TITLE_FONT);
        formTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        formContainer.add(formTitle);
        formContainer.add(Box.createVerticalStrut(25));

        // Start Date Field
        formContainer.add(createLabeledField("Start Date (YYYY-MM-DD):", startDateField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(10));

        // End Date Field
        formContainer.add(createLabeledField("End Date (YYYY-MM-DD):", endDateField = new JTextField()));
        formContainer.add(Box.createVerticalStrut(15));

        // Confirm Button
        confirmButton = new JButton("Confirm Dates");
        confirmButton.setFont(StyleConfig.BUTTON_FONT);
        confirmButton.setBackground(StyleConfig.PRIMARY_COLOR);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setMaximumSize(new Dimension(200, 40));
        formContainer.add(confirmButton);
        formContainer.add(Box.createVerticalStrut(25));

        // Add Action Listener for Confirm Dates button
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse the dates
                    String startDateStr = startDateField.getText().trim();
                    String endDateStr = endDateField.getText().trim();
                    
                    if (startDateStr.isEmpty() || endDateStr.isEmpty()) {
                        showError("Please enter both start and end dates.");
                        return;
                    }
                    
                    // Convert strings to LocalDate objects
                    LocalDate startDate = LocalDate.parse(startDateStr);
                    LocalDate endDate = LocalDate.parse(endDateStr);
                    
                    // Validate dates
                    if (startDate.isAfter(endDate)) {
                        showError("Start date cannot be after end date.");
                        return;
                    }
                    
                    if (startDate.isBefore(LocalDate.now())) {
                        showError("Start date cannot be in the past.");
                        return;
                    }
                    
                    // Update the room type dropdown with available rooms
                    updateRoomTypeDropdown(hotel.getListTypes(), hotel, startDate, endDate);
                    
                } catch (Exception ex) {
                    showError("Invalid date format. Please use YYYY-MM-DD format.");
                }
            }
        });

        // Room Type Dropdown - Use similar approach as text fields
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        typePanel.setName("typePanel"); // Add this line so we can find it later
        typePanel.setOpaque(false);
        typePanel.setVisible(false); // Hide initially

        JPanel typeLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        typeLabelPanel.setOpaque(false);
        JLabel typeLabel = new JLabel("Room Type:");
        typeLabel.setFont(StyleConfig.NORMAL_FONT);
        typeLabelPanel.add(typeLabel);
        typePanel.setVisible(false); // Hide initially

        // Create wrapper for alignment
        JPanel typeWrapper = new JPanel();
        typeWrapper.setLayout(new BoxLayout(typeWrapper, BoxLayout.Y_AXIS));
        typeWrapper.setOpaque(false);

        // Configure the combo box with matching styling
        typeComboBox = new JComboBox<>();
        typeComboBox.setFont(StyleConfig.NORMAL_FONT);
        typeComboBox.setBackground(Color.WHITE);
        typeComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, typeComboBox.getPreferredSize().height));
        typeComboBox.setVisible(false); // Hide initially

        // Apply the same border as the text fields
        Color borderColor = new Color(203, 203, 203);
        typeComboBox.setBorder(new RoundedBorder(borderColor, 10));

        // Add components with same spacing pattern
        typeWrapper.add(typeLabelPanel);
        typeWrapper.add(Box.createVerticalStrut(5));
        typeWrapper.add(typeComboBox);
        typePanel.add(typeWrapper);
        formContainer.add(typePanel);
        formContainer.add(Box.createVerticalStrut(10));

        // Email Panel - styled consistently with other fields
        emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        emailPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        emailPanel.setOpaque(false); // Make transparent
        emailPanel.setVisible(false); // Hide initially
        formContainer.add(emailPanel);
        formContainer.add(Box.createVerticalStrut(15));

        // Add Reservation Button
        addReservationButton = new JButton("Add Reservation");
        addReservationButton.setFont(StyleConfig.BUTTON_FONT);
        addReservationButton.setBackground(StyleConfig.PRIMARY_COLOR);
        addReservationButton.setForeground(Color.WHITE);
        addReservationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addReservationButton.setMaximumSize(new Dimension(200, 40));
        formContainer.add(addReservationButton);

        // Add the form container to the right panel
        rightPanel.add(formContainer, BorderLayout.CENTER);
        rightSideWrapper.add(rightPanel, BorderLayout.CENTER);
        add(rightSideWrapper, BorderLayout.EAST);

        // Add Action Listener for Room Type Selection
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmailField();
            }
        });
    }

    // Update the email field dynamically based on room type selection
    private void updateEmailField() {
        emailPanel.removeAll(); // Clear the email panel
        String selectedType = (String) typeComboBox.getSelectedItem();
        if (selectedType != null) {
            // Create email field using the same method as other fields
            emailField = new JTextField();
            JPanel emailFieldPanel = createLabeledField("Client Email:", emailField);
            
            // Add the complete panel with properly formatted field
            emailPanel.add(emailFieldPanel);
        }
        emailPanel.setVisible(true);
        emailPanel.revalidate();
        emailPanel.repaint();
    }

    // Populate the reservation table with reservation data
    public void populateReservationTable(Vector<Reservation> reservations) {
        DefaultTableModel tableModel = (DefaultTableModel) reservationTable.getModel();
        tableModel.setRowCount(0); // Clear the table

        for (Reservation reservation : reservations) {
            tableModel.addRow(new Object[]{
                reservation.getId(),
                reservation.getDateDeb(),
                reservation.getDateFin(),
                reservation.getClientMail(),
                reservation.getType().getName(),
                "🗑️" // Use trash bin emoji for delete
            });
        }
    }

    // Updated to match EmployeePanel style
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

    // Update room type dropdown based on availability
    public void updateRoomTypeDropdown(Vector<RoomType> roomTypes, Hotel hotel, LocalDate startDate, LocalDate endDate) {
        // Show the room type components
        typeComboBox.setVisible(true);
        
        // This is what's missing - need to make the typePanel visible
        for (Component comp : formContainer.getComponents()) {
            if (comp instanceof JPanel && comp.getName() != null && comp.getName().equals("typePanel")) {
                comp.setVisible(true);
            }
        }
        
        // Clear and populate the dropdown
        typeComboBox.removeAllItems();
        boolean roomsAvailable = false;
        
        for (RoomType roomType : roomTypes) {
            int availableRooms = hotel.countChambresDisponibles(roomType.getName(), startDate, endDate);
            if (availableRooms > 0) { // Only add room types with available rooms
                typeComboBox.addItem(roomType.getName() + " (" + availableRooms + " available)");
                roomsAvailable = true;
            }
        }
        
        if (!roomsAvailable) {
            showError("No rooms available for the selected dates.");
        }
        
        // Force refresh of the UI
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    // Remaining methods unchanged
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void clearForm() {
        startDateField.setText("");
        endDateField.setText("");
        if (emailField != null) {
            emailField.setText("");
        }
        typeComboBox.setSelectedIndex(-1); // Reset the dropdown
        
        // Hide the room type components
        typeComboBox.setVisible(false);
        emailPanel.setVisible(false);
    }

    // Getters
    public JTextField getStartDate() { return startDateField; }
    public JTextField getEndDate() { return endDateField; }
    public JComboBox<String> getTypeComboBox() { return typeComboBox; }
    public JButton getConfirmDateButton() { return confirmButton; }
    public JButton getAddButton() { return addReservationButton; }
    public JTable getTable() { return reservationTable; }
    public JTextField getEmailField() { return emailField; }
}