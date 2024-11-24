package com.be.inventorymaneger.repository;

import com.be.inventorymaneger.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT p FROM Category p WHERE CONCAT(p.category_name, p.code, p.decription) LIKE %?1%")
    List<Category> search(String keyword);
}
