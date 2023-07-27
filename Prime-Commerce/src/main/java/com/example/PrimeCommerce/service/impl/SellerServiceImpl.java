package com.example.PrimeCommerce.service.impl;

import com.example.PrimeCommerce.dto.request.SellerRequestDto;
import com.example.PrimeCommerce.dto.response.SellerResponseDto;
import com.example.PrimeCommerce.model.Seller;
import com.example.PrimeCommerce.repository.SellerRepository;
import com.example.PrimeCommerce.service.SellerService;
import com.example.PrimeCommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {
        Seller seller = SellerTransformer.sellerReqDtoToSeller(sellerRequestDto);

        Seller savedSeller = sellerRepository.save(seller);

        return SellerTransformer.sellerToSellerResDto(savedSeller);
    }
}
