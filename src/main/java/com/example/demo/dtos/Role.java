package com.example.demo.dtos;

public enum Role {
    ADMIN, PREMIUM_MEMBER, MEMBER;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
