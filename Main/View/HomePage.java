package View;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {
    JPanel north , center , left , right ;

    public HomePage(){
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(StyleConfig.WINDOW_SIZE);
    setResizable(false);
    setLayout(new BorderLayout());
    left= new JPanel();
    left.setBackground(StyleConfig.ERROR_COLOR);
    add(left,BorderLayout.WEST);

    north=new JPanel();
    north.setBackground(StyleConfig.PRIMARY_COLOR);
    add(north,BorderLayout.NORTH);

    right= new JPanel();
    right.setBackground(StyleConfig.SECONDARY_COLOR);
    add(right, BorderLayout.EAST);

    center = new JPanel();
    add(center,BorderLayout.CENTER);





    }

}
