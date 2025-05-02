package View;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Centralized style configuration for consistent UI appearance
 */
public class StyleConfig {

    // Colors
    public static final Color PRIMARY_COLOR = new Color(37, 99, 235);
    public static final Color PRIMARY_DARK_COLOR = new Color(20, 60, 160);
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final Color TABLE_HEADER_COLOR = new Color(240, 240, 240);
    public static final Color SECONDARY_COLOR = new Color(255, 255, 255); // White
    public static final Color ERROR_COLOR = new Color(220, 53, 69); // Red for errors
    public static final Color SUCCESS_COLOR = new Color(40, 167, 69); // Green for success

    // Fonts
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 18);
    public static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14);
    public static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 24);

    // Dimensions
    public static final Dimension WINDOW_SIZE = new Dimension(1200, 700);
    public static final Dimension SMALL_WINDOW_SIZE = new Dimension(450, 450);
    public static final Dimension BUTTON_SIZE = new Dimension(150, 40);
    public static final Dimension right_tab_size = new Dimension(300, 700);
    public static final Dimension left_tab_size = new Dimension(200, 700);

    //// Jtable style : a modifier 
    public void JtableDesigne( JTable table) {
        table.setBackground(StyleConfig.BACKGROUND_COLOR); // Set background color
        table.setForeground(Color.DARK_GRAY); // Set text color
        table.setFont(StyleConfig.NORMAL_FONT); // Set font
        table.setRowHeight(30); // Set row height
    
        // Set header properties
        JTableHeader header = table.getTableHeader();
        header.setBackground(StyleConfig.PRIMARY_COLOR); // Header background color
        header.setForeground(Color.WHITE); // Header text color
        header.setFont(StyleConfig.HEADER_FONT); // Header font
        header.setReorderingAllowed(false); // Disable column reordering
    
        // Set cell renderers for better appearance
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }
}


