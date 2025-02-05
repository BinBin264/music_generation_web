package com.pbl6.music.dto.request;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;       // Tên người dùng (Username)
    private String email;          // Email
    private String fullName;       // Họ tên đầy đủ (Full Name)
    private String password;       // Mật khẩu (Password)
    private String phoneNumber;    // Số điện thoại (Phone Number)
}
