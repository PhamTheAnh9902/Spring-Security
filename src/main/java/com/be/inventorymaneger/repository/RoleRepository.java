package com.be.inventorymaneger.repository;

import com.be.inventorymaneger.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByDescription(String name);
}
