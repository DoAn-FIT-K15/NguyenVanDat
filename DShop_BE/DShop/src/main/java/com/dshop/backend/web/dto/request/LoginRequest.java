package com.dshop.backend.web.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;

    private String password;

}
