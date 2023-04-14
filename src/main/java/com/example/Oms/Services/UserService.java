package com.example.Oms.Services;

import com.example.Oms.Entity.UserInfo;
import com.example.Oms.Repositories.UserCredRepo;
import com.example.Oms.Repositories.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private UserCredRepo userCredRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (this.userInfoRepo.existsByUserEmail(username)) {
            UserInfo userInfo = this.userInfoRepo.findByUserEmail(username);
            return (UserDetails) this.userCredRepo.findByUserInfo(userInfo);
        } else {
            throw new UsernameNotFoundException("User with this Username does not exist");
        }
    }
}
