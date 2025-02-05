package com.pbl6.music.service.impl;

import com.pbl6.music.dto.request.RegisterRequestDTO;
import com.pbl6.music.entity.CustomUserDetails;
import com.pbl6.music.entity.UserEntity;
import com.pbl6.music.filter.JwtTokenProvider;
import com.pbl6.music.repository.UserRepository;
import com.pbl6.music.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequestDTO registerRequestDTO) throws Exception {
        System.out.println("hello");
        System.out.println(registerRequestDTO.getEmail());
        // Kiểm tra xem email đã tồn tại chưa
        if (userRepository.existsByEmail(registerRequestDTO.getEmail())) {
            throw new Exception("Email already exists");
        }

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(registerRequestDTO.getPassword());
                // Tạo đối tượng người dùng mới
        UserEntity newUser = new UserEntity();
        newUser.setUserId(UUID.randomUUID());  // Tạo một UUID mới cho người dùng
        newUser.setCreatedBy("system");    // Người tạo
        newUser.setCreatedDate(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault())); // Ngày tạo
        newUser.setModifiedBy("system");    // Người cập nhật
        newUser.setModifiedDate(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault())); //Ngày cập nhật
        newUser.setUserName(registerRequestDTO.getUsername());  // Tên người dùng
        newUser.setEmail(registerRequestDTO.getEmail());        // Email
        newUser.setPassword(encodedPassword); // Mật khẩu đã được mã hóa
        newUser.setFullName(registerRequestDTO.getFullName());  // Tên đầy đủ
        newUser.setUserRole("USER"); // Vai trò người dùng
        newUser.setPhoneNumber(registerRequestDTO.getPhoneNumber()); // Số điện thoại

        userRepository.save(newUser);
    }
}
