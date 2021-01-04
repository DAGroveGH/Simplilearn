package fileIO.locations;

import fileIO.DistributionCenter;
import practice.company.undeliverable.DeliveryUnavailableException;

public class SeattleDistributionCenter extends DistributionCenter {
    Delivery delivery = new Delivery();

    static class Delivery {
        boolean isLost = true;
        void deliver(DistributionCenter.Product product) {
            if(isLost) {
                throw new DeliveryUnavailableException("Your product has been misplaced. We will work to resolve this as soon as possible");
            }
            System.out.println("Your product " + product.name + " has been found and is on it's way");
        }
        public void setLost(boolean isLost) {
            this.isLost = isLost;
        }
    }
}
