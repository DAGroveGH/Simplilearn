package practice.company;

import practice.company.losangeles.LosAngelesDistributionCenter;
import practice.company.newyork.NewYorkDistributionCenter;
import practice.company.undeliverable.DeliveryRefusedException;
import practice.company.undeliverable.DeliveryUnavailableException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyName {
    Map<String, DistributionCenter> distributionCenters = new HashMap<String, DistributionCenter>();

    List<Order> orders = new ArrayList<Order>();

    Integer orderProcessed = 0;

    Float revenue = 0.0F;


    public CompanyName() {
        distributionCenters.put("New York", new NewYorkDistributionCenter());
        distributionCenters.put("Los Angeles", new LosAngelesDistributionCenter());
    }

    public void receiveShipments() {
        distributionCenters.get("New York").receiveShipment(new DistributionCenter.Product(
                "posters", 500
        ));
        distributionCenters.get("New York").receiveShipment(new DistributionCenter.Product(
                "cardboard", 500
        ));
        distributionCenters.get("Los Angeles").receiveShipment(new DistributionCenter.Product(
                "paperstock", 500
        ));
        distributionCenters.get("Los Angeles").receiveShipment(new DistributionCenter.Product(
                "printerpaper", 500
        ));
        distributionCenters.get("New York").receiveShipment(new DistributionCenter.Product(
                "kettlebells", 5
        ));
    }

    public void receiveOrders() {
        orders.add(new Order("paper", "New York", 50, 50.00F));
        orders.add(new Order("poster", "Los Angeles", 50, 60.00F));
        orders.add(new Order("glass", "New York", 50, 50.00F));
        orders.add(new Order("poster", "Los Angeles", 50, 60.00F));
    }

    public void processOrders() {
        for(Order order : orders) {
            try {
                this.processOrder(order);
            } catch (DeliveryRefusedException e) {
                System.out.println("Delivery refused. Message: " + e.getMessage());
                System.out.println("Attempting to shup from Los Angeles instead");
                order.closestLocation = "Los Angeles";
                try {
                    this.processOrder(order);
                } catch (Exception error) {
                    System.out.println("Unable to try delivery again after shipping from Los Angeles instead. Message: " + error.getMessage());
                }
            } catch (DeliveryUnavailableException e) {
                System.out.println("Delivery unavailable. Message: " + e.getMessage());

                System.out.println("Fixing the truck");
                ((LosAngelesDistributionCenter) distributionCenters.get("Los Angeles"))
                        .delivery.setBroken(false);
                try {
                    System.out.println("Retrying the order");
                    this.processOrder(order);
                } catch (Exception error) {
                    System.out.println("Delivery not possible. Message: " + e.getMessage());
                }
            } catch (Exception e) {
                System.out.println("Delivery not possible. Message: " + e.getMessage());
            } finally {
                System.out.println("-------- Moving On --------");
            }
        }
    }

    private void processOrder(Order order) throws DeliveryRefusedException, DeliveryUnavailableException {
        this.distributionCenters.get(order.closestLocation).shipProduct (
                new DistributionCenter.Product(
                        order.product,
                        order.size
                )
        );

        //Order is processed
        order.setProcessed(true);

        //Needs to be accurate
        revenue += order.price;
        orderProcessed++;
    }

    public void printSummary() {
        System.out.println("Processed " + orderProcessed + " orders");
        System.out.println("Gained $" + revenue + "today");
    }

    class Order {

        public Order(String product, String closestLocation, int size, float price) {
            this.product = product;
            this.closestLocation = closestLocation;
            this.size = size;
            this.price = price;
        }

        boolean processed = false;
        public String product;
        public String closestLocation;
        public int size = 0;
        public float price = 0;

        public void setProcessed(boolean processed) {
            this.processed = processed;
        }

        public boolean isProcessed() {
            return processed;
        }
    }
}
