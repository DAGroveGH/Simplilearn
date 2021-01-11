package practice.company;

import java.util.ArrayList;
import java.util.List;

public abstract class DistributionCenter {
    List<Product> productOnHand = new ArrayList<Product>();

    public static class Product {
        public String name;
        public int quantityOnHand = 0;
        public Product(String name, int quantityOnHand) {
            this.name = name;
            this.quantityOnHand = quantityOnHand;
        }
    }

    public void receiveShipment(Product product) {
        if(!this.productOnHand.contains(product)) {
            this.productOnHand.add(product);
        }
        for(int i = 0; i < productOnHand.size(); i++) {
            if(productOnHand.get(i).name.equals(product.name)) {
                productOnHand.get(i).quantityOnHand += product.quantityOnHand;
            }
        }
    }

    public abstract void shipProduct(Product product);
}
