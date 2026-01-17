package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.OrderService;
import service.PricingService;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Starting application (starter)");

        for (int i = 1; i <= 3; i++) {
            logger.info("--- Simulated request {} ---", i);
            PricingService pricingService = new PricingService();
            OrderService orderService = new OrderService();

            double price = pricingService.calculatePrice(100.0 + i);
            orderService.placeOrder("ORD-" + i, price);
        }

        logger.info("Application finished (starter)");
    }
}
