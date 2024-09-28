package com.shop.display.infrastructure.dto;

import com.shop.display.domain.Product;

public record CategoryPriceDTO(Product product, long lowestPrice){
}
