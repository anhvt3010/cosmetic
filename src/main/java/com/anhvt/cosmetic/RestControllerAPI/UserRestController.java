package com.anhvt.cosmetic.RestControllerAPI;

import com.anhvt.cosmetic.Entity.User;
import com.anhvt.cosmetic.Service.RoleService;
import com.anhvt.cosmetic.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
public class UserRestController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    //Get All information User
    @GetMapping()
    public ResponseEntity<Iterable<User>> getAll(){
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        Optional<User> optionalUser = userService.findById(id);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Save User
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
    //Edit User
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newUser){
        Optional<User> user = userService.findById(id);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newUser.setId(id);
        return new ResponseEntity<>(userService.save(newUser), HttpStatus.OK);
    }
}
