package com.example.PrimeCommerce.contoller;

import com.example.PrimeCommerce.dto.request.CheckOutRequestDto;
import com.example.PrimeCommerce.dto.request.ItemRequestDto;
import com.example.PrimeCommerce.dto.response.CartResponseDto;
import com.example.PrimeCommerce.dto.response.OrderResponseDto;
import com.example.PrimeCommerce.model.Item;
import com.example.PrimeCommerce.service.CartService;
import com.example.PrimeCommerce.service.ItemService;
import com.example.PrimeCommerce.service.impl.CartServiceImpl;
import com.example.PrimeCommerce.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemServiceImpl itemService;

    @Autowired
    CartServiceImpl cartService;

    @PostMapping("/add-to-cart")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) {

        try{
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addToCart(item,itemRequestDto);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkOut")
    public ResponseEntity checkOutCart(@RequestBody CheckOutRequestDto checkOutRequestDto) {

        try{
            OrderResponseDto orderResponseDto = cartService.checkOutCart(checkOutRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
