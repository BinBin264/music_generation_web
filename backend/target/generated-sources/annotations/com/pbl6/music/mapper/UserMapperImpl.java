package com.pbl6.music.mapper;

import com.pbl6.music.dto.request.UserRequestDTO;
import com.pbl6.music.dto.response.UserResponseDTO;
import com.pbl6.music.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-05T17:27:28+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toUserEntity(UserRequestDTO userRequestDTO) {
        if ( userRequestDTO == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.userName( userRequestDTO.getUserNameRequestDTO() );
        userEntity.email( userRequestDTO.getEmailRequestDTO() );
        userEntity.fullName( userRequestDTO.getFullNameRequestDTO() );
        userEntity.password( userRequestDTO.getPasswordRequestDTO() );
        userEntity.phoneNumber( userRequestDTO.getPhoneNumberRequestDTO() );

        return userEntity.build();
    }

    @Override
    public UserResponseDTO toUserResponseDTO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setUserIdResponse( userEntity.getUserId() );
        userResponseDTO.setUserNameResponse( userEntity.getUserName() );
        userResponseDTO.setEmailResponse( userEntity.getEmail() );
        userResponseDTO.setFullNameResponse( userEntity.getFullName() );
        userResponseDTO.setUserRoleResponse( userEntity.getUserRole() );
        userResponseDTO.setPhoneNumberResponse( userEntity.getPhoneNumber() );

        return userResponseDTO;
    }
}
