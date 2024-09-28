package com.shop.display.infrastructure.jpa;


import com.shop.display.domain.Product;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;
import java.util.stream.Collectors;

import static com.shop.display.domain.Category.ACCESSORIES;
import static com.shop.display.domain.Category.BAGS;
import static com.shop.display.domain.Category.HATS;
import static com.shop.display.domain.Category.OUTERWEAR;
import static com.shop.display.domain.Category.PANTS;
import static com.shop.display.domain.Category.SNEAKERS;
import static com.shop.display.domain.Category.SOCKS;
import static com.shop.display.domain.Category.TOPS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Tag("integrationTest")
@SpringBootTest
class ProductJPARepositoryIntegrationTest {
    @Autowired
    private ProductJPARepository productJPARepository;

    @Test
    void findCategoryLowestPrices() {
        // when
        var result = productJPARepository.findCategoryLowestPrices();

        // then
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();

        var categoryMap = result.stream()
                                .collect(Collectors.toMap(Product::getCategory,
                                                          Function.identity(),
                                                          (p1, p2) -> p1.isExpensiveThan(p2) ? p1 : p2));
        assertAll(() -> assertThat(categoryMap).hasSize(8),
                  () -> assertThat(categoryMap.get(TOPS).getPrice()).isEqualTo(10000),
                  () -> assertThat(categoryMap.get(OUTERWEAR).getPrice()).isEqualTo(5000),
                  () -> assertThat(categoryMap.get(PANTS).getPrice()).isEqualTo(3000),
                  () -> assertThat(categoryMap.get(SNEAKERS).getPrice()).isEqualTo(9000),
                  () -> assertThat(categoryMap.get(BAGS).getPrice()).isEqualTo(2000),
                  () -> assertThat(categoryMap.get(HATS).getPrice()).isEqualTo(1500),
                  () -> assertThat(categoryMap.get(SOCKS).getPrice()).isEqualTo(1700),
                  () -> assertThat(categoryMap.get(ACCESSORIES).getPrice()).isEqualTo(1900)
                  );
    }
}