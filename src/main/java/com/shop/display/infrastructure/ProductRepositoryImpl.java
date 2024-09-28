package com.shop.display.infrastructure;

import com.shop.display.domain.Brand;
import com.shop.display.domain.Category;
import com.shop.display.domain.Product;
import com.shop.display.domain.ProductRepository;
import com.shop.display.infrastructure.jpa.ProductJPARepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean isProductsExistByBrandId(Brand brand) {
        return productJPARepository.existsByBrand(brand);
    }

    @Override
    public Optional<Product> findLowestPriceProductByCategory(Category category) {
        return Optional.ofNullable(productJPARepository.findTopByCategoryOrderByPriceAsc(category));
    }

    @Override
    public Optional<Product> findHighestPriceProductByCategory(Category category) {
        return Optional.ofNullable(productJPARepository.findTopByCategoryOrderByPriceDesc(category));
    }

    @Override
    public Product save(Product product) {
        return productJPARepository.save(product);
    }

    @Override
    public Optional<Product> findProduct(long id) {
        return productJPARepository.findById(id);
    }

    @Override
    public void delete(long id) {
        productJPARepository.deleteById(id);
    }

}
