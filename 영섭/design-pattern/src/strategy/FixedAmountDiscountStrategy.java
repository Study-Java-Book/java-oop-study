package strategy;

import java.math.BigDecimal;

public class FixedAmountDiscountStrategy implements DiscountStrategy {

    @Override
    public BigDecimal apply(BigDecimal price) {
        return price.subtract(BigDecimal.valueOf(5000));
    }
}
