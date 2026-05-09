package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FeatureFlagFileReader;

public class PricingService {
    private static final Logger logger = LoggerFactory.getLogger(PricingService.class);
    FeatureFlagFileReader featureFlagFileReader = FeatureFlagFileReader.getInstance();

    public PricingService() {
        logger.info("Starting PricingService");
    }

    public double calculatePrice(double basePrice) {
        Boolean useDiscount = featureFlagFileReader.getFlagsResult("use-discount");
        if (useDiscount) {
            // 20% discount provided
            basePrice = basePrice * 0.8;
        }
        logger.info("Calculating price with PricingService");
        logger.info("Final price: " + basePrice);
        return basePrice;
    }
}
