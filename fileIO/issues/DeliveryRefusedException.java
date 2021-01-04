package fileIO.issues;

public class DeliveryRefusedException extends RuntimeException {
    public DeliveryRefusedException(String message) {
        super(message);
    }
}
