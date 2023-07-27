package com.example.PrimeCommerce.transformer;

import com.example.PrimeCommerce.Enum.ProductStatus;
import com.example.PrimeCommerce.dto.request.ProductRequestDto;
import com.example.PrimeCommerce.dto.response.ProductResponseDto;
import com.example.PrimeCommerce.model.Product;

public class ProductTransformer {

    public static Product productReqDtoToProduct(ProductRequestDto productRequestDto) {

        return Product.builder()
                .productName(productRequestDto.getProductName())
                .productStatus(ProductStatus.AVAILABLE)
                .productCategory(productRequestDto.getProductCategory())
                .price(productRequestDto.getPrice())
                .availableQuantity(productRequestDto.getAvailableQuantity()).build();
    }

    public static ProductResponseDto productToProductResDto(Product product) {

        return ProductResponseDto.builder()
                .availableQuantity(product.getAvailableQuantity())
                .sellerName(product.getSeller().getName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .productName(product.getProductName())
                .productStatus(product.getProductStatus())
                .build();
    }
}
