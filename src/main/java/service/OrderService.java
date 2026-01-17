package service;

import config.FeatureFlagLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final FeatureFlagLoader featureFlagLoader;

    public OrderService() {
        logger.info("Starting OrderService");
        this.featureFlagLoader = new FeatureFlagLoader();
        logger.info("OrderService using FeatureFlagLoader (id={})", System.identityHashCode(featureFlagLoader));
    }

    public void placeOrder(String orderId, double basePrice) {
        logger.info("Placing order {}", orderId);
        boolean verboseLogging = featureFlagLoader.isEnabled("verbose-logging");
        boolean auditEnabled = featureFlagLoader.isEnabled("audit-orders");

        if (verboseLogging) {
            logger.info("Verbose logging enabled for order {} with base price {}", orderId, basePrice);
        }
        if (auditEnabled) {
            logger.info("Audit flag enabled for order {}", orderId);
        }

        logger.info("Order {} placed", orderId);
    }
}
