package strategy;

import java.math.BigDecimal;

public class Item {

    private Long itemId;
    private String name;
    private BigDecimal price;

    public Item(Long itemId, String name, BigDecimal price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    public Long getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
