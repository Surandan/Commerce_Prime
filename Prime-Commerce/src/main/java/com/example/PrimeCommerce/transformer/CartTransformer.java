package com.example.PrimeCommerce.transformer;

import com.example.PrimeCommerce.dto.response.CartResponseDto;
import com.example.PrimeCommerce.dto.response.ItemResponseDto;
import com.example.PrimeCommerce.model.Cart;
import com.example.PrimeCommerce.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponseDto CartToCartReponseDto(Cart cart){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item: cart.getItemList()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtoList)
                .build();
    }
}