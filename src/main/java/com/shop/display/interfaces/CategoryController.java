package com.shop.display.interfaces;

import com.shop.display.application.LowestPriceService;
import com.shop.display.application.PriceSearchService;
import com.shop.display.domain.Category;
import com.shop.display.interfaces.model.CategoryLowestPriceWrapperResponse;
import com.shop.display.interfaces.model.CategoryMinMaxResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private final LowestPriceService lowestPriceService;
    private final PriceSearchService priceSearchService;
    public static final String V1_PREFIX = "/v1";

    public CategoryController(LowestPriceService lowestPriceService,
                              PriceSearchService priceSearchService) {
        this.lowestPriceService = lowestPriceService;
        this.priceSearchService = priceSearchService;
    }

    @GetMapping(V1_PREFIX + "/categories/lowest")
    public CategoryLowestPriceWrapperResponse getCategoryLowestPrice() {
        return lowestPriceService.findCategoryLowestPrice();
    }

    @GetMapping(V1_PREFIX + "/categories/{categoryName}/range")
    public CategoryMinMaxResponse getCategoryRange(@PathVariable String categoryName) {
        Category category;
        try {
            category = Category.valueOf(categoryName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid category name");
        }
        return priceSearchService.findCategoryRange(category);
    }
}
