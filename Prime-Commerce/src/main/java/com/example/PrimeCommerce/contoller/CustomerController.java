package com.example.PrimeCommerce.contoller;

import com.example.PrimeCommerce.dto.request.CustomerAddReqDto;
import com.example.PrimeCommerce.dto.response.CustomerAddResDto;
import com.example.PrimeCommerce.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    @PostMapping("/add-customer")
    public ResponseEntity<CustomerAddResDto> addCustomer(@RequestBody CustomerAddReqDto customerAddReqDto) {

        CustomerAddResDto response = customerService.addCustomer(customerAddReqDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
