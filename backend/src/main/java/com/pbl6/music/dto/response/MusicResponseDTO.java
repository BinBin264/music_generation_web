package com.pbl6.music.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MusicResponseDTO {

    private Long musicIdResponse;            // ID của bản nhạc
    private String titleResponse;            // Tên của bản nhạc
    private String fullUrlResponse;          // URL đầy đủ của bản nhạc
    private BigDecimal priceResponse;        // Giá của bản nhạc
    private boolean isPurchasedResponse;     // Trạng thái đã mua hay chưa
    private UUID composerIdResponse;
    private String composerNameResponse;// id người soạn nhạc
    private Long categoryIdResponse;     // id danh mục của bản nhạc

    //private Long purchaseIdResponse;         // ID của giao dịch mua (nếu có)
}
