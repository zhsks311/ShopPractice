package com.shop.display.infrastructure;

import com.shop.display.domain.Brand;
import com.shop.display.domain.Product;
import com.shop.display.domain.ProductRepository;
import com.shop.display.infrastructure.jpa.ProductJPARepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJPARepository productJPARepository;

    public ProductRepositoryImpl(ProductJPARepository productJPARepository) {
        this.productJPARepository = productJPARepository;
    }

    @Override
    public List<Product> findLowestPriceByCategory() {
        return productJPARepository.findCategoryLowestPrices();
    }

    @Override
    public List<Product> findProductsByBrandId(Brand brand) {
        return productJPARepository.findProductsByBrand(brand);
    }

}
