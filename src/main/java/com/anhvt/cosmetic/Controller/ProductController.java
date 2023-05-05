package com.anhvt.cosmetic.Controller;


import com.anhvt.cosmetic.DTO.ProductDTO;
import com.anhvt.cosmetic.Entity.Category;
import com.anhvt.cosmetic.Entity.Product;
import com.anhvt.cosmetic.Form.ProductForm;
import com.anhvt.cosmetic.Service.CategoryService;
import com.anhvt.cosmetic.Service.GaleryService;
import com.anhvt.cosmetic.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Optional;

import static com.anhvt.cosmetic.Mapper.ProductMapper.convertToProductDTO;
import static com.anhvt.cosmetic.Mapper.ProductMapper.convertToProductDTOs;
import static com.anhvt.cosmetic.Utils.ConvertDate.DateToTimestamp;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final GaleryService galeryService;

    @Value("${image.product.upload-dir}")
    private String imageUploadDir;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, GaleryService galeryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.galeryService = galeryService;
    }

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(name = "q") Optional<String> q
                             ,@RequestParam(defaultValue = "0") int page){
        ModelAndView mav = new ModelAndView("admin/product/list");
        Pageable pageable = PageRequest.of(page, 5
//                , Sort.by("created").descending()
        );
        Page<Product> products = productService.findAll(pageable);
        if(q.isPresent()){
            products = (Page<Product>) productService.findByNameContaining(q.get());
        }
        mav.addObject("currentPage", page);
        mav.addObject("totalPages", products.getTotalPages());
        mav.addObject("products",products);
        return mav;
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView mav = new ModelAndView("admin/product/add");
        Iterable<Category> Categories = categoryService.findByChild();
        mav.addObject("product", new ProductForm());
        mav.addObject("Categories",Categories);
        return mav;
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute ProductForm productForm){
        if (productForm.getImage().isEmpty()) {
            // Xử lý khi tệp tin rỗng
            return "redirect:/upload/error";
        }
        String fileName = productForm.getImage().getOriginalFilename();
        long curentTime = System.currentTimeMillis(); // xử lý lấy thời gian hiện tại
        fileName = "product" + curentTime + fileName;
        try {
            byte[] bytes = productForm.getImage().getBytes();
            Path path = Paths.get(imageUploadDir + fileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product newProduct = new Product();
        newProduct.setName(productForm.getName());
        newProduct.setPrice(productForm.getPrice());
        newProduct.setCategory(productForm.getCategory());
        newProduct.setQuantity(productForm.getQuantity());
        newProduct.setDescription(productForm.getDescription());
        newProduct.setStatus((byte) 1);
        newProduct.setCreated_at(DateToTimestamp(Calendar.getInstance().getTime()));
        newProduct.setImage(fileName);
        productService.save(newProduct);
        return "redirect:/admin/products/list";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/product/edit");
        Optional<Product> optionalProduct = productService.findbyID(id);
        if (optionalProduct.isPresent()){

            ProductDTO productDTO = convertToProductDTO(optionalProduct.get());
            modelAndView.addObject("product", productDTO);

            Iterable<Category> Categories = categoryService.findByChild();
            modelAndView.addObject("Categories",Categories);

            long category = productDTO.getCategory().getId();
            modelAndView.addObject("selected",category);

//            Iterable<Galery> galeries = galeryService.findByProduct(id);
//            modelAndView.addObject("galeries",galeries);

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
