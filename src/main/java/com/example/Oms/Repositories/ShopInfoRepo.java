package com.example.Oms.Repositories;

import com.example.Oms.Entity.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopInfoRepo extends JpaRepository<ShopInfo, Integer> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNo(String phoneNo);

    boolean existsByGstin(String gstIn);

    ShopInfo findByEmail(String email);
}
