package com.example.Oms.Repositories;

import com.example.Oms.Entity.Inventory;
import com.example.Oms.Entity.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

    boolean existsByItemName(String itemName);

    List<Inventory> findAllByShopInfo(ShopInfo shopInfo);
}
