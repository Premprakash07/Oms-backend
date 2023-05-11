package com.example.Oms.Configuration;

import com.example.Oms.Services.ShopService;
import com.example.Oms.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/public/**")
                .permitAll()
                .requestMatchers("/shopadmin/**").hasAuthority("SHOPKEEPER")
                .requestMatchers("/customer/**").hasAuthority("CUSTOMER")
                .anyRequest()
                .authenticated();

        return http.build();
    }
}