import javax.swing.*;

import View.LoginPage;
import Models.*;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = DataInitializer.initializeHotel();
        new LoginPage(hotel);
    }
}
