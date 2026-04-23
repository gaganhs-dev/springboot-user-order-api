package com.user_order_management_api.repository;

import com.user_order_management_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByNameContainingIgnoreCase(String name);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
