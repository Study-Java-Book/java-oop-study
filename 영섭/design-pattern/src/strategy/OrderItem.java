package strategy;

import java.math.BigDecimal;

public class OrderItem {

    private Item item;
    private int quantity;

    public OrderItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return item.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
