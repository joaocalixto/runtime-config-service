package service.singleton;

import config.singleton.FeatureFlagRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final FeatureFlagRegistry registry;

    public OrderService() {
        logger.info("Starting OrderService (singleton)");
        this.registry = FeatureFlagRegistry.getInstance();
        logger.info("OrderService using FeatureFlagRegistry (id={})", System.identityHashCode(registry));
    }

    public void placeOrder(String orderId, double basePrice) {
        logger.info("Placing order {} (singleton)", orderId);
        boolean verboseLogging = registry.isEnabled("verbose-logging");
        boolean auditEnabled = registry.isEnabled("audit-orders");

        if (verboseLogging) {
            logger.info("Verbose logging enabled for order {} with base price {}", orderId, basePrice);
        }
        if (auditEnabled) {
            logger.info("Audit flag enabled for order {}", orderId);
        }

        logger.info("Order {} placed", orderId);
    }
}
