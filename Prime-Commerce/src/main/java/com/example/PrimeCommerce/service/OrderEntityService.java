package com.example.PrimeCommerce.service;

import com.example.PrimeCommerce.dto.request.OrderRequestDto;
import com.example.PrimeCommerce.dto.response.OrderResponseDto;
import com.example.PrimeCommerce.model.Card;
import com.example.PrimeCommerce.model.Cart;
import com.example.PrimeCommerce.model.OrderEntity;

public interface OrderEntityService {

    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto);

    OrderEntity placeOrder(Cart cart, Card card);
}
