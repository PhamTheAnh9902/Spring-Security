package com.be.inventorymaneger.service.Impl;

import com.be.inventorymaneger.model.Category;
import com.be.inventorymaneger.model.Product_info;
import com.be.inventorymaneger.repository.ProductInfoRepository;
import com.be.inventorymaneger.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Override
    public Page<Product_info> findAll(int pageNum) {
       int pageSize =3;
        Pageable pageable = PageRequest.of(pageNum -1, pageSize);
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public List<Product_info> findAll() {
        return productInfoRepository.findAll();
    }

    @Override
    public void save(Product_info productInfo) {
        productInfoRepository.save(productInfo);
    }

    @Override
    public Product_info findProduct_InfoById(Integer id) {
        Optional<Product_info> optional = productInfoRepository.findById(id);
        Product_info product_info = null;
        if(optional.isPresent()){
            product_info = optional.get();
        }
        else {
            throw new RuntimeException("Product not found for id: "+id);
        }
        return product_info;
    }

    @Override
    public void deleteProduct_infoById(Product_info product_info) {
        productInfoRepository.delete(product_info);
    }

    @Override
    public List<Product_info> search(String keyword) {
        if(keyword != null){
            return productInfoRepository.search(keyword);
        }
        return productInfoRepository.findAll();
    }
}
