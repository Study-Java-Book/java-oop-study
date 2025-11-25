package strategy;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private static Long orderIdCounter = 1L;

    private Long orderId;
    private final List<OrderItem> orderItems;
    private final BigDecimal totalPrice;

    private final DiscountStrategy discountStrategy;

    public Order(List<OrderItem> orderItems, DiscountStrategy discountStrategy) {
        this.orderId = orderIdCounter++;
        this.orderItems = orderItems;
        this.discountStrategy = discountStrategy;
        this.totalPrice = calculateTotalPrice();
    }

    private BigDecimal calculateTotalPrice() {
        BigDecimal orderItemPrice = orderItems.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return discountStrategy.apply(orderItemPrice);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Long getOrderId() {
        return orderId;
    }
}
