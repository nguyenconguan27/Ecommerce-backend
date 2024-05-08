package com.example.ecommerceproject.apis;

import com.example.ecommerceproject.dto.OrderDTO;
import com.example.ecommerceproject.payload.request.AddToCartRequest;
import com.example.ecommerceproject.payload.response.ResponseObject;
import com.example.ecommerceproject.service.CartService;
import com.google.api.Http;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartApis {

    @Autowired
    private CartService cartService;
    @PostMapping("/add-to-cart")
    public ResponseEntity<ResponseObject> addToCart(@RequestParam Integer cartId, @RequestBody @Valid AddToCartRequest addToCartRequest) throws Exception {
        OrderDTO orderDTO = cartService.addToCart(cartId, addToCartRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Add to cart successfully")
                        .httpStatus(HttpStatus.CREATED)
                        .data(orderDTO)
                        .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getOrders(@RequestParam Integer customerId) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get order in cart successfully")
                        .httpStatus(HttpStatus.OK)
                        .data(cartService.getOrderInCart(customerId))
                        .build()
        );
    }
}
