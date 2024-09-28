package com.shop.display.application;

import com.shop.display.domain.Brand;
import com.shop.display.domain.BrandRepository;
import com.shop.display.interfaces.model.BrandCommand;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Transactional
    public void save(BrandCommand command) {
        brandRepository.findBrandByName(command.name())
                          .ifPresent(brand -> {
                              throw new RuntimeException("Brand already exists");
                          });
        brandRepository.save(command.name());
    }

    @Transactional
    public void update(long id,
                       BrandCommand command) {
        Brand brand = brandRepository.findBrandById(id)
                                     .orElseThrow(() -> new RuntimeException("Brand not found"));
        brand.setName(command.name());
    }

    @Transactional
    public void delete(long id) {
        brandRepository.findBrandById(id)
                       .orElseThrow(() -> new RuntimeException("Brand not found"));
        brandRepository.delete(id);
    }
}
