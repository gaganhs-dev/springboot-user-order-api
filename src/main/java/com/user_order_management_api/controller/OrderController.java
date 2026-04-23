package com.user_order_management_api.controller;

import com.user_order_management_api.dto.OrderRequestDTO;
import com.user_order_management_api.dto.OrderResponseDTO;
import com.user_order_management_api.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}/orders")
    public OrderResponseDTO createOrder(@PathVariable Integer userId, @Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.createOrder(userId, orderRequestDTO);
    }

    // Get all orders of a user with pagination
    @GetMapping("/{userId}/orders")
    public Page<OrderResponseDTO> getOrdersOfUser(@PathVariable Integer userId,
                                                  @RequestParam int page,
                                                  @RequestParam int size) {
        return orderService.getOrderOfUsers(userId, page, size);
    }

    // Get single order of a user
    @GetMapping("/{userId}/orders/{orderId}")
    public OrderResponseDTO getOrderById(@PathVariable Integer userId, @PathVariable Integer orderId) {
        return orderService.getOrderById(userId, orderId);
    }

    // update order of a user
    @PutMapping("/{userId}/orders/{orderId}")
    public OrderResponseDTO updateOrder(@PathVariable Integer userId,
                                        @PathVariable Integer orderId,
                                        @Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.updateOrder(userId, orderId, orderRequestDTO);
    }

    // Delete order of a user
    @DeleteMapping("/{userId}/orders/{orderId}")
    public String deleteOrder(@PathVariable Integer userId, @PathVariable Integer orderId) {
        return orderService.deleteOrder(userId, orderId);
    }

    // search orders by product name
    @GetMapping("/{userId}/orders/search")
    public Page<OrderResponseDTO> searchOrders(@PathVariable Integer userId,
                                               @RequestParam String productName,
                                               @RequestParam int page,
                                               @RequestParam int size) {
        return orderService.searchOrders(userId, productName, page, size);
    }


}
