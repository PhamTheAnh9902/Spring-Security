package com.be.inventorymaneger.repository;

import com.be.inventorymaneger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUsersByEmail(String email);
}
