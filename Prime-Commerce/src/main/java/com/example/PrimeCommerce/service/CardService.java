package com.example.PrimeCommerce.service;

import com.example.PrimeCommerce.dto.request.CardRequestDto;
import com.example.PrimeCommerce.dto.response.CardResponseDto;

public interface CardService {

    public CardResponseDto addCard(CardRequestDto cardRequestDto);
}
