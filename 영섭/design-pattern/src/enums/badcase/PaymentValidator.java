package enums.badcase;

public class PaymentValidator {

    public boolean validate(PaymentMethod method, String input) {
        return switch (method) {
            case CREDIT_CARD -> input.matches("\\d{16}");
            case BANK_TRANSFER -> input.matches("\\d{10,14}");
            case PAYPAL -> input.contains("@");
        };
    }
}
