package practice.company.undeliverable;

public class DeliveryRefusedException extends RuntimeException {
    public DeliveryRefusedException(String message) {
        super(message);
    }
}
