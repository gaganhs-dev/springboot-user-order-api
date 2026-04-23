package com.user_order_management_api.service;

import com.user_order_management_api.dto.UserRequestDTO;
import com.user_order_management_api.dto.UserResponseDTO;
import com.user_order_management_api.entity.User;
import com.user_order_management_api.exception.ResourceNotFoundException;
import com.user_order_management_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new ResourceNotFoundException("Email already registered...");
        }
        User user = new User(
                userRequestDTO.getName(),
                userRequestDTO.getAge(),
                userRequestDTO.getEmail(),
                passwordEncoder.encode(userRequestDTO.getPassword())
        );

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(savedUser.getId(),
                savedUser.getName(),
                savedUser.getAge(),
                savedUser.getEmail()
        );
    }

    public Page<UserResponseDTO> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(pageable);
        return users.map(user -> new UserResponseDTO(user.getId(),
                        user.getName(),
                        user.getAge(),
                        user.getEmail()
                )
        );
    }

    public UserResponseDTO updateUser(Integer id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));

        user.setName(userRequestDTO.getName());
        user.setAge(userRequestDTO.getAge());
        user.setEmail(userRequestDTO.getEmail());

        userRepository.save(user);
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getEmail()
        );
    }

    @Transactional
    public String deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));

        userRepository.delete(user);
        return "User deleted successfully.....";
    }

    public List<UserResponseDTO> searchUserByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(
                        user -> new UserResponseDTO(
                                user.getId(),
                                user.getName(),
                                user.getAge(),
                                user.getEmail()
                        )
                )
                .collect(Collectors.toList());
    }


}
