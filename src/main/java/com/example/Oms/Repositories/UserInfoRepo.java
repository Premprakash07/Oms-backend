package com.example.Oms.Repositories;

import com.example.Oms.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNo(String phoneNo);

    UserInfo findByEmail(String email);
}
