package com.anhvt.cosmetic.Service;

import com.anhvt.cosmetic.Entity.User;
import com.anhvt.cosmetic.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserService(){}

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    public User save(User user){
        return userRepository.save(user);
    }

}
