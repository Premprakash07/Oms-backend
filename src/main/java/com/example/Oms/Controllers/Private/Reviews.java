package com.example.Oms.Controllers.Private;

import com.example.Oms.Entity.ItemReviews;
import com.example.Oms.Entity.ShopReviews;
import com.example.Oms.Services.ItemReviewService;
import com.example.Oms.Services.ShopReviewService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@CrossOrigin
@RestController
@RequestMapping("/review")
public class Reviews {

    @Autowired
    private ShopReviewService shopReviewService;

    @Autowired
    private ItemReviewService itemReviewService;

    @PostMapping("/item/{itemId}")
    public String addItemReview(HttpServletResponse response, @PathVariable("itemId") int itemId, @RequestBody ItemReviews itemReviews) throws IOException {
        String res = this.itemReviewService.addReview(response, itemId, itemReviews);

        return res;
    }

    @PostMapping("/shop/{shopId}")
    public String addShopReview(HttpServletResponse response, @PathVariable("shopId") int shopId, @RequestBody ShopReviews shopReviews) throws IOException {
        String res = this.shopReviewService.addReview(response, shopId, shopReviews);

        return res;
    }
}
