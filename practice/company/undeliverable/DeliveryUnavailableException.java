package practice.company.undeliverable;

public class DeliveryUnavailableException extends RuntimeException {
    public DeliveryUnavailableException(String message) {
        super(message);
    }
}
