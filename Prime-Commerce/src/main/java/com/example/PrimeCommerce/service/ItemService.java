package com.example.PrimeCommerce.service;

import com.example.PrimeCommerce.dto.request.CheckOutRequestDto;
import com.example.PrimeCommerce.dto.request.ItemRequestDto;
import com.example.PrimeCommerce.model.Item;

public interface ItemService {

    public Item createItem(ItemRequestDto itemRequestDto);


}
