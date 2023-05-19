package com.example.Oms.Controllers.Private;

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

    @PutMapping("/update")
    public String updateShop(HttpServletResponse response, @RequestBody HashMap<String, Object> shopUpdateDetails) {
        String res = this.shopService.updateShop(response, shopUpdateDetails);

        return res;
    }
}
