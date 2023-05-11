package com.example.Oms.Controllers.Private;

import com.example.Oms.Entity.Inventory;
import com.example.Oms.Services.InventoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/private/inventory")
public class InventoryControllers {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/addnewitem")
    public String addNewItem(HttpServletResponse response, @RequestBody Inventory inventory) {
        String res = this.inventoryService.addNewItem(response, inventory);

        return res;
    }

    @PostMapping("/updateitem")
    public String updateItem (HttpServletResponse response, @RequestBody HashMap<String , Object> updateDetails) {
        String res = this.inventoryService.updateItem(response, updateDetails);

        return res;
    }
}
