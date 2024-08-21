package com.ishop.li.repository;

// import org.apache.el.stream.Optional;
// import org.hibernate.mapping.Map;

import java.util.Map;
// import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ishop.li.model.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
        @Query(value = "SELECT p.id, p.name, p.description, p.quantity, p.price, p.image, c.name AS category, p.created_at, p.updated_at\n"
                        + "FROM tb_products p INNER JOIN tb_categories c\n"
                        + "ON p.category_id=c.id ORDER BY p.id DESC", nativeQuery = true)
        List<Map<String, Object>> getAllProducts();

        @Query(value = "SELECT p.id, p.name, p.description, p.quantity, p.price, p.image, c.name AS category, p.created_at, p.updated_at\n"
                        + "FROM tb_products p INNER JOIN tb_categories c\n"
                        + "ON p.category_id=c.id ORDER BY p.id DESC LIMIT :limit OFFSET :page", nativeQuery = true)
        List<Map<String, Object>> getPageProducts(int page, int limit);

        @Query(value = "SELECT p.id, p.name, p.description, p.quantity, p.price, p.image, c.name AS category, p.created_at, p.updated_at\n"
                        + "FROM tb_products p INNER JOIN tb_categories c\n"
                        + "ON p.category_id=c.id WHERE p.category_id=:categoryId\n"
                        + "ORDER BY p.id DESC", nativeQuery = true)
        List<Map<String, Object>> getAllProductByCategory(Long categoryId);

        @Query(value = "SELECT p.id, p.name, p.description, p.quantity, p.price, p.image, c.name AS category, p.created_at, p.updated_at\n"
                        + "FROM tb_products p INNER JOIN tb_categories c\n"
                        + "ON p.category_id=c.id WHERE p.category_id=:categoryId\n"
                        + "ORDER BY p.id DESC LIMIT :limit OFFSET :page", nativeQuery = true)
        List<Map<String, Object>> getPageProductByCategory(Long categoryId, int page, int limit);

        @Query(value = "SELECT p.id, p.name, p.description, p.quantity, p.price, p.image, c.name AS category, p.created_at, p.updated_at\n"
                        + "FROM tb_products p INNER JOIN tb_categories c\n"
                        + "ON p.category_id=c.id WHERE p.id=:id", nativeQuery = true)
        Map<String, Object> getProductById(Long id);

}
