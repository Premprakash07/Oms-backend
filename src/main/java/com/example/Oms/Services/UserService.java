package com.example.Oms.Services;

import com.example.Oms.Entity.UserAuthCred;
import com.example.Oms.Entity.UserInfo;
import com.example.Oms.Repositories.UserCredRepo;
import com.example.Oms.Repositories.UserInfoRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private UserCredRepo userCredRepo;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public String createUser(HttpServletResponse response, UserInfo userInfo) {
        if (this.userInfoRepo.existsByEmail(userInfo.getEmail())) {
            response.setStatus(400);
            return "1";
        }
        if (this.userInfoRepo.existsByPhoneNo(userInfo.getPhoneNo())) {
            response.setStatus(400);
            return "2";
        }
        else {

            UserAuthCred userAuthCred = userInfo.getUserAuthCred();
            userAuthCred.setUserInfo(userInfo);
            userAuthCred.setPassword(passwordEncoder().encode(userAuthCred.getPassword()));
            
            this.userInfoRepo.save(userInfo);

            return "User has been saved";
        }
    }

    public String updateUser(HttpServletResponse response, HashMap<String, Object> udpateDetails) {
        return "updated";
    }

    public Object getUserDetails (HttpServletResponse response) {
        return "userDetails";
    }

    public String deleteUser(HttpServletResponse response) {
        Authentication userToken = SecurityContextHolder.getContext().getAuthentication();
        if (!(userToken instanceof AnonymousAuthenticationToken)){
            UserInfo user = this.userInfoRepo.findByEmail((String) userToken.getName());
            if (this.userInfoRepo.existsById(user.getUserInfoId())) {
                this.userInfoRepo.delete(user);

                return "User has been deleted";
            } else {
                response.setStatus(400);

                return "User with this id does not exist";
            }
        }
        response.setStatus(401);
        return "Unauthorized Access";
     }

    @Override
    public UserAuthCred loadUserByUsername(String username) throws UsernameNotFoundException {
        if (this.userInfoRepo.existsByEmail(username)) {
            UserInfo userInfo = this.userInfoRepo.findByEmail(username);
            return  this.userCredRepo.findByUserInfo(userInfo);
        } else {
            throw new UsernameNotFoundException("User with this Username does not exist");
        }
    }
}
