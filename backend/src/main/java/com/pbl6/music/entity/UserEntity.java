package com.pbl6.music.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Tự động sinh UUID
    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId; // Thay đổi từ Long thành UUID

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName; // Tên người dùng phải là duy nhất

    @Column(name = "email", nullable = false, unique = true)
    private String email; // Email cũng phải duy nhất

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "user_role", nullable = false)
    private String userRole;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "composer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MusicEntity> music;

}
