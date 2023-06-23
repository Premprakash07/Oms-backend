package com.example.Oms.Services;

import com.example.Oms.Entity.OrderItems;
import com.example.Oms.Entity.ShopInfo;
import com.example.Oms.Repositories.OrderItemRepo;
import com.example.Oms.Repositories.ShopInfoRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopOrderService {

    @Autowired
    private ShopInfoRepo shopInfoRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    public Object getAllOrders (HttpServletResponse res, int shopId) {
        if (this.shopInfoRepo.existsById(shopId)){
            ShopInfo shopInfo = this.shopInfoRepo.findById(shopId).get();

            List<OrderItems> orderItemsList = this.orderItemRepo.findAllByInventory_ShopInfo(shopInfo);

            return orderItemsList;
        } else {
            res.setStatus(400);

            return "Shop with this id does not exist";
        }
    }

    public String updateOrder (HttpServletResponse res, int orderItemId, String status) {
        if (this.orderItemRepo.existsById(orderItemId)) {
            OrderItems orderItem = this.orderItemRepo.findById(orderItemId).get();

            orderItem.setStatus(status);

            this.orderItemRepo.save(orderItem);

            return "Order updated";
        } else {
            res.setStatus(400);

            return "Order item does not exist";
        }
    }
}
