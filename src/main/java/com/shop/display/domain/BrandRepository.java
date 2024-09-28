package com.shop.display.domain;

import java.util.Optional;

public interface BrandRepository {
    Optional<Brand> findLowestPriceBrand();

    Optional<Brand> findBrandByName(String brandName);

    Optional<Brand> findBrandById(long brandName);

    Brand save(String name);

    void delete(long id);
}
