package com.example.Oms.Controllers.Private;

import com.example.Oms.Entity.ShopInfo;
import com.example.Oms.Services.ShopService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@CrossOrigin
@RestController
@RequestMapping("/shop")
public class Shop {

    @Autowired
    private ShopService shopService;

//    @DeleteMapping("/delete")
//    public String deleteShop(HttpServletResponse response) {
//        String res = this.shopService.deleteShop(response);
//
//        return res;
//    }


    @PatchMapping("/update")
    public String updateShop(HttpServletResponse res, @RequestBody ShopInfo shopInfo) {
        String response = this.shopService.updateShop(res, shopInfo);

        return response;}

    @PatchMapping("update/pwd")
    public String updatePwd(HttpServletResponse res, @RequestBody HashMap<String, String> pwdDetails){
        String response = this.shopService.updatePwd(res, pwdDetails);

        return response;
    }
}
