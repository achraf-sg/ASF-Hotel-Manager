package View;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LeftPanel extends JPanel {
    private String activeItem;
    private Map<String, JPanel> navItems = new HashMap<>();
    private final Color HIGHLIGHT_COLOR = new Color(37, 99, 235);
    private final Color ACTIVE_BG = new Color(230, 230, 230);
    private final Color INACTIVE_BG = new Color(250, 250, 250);
    private final Color HOVER_BG = new Color(240, 240, 240);
    private RoundedPanel mainPanel;

    public LeftPanel(List<String> pages, Runnable[] actions) {
        // Set the first page as active by default
        activeItem = pages.size() > 0 ? pages.get(0) : "";

        // Use a BorderLayout for the main panel
        setLayout(new BorderLayout());
        // Transparent background for the outer panel
        setOpaque(true);
        setBackground(Color.WHITE);
        // Add margin around the entire panel
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create a rounded main panel to hold all content
        mainPanel = new RoundedPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(INACTIVE_BG);
        // Add internal padding
        mainPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        // Content will go inside this main panel
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(INACTIVE_BG);
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(5, 0, 5, 0));

        // Add menu items
        for (int i = 0; i < pages.size(); i++) {
            final int index = i;
            final String pageName = pages.get(i);
            String icon = getIconForPage(pageName);

            JPanel itemPanel = createNavItem(icon, pageName);
            navItems.put(pageName, itemPanel);

            // Add mouse listener with existing functionality
            itemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setActiveItem(pageName);
                    actions[index].run();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!pageName.equals(activeItem)) {
                        Component c = ((BorderLayout) itemPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
                        if (c instanceof JPanel) {
                            c.setBackground(HOVER_BG);
                            ((JPanel) c).setOpaque(true);
                        }
                    }
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!pageName.equals(activeItem)) {
                        Component c = ((BorderLayout) itemPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
                        if (c instanceof JPanel) {
                            c.setBackground(INACTIVE_BG);
                            ((JPanel) c).setOpaque(false);
                        }
                    }
                }
            });

            // Add spacing between items (increase from 5 to 10)
            content.add(itemPanel);
            content.add(Box.createVerticalStrut(10));
        }

        // Add the content to the main rounded panel
        mainPanel.add(content, BorderLayout.NORTH);

        // Add the main panel to the outer panel
        add(mainPanel, BorderLayout.CENTER);

        // Set the preferred size for the entire component
        setPreferredSize(new Dimension(230, 500));
    }

    // Public method to set the active item from MainFrame
    public void setActiveItem(String item) {
        if (!item.equals(activeItem)) {
            activeItem = item;
            updateAllItems();
        }
    }

    private String getIconForPage(String pageName) {
        switch (pageName.toLowerCase()) {
            case "dashboard":
                return "🏠";
            case "employees":
                return "👥";
            case "reservations":
                return "📅";
            case "rooms":
                return "🛏️";
            case "products":
                return "📦";
            case "clients":
                return "👤";
            case "cleaning":
                return "🧹";
            case "checkin":
                return "✓";
            default:
                return "📄";
        }
    }

    private JPanel createNavItem(String icon, String label) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        // Increase height from 45 to 55 for more vertical space
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        itemPanel.setPreferredSize(new Dimension(200, 55)); // Set preferred size too
        itemPanel.setOpaque(false);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        // Add vertical padding to the content panel
        content.setBorder(new EmptyBorder(12, 0, 12, 0)); // Add top/bottom padding

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
        iconLabel.setBorder(new EmptyBorder(0, 15, 0, 10));
        iconLabel.setAlignmentY(Component.CENTER_ALIGNMENT); // Ensure vertical centering

        JLabel textLabel = new JLabel(label);
        textLabel.setFont(new Font("SansSerif", Font.PLAIN, 17));
        textLabel.setAlignmentY(Component.CENTER_ALIGNMENT); // Ensure vertical centering

        content.add(iconLabel);
        content.add(textLabel);
        content.add(Box.createHorizontalGlue());

        boolean isActive = label.equals(activeItem);
        content.setOpaque(isActive);
        content.setBackground(isActive ? ACTIVE_BG : INACTIVE_BG);
        iconLabel.setForeground(isActive ? HIGHLIGHT_COLOR : Color.BLACK);
        textLabel.setForeground(isActive ? HIGHLIGHT_COLOR : Color.BLACK);

        if (isActive) {
            JPanel blueBar = new JPanel();
            blueBar.setBackground(HIGHLIGHT_COLOR);
            blueBar.setPreferredSize(new Dimension(4, 0));
            itemPanel.add(blueBar, BorderLayout.EAST);
        }

        itemPanel.add(content, BorderLayout.CENTER);
        return itemPanel;
    }

    private void updateAllItems() {
        for (String key : navItems.keySet()) {
            updateItemHighlighting(navItems.get(key), key);
        }
        revalidate();
        repaint();
    }

    private void updateItemHighlighting(JPanel panel, String pageName) {
        if (panel.getLayout() instanceof BorderLayout) {
            Component centerComp = ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
            if (centerComp instanceof JPanel) {
                JPanel contentPanel = (JPanel) centerComp;
                boolean isActive = pageName.equals(activeItem);

                contentPanel.setOpaque(isActive);
                contentPanel.setBackground(isActive ? ACTIVE_BG : INACTIVE_BG);

                if (contentPanel.getComponentCount() >= 2) {
                    ((JLabel) contentPanel.getComponent(0)).setForeground(isActive ? HIGHLIGHT_COLOR : Color.BLACK);
                    ((JLabel) contentPanel.getComponent(1)).setForeground(isActive ? HIGHLIGHT_COLOR : Color.BLACK);
                }

                Component eastComp = ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.EAST);
                if (eastComp != null) panel.remove(eastComp);

                if (isActive) {
                    JPanel blueBar = new JPanel();
                    blueBar.setBackground(HIGHLIGHT_COLOR);
                    blueBar.setPreferredSize(new Dimension(4, 0));
                    panel.add(blueBar, BorderLayout.EAST);
                }
            }
        }
    }

    // Updated RoundedPanel class without shadow effect
    static class RoundedPanel extends JPanel {
        private final int cornerRadius = 20;
        private boolean drawShadow = false; // Set to false to disable shadow
        private int shadowSize = 0; // Set shadow size to 0

        public RoundedPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw shadow section removed or disabled
            
            // Draw the panel background - adjusted to remove shadow offset
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.dispose();
        }
    }
}