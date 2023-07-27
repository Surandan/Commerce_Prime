package com.example.PrimeCommerce.transformer;

import com.example.PrimeCommerce.dto.request.SellerRequestDto;
import com.example.PrimeCommerce.dto.response.SellerResponseDto;
import com.example.PrimeCommerce.model.Seller;

public class SellerTransformer {

    public static Seller sellerReqDtoToSeller(SellerRequestDto sellerRequestDto) {

        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .panNo(sellerRequestDto.getPanNo())
                .build();
    }

    public static SellerResponseDto sellerToSellerResDto(Seller seller) {

        return SellerResponseDto.builder()
                .name(seller.getName())
                .emailId(seller.getEmailId())
                .build();
    }
}
