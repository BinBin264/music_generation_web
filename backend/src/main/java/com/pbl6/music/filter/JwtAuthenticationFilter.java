package com.pbl6.music.filter;

import com.pbl6.music.service.impl.CustomUserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
@AllArgsConstructor // Chỉ giữ constructor này
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailServiceImpl customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Lấy jwt từ request
            String jwt = getJwtFromRequest(request);
            if (tokenProvider.validateToken(jwt)) {
                // Lấy email user từ chuỗi jwt
                String userEmail = tokenProvider.getUserEmailFromJWT(jwt);
                // Lấy thông tin người dùng từ email
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
                if(userDetails != null) {
                    // Nếu người dùng hợp lệ, set thông tin cho Seturity Context
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } else {
                System.out.println("Invalid JWT");
            }
        } catch (Exception ex) {
            log.error("failed on set user authentication", ex);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        // Lấy header "Authorization" từ request
        String token = request.getHeader("Authorization");
        System.out.println("request token: " + token);

        // Kiểm tra nếu bearerToken không null và bắt đầu bằng "Bearer "
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7); // Bỏ "Bearer " để lấy token
        }
        return null;
    }


}
