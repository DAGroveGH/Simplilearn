package fileIO;

import fileIO.accounting.AccountingDepartment;
import fileIO.it.ItDepartment;
import fileIO.locations.ChicagoDistributionCenter;
import fileIO.locations.NewYorkDistributionCenter;
import fileIO.locations.SeattleDistributionCenter;
import practice.company.undeliverable.DeliveryRefusedException;
import practice.company.undeliverable.DeliveryUnavailableException;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class Company {
    Map<String, DistributionCenter> distributionCenters = new HashMap<String, DistributionCenter>();

    ItDepartment itDepartment = new ItDepartment();

    AccountingDepartment accountingDepartment = new AccountingDepartment();

    List<Order> orders = new ArrayList<Order>();

    Integer orderProcessed = 0;
    Float revenueProcessed = 0F;

    public Company() {
        distributionCenters.put("newyork", new NewYorkDistributionCenter());
        distributionCenters.put("chicago", new ChicagoDistributionCenter());
        distributionCenters.put("seattle", new SeattleDistributionCenter());
    }

    public void receiveShipments() {
        distributionCenters.get("newyork").receiveShipment(new DistributionCenter.Product("tv", 50, 30));
        distributionCenters.get("chicago").receiveShipment(new DistributionCenter.Product("laptop", 20, 5));
        distributionCenters.get("seattle").receiveShipment(new DistributionCenter.Product("headphones", 100, 2));
        distributionCenters.get("newyork").receiveShipment(new DistributionCenter.Product("3dprinter", 10, 40));
        distributionCenters.get("chicago").receiveShipment(new DistributionCenter.Product("couch", 5, 80));
    }

    public void readFromFile() throws IOException, URISyntaxException {
        File orderInputFile = new File("fileIO/orderLogs/orders.csv");
        FileInputStream fileInputStream = new FileInputStream(orderInputFile);
        int r = 0;
        StringBuilder sb = new StringBuilder();
        while((r = fileInputStream.read())  != -1) {
            sb.append((char) r);
        }
        String orders = sb.toString();
        System.out.println(orders);
        String[] lines = orders.split("\n");
        for(String line : lines) {
            String[] columns = line.split(",");
            if(columns.length == 5) {
                Order order = new Order (
                    columns[0],
                    columns[1],
                    Integer.parseInt(columns[2]),
                    Integer.parseInt(columns[3]),
                    Float.parseFloat(columns[4])
                );
                receiveOrder(order);
            }
        }
    }

    public void receiveOrders() {
        receiveOrder(new Order("tv","newyork", 50, 30, 400F));
        receiveOrder(new Order("laptop", "chicago", 20, 5, 350F));
        receiveOrder(new Order("headphones", "newyork", 100, 2, 150F));
        receiveOrder(new Order("3dprinter", "seattle", 10, 40, 250F));
        receiveOrder(new Order("couch", "seattle", 5, 80, 800F));
    }

    private void receiveOrder(Order order) {
        try {
            orders.add(order);
        } catch (Exception e) {
            itDepartment.logIssue("Unable to receive order. Message: " + e.getMessage());
        }
    }

    public void processOrders() {
        for(Order order : orders) {
            try {
                this.processOrder(order);
            } catch (DeliveryRefusedException e) {
                itDepartment.logIssue("Delivery refused. Message: " + e.getMessage());
                itDepartment.logIssue("Attempting to ship from another location instead");
                order.closestLocation = "chicago";
                try {
                    this.processOrder(order);
                } catch (Exception error) {
                    itDepartment.logIssue("Unable to try delivery again after shipping from Chicago instead. Message: " + error.getMessage());
                }
            } catch (DeliveryUnavailableException e) {
                itDepartment.logIssue("Delivery unavailable. Message: " + e.getMessage());
                itDepartment.logIssue("Contacting repair crew");
                ((ChicagoDistributionCenter) distributionCenters.get("chicago"))
                        .delivery.setBroken(false);
                try {
                    itDepartment.logIssue("Retrying the order");
                    this.processOrder(order);
                } catch (Exception error) {
                    itDepartment.logIssue("Unable to try delivery again after fixing truck. Message: " + error.getMessage());
                }
            } catch (Exception e) {
                itDepartment.logIssue("Delivery not possible. Message: " + e.getMessage());
            } finally {
                itDepartment.logIssue("-------- moving on --------");
            }
        }
    }

    private void processOrder(Order order) throws DeliveryRefusedException, DeliveryUnavailableException {
        this.distributionCenters.get(order.closestLocation).shipProduct(
                new DistributionCenter.Product(
                        order.product,
                        order.size,
                        order.weight
                )
        );

        order.setProcessed(true);

        this.accountingDepartment.addToLedger(
                order.toString()
        );

        revenueProcessed += order.price;
        orderProcessed++;
    }

    public void printSummary() {
        System.out.println("Processed " +  orderProcessed + " orders");
        System.out.println("Today we had $" +  revenueProcessed + " in revenue");
    }

    static class Order {

        boolean processed = false;
        public String product;
        public String closestLocation;
        public int size = 0;
        public int weight = 0;
        public float price = 0;

        public Order(String productName, String closestLocation, int size, int weight, float price) {
            this.product = productName;
            this.closestLocation = closestLocation;
            this.size = size;
            this.weight = weight;
            this.price = price;
        }

        public void setProcessed(boolean processed) {
            this.processed = processed;
        }

        public boolean isProcessed() {
            return processed;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "processed=" + processed +
                    ", product='" + product + '\'' +
                    ", closestLocation='" + closestLocation + '\'' +
                    ", size=" + size +
                    ", price=" + price +
                    '}';
        }
    }
}
