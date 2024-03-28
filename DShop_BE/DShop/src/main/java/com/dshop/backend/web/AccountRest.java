package com.dshop.backend.web;

import com.dshop.backend.helper.ApiResponse;
import com.dshop.backend.securities.JwtConfig;
import com.dshop.backend.services.UserService;
import com.dshop.backend.web.dto.request.LoginRequest;
import com.dshop.backend.web.dto.request.RegisterRequest;
import com.dshop.backend.web.dto.response.LoginResponse;
import com.dshop.backend.web.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountRest {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final UserService userService;

    @Autowired
    public AccountRest(AuthenticationManager authenticationManager, JwtConfig jwtConfig, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            UserResponse userResponse = userService.findByUserName(loginRequest.getUsername());
            if (userResponse != null) {
                if (userResponse.getStatus() != 0) {
                    try {
                        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(), loginRequest.getPassword()));

                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        //get token form tokenProvider
                        String token = jwtConfig.generateToken(authentication);

                        LoginResponse loginResponse = new LoginResponse();
                        loginResponse.setToken(token);
                        loginResponse.setUser(userResponse);

                        return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", loginResponse), HttpStatus.OK);
                    } catch (Exception e) {
                        return new ResponseEntity<>(ApiResponse.build(400, false, "Thành công", "Mật khẩu không chính xác"), HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<>(ApiResponse.build(400, false, "Thành công", "Tài khoản của bạn đã bị khóa!"), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(ApiResponse.build(400, false, "Thành công", "Tài khoản không tồn tại"), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi ! " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Invalidate the current user's authentication and clear the SecurityContextHolder
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            Object userResponse = userService.registerUser(registerRequest);
            if (userResponse instanceof String) {
                String errorMessage = (String) userResponse;
                return new ResponseEntity<>(ApiResponse.build(201, false, "Thất bại", errorMessage), HttpStatus.OK);
            } else {
                UserResponse userResponses = (UserResponse) userResponse;
                return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", userResponses), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}