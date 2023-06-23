package com.example.Oms.Controllers.Private;

import com.example.Oms.Entity.Inventory;
import com.example.Oms.Services.InventoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/inventory")
public class InventoryControllers {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/addnewitem/{shopId}")
    public String addNewItem(HttpServletResponse response, @RequestBody Inventory inventory, @PathVariable("shopId") int shopId) {
        String res = this.inventoryService.addNewItem(response, inventory, shopId);

        return res;
    }

    @PostMapping("/updateitem")
    public String updateItem (HttpServletResponse response, @RequestBody HashMap<String, Object> updateDetails) {
        String res = this.inventoryService.updateItem(response, updateDetails);

        return res;
    }

    @DeleteMapping("/deleteitem/{itemId}")
    public String deleteItem (HttpServletResponse res, @PathVariable("itemId") int itemId) {
        String response = this.inventoryService.removeItem(res, itemId);

        return  response;
    }
}
