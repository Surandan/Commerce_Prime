package com.example.PrimeCommerce.transformer;

import com.example.PrimeCommerce.dto.request.CardRequestDto;
import com.example.PrimeCommerce.dto.response.CardResponseDto;
import com.example.PrimeCommerce.model.Card;

public class CardTransformer {

    public static Card CardReqDtoToCard(CardRequestDto cardRequestDto) {

        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .validTill(cardRequestDto.getValidTill())
                .build();
    }

    public static CardResponseDto CardToCardResponseDto(Card card) {

        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cardType(card.getCardType())
                .validTill(card.getValidTill())
                .build();
    }
}
