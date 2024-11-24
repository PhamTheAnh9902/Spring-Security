package com.be.inventorymaneger.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "product_info")
@Data
public class Product_info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer cate_id;
    @Column
    private String product_name;
    @Column
    private String product_code;
    @Column
    private String product_description;
    @Column
    private Timestamp create_date;
    @Column
    private Timestamp update_date;
    @Column(nullable = true, length = 64)
    private String photos;

    @Transient
    public String getPhotosImagePath(){
        if (photos == null|| id == null) return null;
        return "/product-photos/" + id + "/" + photos;
    }
}
