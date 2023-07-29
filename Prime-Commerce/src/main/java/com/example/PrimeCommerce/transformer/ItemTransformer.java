package com.example.PrimeCommerce.transformer;

import com.example.PrimeCommerce.dto.response.ItemResponseDto;
import com.example.PrimeCommerce.model.Item;

public class ItemTransformer {


    public static Item ItemRequestDtoToItem(int requiredQuantity){

        return Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item){

        return ItemResponseDto.builder()
                .itemPrice(item.getProduct().getPrice())
                .itemName(item.getProduct().getProductName())
                .quantityAdded(item.getRequiredQuantity())
                .category(item.getProduct().getProductCategory())
                .build();
    }
}
