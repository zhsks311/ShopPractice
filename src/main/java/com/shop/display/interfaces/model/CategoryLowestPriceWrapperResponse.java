package com.shop.display.interfaces.model;

import com.shop.display.domain.Category;

import java.util.List;


public record CategoryLowestPriceWrapperResponse(List<CategoryLowestPriceResponse> categoryLowestPriceResponses, String total) {
    public record CategoryLowestPriceResponse(Category category, String brandName, String price) {

    }
}