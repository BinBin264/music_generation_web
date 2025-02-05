//package com.pbl6.music.service.impl;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class encode {
//
//    private final PasswordEncoder passwordEncoder;
//
//    // Constructor để inject PasswordEncoder
//    public encode(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public String encodePassword(String rawPassword) {
//        return passwordEncoder.encode(rawPassword);
//    }
//
//    public static void main(String[] args) {
//        // Tạo PasswordEncoder tạm thời để test
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        String rawPassword = "password456";
//        String encodedPassword = encoder.encode(rawPassword);
//
//        System.out.println("Raw password: " + rawPassword);
//        System.out.println("Encoded password: " + encodedPassword);
//    }
//}
