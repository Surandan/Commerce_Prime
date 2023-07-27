package com.example.PrimeCommerce.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SellerResponseDto {
    String name;
    String emailId;
}
