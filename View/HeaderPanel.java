package View;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {

    public HeaderPanel(String title) {
        setBackground(new Color(37, 99, 235)); // Set background color
        setPreferredSize(new Dimension(800, 100)); // Set preferred height for the header
        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24)); // Set font and size
        titleLabel.setForeground(Color.WHITE); // Set text color
        add(titleLabel, BorderLayout.CENTER);
    }
}
