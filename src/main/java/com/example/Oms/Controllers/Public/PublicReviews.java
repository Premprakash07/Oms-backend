package com.example.Oms.Controllers.Public;

import com.example.Oms.Services.ItemReviewService;
import com.example.Oms.Services.ShopReviewService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/public/review")
public class PublicReviews {

    @Autowired
    private ShopReviewService shopReviewService;

    @Autowired
    private ItemReviewService itemReviewService;

    @GetMapping("/shop/getall/{shopId}")
    public Object getAllShopReviews(HttpServletResponse response, @PathVariable("shopId") int shopId) {
        Object res = this.shopReviewService.getAllReviews(response, shopId);

        return res;
    }

    @GetMapping("/item/getall/{itemId}")
    public Object getAllItemReviews(HttpServletResponse response, @PathVariable("itemId") int itemId) {
        Object res = this.itemReviewService.getAllReviews(response, itemId);

        return res;
    }
}
