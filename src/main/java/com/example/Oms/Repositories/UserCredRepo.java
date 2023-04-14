package com.example.Oms.Repositories;

import com.example.Oms.Entity.UserAuthCred;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredRepo extends JpaRepository<UserAuthCred, Integer> {
}
