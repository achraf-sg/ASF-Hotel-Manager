package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LeftPanel extends JPanel {
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
            add(Box.createVerticalStrut(12)); // Add spacing between buttons
        }
    }
}