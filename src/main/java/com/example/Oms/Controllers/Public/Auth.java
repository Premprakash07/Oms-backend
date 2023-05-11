package com.example.Oms.Controllers.Public;

import com.example.Oms.Services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/public")
public class Auth {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login/customer")
    public Object customerLogin(HttpServletResponse response, @RequestBody HashMap<String, String> loginCred) {
        Object res = this.authenticationService.login(response, loginCred, "CUSTOMER");

        return res;
    }

    @PostMapping("/login/shopkeeper")
    public Object shopkeeperLogin(HttpServletResponse response, @RequestBody HashMap<String, String> loginCred) {
        Object res = this.authenticationService.login(response, loginCred, "SHOPKEEPER");

        return res;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie authCookie = request.getCookies()[0];

        authCookie.setValue(null);
        authCookie.setMaxAge(0);

        response.addCookie(authCookie);

        return "logged out";
    }
}
