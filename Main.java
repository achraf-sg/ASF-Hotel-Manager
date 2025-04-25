import javax.swing.*;

import View.Login;
import Models.*;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = DataInitializer.initializeHotel();
        new Login(hotel);
    }
}
