package com.example.Oms.Repositories;

import com.example.Oms.Entity.Orders;
import com.example.Oms.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {

    List<Orders> findByUserInfo(UserInfo userInfo);
}
