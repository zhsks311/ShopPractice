package com.shop.display.domain;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    @Test
    void isExpensive_when_otherIsExpensive_then_returnFalse() {
        // given
        Product product1 = Product.builder()
                                 .price(1000)
                                 .build();
        Product product2 = Product.builder()
                                  .price(1001)
                                  .build();

        // when
        boolean result = product1.isExpensiveThan(product2);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void isExpensive_when_otherIsCheaper_then_returnFalse() {
        // given
        Product product1 = Product.builder()
                                  .price(1000)
                                  .build();
        Product product2 = Product.builder()
                                  .price(999)
                                  .build();

        // when
        boolean result = product1.isExpensiveThan(product2);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void isExpensive_when_otherEquals_then_returnFalse() {
        // given
        Product product1 = Product.builder()
                                  .price(1000)
                                  .build();
        Product product2 = Product.builder()
                                  .price(1000)
                                  .build();

        // when
        boolean result = product1.isExpensiveThan(product2);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void getPriceStringWithComma() {
        // given
        Product product = Product.builder()
                                 .price(1000)
                                 .build();

        // when
        String result = product.getPriceStringWithComma();

        // then
        assertThat(result).isEqualTo("1,000");
    }
}