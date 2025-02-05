package com.pbl6.music.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty; // Import thêm
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String userNameRequestDTO;

    private String fullNameRequestDTO;

    private String emailRequestDTO;

    private String passwordRequestDTO;

    private String phoneNumberRequestDTO;
}
