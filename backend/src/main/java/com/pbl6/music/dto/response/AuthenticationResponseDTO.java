package com.pbl6.music.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDTO {

    private String tokenAuthenticationResponseDTO;
    private UserResponseDTO userAuthenticationResponseDTO;

}
