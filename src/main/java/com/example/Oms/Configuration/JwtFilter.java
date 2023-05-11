package com.example.Oms.Configuration;

import com.auth0.jwt.interfaces.Claim;
import com.example.Oms.Entity.ShopInfo;
import com.example.Oms.Entity.UserInfo;
import com.example.Oms.Repositories.ShopInfoRepo;
import com.example.Oms.Repositories.UserInfoRepo;
import com.example.Oms.Services.ShopService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class JwtFilter implements Filter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    private ShopInfoRepo shopInfoRepo;

    @Autowired
    private ShopService shopService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String authHeader = request.getHeader("Cookie");
        if (authHeader != null && authHeader.startsWith("loginToken=") && !authHeader.isBlank()) {
            String token = authHeader.substring(15);
            if (!token.isBlank()) {
                try {
                    Map<String, Claim> claims = this.jwtUtils.validateToken(token);

                    if (claims.get("Type").asString().equals("CUSTOMER")) {

                        UserInfo userProfile = new UserInfo();

                        String email = claims.get("Email").asString();

                        if (this.userInfoRepo.existsByEmail(email)){
                            userProfile = this.userInfoRepo.findByEmail(email);
                        } else {
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid token");
                        }

                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, userProfile.getUserAuthCred().getPassword(), userProfile.getUserAuthCred().getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authToken);

                    } else {

                        ShopInfo shopInfo = new ShopInfo();

                        String email = claims.get("Email").asString();

                        if (this.shopInfoRepo.existsByEmail(email)) {
                            shopInfo = this.shopInfoRepo.findByEmail(email);
                        } else {
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid token");
                        }

                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, shopInfo.getShopAuthCred().getPassword(), shopInfo.getShopAuthCred().getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }

                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid token");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid token");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
