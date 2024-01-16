package com.ishop.li.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ishop.li.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
