package fileIO;

import java.io.IOException;
import java.net.URISyntaxException;

public class App {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Company sonee = new Company();
        sonee.receiveShipments();
        sonee.readFromFile();
        sonee.processOrders();
        sonee.printSummary();
    }
}
