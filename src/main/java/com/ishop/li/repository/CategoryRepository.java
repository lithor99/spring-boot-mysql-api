package com.ishop.li.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ishop.li.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
