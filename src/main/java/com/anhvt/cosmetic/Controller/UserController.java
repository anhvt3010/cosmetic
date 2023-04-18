package com.anhvt.cosmetic.Controller;

import com.anhvt.cosmetic.Entity.User;
import com.anhvt.cosmetic.Service.RoleService;
import com.anhvt.cosmetic.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
public class UserController {
    private final UserService userService;
    private final RoleService roleService ;
    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("/admin/user/list");
        Iterable<User> users  = userService.findAll();
        mav.addObject("users",users);
        return mav;
    }
}
