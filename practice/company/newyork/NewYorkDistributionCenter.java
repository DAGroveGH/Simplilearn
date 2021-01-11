package practice.company.newyork;

import practice.company.DistributionCenter;
import practice.company.undeliverable.DeliveryRefusedException;

public class NewYorkDistributionCenter extends DistributionCenter {

    Delivery delivery = new Delivery();

    @Override
    public void shipProduct(DistributionCenter.Product product) {
        delivery.deliver(product);
    }

    static class Delivery {
        void deliver(DistributionCenter.Product product) {
            if(product.name.equals("kettlebells")) {
                System.out.println("Not delivering heavy product");
                throw new DeliveryRefusedException("Not delivering heavy product");
            }
            System.out.println("Your " + product.name + " is being delivered");
        }
    }
}


