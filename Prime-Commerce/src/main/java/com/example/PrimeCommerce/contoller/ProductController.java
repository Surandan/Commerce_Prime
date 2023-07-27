package com.example.PrimeCommerce.contoller;


import com.example.PrimeCommerce.Enum.ProductCategory;
import com.example.PrimeCommerce.dto.request.ProductRequestDto;
import com.example.PrimeCommerce.dto.response.ProductResponseDto;
import com.example.PrimeCommerce.exception.SellerNotFoundException;
import com.example.PrimeCommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @PostMapping("/add-product")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) {

        try {
            ProductResponseDto response = productService.addProduct(productRequestDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (SellerNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getProdByCategoryAndUnderPrice")
    public ResponseEntity getProdByCategoryAndUnderPrice(@RequestParam ProductCategory productCategory,
                                                         @RequestParam int price) {
        List<ProductResponseDto> responseDtos = productService.getProdByCategoryAndUnderPrice(productCategory,price);
        return new ResponseEntity<>(responseDtos,HttpStatus.FOUND);
    }
}
