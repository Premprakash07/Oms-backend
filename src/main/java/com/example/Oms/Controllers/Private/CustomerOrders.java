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

    @PostMapping("/place")
    public String placeOrder (HttpServletResponse response, @RequestBody Orders orders) {
        String res = this.customerOrderService.placeOrders(response, orders);

        return res;
    }

    @DeleteMapping("/cancel/{orderId}")
    public String cancelOrder (HttpServletResponse response, @PathVariable("orderId") int orderId){
        String res = this.customerOrderService.cancelOrder(response, orderId);

        return res;
    }

    @GetMapping("/all")
    public Object getAllOrders(HttpServletResponse response) {
        Object orders = this.customerOrderService.getAllOrders(response);

        return orders;
    }

}
