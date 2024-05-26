package com.ecommerceproject.apis;

import com.ecommerceproject.payload.response.ResponseObject;
import com.ecommerceproject.service.PaymentService;
import com.ecommerceproject.service.ReceiptService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/payment")
public class VNPayApis {
    @Autowired
    private  PaymentService paymentService;
    @Autowired
    private ReceiptService receiptService;
    @GetMapping("/vn-pay")
    public ResponseEntity<ResponseObject> pay(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseObject.builder()
                        .message("Create payment successfully")
                        .httpStatus(HttpStatus.OK)
                        .data(paymentService.createVNPayCheckoutMethod(request))
                .build());
    }
    @GetMapping("/vn-pay-callback")
    public void payCallbackHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
//            return ResponseEntity.status(HttpStatus.OK).body(ResponseObject.builder()
//                            .message("Payment successfully")
//                            .httpStatus(HttpStatus.OK)
//                            .data("")
//                    .build());
            response.sendRedirect("http://localhost:3001/user/purchase?status=1&checkout=1");
        } else {
//            return ResponseEntity.status(HttpStatus.OK).body(ResponseObject.builder()
//                    .message("Payment failed")
//                    .httpStatus(HttpStatus.BAD_REQUEST)
//                    .build());
            response.sendRedirect("http://localhost:3001/cart");
        }
    }
}
