package com.anhvt.cosmetic.Service;

import com.anhvt.cosmetic.Entity.Blog;
import com.anhvt.cosmetic.Entity.Product;
import com.anhvt.cosmetic.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    public Iterable<Blog> findAll(){
        return blogRepository.findAll();
    }

    public Iterable<Blog> findAll(Integer pageNo, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Blog> blogs = blogRepository.findAll(pageable);
        if(blogs.hasContent()) {
            return blogs.getContent();
        } else {
            return null;
        }
    }

    public Optional<Blog> findByID(Long id){
        return blogRepository.findById(id);
    }

    public Blog save(Blog blog){
        return blogRepository.save(blog);
    }
}
