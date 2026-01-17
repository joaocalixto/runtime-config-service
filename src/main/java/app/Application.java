package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.singleton.OrderService;
import service.singleton.PricingService;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Starting application (singleton)");

        for (int i = 1; i <= 10; i++) {
            logger.info("--- Simulated request {} (singleton) ---", i);
            PricingService pricingService = new PricingService();
            OrderService orderService = new OrderService();

            double price = pricingService.calculatePrice(100.0 + i);
            orderService.placeOrder("ORD-" + i, price);
        }

        logger.info("Application finished (singleton)");
    }
}
