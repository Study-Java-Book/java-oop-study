package templatecallback;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private static Long orderIdCounter = 1L;

    private Long orderId;
    private final List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        this.orderId = orderIdCounter++;
        this.orderItems = orderItems;
    }

    public BigDecimal calculateTotalPrice(DiscountStrategy discountStrategy) {
        BigDecimal orderItemPrice = orderItems.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return discountStrategy.apply(orderItemPrice);
    }

    public Long getOrderId() {
        return orderId;
    }
}
