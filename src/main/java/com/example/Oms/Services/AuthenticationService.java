package com.example.Oms.Services;

import com.example.Oms.Configuration.JwtUtils;
import com.example.Oms.Repositories.ShopCredRepo;
import com.example.Oms.Repositories.ShopInfoRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService {

    @Autowired
    private ShopInfoRepo shopInfoRepo;

    @Autowired
    private ShopCredRepo shopCredRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public String login(HttpServletResponse response, HashMap<String, String> loginCred, String type) {
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(loginCred.get("userEmail"), loginCred.get("password"));
        try {
            authenticationManager.authenticate(userToken);

            String jwtToken = this.jwtUtils.genereateJwtToken(type, loginCred.get("userEmail"));

            Cookie authCookie = new Cookie("loginToken", jwtToken);
            authCookie.setMaxAge(60 * 5);
            authCookie.setHttpOnly(true);

            response.addCookie(authCookie);
        } catch (Exception e) {

            System.out.println(e.getMessage());

            response.setStatus(400);

            return "Something went wrong. Try again.";
        }

        return "User Logiged in";
    }
}
