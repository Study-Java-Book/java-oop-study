package templatecallback;

import java.util.List;

public class Client {

    private Long clientId;
    private String name;

    public Client(Long clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public Order createOrder(List<OrderItem> orderItems) {
        return new Order(orderItems);
    }

    public String getName() {
        return name;
    }
}
