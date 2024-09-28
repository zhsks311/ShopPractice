package com.shop.display.domain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findLowestPriceByCategory();

    List<Product> findProductsByBrandId(Brand brand);
    boolean isProductsExistByBrandId(Brand brand);

    Optional<Product> findLowestPriceProductByCategory(Category category);

    Optional<Product> findHighestPriceProductByCategory(Category category);

    Product save(Product product);

    Optional<Product> findProduct(long id);

    void delete(long id);
}
