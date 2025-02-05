package com.pbl6.music.dto.response;

import com.pbl6.music.dto.response.MusicResponseDTO;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    private Long categoryIdResponse;  // ID của danh mục
    private String categoryNameResponse;  // Tên của danh mục

}
