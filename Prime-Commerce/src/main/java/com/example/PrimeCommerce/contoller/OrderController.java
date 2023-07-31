package com.example.PrimeCommerce.contoller;

import com.example.PrimeCommerce.dto.request.OrderRequestDto;
import com.example.PrimeCommerce.dto.response.OrderResponseDto;
import com.example.PrimeCommerce.service.impl.OrderEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    OrderEntityServiceImpl orderEntityService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto) {

        try{

            OrderResponseDto orderResponseDto = orderEntityService.placeOrder(orderRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
