package be.fooda.backend.product.service.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource NOT found..");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
