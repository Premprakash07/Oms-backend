package com.example.Oms.Controllers.Public;

import com.example.Oms.Entity.ShopInfo;
import com.example.Oms.Services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/public")
public class Auth {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public Object customerLogin(HttpServletResponse response, @RequestBody HashMap<String, String> loginCred) {
        Object res = this.authenticationService.login(response, loginCred);

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
