package com.user_order_management_api.repository;

import com.user_order_management_api.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findByUserId(Integer userId, Pageable pageable);

    Optional<Order> findByIdAndUserId(Integer id, Integer userId);

    Page<Order> findByUserIdAndProductNameContainingIgnoreCase(Integer userId, String productName, Pageable pageable);
}
