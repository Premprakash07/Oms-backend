package com.example.Oms.Controllers.Private;

import com.example.Oms.Entity.Orders;
import com.example.Oms.Services.CustomerOrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/customer/orders")
public class CustomerOrders {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping("/place/{userId}")
    public String placeOrder (HttpServletResponse response, @RequestBody Orders orders, @PathVariable("userId") int userId) {
        String res = this.customerOrderService.placeOrders(response, orders, userId);

        return res;
    }

    @DeleteMapping("/cancel/{orderId}")
    public String cancelOrder (HttpServletResponse response, @PathVariable("orderId") int orderId){
        String res = this.customerOrderService.cancelOrder(response, orderId);

        return res;
    }

    @GetMapping("/all/{userId}")
    public Object getAllOrders(HttpServletResponse response, @PathVariable("userId") int userId) {
        Object orders = this.customerOrderService.getAllOrders(response, userId);

        return orders;
    }

}
