package com.pbl6.music.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotBlank
    private String emailRequestDTO;
    @NotBlank
    private String passwordRequestDTO;
}
