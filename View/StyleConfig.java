package View;
import java.awt.*;

public class StyleConfig {

    // Colors for a white and blue theme
    public static final Color PRIMARY_COLOR = new Color(37, 99, 235); // Blue
    public static final Color SECONDARY_COLOR = new Color(255, 255, 255); // White
    public static final Color BACKGROUND_COLOR = new Color(240, 248, 255); // Light Blue (Alice Blue)
    public static final Color ERROR_COLOR = new Color(220, 53, 69); // Red for errors
    public static final Color SUCCESS_COLOR = new Color(40, 167, 69); // Green for success

    // Professional fonts
    public static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 24); // Header font
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 18); // Title font
    public static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 14); // Normal text font
    public static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14); // Button font

    // Dimensions
    public static final Dimension WINDOW_SIZE = new Dimension(1200, 700);
    public static final Dimension SMALL_WINDOW_SIZE = new Dimension(450, 450);
    public static final Dimension BUTTON_SIZE = new Dimension(150, 40);
    public static final Dimension right_tab_size = new Dimension(300, 700);
    public static final Dimension left_tab_size = new Dimension(200, 700);
}
