package com.pbl6.music.repository;

import com.pbl6.music.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("SELECT u " +
            "FROM UserEntity u")
    Page<UserEntity> findAllUser(Pageable pageable);

    @Query("SELECT u " +
            "FROM UserEntity u WHERE u.userName LIKE %:username%")
    List<UserEntity> findListUserByUsername(String username);

    @Query("SELECT u " +
            "FROM UserEntity u WHERE u.userName = :username")
    Optional<UserEntity> findUserByUsername(String username);

    @Query("SELECT u "+
            " FROM UserEntity u WHERE u.email = :email")
    Optional<UserEntity> findUserByEmail(String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
}
