package com.pbl6.music.mapper;

import com.pbl6.music.dto.request.UserRequestDTO;
import com.pbl6.music.dto.response.UserResponseDTO;
import com.pbl6.music.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Chuyển từ DTO yêu cầu sang entity
    @Mapping(source = "userNameRequestDTO", target = "userName")
    @Mapping(source = "emailRequestDTO", target = "email")
    @Mapping(source = "fullNameRequestDTO", target = "fullName")
    @Mapping(source = "passwordRequestDTO", target = "password")
    @Mapping(source = "phoneNumberRequestDTO", target = "phoneNumber")
    UserEntity toUserEntity(UserRequestDTO userRequestDTO);

    // Chuyển từ UserEntity sang UserResponseDTO
    @Mapping(source = "userId", target = "userIdResponse")
    @Mapping(source = "userName", target = "userNameResponse")
    @Mapping(source = "email", target = "emailResponse")
    @Mapping(source = "fullName", target = "fullNameResponse")
    @Mapping(source = "userRole", target = "userRoleResponse")
    @Mapping(source = "phoneNumber", target = "phoneNumberResponse")
    UserResponseDTO toUserResponseDTO(UserEntity userEntity);
}
