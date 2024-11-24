package com.be.inventorymaneger.service;

import com.be.inventorymaneger.model.Category;
import com.be.inventorymaneger.model.Product_info;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductInfoService {
    Page<Product_info> findAll(int pageNum);
    List<Product_info> findAll();

    void save(Product_info productInfo);

    Product_info findProduct_InfoById(Integer id);

    void deleteProduct_infoById(Product_info product_info);

    List<Product_info> search(String keyword);
}
