package com.example.Oms.Services;

import com.example.Oms.Entity.Inventory;
import com.example.Oms.Entity.ShopInfo;
import com.example.Oms.Repositories.InventoryRepo;
import com.example.Oms.Repositories.ShopInfoRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ShopInfoRepo shopInfoRepo;

//    public String updateQuantity(HttpServletResponse response, int itemId, int quantity) {
//        if (this.inventoryRepo.existsById(itemId)) {
//            Inventory itemDetails = this.inventoryRepo.findById(itemId).get();
//
//            itemDetails.setQuantityLeft(itemDetails.getQuantityLeft() + quantity);
//
//            this.inventoryRepo.save(itemDetails);
//
//            return "Item has been added";
//        } else {
//            response.setStatus(400);
//
//            return "Item does not exist";
//        }
//    }

    public String updateItem(HttpServletResponse response, HashMap<String, Object> itemUpdateDetails) {
        if (this.inventoryRepo.existsById((Integer) itemUpdateDetails.get("itemId"))) {
            Inventory itemDetails = this.inventoryRepo.findById((Integer) itemUpdateDetails.get("itemId")).get();

//            itemDetails.setQuantityLeft(itemDetails.getQuantityLeft() + quantity);

            this.inventoryRepo.save(itemDetails);

            return "Item has been added";
        } else {
            response.setStatus(400);

            return "Item does not exist";
        }
    }

    public String addNewItem(HttpServletResponse response, Inventory itemDetails){
        Authentication shopToken = SecurityContextHolder.getContext().getAuthentication();
        if (!(shopToken instanceof AnonymousAuthenticationToken)) {
            ShopInfo shopInfo = this.shopInfoRepo.findByEmail((String) shopToken.getName());
            if (this.inventoryRepo.existsByItemNameAndShopInfo(itemDetails.getItemName(), shopInfo)) {
                response.setStatus(400);

                return "Item already exists";
            } else {
                itemDetails.setShopInfo(shopInfo);

                this.inventoryRepo.save(itemDetails);

                return "Item has been added";
            }
        }

        response.setStatus(401);
        return "Unauthorized Access";
    }

//    public String removeItem(HttpServletResponse response, int itemId) {
//        Authentication shopToken = SecurityContextHolder.getContext().getAuthentication();
//        if (!(shopToken instanceof AnonymousAuthenticationToken)) {
//            ShopInfo shopInfo = this.shopInfoRepo.findByEmail((String) shopToken.getName());
//            if (this.inventoryRepo.existsByIdAndShopInfo(itemId, shopInfo)) {
//                this.inventoryRepo.deleteById(itemId);
//
//                return "Item has been deleted";
//            } else {
//                response.setStatus(400);
//
//                return "Item with id does not exist";
//            }
//        }
//
//        response.setStatus(401);
//        return "Unauthorized Access";
//    }

    public Object getAllItemFromShop(HttpServletResponse response, int shopId) {
        if (this.shopInfoRepo.existsById(shopId)) {
            ShopInfo shopInfo = this.shopInfoRepo.findById(shopId).get();

            List<Inventory> inventory = this.inventoryRepo.findAllByShopInfo(shopInfo);

            return inventory;
        } else {
            response.setStatus(400);

            return "ShopPublic with this id does not exist";
        }
    }

    public String addItemFromFile(HttpServletResponse response) {
        return "Items has been added";
    }
}
