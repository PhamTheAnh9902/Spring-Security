package com.be.inventorymaneger.service;

import com.be.inventorymaneger.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Page<Category> findAll(int pageNum);
    void save(Category category);

    Category findCategoryById(String id);
    void deleteCategoryById(Category category);

    List<Category> search(String keyword);


}
