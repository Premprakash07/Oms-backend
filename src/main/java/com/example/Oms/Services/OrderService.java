package com.example.Oms.Services;

import com.example.Oms.Repositories.InventoryRepo;
import com.example.Oms.Repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private InventoryRepo inventoryRepo;
}
