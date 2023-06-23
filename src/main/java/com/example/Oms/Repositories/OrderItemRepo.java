package com.example.Oms.Repositories;

import com.example.Oms.Entity.OrderItems;
import com.example.Oms.Entity.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItems, Integer> {

    List<OrderItems> findAllByInventory_ShopInfo(ShopInfo shopInfo);
}
