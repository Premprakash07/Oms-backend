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

//    @GetMapping("/all")
//    public List<Orders> getAllOrders(HttpServletResponse response) {
//        List<Orders> orders = this.shopOrderService.
//    }
//
//    @PutMapping("/updatestatus/{orderId}")
//    public String updateStatus(HttpServletResponse response, @PathVariable("orderId") int orderId) {
//
//    }
}
