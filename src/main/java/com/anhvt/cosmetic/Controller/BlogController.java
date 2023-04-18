package com.anhvt.cosmetic.Controller;

import com.anhvt.cosmetic.Entity.Blog;
import com.anhvt.cosmetic.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/blogs")
public class BlogController {
    private final BlogService blogService;
    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("/list")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView("admin/blog/list");
        Iterable<Blog> blogs = blogService.findAll();
        mav.addObject("blogs", blogs);
        return mav;
    }
}
