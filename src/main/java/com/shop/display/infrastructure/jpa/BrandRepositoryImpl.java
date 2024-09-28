package com.shop.display.infrastructure.jpa;

import com.shop.display.domain.Brand;
import com.shop.display.domain.BrandRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BrandRepositoryImpl implements BrandRepository {
    private final BrandJPARepository brandJPARepository;

    public BrandRepositoryImpl(BrandJPARepository brandJPARepository) {
        this.brandJPARepository = brandJPARepository;
    }

    @Override
    public Optional<Brand> findLowestPriceBrand() {
        var lowestPriceBrand = brandJPARepository.findLowestPriceBrand(Pageable.ofSize(1));
        if (lowestPriceBrand.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(lowestPriceBrand.getFirst());
    }

    @Override
    public Optional<Brand> findBrandByName(String brandName) {
        return Optional.ofNullable(brandJPARepository.findByName(brandName));
    }
    @Override
    public Optional<Brand> findBrandById(long brandName) {
        return brandJPARepository.findById(brandName);
    }

    @Override
    public Brand save(String name) {
        return brandJPARepository.save(Brand.builder().name(name).build());
    }

    @Override
    public void delete(long id) {
        brandJPARepository.deleteById(id);
    }
}
