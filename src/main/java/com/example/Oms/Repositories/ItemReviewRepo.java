package com.example.Oms.Repositories;

import com.example.Oms.Entity.ItemReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemReviewRepo extends JpaRepository<ItemReviews, Integer> {
}
