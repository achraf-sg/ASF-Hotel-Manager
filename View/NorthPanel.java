package View;

import javax.swing.*;
import java.awt.*;
import Models.Employe;

public class NorthPanel extends JPanel {
    private JLabel titleLabel, nameLabel;
    private GridBagConstraints gbc;

    public NorthPanel(Employe emp) {
        // Constructor implementation

        setBackground(StyleConfig.PRIMARY_COLOR); // Use primary color for the header
        setPreferredSize(new Dimension(StyleConfig.WINDOW_SIZE.width, 100));
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        // Title Label
        titleLabel = new JLabel("Maansions");
        titleLabel.setFont(StyleConfig.HEADER_FONT); // Use header font
        titleLabel.setForeground(StyleConfig.SECONDARY_COLOR); // Use secondary color for text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        add(titleLabel, gbc);

        // Name Label
        nameLabel = new JLabel("Role: " + emp.getClass().getSimpleName() + " | Name: " + emp.getNom());
        nameLabel.setFont(StyleConfig.TITLE_FONT); // Use title font
        nameLabel.setForeground(StyleConfig.SECONDARY_COLOR); // Use secondary color for text
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(nameLabel, gbc);
    }
}
