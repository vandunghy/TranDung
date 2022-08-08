package com.example.springboot_thymeleaf_phim.entity;

public class Messages {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Messages(String name) {
        this.name = name;
    }

    private String name;
}
