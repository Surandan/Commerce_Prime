package com.example.PrimeCommerce.dto.response;

import com.example.PrimeCommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CardResponseDto {

    String customerName;

    String cardNo;  //masked

    Date validTill;

    CardType cardType;
}
