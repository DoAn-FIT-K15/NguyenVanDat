package com.dshop.datn.web.dto.request;

import lombok.Data;

@Data
public class AddEmpRequest {

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String phone;
}
