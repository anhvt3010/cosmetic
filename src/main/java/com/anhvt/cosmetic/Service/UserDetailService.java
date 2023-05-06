package com.anhvt.cosmetic.Service;


import com.anhvt.cosmetic.Entity.User;
import com.anhvt.cosmetic.Entity.UserDetail;
import com.anhvt.cosmetic.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username).get();
        if(user==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserDetail(user);
    }
}

