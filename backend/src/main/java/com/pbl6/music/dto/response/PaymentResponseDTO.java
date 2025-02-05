package com.pbl6.music.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class PaymentResponseDTO {


    public String code;
    public String message;
    public String paymentUrl;

    @Builder
    public PaymentResponseDTO(String code, String message, String paymentUrl) {
        this.code = code;
        this.message = message;
        this.paymentUrl = paymentUrl;
    }

}
