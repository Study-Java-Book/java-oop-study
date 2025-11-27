package enums.badcase;

public class PaymentCalculateFee {

    public double calculateFee(PaymentMethod method, double amount) {
        return switch (method) {
            case CREDIT_CARD -> amount * 0.03;
            case BANK_TRANSFER -> 1000;
            case PAYPAL -> amount * 0.05;
        };
    }
}
