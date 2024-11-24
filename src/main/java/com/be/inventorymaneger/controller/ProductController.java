package com.be.inventorymaneger.controller;

import com.be.inventorymaneger.model.Product_info;
import com.be.inventorymaneger.service.ProductInfoService;
import com.be.inventorymaneger.util.FileUploadUtil;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductInfoService productInfoService;

    // show listProduct
    @GetMapping("/listProduct")
    public String listProductForm(Model model){
        model.addAttribute("products",productInfoService.findAll());
        return "product/listProduct";
    }

    // add Product
    @GetMapping("/addProduct")
    public String addProductForm(Model model){
        model.addAttribute("product",new Product_info());
        model.addAttribute("title","Add Product");
        return "product/addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product_info productInfo,
                             @RequestParam("image") MultipartFile multipartFile) throws IOException {
        // lấy tên của tệp đã tải lên lưu vào cơ sở dữ liệu
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        productInfo.setPhotos(filename);

        // chỉ lưu trữ tên tệp trong bảng cơ sở dữ liệu
        //và tệp đã tải lên thực tế được lưu trữ trong hệ thống tệp:
        String uploadDir = "products-photos/" + productInfo.getId();
        FileUploadUtil.saveFile(uploadDir,filename,multipartFile);

        return "redirect: /listProduct";
    }

    // find Product
    @GetMapping("/findProduct")
    public String findProductForm(){
        return "product/findProduct";
    }
    @PostMapping("/findProduct")
    public String findProduct(@RequestParam("keyword")String keyword, Model model){
        List<Product_info> products =  productInfoService.search(keyword);
        model.addAttribute("products_find",products);
        return "product/listProductFind";
    }
}
