package com.example.PrimeCommerce.service;

import com.example.PrimeCommerce.dto.request.CustomerAddReqDto;
import com.example.PrimeCommerce.dto.response.CustomerAddResDto;

public interface CustomerService {

    public CustomerAddResDto addCustomer(CustomerAddReqDto customerAddReqDto);

}
