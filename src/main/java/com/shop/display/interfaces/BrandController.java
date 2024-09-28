package com.shop.display.interfaces;

import com.shop.display.application.LowestPriceService;
import com.shop.display.interfaces.model.BrandLowestPriceResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrandController {
    private final LowestPriceService lowestPriceService;
    public static final String V1_PREFIX = "/v1";

    public BrandController(LowestPriceService lowestPriceService) {
        this.lowestPriceService = lowestPriceService;
    }

    @GetMapping(V1_PREFIX + "/brands/all/lowest")
    public BrandLowestPriceResponse getBrandLowestPrice() {
        return lowestPriceService.findBrandLowestPrice();
    }
}
