package com.example.Oms.Repositories;

import com.example.Oms.Entity.ShopInfo;
import com.example.Oms.Entity.ShopReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopReviewRepo extends JpaRepository<ShopReviews, Integer> {

    List<ShopReviews> findAllByShopInfo(ShopInfo shopInfo);
}
