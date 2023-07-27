package com.example.PrimeCommerce.service;

import com.example.PrimeCommerce.Enum.ProductCategory;
import com.example.PrimeCommerce.dto.request.ProductRequestDto;
import com.example.PrimeCommerce.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto);

    public List<ProductResponseDto> getProdByCategoryAndUnderPrice(ProductCategory productCategory, int price);
}
