package service;

import static org.junit.jupiter.api.Assertions.*;
class PricingServiceTest {

    PricingService pricingService = new PricingService();

    @org.junit.jupiter.api.Test
    void calculatePrice() {
        assertEquals(80, pricingService.calculatePrice(100));
    }
}