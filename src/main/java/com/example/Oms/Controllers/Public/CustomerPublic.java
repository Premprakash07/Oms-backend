package com.example.Oms.Controllers.Public;

import com.example.Oms.Entity.UserInfo;
import com.example.Oms.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/customer")
public class CustomerPublic {
    
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String createShop(HttpServletResponse response, @RequestBody UserInfo userInfo) {
        String res = this.userService.createUser(response, userInfo);
        return res;
    }
}
