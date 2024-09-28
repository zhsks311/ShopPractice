package com.shop.display.infrastructure.jpa;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("integrationTest")
@SpringBootTest
class BrandJPARepositoryIntegrationTest {
    @Autowired
    private BrandJPARepository brandJPARepository;

    @Test
    void findLowestPriceBrand() {
        // when
        var result = brandJPARepository.findLowestPriceBrand(Pageable.ofSize(1));

        // then
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getName()).isEqualTo("D");
    }
}