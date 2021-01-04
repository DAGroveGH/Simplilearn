package fileIO;

import java.util.List;
import java.util.ArrayList;

public class DistributionCenter {

    List<Product> productOnHand = new ArrayList<Product>();

    public static class Product {
        public String name;
        public int quantity;
        public int weight;
        public Product(String name, int quantity, int weight) {
            this.name = name;
            this.quantity = quantity;
            this.weight = weight;
        }
    }

    public void receiveShipment(Product product) {
        if(!this.productOnHand.contains(product)) {
            this.productOnHand.add(product);
        }
        for(int i = 0; i < productOnHand.size(); i++) {
            if(productOnHand.get(i).name.equals(product.name)) {
                productOnHand.get(i).quantity += product.quantity;
            }
        }
    }

    public abstract void shipProduct(Product product);

}
