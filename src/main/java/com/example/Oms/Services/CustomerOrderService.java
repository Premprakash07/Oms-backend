package com.example.Oms.Services;

import com.example.Oms.Entity.OrderItems;
import com.example.Oms.Entity.Orders;
import com.example.Oms.Entity.UserInfo;
import com.example.Oms.Repositories.InventoryRepo;
import com.example.Oms.Repositories.OrderRepo;
import com.example.Oms.Repositories.UserInfoRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerOrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private UserInfoRepo userInfoRepo;


    public String placeOrders(HttpServletResponse response, Orders orders, int userId) {
//        Authentication userToken = SecurityContextHolder.getContext().getAuthentication();

        if (this.userInfoRepo.existsById(userId)){

            UserInfo userInfo = this.userInfoRepo.findById(userId).get();

            orders.setUserInfo(userInfo);
            List<OrderItems> orderItems = orders.getOrderItemsList();
            for (OrderItems item: orderItems) {
                item.setOrders(orders);
            }

            this.orderRepo.save(orders);

            return "Order placed successfully";

        } else {
            response.setStatus(400);

            return "User with this id does not exist";
        }
    }

    public String cancelOrder(HttpServletResponse response, int orderId) {
        return "cancle orders";
    }

    public Object getAllOrders (HttpServletResponse response, int userId) {

        if (this.userInfoRepo.existsById(userId)) {
            UserInfo userInfo = this.userInfoRepo.findById(userId).get();

            List<Orders> orders = this.orderRepo.findByUserInfo(userInfo);

            return orders;
        } else {
            response.setStatus(400);

            return "User with this is does not exist";
        }
    }
}
