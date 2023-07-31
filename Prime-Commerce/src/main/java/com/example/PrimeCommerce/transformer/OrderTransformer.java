package com.example.PrimeCommerce.transformer;

import com.example.PrimeCommerce.dto.response.ItemResponseDto;
import com.example.PrimeCommerce.dto.response.OrderResponseDto;
import com.example.PrimeCommerce.model.Item;
import com.example.PrimeCommerce.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformer {

    public static OrderResponseDto OrderToOrderResponseDto(OrderEntity order){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item: order.getItemList()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .card(order.getCardUsed())
                .totalPrice(order.getOrderTotal())
                .customerName(order.getCustomer().getName())
                .items(itemResponseDtoList)
                .build();

    }
}