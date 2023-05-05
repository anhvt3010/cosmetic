package com.anhvt.cosmetic.Controller;

import com.anhvt.cosmetic.DTO.BlogDTO;
import com.anhvt.cosmetic.Entity.Blog;
import com.anhvt.cosmetic.Entity.Product;
import com.anhvt.cosmetic.Form.BlogForm;
import com.anhvt.cosmetic.Form.ProductForm;
import com.anhvt.cosmetic.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Optional;

import static com.anhvt.cosmetic.Mapper.BlogMapper.convertToBlogDTO;
import static com.anhvt.cosmetic.Mapper.BlogMapper.convertToBlogDTOs;
import static com.anhvt.cosmetic.Utils.ConvertDate.DateToTimestamp;

@Controller
@RequestMapping("admin/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Value("${image.blog.upload-dir}")
    private String imageUploadDir;

    @RequestMapping("/list")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView("admin/blog/list");
        Iterable<BlogDTO> blogs = convertToBlogDTOs(blogService.findAll());
        mav.addObject("blogs", blogs);
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView showForm(){
        ModelAndView modelAndView = new ModelAndView("admin/blog/add");
        modelAndView.addObject("blog", new BlogForm());
        return modelAndView;
    }

    @PostMapping("/add")
    public String createProduct(@ModelAttribute BlogForm blogForm){
        if (blogForm.getImage().isEmpty()) {
            // Xử lý khi tệp tin rỗng
            return "redirect:/upload/error";
        }
        String fileName = blogForm.getImage().getOriginalFilename();
        long curentTime = System.currentTimeMillis(); // xử lý lấy thời gian hiện tại
        fileName = "blog" + curentTime + fileName;
        try {
            byte[] bytes = blogForm.getImage().getBytes();
            Path path = Paths.get(imageUploadDir + fileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Blog blog = new Blog();
        blog.setId(blogForm.getId());
        blog.setTitle(blogForm.getTitle());
        blog.setContent(blogForm.getContent());
        blog.setStatus((byte) 1);
        blog.setCreated_at(DateToTimestamp(Calendar.getInstance().getTime()));
        blog.setImage(fileName);
        blogService.save(blog);
        return "redirect:/admin/blogs/list";
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
