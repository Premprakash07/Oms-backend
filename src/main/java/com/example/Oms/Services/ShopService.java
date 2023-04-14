package com.example.Oms.Services;

import com.example.Oms.Entity.ShopInfo;
import com.example.Oms.Repositories.ShopCredRepo;
import com.example.Oms.Repositories.ShopInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ShopService implements UserDetailsService {
    @Autowired
    private ShopInfoRepo shopInfoRepo;

    @Autowired
    private ShopCredRepo shopCredRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (this.shopInfoRepo.existsByShopEmail(username)) {
            ShopInfo shopInfo = this.shopInfoRepo.findByShopEmail(username);
            return (UserDetails) this.shopCredRepo.findByShopInfo(shopInfo);
        } else {
            throw new UsernameNotFoundException("Shop with this Username is not present");
        }
    }
}
