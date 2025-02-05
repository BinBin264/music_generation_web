package com.pbl6.music.controller;

import com.pbl6.music.dto.request.LoginRequestDTO;
import com.pbl6.music.dto.request.RegisterRequestDTO;
import com.pbl6.music.dto.response.AuthenticationResponseDTO;
import com.pbl6.music.dto.response.UserResponseDTO;
import com.pbl6.music.entity.CustomUserDetails;
import com.pbl6.music.filter.JwtTokenProvider;
import com.pbl6.music.service.IAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class AuthenticationController {

    //Đăng nhập
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    //Đăng ký
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        try {
            authenticationService.registerUser(registerRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public AuthenticationResponseDTO authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmailRequestDTO(),
                        loginRequest.getPasswordRequestDTO()
                )
        );

        // Lấy đối tượng CustomUserDetails từ authentication
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken(userDetails);
        System.out.println(jwt);

        // Chuyển đổi từ CustomUserDetails sang UserResponseDTO
        UserResponseDTO userResponseDTO = new UserResponseDTO(
                userDetails.getUsername(), // email
                userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("") // lấy role đầu tiên, nếu có
        );
        return new AuthenticationResponseDTO(jwt, userResponseDTO);
    }

}
