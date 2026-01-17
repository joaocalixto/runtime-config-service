package service.singleton;

import config.singleton.FeatureFlagRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PricingService {
    private static final Logger logger = LoggerFactory.getLogger(PricingService.class);

    private final FeatureFlagRegistry registry;

    public PricingService() {
        logger.info("Starting PricingService (singleton)");
        this.registry = FeatureFlagRegistry.getInstance();
        logger.info("PricingService using FeatureFlagRegistry (id={})", System.identityHashCode(registry));
    }

    public double calculatePrice(double basePrice) {
        logger.info("Calculating price with PricingService (singleton)");
        boolean useDiscount = registry.isEnabled("use-discount");
        boolean usePremiumPricing = registry.isEnabled("use-premium-pricing");

        double price = basePrice;
        if (usePremiumPricing) {
            price = price * 1.25;
        }
        if (useDiscount) {
            price = price * 0.90;
        }

        logger.info("Price calculated: {} (use-discount={}, use-premium-pricing={})", price, useDiscount, usePremiumPricing);
        return price;
    }
}
