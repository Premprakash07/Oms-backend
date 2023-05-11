package com.example.Oms.Services;

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

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private UserInfoRepo userInfoRepo;


    public String placeOrders(HttpServletResponse response, Orders orders) {
        Authentication userToken = SecurityContextHolder.getContext().getAuthentication();

        if (this.userInfoRepo.existsByEmail((String) userToken.getName())){

            UserInfo userInfo = this.userInfoRepo.findByEmail((String) userToken.getName());

            orders.setUserInfo(userInfo);

            this.orderRepo.save(orders);

            return "Order placed successfully";

        } else {
            response.setStatus(400);

            return "User with this id does not exist";
        }
    }

    public String cancelOrder(HttpServletResponse response, int orderId) {

    }
}
