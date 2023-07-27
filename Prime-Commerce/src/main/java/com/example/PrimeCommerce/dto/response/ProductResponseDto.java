package com.example.PrimeCommerce.dto.response;

import com.example.PrimeCommerce.Enum.ProductCategory;
import com.example.PrimeCommerce.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponseDto {

    String sellerName;

    String productName;

    int price;

    int availableQuantity;

    ProductCategory productCategory;

    ProductStatus productStatus;
}
