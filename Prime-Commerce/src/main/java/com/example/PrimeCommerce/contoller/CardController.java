package com.example.PrimeCommerce.contoller;

import com.example.PrimeCommerce.dto.request.CardRequestDto;
import com.example.PrimeCommerce.dto.response.CardResponseDto;
import com.example.PrimeCommerce.exception.CustomerNotFoundException;
import com.example.PrimeCommerce.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/card")
@RestController
public class CardController {

    @Autowired
    CardServiceImpl cardService;

    @PostMapping("/add-card")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) {

        try{
            CardResponseDto responseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        }
        catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
