package com.anhvt.cosmetic.Controller;


//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@CrossOrigin
@RequestMapping("/admin")
public class HomeController {

    @RequestMapping(value = {"", "/", "/home"})
    public String index() {
        return "admin/index";
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView product() {
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }

    @RequestMapping(value = "/forgot_password", method = RequestMethod.GET)
    public String forgot_password() {
        return "redirect:/admin/views/forgot_password";
    }

//        @RequestMapping(value = "/login",method = RequestMethod.GET)
//        public String login(){
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if(authentication.getPrincipal() instanceof UserDetails) return "redirect:/admin/home";
//            else return "/login";
//        }
//        @RequestMapping(value = "/logout", method = RequestMethod.GET)
//        public String logout(SessionStatus session) {
//            SecurityContextHolder.getContext().setAuthentication(null);
//            session.setComplete();
//            return "redirect:/admin/home";
//        }
//        @RequestMapping(value = "/welcome",method = RequestMethod.GET)
//        public String loginWelcome(Model model, HttpSession session){
//            UsernamePasswordAuthenticationToken authentication
//                    = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//            User userInfo = ((UserDetail) authentication.getPrincipal()).getUserDetails();
//            model.addAttribute("currentUser", userInfo.getUsername());
//            session.setAttribute("userId", userInfo.getId());
//            return "admin/welcome";
//        }
//        @RequestMapping(value = "/redirect",method = RequestMethod.POST)
//        public String loginWelcome(){
//            return "redirect:/admin/home";
//        }
//        private void validatePrinciple(Object principal) {
//            if (!(principal instanceof UserDetail)) {
//                throw new  IllegalArgumentException("Principal can not be null!");
//            }
//        }
}


