package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PricingService {
    private static final Logger logger = LoggerFactory.getLogger(PricingService.class);

    public PricingService() {
        logger.info("Starting PricingService");
    }

    public double calculatePrice(double basePrice) {
        logger.info("Calculating price with PricingService");
        return basePrice;
    }
}
