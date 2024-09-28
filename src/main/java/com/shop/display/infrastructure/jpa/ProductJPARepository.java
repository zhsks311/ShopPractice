package com.shop.display.infrastructure.jpa;

import com.shop.display.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductJPARepository extends JpaRepository<Product, Long> {
    @Query("""
           SELECT p
           FROM Product p
           JOIN FETCH p.brand
           LEFT JOIN Product p2
            ON p.category = p2.category AND (p2.price < p.price OR (p2.price = p.price AND p2.id < p.id))
           WHERE p2.id IS NULL
           ORDER BY p.category
           """)
    List<Product> findCategoryLowestPrices();

}
