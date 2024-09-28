package com.shop.display.domain;

import java.util.List;

public interface ProductRepository {
    List<Product> findLowestPriceByCategory();
}
