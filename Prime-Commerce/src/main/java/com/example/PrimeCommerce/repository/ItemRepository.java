package com.example.PrimeCommerce.repository;

import com.example.PrimeCommerce.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
