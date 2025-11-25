package strategy;

import java.math.BigDecimal;
import java.util.List;

public class OrderMain {

    public static void main(String[] args) {
        // 상품 생성
        Item item1 = new Item(1L, "볼펜", new BigDecimal(1000));
        Item item2 = new Item(2L, "노트북", new BigDecimal(2_000_000));

        // 주문 항목 생성
        List<OrderItem> orderItems = List.of(new OrderItem(item1, 5), new OrderItem(item2, 1));

        // 고객 생성
        Client client = new Client(1L, "이영섭");

        // 주문 생성
        // 1. 정률 할인
        Order percentageDiscountOrder = client.createOrder(orderItems, DiscountPolicy.PERCENTAGE);

        // 2. 정액 할인
        Order fixedAmountDiscountOrder = client.createOrder(orderItems, DiscountPolicy.FIXED_AMOUNT);

        // 3. 할인 없음
        Order noneDiscountOrder = client.createOrder(orderItems, DiscountPolicy.NONE);

        System.out.println("percentageDiscountOrder.getTotalPrice() = " + percentageDiscountOrder.getTotalPrice());

        System.out.println("fixedAmountDiscountOrder.getTotalPrice() = " + fixedAmountDiscountOrder.getTotalPrice());

        System.out.println("noneDiscountOrder.getTotalPrice() = " + noneDiscountOrder.getTotalPrice());
    }
}
