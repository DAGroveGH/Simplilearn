package practice.company.losangeles;

import practice.company.DistributionCenter;
import practice.company.undeliverable.DeliveryUnavailableException;

public class LosAngelesDistributionCenter extends DistributionCenter {

    public Delivery delivery = new Delivery();

    public void shipProduct(DistributionCenter.Product product) throws DeliveryUnavailableException {
        delivery.deliver(product);
    }

    public static class Delivery {
        boolean isBroken = true;
        public void deliver(DistributionCenter.Product product) {
            if(isBroken) {
                throw new DeliveryUnavailableException("The carrier holding your package has broken down. We will provide an update as soon as we can.");
            }
            System.out.println("Your "  + product.name + " is being delivered");
        }
        public void setBroken(boolean broken) {
            isBroken = broken;
        }
    }
}