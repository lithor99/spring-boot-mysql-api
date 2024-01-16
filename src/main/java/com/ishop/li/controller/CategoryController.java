package com.ishop.li.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ishop.li.model.Category;
import com.ishop.li.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getUsers() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category getUser(@PathVariable Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @PostMapping()
    public Category createUser(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public Category updateUser(@PathVariable Long id, @RequestBody Category category) {
        Category _category = categoryRepository.findById(id).orElse(null);
        if (_category != null) {
            _category.setName(category.getName());
            return categoryRepository.save(_category);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
}
