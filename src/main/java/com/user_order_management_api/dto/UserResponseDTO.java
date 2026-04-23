package com.user_order_management_api.dto;

public class UserResponseDTO {

    private Integer id;
    private String name;
    private int age;
    private String email;

    public UserResponseDTO(Integer id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
