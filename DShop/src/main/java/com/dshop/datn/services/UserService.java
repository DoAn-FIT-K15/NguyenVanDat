package com.dshop.datn.services;

import com.dshop.datn.models.dtos.TopUserDto;
import com.dshop.datn.web.dto.response.UserResponse;
import com.dshop.datn.web.dto.request.AddEmpRequest;
import com.dshop.datn.web.dto.request.PasswordRequest;
import com.dshop.datn.web.dto.request.RegisterRequest;
import com.dshop.datn.web.dto.request.UserRequest;
import org.springframework.data.util.Pair;

import java.util.List;

public interface UserService {
    /**
     *
     */
    Pair<List<UserResponse>, Integer> getAllUsers(String keyword, Integer status, int pageNo, int pageSize, String sortBy, boolean asc);
    List<UserResponse> findAllUser();
    String hideUser(Long userId, Long id);
    String addEmP(AddEmpRequest addEmpRequest);
    String showUser(Long userId, Long id);
    UserResponse findByUserName(String username);
    UserResponse findByEmail(String email);
    UserResponse getUser(long userId);
    Object registerUser(RegisterRequest registerRequest);
    String updateUser(Long userId, UserRequest userRequest);
    String updateProfile(Long userId, UserRequest userRequest);
    String changePassword(Long userId, PasswordRequest passwordRequest);
    String forgotPassword(String username);
    List<TopUserDto> findTopUser();
}
