package com.dantruong.lucky_money_management.model.dto;

public class UserDto {
    private  String name;
    private Integer id;

    public UserDto(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public UserDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
