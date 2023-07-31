package com.example.PrimeCommerce.service;

import com.example.PrimeCommerce.dto.request.CheckOutRequestDto;
import com.example.PrimeCommerce.dto.request.ItemRequestDto;
import com.example.PrimeCommerce.dto.response.CartResponseDto;
import com.example.PrimeCommerce.dto.response.OrderResponseDto;
import com.example.PrimeCommerce.model.Item;

public interface CartService {
    CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto);

    public OrderResponseDto checkOutCart(CheckOutRequestDto checkOutRequestDto);
}
