package com.shop.display.application;

import com.shop.display.domain.Category;
import com.shop.display.domain.Product;
import com.shop.display.domain.ProductRepository;
import com.shop.display.interfaces.model.CategoryMinMaxResponse;
import org.springframework.stereotype.Service;

@Service
public class PriceSearchService {
    private final ProductRepository productRepository;

    public PriceSearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CategoryMinMaxResponse findCategoryRange(Category category) {
        var highest = productRepository.findHighestPriceProductByCategory(category);
        var lowest = productRepository.findLowestPriceProductByCategory(category);
        return toCategoryMinMaxResponse(highest.orElseThrow(), lowest.orElseThrow());
    }

    private CategoryMinMaxResponse toCategoryMinMaxResponse(Product highest, Product lowest) {
        return CategoryMinMaxResponse.builder()
                .category(highest.getCategory())
                .highest(new CategoryMinMaxResponse.BrandProduct(highest.getBrandName(), highest.getPriceStringWithComma()))
                .lowest(new CategoryMinMaxResponse.BrandProduct(lowest.getBrandName(), lowest.getPriceStringWithComma()))
                .build();
    }
}

