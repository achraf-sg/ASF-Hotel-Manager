package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class LeftPanel extends JPanel {

    public LeftPanel(List<String> pages, Runnable[] actions) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding around the left panel
        setBackground(new Color(240, 240, 240)); // Light gray background

        for (int i = 0; i < pages.size(); i++) {
            JButton button = new JButton(pages.get(i));
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBackground(new Color(37, 99, 235)); // Blue background
            button.setForeground(Color.WHITE); // White text
            button.setFocusPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(200, 40)); // Set button size
            int index = i; // Capture the index for the lambda
            button.addActionListener(e -> actions[index].run());
            add(Box.createVerticalStrut(10)); // Add spacing between buttons
            add(button);
        }
    }
}