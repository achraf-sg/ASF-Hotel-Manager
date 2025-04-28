package View;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {

    public Header() {
        // Set the layout and background color
        setLayout(new BorderLayout());
        setBackground(new Color(70, 130, 180)); // Steel Blue color

        // Add a blank label to act as a placeholder
        JLabel placeholder = new JLabel(" ");
        add(placeholder, BorderLayout.CENTER);
    }
}
