package com.shop.display.interfaces.model;

import com.shop.display.domain.Category;
import lombok.Builder;

@Builder
public record CategoryMinMaxResponse(Category category, BrandProduct lowest, BrandProduct highest) {
    public record BrandProduct(String brand, String price) { }
}
