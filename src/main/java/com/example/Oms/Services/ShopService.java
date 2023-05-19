package com.example.Oms.Services;

import com.example.Oms.Entity.ShopAuthCred;
import com.example.Oms.Entity.ShopInfo;
import com.example.Oms.Repositories.ShopCredRepo;
import com.example.Oms.Repositories.ShopInfoRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ShopService implements UserDetailsService {
    @Autowired
    private ShopInfoRepo shopInfoRepo;

    @Autowired
    private ShopCredRepo shopCredRepo;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public String createShop(HttpServletResponse response, ShopInfo shopInfo) {
        if (this.shopInfoRepo.existsByEmail(shopInfo.getEmail())) {
            response.setStatus(400);
            return "1";
        }
        if (this.shopInfoRepo.existsByPhoneNo(shopInfo.getPhoneNo())) {
            response.setStatus(400);
            return "2";
        }
        if (this.shopInfoRepo.existsByGstin(shopInfo.getGstin())){
            response.setStatus(400);
            return "3";
        }
        else {

            ShopAuthCred shopAuthCred = shopInfo.getShopAuthCred();
            shopAuthCred.setShopInfo(shopInfo);
            shopAuthCred.setPassword(passwordEncoder().encode(shopAuthCred.getPassword()));

            this.shopInfoRepo.save(shopInfo);

            return "Shop details saved";
        }
    }

    public String deleteShop(HttpServletResponse response) {
        Authentication shopToken = SecurityContextHolder.getContext().getAuthentication();
        if (!(shopToken instanceof AnonymousAuthenticationToken)){
            ShopInfo shopInfo = this.shopInfoRepo.findByEmail((String) shopToken.getName());
            if (this.shopInfoRepo.existsById(shopInfo.getShopId())) {
                this.shopInfoRepo.delete(shopInfo);

                return "Shop has been deleted";
            } else {
                response.setStatus(400);
                return "Shop with this id not present";
            }
        }

        response.setStatus(401);
        return "Unauthorized request";
    }

    public String updateShop(HttpServletResponse response, HashMap<String, Object> updateDetails) {
        if (this.shopInfoRepo.existsById((Integer) updateDetails.get("shopId"))) {

            return "Shop updated";
        } else {
            response.setStatus(400);

            return "Shop with this id is not present";
        }
    }

    public Object getShopDetails(HttpServletResponse response, int shopId) {
        if (this.shopInfoRepo.existsById(shopId)) {
            ShopInfo shopInfo = this.shopInfoRepo.findById(shopId).get();

            return shopInfo;
        } else {
            response.setStatus(400);
            return "Shop with this id is not present";
        }
    }

    public List<ShopInfo> getAllShops() {

        List<ShopInfo> shopInfoList = this.shopInfoRepo.findAll();

        return shopInfoList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (this.shopInfoRepo.existsByEmail(username)) {
            ShopInfo shopInfo = this.shopInfoRepo.findByEmail(username);
            return (UserDetails) this.shopCredRepo.findByShopInfo(shopInfo);
        } else {
            throw new UsernameNotFoundException("Shop with this Username is not present");
        }
    }
}
