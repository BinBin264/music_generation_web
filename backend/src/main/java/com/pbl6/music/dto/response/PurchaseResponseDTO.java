package com.pbl6.music.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponseDTO {

    private Long purchaseIdResponse;       // ID của giao dịch mua
    private UUID buyerIdResponse;          // ID của người mua
    private String buyerNameResponse;      // Tên của người mua
    private Long musicIdResponse;          // ID của bản nhạc
    private String musicTitleResponse;     // Tên của bản nhạc
    private Date purchaseDateResponse;     // Ngày giao dịch
    private BigDecimal amountResponse;     // Số tiền giao dịch

}
