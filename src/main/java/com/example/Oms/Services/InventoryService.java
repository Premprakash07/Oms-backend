package com.example.Oms.Services;

import com.example.Oms.Repositories.InventoryRepo;
import com.example.Oms.Repositories.ShopInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ShopInfoRepo shopInfoRepo;
}
