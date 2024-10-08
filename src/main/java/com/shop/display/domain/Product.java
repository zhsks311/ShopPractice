package com.shop.display.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.shop.display.ProductUtils.priceToStringWithComma;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product",
       indexes = {
               @Index(name = "idx_product_brand_id", columnList = "brand_id"),
               @Index(name = "idx_product_category_price", columnList = "category, price")
       }
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private long price;

    public boolean isExpensiveThan(Product otherProduct) {
        return this.getPrice() > otherProduct.getPrice();
    }

    public String getBrandName() {
        return brand.getName();
    }

    public String getPriceStringWithComma() {
        return priceToStringWithComma(this.getPrice());
    }
}


