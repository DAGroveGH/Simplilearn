package practice;

import practice.company.CompanyName;

public class App {
    public static void main(String [] args) {
        CompanyName companyName = new CompanyName();
        companyName.receiveShipments();
        companyName.receiveOrders();
        companyName.processOrders();
        companyName.printSummary();
    }
}
