package com.example.PrimeCommerce.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartResponseDto {

    String customerName;

    int cartTotal;

    List<ItemResponseDto> items;
}
