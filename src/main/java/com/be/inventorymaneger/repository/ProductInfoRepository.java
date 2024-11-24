package com.be.inventorymaneger.repository;

import com.be.inventorymaneger.model.Category;
import com.be.inventorymaneger.model.Product_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoRepository extends JpaRepository<Product_info,Integer> {
    @Query("SELECT p FROM Product_info p WHERE CONCAT(p.product_name, p.product_code, p.product_description) LIKE %?1%")
    List<Product_info> search(String keyword);
}
