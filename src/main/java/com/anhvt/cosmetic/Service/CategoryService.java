package com.anhvt.cosmetic.Service;


import com.anhvt.cosmetic.Entity.Category;
import com.anhvt.cosmetic.Repository.CategoryRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public Optional<Category> findByID(Long id){
        return categoryRepository.findById(id);
    }
    public Iterable<Category> findByParent(){
        return categoryRepository.findByParent();
    }

    public Optional<Category> findByParentID(Long id){
        return categoryRepository.findByParentID(id);
    }
    public Iterable<Category> findByChild(){
        return categoryRepository.findByChild();
    }
    public Iterable<Category> findByChild(Integer pageNo, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Category> categories = categoryRepository.findByChild(pageable);
        if(categories.hasContent()){
            return categories.getContent();
        } else {
            return null;
        }
    }
    public void save(Category category){
        categoryRepository.save(category);
    }
}
