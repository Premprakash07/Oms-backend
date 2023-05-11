package com.example.Oms.Services;

import com.example.Oms.Entity.UserAuthCred;
import com.example.Oms.Entity.UserInfo;
import com.example.Oms.Repositories.UserCredRepo;
import com.example.Oms.Repositories.UserInfoRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
            return "User this this email already exists";
        } else {

            UserAuthCred userAuthCred = userInfo.getUserAuthCred();
            userAuthCred.setUserInfo(userInfo);
            userAuthCred.setPassword(passwordEncoder().encode(userAuthCred.getPassword()));
            
            this.userInfoRepo.save(userInfo);

            return "User has been saved";
        }
     }
    public String deleteUser(HttpServletResponse response) {
        Authentication userToken = SecurityContextHolder.getContext().getAuthentication();
        UserInfo user = this.userInfoRepo.findByEmail((String) userToken.getName());
        if (this.userInfoRepo.existsById(user.getUserInfoId())) {
            this.userInfoRepo.delete(user);

            return "User has been deleted";
        } else {
            response.setStatus(400);

            return "User with this id does not exist";
        }
     }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (this.userInfoRepo.existsByEmail(username)) {
            UserInfo userInfo = this.userInfoRepo.findByEmail(username);
            return (UserDetails) this.userCredRepo.findByUserInfo(userInfo);
        } else {
            throw new UsernameNotFoundException("User with this Username does not exist");
        }
    }
}
