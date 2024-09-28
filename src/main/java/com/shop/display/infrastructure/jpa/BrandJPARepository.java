package com.shop.display.infrastructure.jpa;

import com.shop.display.domain.Brand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandJPARepository extends JpaRepository<Brand, Long> {
    @Query("""
            SELECT b FROM Brand b
                 JOIN b.products p
                 GROUP BY b.id
                 ORDER BY SUM(p.price) ASC
    """)
    List<Brand> findLowestPriceBrand(Pageable pageable);
    Brand findByName(String name);
}
