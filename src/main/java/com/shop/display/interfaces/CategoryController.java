package com.shop.display.interfaces;

import com.shop.display.application.CategoryPriceService;
import com.shop.display.interfaces.model.CategoryLowestPriceWrapperResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private final CategoryPriceService categoryPriceService;
    public static final String V1_PREFIX = "/v1";

    public CategoryController(CategoryPriceService categoryPriceService) {
        this.categoryPriceService = categoryPriceService;
    }

    @GetMapping(V1_PREFIX + "/categories/lowest")
    public CategoryLowestPriceWrapperResponse getCategoryLowestPrice() {
        return categoryPriceService.findCategoryLowestPrice();
    }
}
