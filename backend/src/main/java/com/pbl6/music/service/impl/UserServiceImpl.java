package com.pbl6.music.service.impl;

import com.pbl6.music.dto.request.UserRequestDTO;
import com.pbl6.music.dto.response.UserResponseDTO;
import com.pbl6.music.entity.UserEntity;
import com.pbl6.music.exception.ApiException;
import com.pbl6.music.exception.ErrorCode;
import com.pbl6.music.mapper.UserMapper;
import com.pbl6.music.repository.UserRepository;
import com.pbl6.music.service.IUserService;
import com.pbl6.music.util.PageResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponseDTO getUserById(UUID id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponseDTO(userEntity);
    }

    public List<UserResponseDTO> getListUserByUsername(String username) {
        // Tìm kiếm danh sách UserEntity bằng username
        List<UserEntity> users = userRepository.findListUserByUsername(username);

        // Dùng stream để chuyển đổi từng phần tử trong danh sách
        List<UserResponseDTO> userResponseDTOs = users.stream()
                .map(userMapper::toUserResponseDTO) // Dùng method của mapper để chuyển mỗi UserEntity thành UserResponseDTO
                .collect(Collectors.toList()); // Tập hợp lại thành một List

        // Kiểm tra danh sách có phần tử nào không và trả về phần tử đầu tiên nếu có
        if (userResponseDTOs.isEmpty()) {
            throw new ApiException(ErrorCode.USER_NOT_FOUND); // Giả sử ApiException tồn tại
        }

        // Trả về danh sách UserResponseDTO
        return userResponseDTOs;
    }


  public Optional<UserResponseDTO> getUserByUsername(String username) {
    // Tìm kiếm UserEntity bằng username và lấy Optional<UserEntity>
    Optional<UserEntity> userOptional = userRepository.findUserByUsername(username);

    // Kiểm tra nếu user không tồn tại thì trả về Optional.empty()
    if (userOptional.isEmpty()) {
        return Optional.empty();
    }

    // Lấy UserEntity từ Optional và chuyển đổi thành UserResponseDTO
    UserEntity user = userOptional.get();
    UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(user);

    // Trả về UserResponseDTO bọc trong Optional
    return Optional.of(userResponseDTO);
    }


    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmailRequestDTO())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }
        if (userRepository.existsByUserName(userRequestDTO.getUserNameRequestDTO())) {
            throw new IllegalArgumentException("Username đã tồn tại");
        }
        UserEntity userEntity = userMapper.toUserEntity(userRequestDTO);

        // Mã hóa mật khẩu
        userEntity.setPassword(passwordEncoder.encode(userRequestDTO.getPasswordRequestDTO()));
        userEntity = userRepository.save(userEntity);
        return userMapper.toUserResponseDTO(userEntity);
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequestDTO) {
        // Tìm người dùng theo ID. Nếu không tìm thấy, ném ra ngoại lệ.
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        // Ánh xạ thông tin từ DTO vào đối tượng UserEntity hiện có
        existingUser.setUserName(userRequestDTO.getUserNameRequestDTO());
        existingUser.setEmail(userRequestDTO.getEmailRequestDTO());
        existingUser.setFullName(userRequestDTO.getFullNameRequestDTO());
        existingUser.setUserRole("USER");
        existingUser.setPhoneNumber(userRequestDTO.getPhoneNumberRequestDTO());
        // Nếu bạn có trường mật khẩu, hãy xem xét việc xử lý nó (băm mật khẩu, v.v.)
        // existingUser.setPassword(userRequestDTO.getPassword());

        // Lưu lại người dùng đã cập nhật và trả về DTO
        return userMapper.toUserResponseDTO(userRepository.save(existingUser));
    }


    public void deleteUser(UUID id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        userRepository.delete(user);
    }

    public PageResponse<?> getAllUser(int page, int pageSize) {
        // Tạo đối tượng Pageable
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize);

        // Lấy danh sách UserEntity từ UserRepository
        Page<UserEntity> users = userRepository.findAllUser(pageable);

        // Chuyển đổi từ Page<UserEntity> sang Page<UserResponseDTO>
        // Dùng .map để chuyển đổi từng phần tử trong Page
        Page<UserResponseDTO> userResponseDTOS = users.map(userMapper::toUserResponseDTO);

        // Trả về PageResponse với các thông tin cần thiết
        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(userResponseDTOS.getTotalPages()) // Sử dụng userResponseDTOS thay vì users
                .items(userResponseDTOS.getContent()) // Lấy nội dung từ userResponseDTOS
                .build();
    }

    public Optional<UserResponseDTO> getUserByEmail(String email) {
        // Tìm UserEntity cùng với WalletEntity
        Optional<UserEntity> userOptional = userRepository.findUserByEmail(email);
        // Kiểm tra nếu user không tồn tại thì trả về Optional.empty()
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }
        // Lấy UserEntity từ Optional
        UserEntity user = userOptional.get();
        // Chuyển đổi UserEntity thành UserResponseDTO
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(user);
        // Trả về UserResponseDTO bọc trong Optional
        return Optional.of(userResponseDTO);
    }

}

