package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Models.*;
import java.time.LocalDate;
import View.*;
public class HomePage extends JFrame {
    JPanel north, left, right, leftContent, center;
    JButton homeButton, guestsButton, reservationButton, settingsButton, roomsButton, minibarButton;
    JLabel titleLabel, nameLabel, roomCleanLabel, quickActionLabel, centerJLabel;
    GridBagConstraints gbc;
    JTable GuestsTable;
    JScrollPane scrollPane;
    LeftPanel leftPanel;
    NorthPanel northPanel ;
    public HomePage(Employe emp, Hotel hotel) {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(StyleConfig.WINDOW_SIZE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(StyleConfig.BACKGROUND_COLOR); // Use background color from StyleConfig
        setLayout(new BorderLayout());

        // North Panel
      
   northPanel = new NorthPanel(emp);
  add(northPanel, BorderLayout.NORTH);

        // Left Panel
         leftPanel = new LeftPanel();
        add(leftPanel, BorderLayout.WEST);

        // Right Panel
        right = new JPanel();
        right.setBackground(StyleConfig.BACKGROUND_COLOR); // Use background color for the right panel
        right.setPreferredSize(StyleConfig.right_tab_size);
        right.setLayout(new BorderLayout());

        JPanel roomClean = new JPanel();
        roomClean.setBackground(StyleConfig.SECONDARY_COLOR); // Use secondary color for roomClean panel
        roomClean.setPreferredSize(
                new Dimension(StyleConfig.WINDOW_SIZE.width - 900, StyleConfig.WINDOW_SIZE.height - 400));
        roomClean.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(15), // Add rounded border
                new EmptyBorder(10, 10, 10, 10) // Add padding
        ));
        roomCleanLabel = new JLabel("Room Cleaning Status");
        roomCleanLabel.setFont(StyleConfig.TITLE_FONT); // Use title font
        roomCleanLabel.setForeground(StyleConfig.PRIMARY_COLOR); // Use primary color for text
        roomCleanLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roomClean.add(roomCleanLabel);

        JPanel quickAction = new JPanel();
        quickAction.setBackground(StyleConfig.SECONDARY_COLOR); // Use secondary color for quickAction panel
        quickAction.setPreferredSize(
                new Dimension(StyleConfig.WINDOW_SIZE.width - 900, StyleConfig.WINDOW_SIZE.height - 600));
        quickAction.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(15), // Add rounded border
                new EmptyBorder(10, 10, 10, 10) // Add padding
        ));
        quickActionLabel = new JLabel("Quick Actions");
        quickActionLabel.setFont(StyleConfig.TITLE_FONT); // Use title font
        quickActionLabel.setForeground(StyleConfig.PRIMARY_COLOR); // Use primary color for text
        quickAction.add(quickActionLabel);

        right.add(quickAction, BorderLayout.SOUTH);
        right.add(roomClean, BorderLayout.NORTH);
        add(right, BorderLayout.EAST);

        // Center Panel
        center = new JPanel();
        center.setBackground(StyleConfig.BACKGROUND_COLOR); // Use background color for the center panel
        center.setLayout(new BorderLayout()); // Set a layout for the center panel
        center.setPreferredSize(
                new Dimension(StyleConfig.WINDOW_SIZE.width - 200, StyleConfig.WINDOW_SIZE.height - 100));

        centerJLabel = new JLabel("Welcome to the Home Page!");
        centerJLabel.setFont(StyleConfig.HEADER_FONT); // Use header font
        centerJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerJLabel.setForeground(StyleConfig.PRIMARY_COLOR); // Use primary color for text
        center.add(centerJLabel, BorderLayout.NORTH);

        String[] columnNames = { "Client ID", "Name", "Start Date", "End Date", "Room Type", "Room Number",
                "Total Price" };
        Object[][] data = hotel.prepareTableData();

        scrollPane = new JScrollPane(getGuestsTable(data, columnNames));
        center.add(scrollPane, BorderLayout.CENTER);

        center.add(scrollPane, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        setVisible(true);
    }

    public JTable getGuestsTable(Object[][] data, String[] columnNames) {

        GuestsTable = new JTable(data, columnNames);
        GuestsTable.setFont(StyleConfig.NORMAL_FONT);
        GuestsTable.getTableHeader().setFont(StyleConfig.TITLE_FONT);
        GuestsTable.getTableHeader().setBackground(StyleConfig.PRIMARY_COLOR);
        GuestsTable.getTableHeader().setForeground(StyleConfig.SECONDARY_COLOR);
        GuestsTable.setBackground(StyleConfig.SECONDARY_COLOR);
        GuestsTable.setForeground(Color.BLACK);
        GuestsTable.setRowHeight(30);
        GuestsTable.setSelectionBackground(StyleConfig.ERROR_COLOR);
        GuestsTable.setSelectionForeground(Color.WHITE);

        return GuestsTable;
    }

    // Custom RoundedBorder class for rounded corners
    private static class RoundedBorder extends javax.swing.border.AbstractBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(StyleConfig.PRIMARY_COLOR); // Use primary color for border
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = insets.top = insets.bottom = radius + 1;
            return insets;
        }
    }

   
        
               
}
