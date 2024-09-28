package com.shop.display.application;

import com.shop.display.domain.BrandRepository;
import com.shop.display.domain.Product;
import com.shop.display.domain.ProductRepository;
import com.shop.display.interfaces.exception.BadRequestException;
import com.shop.display.interfaces.model.BrandLowestPriceResponse;
import com.shop.display.interfaces.model.CategoryLowestPriceWrapperResponse;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shop.display.ProductUtils.priceToStringWithComma;

@Service
public class LowestPriceService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    public LowestPriceService(ProductRepository productRepository,
                              BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
    }

    public CategoryLowestPriceWrapperResponse findCategoryLowestPrice() {
        var lowestPriceByCategory = productRepository.findLowestPriceByCategory();
        return mapToCategoryLowestPriceWrapperResponse(lowestPriceByCategory);
    }

    private CategoryLowestPriceWrapperResponse mapToCategoryLowestPriceWrapperResponse(List<Product> products) {
        var responses = products.stream()
                                .map(product -> new CategoryLowestPriceWrapperResponse.CategoryLowestPriceResponse(
                                        product.getCategory(), product.getBrandName(),
                                        product.getPriceStringWithComma()))
                                .toList();
        long totalPrice = products.stream()
                                  .map(Product::getPrice)
                                  .reduce(Long::sum)
                         .orElse(0L);
        return new CategoryLowestPriceWrapperResponse(responses, priceToStringWithComma(totalPrice));
    }

    public BrandLowestPriceResponse findBrandLowestPrice() {
        var lowestPriceBrand = brandRepository.findLowestPriceBrand()
                                              .orElseThrow(() -> new BadRequestException("No brand found"));
        var lowestTotalPriceBrand = productRepository.findProductsByBrandId(lowestPriceBrand);

        return mapToBrandLowestPriceResponse(lowestTotalPriceBrand);
    }

    private BrandLowestPriceResponse mapToBrandLowestPriceResponse(List<Product> products) {
        if (products.isEmpty()) {
            throw new BadRequestException("No product found");
        }
        var categoryPrices
                = products.stream()
                          .map(product -> new BrandLowestPriceResponse.CategoryPrice(product.getCategory(),
                                                                                     product.getPriceStringWithComma()))
                          .toList();
        long totalPrice = products.stream()
                                  .map(Product::getPrice)
                                  .reduce(Long::sum)
                                  .orElse(0L);
        return new BrandLowestPriceResponse(BrandLowestPriceResponse.LowestPrice
                                                    .builder()
                                                    .brand(products.get(0).getBrandName())
                                                    .categoryPrices(categoryPrices)
                                                    .totalAmount(priceToStringWithComma(totalPrice))
                                                    .build());
    }

}
