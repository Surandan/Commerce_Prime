package com.example.PrimeCommerce.repository;

import com.example.PrimeCommerce.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {
}
