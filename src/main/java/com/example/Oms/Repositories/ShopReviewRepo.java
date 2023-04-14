package com.example.Oms.Repositories;

import com.example.Oms.Entity.ShopReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopReviewRepo extends JpaRepository<ShopReviews, Integer> {
}
