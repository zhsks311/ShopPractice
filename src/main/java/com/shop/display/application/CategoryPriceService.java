package com.shop.display.application;

import com.shop.display.domain.Product;
import com.shop.display.domain.ProductRepository;
import com.shop.display.interfaces.model.CategoryLowestPriceWrapperResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryPriceService {
    private final ProductRepository productRepository;

    public CategoryPriceService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CategoryLowestPriceWrapperResponse findCategoryLowestPrice() {
        var lowestPriceByCategory = productRepository.findLowestPriceByCategory();
        return mapToResponse(lowestPriceByCategory);
    }
    private CategoryLowestPriceWrapperResponse mapToResponse(List<Product> products) {
        var responses = products.stream()
                               .map(product -> new CategoryLowestPriceWrapperResponse.CategoryLowestPriceResponse(
                                       product.getCategory(), product.getBrandName(),
                                       product.getPrice()))
                               .toList();
        long totalPrice = products.stream()
                         .map(Product::getPrice)
                         .reduce(Long::sum)
                         .orElse(0L);
        return new CategoryLowestPriceWrapperResponse(responses, totalPrice);
    }
}
