package fitch.product.card.service.exceptions;

public class CardNotFoundException extends CustomException {
    public CardNotFoundException(String message) {
        super(message);
    }
}
