package com.be.inventorymaneger.service.Impl;

import com.be.inventorymaneger.model.Category;
import com.be.inventorymaneger.repository.CategoryRepository;
import com.be.inventorymaneger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findCategoryById(String id) {
        Optional<Category> optional = categoryRepository.findById(Long.valueOf(id));
        Category category = null;
        if (optional.isPresent()){
            category = optional.get();
        }
        else {
            throw new RuntimeException("Category not found for id: "+id);
        }
        return category;
    }

    @Override
    public void deleteCategoryById(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> search(String keyword) {
        if(keyword != null){
           return categoryRepository.search(keyword);
        }
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAll(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
//        List<Category> categories = categoryRepository.findAll();
        return categoryRepository.findAll(pageable);
    }
}
