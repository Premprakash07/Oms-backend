package com.example.Oms.Repositories;

import com.example.Oms.Entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItems, Integer> {
}
