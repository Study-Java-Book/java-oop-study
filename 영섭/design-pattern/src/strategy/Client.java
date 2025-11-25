package strategy;

import java.util.List;

public class Client {

    private Long clientId;
    private String name;

    public Client(Long clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public Order createOrder(List<OrderItem> orderItems, DiscountPolicy discountPolicy) {
        DiscountStrategy strategy = discountStrategy(discountPolicy);
        return new Order(orderItems, strategy);
    }

    private DiscountStrategy discountStrategy(DiscountPolicy discountPolicy) {
        return switch (discountPolicy) {
            case PERCENTAGE -> new PercentageDiscountStrategy();
            case FIXED_AMOUNT -> new FixedAmountDiscountStrategy();
            case NONE -> new NoDiscountStrategy();
        };
    }

    public String getName() {
        return name;
    }
}
