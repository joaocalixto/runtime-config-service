package service;

import config.FeatureFlagLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PricingService {
    private static final Logger logger = LoggerFactory.getLogger(PricingService.class);

    private final FeatureFlagLoader featureFlagLoader;

    public PricingService() {
        logger.info("Starting PricingService");
        this.featureFlagLoader = new FeatureFlagLoader();
        logger.info("PricingService using FeatureFlagLoader (id={})", System.identityHashCode(featureFlagLoader));
    }

    public double calculatePrice(double basePrice) {
        logger.info("Calculating price with PricingService");
        boolean useDiscount = featureFlagLoader.isEnabled("use-discount");
        boolean usePremiumPricing = featureFlagLoader.isEnabled("use-premium-pricing");

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
