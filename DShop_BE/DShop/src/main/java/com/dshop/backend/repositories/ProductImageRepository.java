package com.dshop.backend.repositories;


import com.dshop.backend.models.Product;
import com.dshop.backend.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProduct(Product product);

    ProductImage findByUrlAndProductId(String url, long productId);
}
