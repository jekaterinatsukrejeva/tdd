package exception;

public class CheckoutException extends RuntimeException {

    public CheckoutException(String errorMessage) {
        super(errorMessage);
    }
}
