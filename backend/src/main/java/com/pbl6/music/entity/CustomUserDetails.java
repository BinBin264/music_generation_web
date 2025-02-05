package com.pbl6.music.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Nếu người dùng có nhiều vai trò, bạn có thể chuyển các vai trò này thành nhiều SimpleGrantedAuthority
        return user.getUserRole() == null ? Collections.emptyList() :
            Collections.singleton(new SimpleGrantedAuthority(user.getUserRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Bạn có thể thêm logic để kiểm tra ngày hết hạn tài khoản
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Bạn có thể thêm logic để kiểm tra xem tài khoản có bị khóa không
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Bạn có thể thêm logic để kiểm tra xem mật khẩu có hết hạn không
    }

    @Override
    public boolean isEnabled() {
        return true; // Bạn có thể thêm logic để kiểm tra xem tài khoản có đang kích hoạt không
    }
}
