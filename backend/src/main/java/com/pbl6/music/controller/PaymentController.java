//package com.pbl6.music.controller;
//
//
//import com.pbl6.music.dto.response.PaymentResponseDTO;
//import com.pbl6.music.service.IPaymentService;
//import com.pbl6.music.util.ResponseObject;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/payment")
//@RequiredArgsConstructor
//public class PaymentController {
//
//    private final IPaymentService paymentService;
//
//    @GetMapping("/vn-pay")
//    public ResponseObject<PaymentResponseDTO> pay(HttpServletRequest request) {
//        return new ResponseObject<>(HttpStatus.OK, "Success", paymentService.createVnPayPayment(request));
//    }
//
//    @GetMapping("/vn-pay-callback")
//    public ResponseObject<PaymentResponseDTO> payCallbackHandler(HttpServletRequest request) {
//        String status = request.getParameter("vnp_ResponseCode");
//        if (status.equals("00")) {
//            return new ResponseObject<>(HttpStatus.OK, "Success", new PaymentResponseDTO("00", "Success", ""));
//        } else {
//            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", null);
//        }
//    }
//}