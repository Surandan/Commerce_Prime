package com.example.PrimeCommerce.service.impl;

import com.example.PrimeCommerce.dto.request.CheckOutRequestDto;
import com.example.PrimeCommerce.dto.request.ItemRequestDto;
import com.example.PrimeCommerce.exception.CustomerNotFoundException;
import com.example.PrimeCommerce.exception.InsufficientQuantityException;
import com.example.PrimeCommerce.exception.ProductNotFoundException;
import com.example.PrimeCommerce.model.Customer;
import com.example.PrimeCommerce.model.Item;
import com.example.PrimeCommerce.model.Product;
import com.example.PrimeCommerce.repository.CustomerRepository;
import com.example.PrimeCommerce.repository.ProductRepository;
import com.example.PrimeCommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Item createItem(ItemRequestDto itemRequestDto) {

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
        if(customer == null) throw new CustomerNotFoundException("Customer doesn't exisit");

        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }

        Product product = productOptional.get();

        if(product.getAvailableQuantity()< itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! Required quantity not avaiable");
        }

        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();

    }


}
