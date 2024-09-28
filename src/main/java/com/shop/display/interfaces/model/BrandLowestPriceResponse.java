package com.shop.display.interfaces.model;

import com.shop.display.domain.Category;
import lombok.Builder;

import java.util.List;

public record BrandLowestPriceResponse(LowestPrice lowestPrice) {
    @Builder
    public record LowestPrice(String brand, List<CategoryPrice> categoryPrices, String totalAmount) { }
    public record CategoryPrice(Category category, String price) { }
}