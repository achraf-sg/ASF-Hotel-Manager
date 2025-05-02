package Models;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FactureTemplate {
    private final String filePath;
    private String clientName = "Client";
    private String roomInfo = "No room details provided.";
    private final List<String[]> barItems = new ArrayList<>();
    private String total = "0€";

    public FactureTemplate(String filePath, String clientName) {
        this.filePath = filePath;
        this.clientName = clientName;
    }

    public void setClientName(String name) {
        this.clientName = name;
    }

    public void addRoom(String roomDetails) {
        this.roomInfo = roomDetails;
    }

    public void addBar(String productName, int quantity, double price) {
        barItems.add(new String[] { productName, String.valueOf(quantity), String.format("%.2f€", price) });
    }

    public void setTotal(double total) {
        this.total = String.format("%.2f€", total);
    }

    public void createTextFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("========== HOTEL INVOICE ==========\n");
            writer.write("Client: " + clientName + "\n");
            writer.write("Date: " + LocalDate.now() + "\n\n");

            writer.write("---- Room Bill ----\n");
            writer.write(roomInfo + "\n\n");

            if (!barItems.isEmpty()) {
                writer.write("---- Bar Charges ----\n");
                writer.write(String.format("%-20s %-10s %-10s%n", "Product", "Quantity", "Price"));
                writer.write("------------------------------------------\n");

                for (String[] item : barItems) {
                    writer.write(String.format("%-20s %-10s %-10s%n", item[0], item[1], item[2]));
                }
                writer.write("\n");
            }

            writer.write("TOTAL: " + total + "\n");
            writer.write("=====================================\n");

            System.out.println("Text invoice created: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
