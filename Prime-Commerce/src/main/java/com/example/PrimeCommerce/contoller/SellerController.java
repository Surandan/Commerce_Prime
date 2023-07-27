package com.example.PrimeCommerce.contoller;

import com.example.PrimeCommerce.dto.request.SellerRequestDto;
import com.example.PrimeCommerce.dto.response.SellerResponseDto;
import com.example.PrimeCommerce.service.impl.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerServiceImpl sellerService;

    @PostMapping("/add-seller")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);

        return new ResponseEntity<>(sellerResponseDto, HttpStatus.CREATED);
    }
}
