package strategy;

import java.math.BigDecimal;

public class PercentageDiscountStrategy implements DiscountStrategy {

    @Override
    public BigDecimal apply(BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(0.9));
    }
}
