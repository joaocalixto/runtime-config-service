package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PricingServiceTest {

    @Test
    void calculatePrice() {
        PricingService pricingService = new PricingService();
        assertEquals(80, pricingService.calculatePrice(100));
    }
    @Test
    void calculatePrice2() {
        PricingService pricingService = new PricingService();
        assertEquals(80, pricingService.calculatePrice(100));
    }
}