package com.user_order_management_api.service;

import com.user_order_management_api.dto.OrderRequestDTO;
import com.user_order_management_api.dto.OrderResponseDTO;
import com.user_order_management_api.entity.Order;
import com.user_order_management_api.entity.User;
import com.user_order_management_api.exception.ResourceNotFoundException;
import com.user_order_management_api.repository.OrderRepository;
import com.user_order_management_api.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public OrderService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    // Create Orders
    public OrderResponseDTO createOrder(Integer userId, OrderRequestDTO orderRequestDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));
        Order order = new Order(orderRequestDTO.getProductName());
        user.addOrder(order);
        orderRepository.save(order);
        return new OrderResponseDTO(order.getId(), order.getProductName());
    }

    // Get all orders from user with pagination
    @Cacheable(value = "orders", key = "#userId + '-' + #page + '-' + #size ")
    public Page<OrderResponseDTO> getOrderOfUsers(Integer userId, int page, int size) {
        System.out.println("Fetching from DataBase...");
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findByUserId(userId, pageable)
                .map(order -> mapToDTO(order));
    }

    // Get single order by id
    public OrderResponseDTO getOrderById(Integer userId, Integer orderId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id : " + userId));

        Order order = orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with id : " + orderId
                ));
        return mapToDTO(order);
    }

    // Update order
    public OrderResponseDTO updateOrder(Integer userId, Integer orderId, OrderRequestDTO orderRequestDTO) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id : " + userId
                ));
        Order order = orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with id: " + orderId
                ));
        order.setProductName(orderRequestDTO.getProductName());
        orderRepository.save(order);
        return mapToDTO(order);
    }

    // Delete Order
    public String deleteOrder(Integer userId, Integer orderId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id : " + userId
                ));
        Order order = orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with id : " + orderId
                ));
        orderRepository.delete(order);
        return "Order deleted successfully";
    }

    // Search order by product name
    public Page<OrderResponseDTO> searchOrders(Integer userId, String productName, int page, int size) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id:" + userId
                ));
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository
                .findByUserIdAndProductNameContainingIgnoreCase(userId, productName, pageable)
                .map(this::mapToDTO);
    }

    // Reusable method to convert order entity to order response dto
    public OrderResponseDTO mapToDTO(Order order) {
        return new OrderResponseDTO(order.getId(), order.getProductName());
    }

}
