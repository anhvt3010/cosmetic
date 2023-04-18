package com.anhvt.cosmetic.RestControllerAPI;

import com.anhvt.cosmetic.Entity.Category;
import com.anhvt.cosmetic.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/categories")
public class CategoryRestController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categoryList = categoryService.findAll();
        if(categoryList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id){
        Optional<Category> optionalCategory = categoryService.findByID(id);
        return optionalCategory.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/parent")
    public ResponseEntity<Iterable<Category>> getByParentID(){
        Iterable<Category> categories =categoryService.findByParent();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/child")
    public ResponseEntity<Iterable<Category>> getByChild(){
        Iterable<Category> categories =categoryService.findByChild();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/child-pagging")
    public ResponseEntity<Iterable<Category>> getByChild(
                                @RequestParam(name = "page",defaultValue = "0") int page
                                ,@RequestParam(name = "sz",defaultValue = "3") int size){
        Iterable<Category> categories =categoryService.findByChild(page, size);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

//    @PostMapping
//    public Category saveCategory(@Valid @RequestBody Category category){
//        return categoryService.save(category);
//    }
}
