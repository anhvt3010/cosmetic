package com.anhvt.cosmetic.RestControllerAPI;


import com.anhvt.cosmetic.Entity.Blog;
import com.anhvt.cosmetic.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("api/v1/blogs")
public class BlogRestController {
    private final BlogService blogService;
    @Autowired
    public BlogRestController(BlogService blogService) {
        this.blogService = blogService;
    }
    @GetMapping
    public ResponseEntity<Iterable<Blog>> getAll(@RequestParam(name = "page",defaultValue = "0") int page
                                                , @RequestParam(name = "sz",defaultValue = "2") int size){
        Iterable<Blog> blogs = blogService.findAll(page, size);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
    @GetMapping("/total")
    public Integer getTotal(){
        List<Blog> blogs = (List<Blog>) blogService.findAll();
        return blogs.size();
    }
}
