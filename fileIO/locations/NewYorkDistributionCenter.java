package fileIO.locations;

import fileIO.DistributionCenter;
import fileIO.issues.DeliveryRefusedException;

public class NewYorkDistributionCenter extends DistributionCenter {

    Delivery delivery = new Delivery();

    @Override
    public void shipProduct(DistributionCenter.Product product) {
        delivery.deliver(product);
    }

    static class Delivery {
        void deliver(DistributionCenter.Product product) {
            if(product.weight > 50) {
                System.out.println("Not delivering heavy product");
                throw new DeliveryRefusedException("Not delivering heavy product");
            }
            System.out.println("Your product " + product.name + " is being delivered");
        }
    }

}
