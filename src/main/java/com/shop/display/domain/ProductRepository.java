package com.shop.display.domain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findLowestPriceByCategory();

    List<Product> findProductsByBrandId(Brand brandId);

    Optional<Product> findLowestPriceProductByCategory(Category category);

    Optional<Product> findHighestPriceProductByCategory(Category category);
}
