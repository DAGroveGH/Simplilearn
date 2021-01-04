package fileIO.locations;

import fileIO.DistributionCenter;
import fileIO.issues.DeliveryUnavailableException;

public class ChicagoDistributionCenter extends DistributionCenter {
    public Delivery delivery = new Delivery();

    public void shipProduct(Product product) throws DeliveryUnavailableException {
        delivery.deliver(product);
    }

    public static class Delivery {
        boolean isBroken = true;
        public void deliver(DistributionCenter.Product product) {
            if(isBroken) {
                throw new DeliveryUnavailableException("Your item has been delayed, we will update you as the situation changes");
            }
            System.out.println("Your product " + product.name + " is being delivered");
        }

        public void setBroken(boolean isBroken) {
            this.isBroken = isBroken;
        }
    }
}
