package com.example.PrimeCommerce.service;

import com.example.PrimeCommerce.dto.request.SellerRequestDto;
import com.example.PrimeCommerce.dto.response.SellerResponseDto;

public interface SellerService {

   public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);
}
