package com.example.Oms.Controllers.Public;

import com.example.Oms.Entity.ShopInfo;
import com.example.Oms.Services.InventoryService;
import com.example.Oms.Services.ShopService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/public/shop")
public class ShopPublic {

    @Autowired
    private ShopService shopService;

    @Autowired
    private InventoryService inventoryService;


    @PostMapping("/create")
    public String createShop(HttpServletResponse response, @RequestBody ShopInfo shopInfo) {
        String res = this.shopService.createShop(response, shopInfo);
        return res;
    }

    @GetMapping("/allshops")
    public List<ShopInfo> getAllShops() {
        List<ShopInfo> shopList = this.shopService.getAllShops();

        return shopList;
    }

    @GetMapping("/getdetails/{shopId}")
    public Object getAllShops(HttpServletResponse response, @PathVariable("shopId") int shopId) {
        Object res = this.shopService.getShopDetails(response, shopId);

        return res;
    }


    @GetMapping("/allItems/{shopId}")
    public Object getAllItems (HttpServletResponse response, @PathVariable("shopId") int shopId) {
        Object inventory = this.inventoryService.getAllItemFromShop(response, shopId);

        return inventory;
    }
}
