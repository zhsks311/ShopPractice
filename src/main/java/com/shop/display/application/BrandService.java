package com.shop.display.application;

import com.shop.display.domain.Brand;
import com.shop.display.domain.BrandRepository;
import com.shop.display.domain.ProductRepository;
import com.shop.display.interfaces.exception.BadRequestException;
import com.shop.display.interfaces.model.BrandCommand;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    public BrandService(BrandRepository brandRepository,
                        ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void save(BrandCommand command) {
        brandRepository.findBrandByName(command.name())
                       .ifPresent(brand -> {
                           throw new BadRequestException("Brand already exists");
                       });
        brandRepository.save(command.name());
    }

    @Transactional
    public void update(long id,
                       BrandCommand command) {
        Brand brand = brandRepository.findBrandById(id)
                                     .orElseThrow(() -> new BadRequestException("Brand not found"));
        brand.setName(command.name());
    }

    @Transactional
    public void delete(long id) {
        Brand brand = brandRepository.findBrandById(id)
                                     .orElseThrow(() -> new BadRequestException("Brand not found"));
        boolean productsExistByBrandId = productRepository.isProductsExistByBrandId(brand);
        if (productsExistByBrandId) {
            throw new BadRequestException("Products exist by brand");
        }
        brandRepository.delete(id);
    }
}
