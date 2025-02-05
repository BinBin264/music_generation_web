package com.pbl6.music.service;

import com.pbl6.music.dto.request.UserRequestDTO;
import com.pbl6.music.util.PageResponse;
import com.pbl6.music.dto.response.UserResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface IUserService {

    PageResponse<?> getAllUser(int page, int pageSize);
    UserResponseDTO getUserById(UUID id);
    Optional<UserResponseDTO>getUserByUsername(String username);
    List<UserResponseDTO> getListUserByUsername(String username);
    UserResponseDTO addUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser( UUID id, UserRequestDTO userRequestDTO);
    void deleteUser(UUID id);
    Optional<UserResponseDTO>getUserByEmail(String email);

}
