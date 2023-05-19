package com.example.Oms.Services;

import com.example.Oms.Configuration.JwtUtils;
import com.example.Oms.Entity.ShopInfo;
import com.example.Oms.Entity.UserInfo;
import com.example.Oms.Repositories.ShopCredRepo;
import com.example.Oms.Repositories.ShopInfoRepo;
import com.example.Oms.Repositories.UserInfoRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService {

    @Autowired
    private ShopInfoRepo shopInfoRepo;
    
    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private ShopCredRepo shopCredRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtUtils jwtUtils;

    public Object login(HttpServletResponse response, HashMap<String, String> loginCred) {

        String jwtToken = this.jwtUtils.genereateJwtToken(loginCred.get("usertype"), loginCred.get("userEmail"));

        Cookie authCookie = new Cookie("loginToken", jwtToken);
        authCookie.setMaxAge(60 * 5);
        authCookie.setPath("/");
        authCookie.setHttpOnly(false);

        if (loginCred.get("usertype").equals("shopkeeper")) {
            if (this.shopInfoRepo.existsByEmail(loginCred.get("userEmail"))) {
                ShopInfo shopInfo = this.shopInfoRepo.findByEmail(loginCred.get("userEmail"));
                if (passwordEncoder.matches(loginCred.get("password"), shopInfo.getShopAuthCred().getPassword())) {
                    response.addCookie(authCookie);
                    
                    return shopInfo;
                } else {
                    response.setStatus(400);
                    return "Wrong password";
                }
            } else {
                response.setStatus(400);
                return "Bad Credentials";
            }
        } else {
            if (this.userInfoRepo.existsByEmail(loginCred.get("userEmail"))) {
                UserInfo userInfo = this.userInfoRepo.findByEmail(loginCred.get("userEmail"));
                if (passwordEncoder.matches(loginCred.get("password"), userInfo.getUserAuthCred().getPassword())) {
                    response.addCookie(authCookie);

                    return userInfo;
                } else {
                    response.setStatus(400);
                    return "Wrong password";
                }
            } else {
                response.setStatus(400);
                return "Bad Credentials";
            }
        }

    }
}
