package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService() {
        logger.info("Starting OrderService");
    }

    public void placeOrder(String orderId, double basePrice) {
        logger.info("Placing order {}", orderId);
        logger.info("Order {} placed", orderId);
    }
}
