package com.anhvt.cosmetic.Controller;


import com.anhvt.cosmetic.Entity.Category;
import com.anhvt.cosmetic.Entity.Galery;
import com.anhvt.cosmetic.Entity.Product;
import com.anhvt.cosmetic.Form.ProductForm;
import com.anhvt.cosmetic.Service.CategoryService;
import com.anhvt.cosmetic.Service.GaleryService;
import com.anhvt.cosmetic.Service.ProductService;
import com.anhvt.cosmetic.Utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final GaleryService galeryService;

//    @Value("${upload.path}")
//    private String uploadPath;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, GaleryService galeryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.galeryService = galeryService;
    }

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(name = "q") Optional<String> q ){
        ModelAndView mav = new ModelAndView("admin/product/list");
        Iterable<Product> products = productService.findAll();
        if(q.isPresent()){
            products = productService.findByNameContaining(q.get());
        }
        mav.addObject("products",products);
        return mav;
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView mav = new ModelAndView("admin/product/addtest");
//        Iterable<Category> Categories = categoryService.findAll();
         mav.addObject("product", new ProductForm());
//        mav.addObject("Categories",Categories);
        return mav;
    }
//
//    @PostMapping("/create")
//    public String createProduct(@ModelAttribute ProductForm productForm){
//        String fileName = productForm.getImage().getOriginalFilename();
//        long curentTime = System.currentTimeMillis(); // xử lý lấy thời gian hiện tại
//        fileName = curentTime + fileName;
//        try {
//            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(this.uploadPath + fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Product newProduct =new Product();
//        newProduct.setName(productForm.getName());
//        newProduct.setPrice(productForm.getPrice());
//        newProduct.setQuantity(productForm.getQuantity());
//        newProduct.setDescription(productForm.getDescription());
////        newProduct.setStatus(productForm.getStatus());
////        newProduct.setCreated_at(productForm.getCreated_at());
//        newProduct.setImage(fileName);
//        productService.save(newProduct);
//        return "redirect:/admin/products/list";
//    }


//    @RequestMapping("/save")
//    public String addPost(@Valid @ModelAttribute("product") Product product , BindingResult br, Model model){
//        if(br.hasErrors()){
//            return "admin/product/create";
//        }
//        Product newProduct = new Product();
//        newProduct.setName(product.getName());
//        newProduct.setCategory(product.getCategory());
//        newProduct.setPrice(product.getPrice());
//        newProduct.setQuantity(product.getQuantity());
//        newProduct.setDescription(product.getDescription());
//        productService.save(newProduct);
//        model.addAttribute("productList",productService.findAll());
//        return "redirect:/admin/products/list";
//    }
    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/product/edit");
        Optional<Product> product = productService.findbyID(id);
        if (product.isPresent()){
            modelAndView.addObject("product", product);
            String time = Convert.TimestampToDate(product.get().getCreated_at());
            modelAndView.addObject("time", time);

            Iterable<Category> Categories = categoryService.findByChild();
            modelAndView.addObject("Categories",Categories);

            long category = product.get().getCategory().getId();
            modelAndView.addObject("selected",category);

            Iterable<Galery> galeries = galeryService.findByProduct(id);
            modelAndView.addObject("galeries",galeries);

            return modelAndView;
        }
        return new ModelAndView("admin/404");
    }

//    @GetMapping("/delete/{id}")
//    public ModelAndView showDeleteProduct(@PathVariable Long id){
//        Optional<Product> productOptional = productService.findbyID(id);
//        if(productOptional.isPresent()){
//            ModelAndView modelAndView = new ModelAndView("/product/delete");
//            modelAndView.addObject("product", productOptional.get());
//            return modelAndView;
//        }
//        return new ModelAndView("error-404"); // tao view 404.
//    }
//    @PostMapping("/delete/{id}")
//    public ModelAndView deleteProduct(@PathVariable Long id){
//        Optional<Product> product = productService.findbyID(id);
//        if(product.isPresent()){
////            File file = new File(uploadPath + product.get().getImage());
////            if (file.exists()){
////                file.delete();
////            }
//            productService.deletebyID(id);
//            return new ModelAndView("redirect:/admin/products/list");
//        }
//        return new ModelAndView("error-404");
//    }

//    @GetMapping("/search")
//    public ModelAndView showProductSearch(@RequestParam("min") Float min,@RequestParam("max") Float max ){
//        Iterable<Product> products = productService.findProductByPrice(min, max);
//        ModelAndView modelAndView = new ModelAndView("/list");
//        modelAndView.addObject("products",products);
//        return modelAndView;
//    }

}
