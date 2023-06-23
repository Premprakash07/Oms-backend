package com.example.Oms.Controllers.Private;

import com.example.Oms.Entity.Orders;
import com.example.Oms.Services.ShopOrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/shop/orders")
public class ShopOrders {

    @Autowired
    private ShopOrderService shopOrderService;

    @GetMapping("/all/{shopId}")
    public Object getAllOrders(HttpServletResponse res, @PathVariable("shopId") int shopId) {
        Object orders = this.shopOrderService.getAllOrders(res, shopId);

        return orders;
    }
//
    @PutMapping("/updatestatus/{orderId}")
    public String updateStatus(HttpServletResponse res, @PathVariable("orderId") int orderId, @RequestBody String status) {
        String response = this.shopOrderService.updateOrder(res, orderId, status);

        return response;
    }
}
