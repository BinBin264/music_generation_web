package com.pbl6.music.dto.response;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private UUID userIdResponse; // UUID của người dùng
    private String userNameResponse; // Tên người dùng
    private String emailResponse; // Email
    private String fullNameResponse; // Tên đầy đủ
    private String userRoleResponse; // Vai trò của người dùng
    private String phoneNumberResponse; // Số điện thoại// Danh sách nhạc của người dùng (chỉ DTO)

    public UserResponseDTO(String username, String role ) {
        this.emailResponse = username;
        this.userRoleResponse = role;
    }
}

