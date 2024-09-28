package com.shop.display.domain;

import java.util.Optional;

public interface BrandRepository {
    Optional<Brand> findLowestPriceBrand();
}
