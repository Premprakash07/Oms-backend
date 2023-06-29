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

    HashMap<String, String> photoUrl = new HashMap<String, String>();
    ShopService(){
        photoUrl.put("Flowers", "https://img.freepik.com/free-vector/flower-stand-concept-illustration_114360-12512.jpg?w=900&t=st=1684737439~exp=1684738039~hmac=f3ab5400de021e49265f124c9b99e0ecd72ea9c934f848703ed6f11793fc21cd");
        photoUrl.put("Food", "https://img.freepik.com/free-vector/vector-cartoon-illustration-traditional-set-fast-food-meal_1441-331.jpg?w=740&t=st=1684686992~exp=1684687592~hmac=0d0e74a8dbce38a31b155b9cdb2c17b154b9d4096ad0f8208f3bea7317581544");
        photoUrl.put("Stationary", "https://img.freepik.com/free-vector/school-student-stationary-supplies-shelf_3446-469.jpg?w=740&t=st=1684687014~exp=1684687614~hmac=28693602a1cfb817544812cd6f63459b7813a54626180d3d56ff60fbaea0677b");
        photoUrl.put("Other", "https://img.freepik.com/free-vector/people-standing-store-queue_23-2148594615.jpg?w=900&t=st=1684686948~exp=1684687548~hmac=9c907dba03d43b43a4846fd3ac5995798d85a813af06b38c5e82b03900add918");
        photoUrl.put("Sports", "https://img.freepik.com/free-vector/sepak-takraw-athlete-man-action-bicycle-kick-net-cartoon-character_1150-50797.jpg?w=900&t=st=1684687063~exp=1684687663~hmac=0708fb551bb106c1c53135394258281b903c658a3e6d0a196f48f73be379d2a7");
        photoUrl.put("Medicine", "https://img.freepik.com/free-vector/illustrated-medical-stickers-set_23-2148967488.jpg?w=740&t=st=1684687092~exp=1684687692~hmac=5ab374709a818703026b7df3a76320fc6270c32f8dae6a4d98b0ee7ed54dbe3c");
        photoUrl.put("Grocery", "https://img.freepik.com/free-vector/support-local-business-concept_23-2148595300.jpg?w=740&t=st=1684687131~exp=1684687731~hmac=59aa3107040448370499f1248c20dea1a0668356102dde1852fff99a290cba5f");
    }

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
            shopInfo.setPhotoUrl(photoUrl.get(shopInfo.getCategory()));
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
    public ShopAuthCred loadUserByUsername(String username) throws UsernameNotFoundException {
        if (this.shopInfoRepo.existsByEmail(username)) {
            ShopInfo shopInfo = this.shopInfoRepo.findByEmail(username);
            return this.shopCredRepo.findByShopInfo(shopInfo);
        } else {
            throw new UsernameNotFoundException("Shop with this Username is not present");
        }
    }
}
