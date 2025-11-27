package enums.goodcase;

public enum PaymentMethod {
    CREDIT_CARD {
        public boolean validate(String input) {
            return input.matches("\\d{16}");
        }
        public double calculateFee(double amount) {
            return amount * 0.03; // 3% 수수료
        }
    },
    BANK_TRANSFER {
        public boolean validate(String input) {
            return input.matches("\\d{10,14}");
        }
        public double calculateFee(double amount) {
            return 1000; // 고정 수수료
        }
    },
    PAYPAL {
        public boolean validate(String input) {
            return input.contains("@");
        }
        public double calculateFee(double amount) {
            return amount * 0.05;
        }
    };

    public abstract boolean validate(String input);
    public abstract double calculateFee(double amount);
}
