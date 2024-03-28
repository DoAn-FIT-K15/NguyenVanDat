package com.dshop.backend.services.impl;

import com.dshop.backend.mapper.UserMapper;
import com.dshop.backend.models.Role;
import com.dshop.backend.models.User;
import com.dshop.backend.repositories.RoleRepository;
import com.dshop.backend.repositories.UserRepository;
import com.dshop.backend.securities.JwtConfig;
import com.dshop.backend.services.UserService;
import com.dshop.backend.web.dto.request.RegisterRequest;
import com.dshop.backend.web.dto.response.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           UserMapper userMapper,
                           AuthenticationManager authenticationManager,
                           JwtConfig jwtConfig) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
    }

    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserResponse findByUserName(String username) {
        return userMapper.mapModelToResponse(userRepository.findByUsername(username));
    }

    @Override
    public Object registerUser(RegisterRequest registerRequest) {
        // Kiểm tra trong csdl đã tồn tại người sử dụng có username tương tự user của yêu cầu đăng kí chưa
        User checkUser = userRepository.findByUsername(registerRequest.getUsername());
        User checkUser2 = userRepository.findByEmail(registerRequest.getEmail());

        if (checkUser == null) {
            if (checkUser2 == null) {
                registerRequest.setPassword(passwordEncoder().encode(registerRequest.getPassword()));
                User user = userMapper.mapSignupToModel(registerRequest);
                // Set default role_id = 2 (ROLE_USER)
                long defaultRoleId = 2;
                Role role = roleRepository.findById(defaultRoleId);
                user.getRoles().add(role);
                // Lưu thông tin tài khoản mới
                User newUser = userRepository.save(user);
                return userMapper.mapModelToResponse(newUser);
            } else {
                return "Email đã tồn tại .";
            }
        } else {
            return "Tài khoản đã tồn tại.";
        }

    }

    @Override
    public String forgotPassword(String username) {
        return null;
    }
}
