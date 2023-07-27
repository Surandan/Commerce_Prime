package com.example.PrimeCommerce.transformer;

import com.example.PrimeCommerce.dto.request.CustomerAddReqDto;
import com.example.PrimeCommerce.dto.response.CustomerAddResDto;
import com.example.PrimeCommerce.model.Customer;

public class CustomerTransformer {
    public static Customer customerReqDtoToCustomer(CustomerAddReqDto customerAddReqDto) {

        return Customer.builder()
                .name(customerAddReqDto.getName())
                .emailId(customerAddReqDto.getEmailId())
                .mobileNo(customerAddReqDto.getMobileNo())
                .gender(customerAddReqDto.getGender()).build();
    }

    public static CustomerAddResDto customerToCustomerAddResDto(Customer customer) {

        return  CustomerAddResDto.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .mobileNo(customer.getMobileNo())
                .gender(customer.getGender()).build();
    }
}
