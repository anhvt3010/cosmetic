package com.anhvt.cosmetic.Controller;

import com.anhvt.cosmetic.DTO.BlogDTO;
import com.anhvt.cosmetic.Entity.Blog;
import com.anhvt.cosmetic.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static com.anhvt.cosmetic.Mapper.BlogConverter.convertToBlogDTO;
import static com.anhvt.cosmetic.Mapper.BlogConverter.convertToBlogDTOs;

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
        Iterable<BlogDTO> blogs = convertToBlogDTOs(blogService.findAll());
        mav.addObject("blogs", blogs);
        return mav;
    }

//    @RequestMapping("/edit/{id}")
//    public ModelAndView edit(@PathVariable Long id){
//        ModelAndView modelAndView = new ModelAndView("admin/blog/detail");
//        Optional<Blog> blog = blogService.findByID(id);
//        if(blog.isPresent()){
//            BlogDTO blogDTO = convertToBlogDTO(blog.get());
//            modelAndView.addObject("blog", blogDTO);
//            return modelAndView;
//        }
//        return new ModelAndView("admin/404");
//    }
    @RequestMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/blog/detail");
        Optional<Blog> blog = blogService.findByID(id);
        if(blog.isPresent()){
            BlogDTO blogDTO = convertToBlogDTO(blog.get());
            modelAndView.addObject("blog", blogDTO);
            return modelAndView;
        }
        return new ModelAndView("admin/404");
    }
    @RequestMapping("/edit/{id}")
    public String save(@PathVariable("id") Long id, @ModelAttribute("blog") Blog newBlog){
        Blog blog = blogService.findByID(newBlog.getId()).get();
        blog.setTitle(newBlog.getTitle());
        blog.setContent(newBlog.getContent());
        blogService.save(blog);
        return "redirect:/admin/blogs/list";
    }
}
