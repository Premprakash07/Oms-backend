package com.example.Oms.Configuration;

import com.auth0.jwt.interfaces.Claim;
import com.example.Oms.Entity.ShopAuthCred;
import com.example.Oms.Entity.UserAuthCred;
import com.example.Oms.Repositories.ShopInfoRepo;
import com.example.Oms.Repositories.UserInfoRepo;
import com.example.Oms.Services.ShopService;
import com.example.Oms.Services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        String loginCookie = new String();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loginToken")) {
                    loginCookie = cookie.getValue();
                }
            }
        }
        if (loginCookie != null && !loginCookie.isBlank()) {

            if (!loginCookie.isBlank()) {
                    Map<String, Claim> claims = this.jwtUtils.validateToken(loginCookie);

                    if (claims.get("Type").asString().equals("customer")) {

                        String email = claims.get("Email").asString();

                        UserAuthCred userInfo = this.userService.loadUserByUsername(email);

                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, userInfo.getPassword(), userInfo.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authToken);

                    } else {

                        String email = claims.get("Email").asString();

                        ShopAuthCred shopInfo = this.shopService.loadUserByUsername(email);

                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, shopInfo.getPassword(), shopInfo.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid token");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
