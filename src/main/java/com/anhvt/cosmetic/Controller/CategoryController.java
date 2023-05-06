package com.anhvt.cosmetic.Controller;

import com.anhvt.cosmetic.Entity.Category;
import com.anhvt.cosmetic.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/list")
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView("admin/category/list");
        List<Category> categoryList = categoryService.findAll();
        mav.addObject("categories", categoryList);
        return mav;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/category/edit");
        Optional<Category> optionalCategory = categoryService.findByID(id);
        Iterable<Category> pCategories = categoryService.findByParent();
        if (optionalCategory.isPresent()) {
            modelAndView.addObject("category", optionalCategory.get());
            if (optionalCategory.get().getParent_id() != 0) {
                Optional<Category> pCategory = categoryService.findByParentID(optionalCategory.get().getParent_id());
                if (pCategory.isPresent()) {
                    modelAndView.addObject("parentCategory", pCategory.get().getId());
                    modelAndView.addObject("pCategories",pCategories);
                } else {
                    return new ModelAndView("admin/404");
                }
            } else {
                modelAndView.addObject("parentCategory", "IS PARENT");
            }
        } else {
            return new ModelAndView("admin/404");
        }
        return modelAndView;
    }
}
