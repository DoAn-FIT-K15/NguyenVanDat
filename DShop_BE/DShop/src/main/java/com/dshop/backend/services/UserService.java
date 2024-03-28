package com.dshop.backend.services;

import com.dshop.backend.web.dto.request.RegisterRequest;
import com.dshop.backend.web.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    /**
     *
     */
    UserResponse findByUserName(String username);
    Object registerUser(RegisterRequest registerRequest);
    String forgotPassword(String username);
}
