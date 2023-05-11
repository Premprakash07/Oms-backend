package com.example.Oms.Services;

import com.example.Oms.Entity.Inventory;
import com.example.Oms.Entity.ItemReviews;
import com.example.Oms.Entity.UserInfo;
import com.example.Oms.Repositories.InventoryRepo;
import com.example.Oms.Repositories.ItemReviewRepo;
import com.example.Oms.Repositories.ShopInfoRepo;
import com.example.Oms.Repositories.UserInfoRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ItemReviewService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ItemReviewRepo itemReviewRepo;

    @Autowired
    private ShopInfoRepo shopInfoRepo;

    public String addReview(HttpServletResponse response, int itemId, ItemReviews itemReviews) throws IOException {
        Authentication userAuthToken = SecurityContextHolder.getContext().getAuthentication();

        if (this.inventoryRepo.existsById(itemId)) {
            Inventory itemDetails = this.inventoryRepo.findById(itemId).get();

            if (this.userInfoRepo.existsByEmail((String) userAuthToken.getPrincipal())) {
                UserInfo userInfo = this.userInfoRepo.findByEmail((String) userAuthToken.getPrincipal());

                itemReviews.setUserInfo(userInfo);
                itemReviews.setInventory(itemDetails);

                this.itemReviewRepo.save(itemReviews);

                return "Item review saved";
            } else {
                response.setStatus(400);
                return "Invalid user id";
            }
        } else {
            response.setStatus(400);
            return "Invalid item id";
        }
    }

    public Object getAllReviews(HttpServletResponse response, int itemId) {
        if (this.inventoryRepo.existsById(itemId)) {
            Inventory itemDetails = this.inventoryRepo.findById(itemId).get();

            List<ItemReviews> itemReviewsList = this.itemReviewRepo.findAllByInventory(itemDetails);

            return itemReviewsList;
        } else {
            response.setStatus(400);
            return "Invalid user id";
        }
    }
}
