package com.example.Oms.Controllers.Private;

import com.example.Oms.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@CrossOrigin
@RestController
@RequestMapping("/customer")
public class Customer {

    @Autowired
    private UserService userService;

    @PutMapping("/update")
    public String updateUser(HttpServletResponse response, HashMap<String, Object> updateDetails) {
        String res = this.userService.updateUser(response, updateDetails);

        return res;
    }

    @DeleteMapping("/delete")
    public String deleteUser (HttpServletResponse response) {
        String res = this.userService.deleteUser(response);

        return res;
    }

    @GetMapping("/getuserdetails")
    public Object getUserDetails (HttpServletResponse response) {
        Object res = this.userService.getUserDetails(response);

        return res;
    }

}
