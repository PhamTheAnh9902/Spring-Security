package com.be.inventorymaneger.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  role_id;
    @Column
    private String role_name;
    @Column
    private String description;
}
