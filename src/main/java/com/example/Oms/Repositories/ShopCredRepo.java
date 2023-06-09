package com.example.Oms.Repositories;

import com.example.Oms.Entity.ShopAuthCred;
import com.example.Oms.Entity.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCredRepo extends JpaRepository<ShopAuthCred, Integer> {


    ShopAuthCred findByShopInfo(ShopInfo shopInfo);
}
