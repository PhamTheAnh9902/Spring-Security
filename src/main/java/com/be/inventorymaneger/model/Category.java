package com.be.inventorymaneger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    @NotEmpty(message = "Category's name is not be null")
    private String category_name;

    @Column(name = "code")
    @NotEmpty(message = "Code is not be null")
    private String code;
    @Column(name = "decription")
    @NotEmpty(message = "decription is not be null")
    private String decription;
}
