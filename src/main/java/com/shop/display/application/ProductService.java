package com.shop.display.application;

import com.shop.display.domain.Brand;
import com.shop.display.domain.BrandRepository;
import com.shop.display.domain.Category;
import com.shop.display.domain.Product;
import com.shop.display.domain.ProductRepository;
import com.shop.display.interfaces.exception.BadRequestException;
import com.shop.display.interfaces.model.ProductCommand;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    public ProductService(ProductRepository productRepository,
                          BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
    }

    @Transactional
    public void save(ProductCommand command) {
        Category category;
        try {
            category = Category.valueOf(command.category());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid category name");
        }
        Brand brand = brandRepository.findBrandByName(command.brand())
                                     .orElseThrow(() -> new BadRequestException("Brand not found"));

        Product product = Product.builder()
                                 .brand(brand)
                                 .category(category)
                                 .price(command.price())
                                 .build();

        productRepository.save(product);
    }

    @Transactional
    public void update(long id,
                       ProductCommand command) {
        Product product = productRepository.findProduct(id)
                                           .orElseThrow(() -> new BadRequestException("Product not found"));

        Category category;
        try {
            category = Category.valueOf(command.category());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid category name");
        }
        Brand brand = brandRepository.findBrandByName(command.brand())
                                     .orElseThrow(() -> new BadRequestException("Brand not found"));

        product.setBrand(brand);
        product.setCategory(category);
        product.setPrice(command.price());
    }

    @Transactional
    public void delete(long id) {
        productRepository.findProduct(id)
                         .orElseThrow(() -> new BadRequestException("Product not found"));
        productRepository.delete(id);
    }
}
