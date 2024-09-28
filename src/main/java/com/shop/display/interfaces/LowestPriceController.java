package com.shop.display.interfaces;

import com.shop.display.application.LowestPriceService;
import com.shop.display.interfaces.model.BrandLowestPriceResponse;
import com.shop.display.interfaces.model.CategoryLowestPriceWrapperResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LowestPriceController {
    private final LowestPriceService lowestPriceService;
    public static final String V1_PREFIX = "/v1";

    public LowestPriceController(LowestPriceService lowestPriceService) {
        this.lowestPriceService = lowestPriceService;
    }

    @GetMapping(V1_PREFIX + "/categories/lowest")
    public CategoryLowestPriceWrapperResponse getCategoryLowestPrice() {
        return lowestPriceService.findCategoryLowestPrice();
    }

    @GetMapping(V1_PREFIX + "/brands/all/lowest")
    public BrandLowestPriceResponse getBrandLowestPrice() {
        return lowestPriceService.findBrandLowestPrice();
    }
}
