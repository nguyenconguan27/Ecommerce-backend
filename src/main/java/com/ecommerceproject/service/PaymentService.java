package com.ecommerceproject.service;

import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {
    String createVNPayCheckoutMethod(HttpServletRequest request);
}
