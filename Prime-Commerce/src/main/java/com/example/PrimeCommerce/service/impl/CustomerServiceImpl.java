package com.example.PrimeCommerce.service.impl;

import com.example.PrimeCommerce.dto.request.CustomerAddReqDto;
import com.example.PrimeCommerce.dto.response.CustomerAddResDto;
import com.example.PrimeCommerce.model.Cart;
import com.example.PrimeCommerce.model.Customer;
import com.example.PrimeCommerce.repository.CustomerRepository;
import com.example.PrimeCommerce.service.CustomerService;
import com.example.PrimeCommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerAddResDto addCustomer(CustomerAddReqDto customerAddReqDto) {

        Customer customer = CustomerTransformer.customerReqDtoToCustomer(customerAddReqDto);

        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.customerToCustomerAddResDto(savedCustomer);
    }
}
