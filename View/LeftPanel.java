package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import View.StyleConfig;

public class LeftPanel extends JPanel {
    JPanel leftContent;
    JButton homeButton, guestsButton, reservationButton, settingsButton, roomsButton, miniBarButton;

    public LeftPanel() {
        setBackground(StyleConfig.SECONDARY_COLOR); // Use secondary color for the left panel
        setPreferredSize(StyleConfig.left_tab_size);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding around the left panel

        // Left Content Panel
        leftContent = new JPanel();
        leftContent.setLayout(new BoxLayout(leftContent, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment
        leftContent.setBackground(StyleConfig.BACKGROUND_COLOR); // Use background color for left content
        leftContent.setPreferredSize(new Dimension(200, StyleConfig.WINDOW_SIZE.height - 200));
        leftContent.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(15), // Add rounded border
                new EmptyBorder(10, 10, 10, 10) // Add padding inside the border
        ));

        // Buttons
        homeButton = new JButton("Home");
        guestsButton = new JButton("Guests");
        reservationButton = new JButton("Reservation");
        roomsButton = new JButton("Rooms");
        miniBarButton = new JButton("Minibar");
        settingsButton = new JButton("Settings");

        JButton[] buttons = { homeButton, guestsButton, reservationButton, roomsButton, miniBarButton, settingsButton };

        for (JButton button : buttons) {
            button.setFont(StyleConfig.BUTTON_FONT); // Use button font
            button.setBackground(StyleConfig.PRIMARY_COLOR); // Use primary color for button background
            button.setForeground(StyleConfig.SECONDARY_COLOR); // Use secondary color for button text
            button.setFocusPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(150, 40)); // Set button size
            leftContent.add(Box.createVerticalStrut(10)); // Add spacing between buttons
            leftContent.add(button);
        }

        // Add the left content panel to the main panel
        add(leftContent); // This line ensures the buttons are displayed
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