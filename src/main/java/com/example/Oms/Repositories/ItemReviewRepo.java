package com.example.Oms.Repositories;

import com.example.Oms.Entity.Inventory;
import com.example.Oms.Entity.ItemReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemReviewRepo extends JpaRepository<ItemReviews, Integer> {

    List<ItemReviews> findAllByInventory(Inventory inventory);
}
