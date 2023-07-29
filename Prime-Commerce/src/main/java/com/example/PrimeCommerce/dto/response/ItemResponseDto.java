package com.example.PrimeCommerce.dto.response;

import com.example.PrimeCommerce.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {

    String itemName;

    int itemPrice;

    int quantityAdded;

    ProductCategory category;
}
