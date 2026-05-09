package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FeatureFlagFileReader;

import java.util.HashMap;
import java.util.Map;

public class PricingService {
    private static final Logger logger = LoggerFactory.getLogger(PricingService.class);
    private static Map<String, Boolean> flagsResult = new HashMap<>();

    public PricingService() {
        logger.info("Starting PricingService");
        FeatureFlagFileReader featureFlagFileReader = new FeatureFlagFileReader();
        this.flagsResult = featureFlagFileReader.readFromClasspath("feature-flags.properties");
    }

    public double calculatePrice(double basePrice) {
        Boolean useDiscount = flagsResult.get("use-discount");
        if (useDiscount) {
            // 20% discount provided
            basePrice = basePrice * 0.8;
        }
        logger.info("Calculating price with PricingService");
        logger.info("Final price: " + basePrice);
        return basePrice;
    }
}
