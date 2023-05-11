package com.example.Oms.Controllers.Private;

import com.example.Oms.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/customer")
public class Customer {

    @Autowired
    private UserService userService;

    
}
