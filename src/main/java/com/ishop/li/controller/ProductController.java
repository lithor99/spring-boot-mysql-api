package com.ishop.li.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ishop.li.model.Product;
import com.ishop.li.repository.ProductRepository;
import com.ishop.li.response.response;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity<?> getProducts(@Param("page") String page, @Param("limit") String limit,
            @Param("category_id") String categoryId) {
        if (page.equals("0") || page.equals("") || page.equals(null)) {
            page = "1";
        }
        if (limit.equals("0") || limit.equals("") || limit.equals(null)) {
            limit = "10";
        }
        String timestamp = LocalDateTime.now(ZoneId.of("Asia/Vientiane"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (!categoryId.equals(null) && !categoryId.equals("")) {
            // System.out.println("---------case-------1:" + limit);
            if (limit.equals("all")) {
                // System.out.println("---------case-------1.1:" + limit);
                List<Map<String, Object>> data = productRepository.getAllProducts();
                return ResponseEntity.status(HttpStatus.OK).body(
                        new response(timestamp, "ສຳເລັດ", true, 200, 1, data.size(), data.size(),
                                data));
            } else {
                // System.out.println("---------case-------1.2");
                List<Map<String, Object>> data = productRepository.getPageProducts(
                        Integer.parseInt(page) * Integer.parseInt(limit) - Integer.parseInt(limit),
                        Integer.parseInt(limit));
                return ResponseEntity.status(HttpStatus.OK).body(
                        new response(timestamp, "ສຳເລັດ", true, 200, Integer.parseInt(page), Integer.parseInt(limit),
                                data.size(),
                                data));
            }
        } else {
            // System.out.println("---------case-------2");
            if (limit.equals("all")) {
                // System.out.println("---------case-------2.1");
                List<Map<String, Object>> data = productRepository.getAllProductByCategory(Long.parseLong(categoryId));
                return ResponseEntity.status(HttpStatus.OK).body(
                        new response(timestamp, "ສຳເລັດ", true, 200, 1, data.size(), data.size(),
                                data));
            } else {
                List<Map<String, Object>> data = productRepository.getPageProductByCategory(Long.parseLong(categoryId),
                        Integer.parseInt(page) * Integer.parseInt(limit) - Integer.parseInt(limit),
                        Integer.parseInt(limit));
                return ResponseEntity.status(HttpStatus.OK).body(
                        new response(timestamp, "ສຳເລັດ", true, 200, Integer.parseInt(page), Integer.parseInt(limit),
                                data.size(),
                                data));
            }
        }

    }

    // @GetMapping("/category/{id}")
    // public ResponseEntity<?> getProductByCategory(@PathVariable Long id,
    // @Param("page") int page,
    // @Param("limit") int limit) {
    // if (page == 0) {
    // page = 1;
    // }
    // if (limit == 0) {
    // limit = 10;
    // }
    // String timestamp = LocalDateTime.now(ZoneId.of("Asia/Vientiane"))
    // .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    // List<Map<String, Object>> data = productRepository.getProductByCategory(id,
    // page * limit - limit, limit);
    // return ResponseEntity.status(HttpStatus.OK).body(
    // new response(timestamp, "ສຳເລັດ", true, 200, page, limit, data.size(),
    // data));

    // }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        String timestamp = LocalDateTime.now(ZoneId.of("Asia/Vientiane"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> data = productRepository.getProductById(id);
        if (data == null || data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new response(timestamp, "ບໍ່ພົບຂໍ້ມູນ", false, 400, 0, 0, 0, data));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new response(timestamp, "ສຳເລັດ", true, 200, 1, 1, 1, data));
    }

    @SuppressWarnings("null")
    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product data = productRepository.save(product);
        String timestamp = LocalDateTime.now(ZoneId.of("Asia/Vientiane"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return ResponseEntity.status(HttpStatus.OK).body(
                new response(timestamp, "ສຳເລັດ", true, 201, 1, 1, 1, data));
    }

    @SuppressWarnings("null")
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product _product = productRepository.findById(id).orElse(null);
        if (_product != null) {
            _product.setName(product.getName() != null ? product.getName() : _product.getName());
            _product.setDescription(
                    product.getDescription() != null ? product.getDescription() : _product.getDescription());
            _product.setPrice(product.getPrice() != null ? product.getPrice() : _product.getPrice());
            _product.setQuantity(product.getQuantity() != null ? product.getQuantity() : _product.getQuantity());
            _product.setImage(product.getImage() != null ? product.getImage() : _product.getImage());
            return productRepository.save(_product);
        } else {
            return null;
        }
    }

    @SuppressWarnings("null")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
