package com.dshop.datn.web.dto.request;


import lombok.Data;

@Data
public class UserRequest {

    private String email;

    private String fullName;

    private String phone;
}
