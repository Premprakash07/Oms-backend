package com.example.Oms.Services;

import com.example.Oms.Entity.ShopInfo;
import com.example.Oms.Entity.ShopReviews;
import com.example.Oms.Entity.UserInfo;
import com.example.Oms.Repositories.ShopInfoRepo;
import com.example.Oms.Repositories.ShopReviewRepo;
import com.example.Oms.Repositories.UserInfoRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ShopReviewService {

    @Autowired
    private ShopReviewRepo shopReviewRepo;

    @Autowired
    private ShopInfoRepo shopInfoRepo;

    @Autowired
    private UserInfoRepo userInfoRepo;

    public void addReview(HttpServletResponse response, int shopId, ShopReviews shopReview) throws IOException {
        Authentication userAuthToken = SecurityContextHolder.getContext().getAuthentication();

        if (this.shopInfoRepo.existsById(shopId)) {
            ShopInfo shopInfo = this.shopInfoRepo.findById(shopId).get();

            if (this.userInfoRepo.existsByUserEmail((String) userAuthToken.getPrincipal())) {
                UserInfo userInfo = this.userInfoRepo.findByUserEmail((String) userAuthToken.getPrincipal());

                shopReview.setUserInfo(userInfo);
                shopReview.setShopInfo(shopInfo);

                this.shopReviewRepo.save(shopReview);

                response.getWriter().write("Shop review saved");
            } else {
                response.setStatus(400);
                response.getWriter().write("Invalid user id");
            }
        } else {
            response.setStatus(400);
            response.getWriter().write("Invalid shop id");
        }
    }

    public Object getAllReviews(HttpServletResponse response, int shopId) {
        if (this.shopInfoRepo.existsById(shopId)) {
            ShopInfo shopInfo = this.shopInfoRepo.findById(shopId).get();

            List<ShopReviews> shopReviewsList = this.shopReviewRepo.findAllByShopInfo(shopInfo);

            return shopReviewsList;
        } else {
            response.setStatus(400);
            return "Invalid shop id";
        }
    }
}
