package com.dshop.backend.web.dto.request;

import lombok.Data;

@Data
public class PasswordRequest {
    private String oldPassword;

    private String newPassword;

    private String cfNewPassword;
}
