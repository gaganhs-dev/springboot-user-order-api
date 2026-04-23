package com.user_order_management_api.controller;

import com.user_order_management_api.dto.UserRequestDTO;
import com.user_order_management_api.dto.UserResponseDTO;
import com.user_order_management_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @GetMapping("/get")
    public Page<UserResponseDTO> getUsers(@RequestParam int page, @RequestParam int size) {
        return userService.getUsers(page, size);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Integer id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateUser(id, userRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/search")
    public List<UserResponseDTO> searchUsers(@RequestParam String name) {
        return userService.searchUserByName(name);
    }

}
