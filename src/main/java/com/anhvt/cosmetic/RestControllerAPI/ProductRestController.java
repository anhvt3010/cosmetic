package com.anhvt.cosmetic.RestControllerAPI;

import com.anhvt.cosmetic.Entity.Category;
import com.anhvt.cosmetic.Entity.Product;
import com.anhvt.cosmetic.Service.CategoryService;
import com.anhvt.cosmetic.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/products")
public class ProductRestController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductRestController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    // Get All product and pagging
    @GetMapping
    public ResponseEntity<Iterable<Product>> getAll(
                                @RequestParam(name = "q") Optional<String> q,
                                @RequestParam(name = "page",defaultValue = "0") int page
                                ,@RequestParam(name = "sz",defaultValue = "3") int size
//                                ,@RequestParam(name = "sortBy",defaultValue = "price") String sort
    ) {
        Iterable<Product> products = productService.findAllREST(page, size
//                , sort
        );
        if(q.isPresent()){
            products = productService.findByNameContainingREST(q.get());
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get Product by Category
    @GetMapping("/category={id}")
    public ResponseEntity<Iterable<Product>> getByCategory(@PathVariable Long id){
        Optional<Category> optionalCategory = categoryService.findByID(id);
        if(optionalCategory.isPresent()){
            Iterable<Product> products = productService.findProductByCategory(id);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Get Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        Optional<Product> optionalProduct = productService.findbyID(id);
        return optionalProduct.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/total")
    public Integer getTotal(){
        List<Product> products = (List<Product>) productService.findAll();
        return products.size();
    }

//    @PostMapping
//    public ResponseEntity<Product> save(@RequestBody Product product){
//        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
//    }

//    @PostMapping("/add")
//    public @ResponseBody JsonResponse add(@Valid @ModelAttribute("product") @RequestBody Product product , BindingResult br, @RequestParam(name="category_id") long catId){
//        JsonResponse jsonResponse = new JsonResponse();
//        if(!br.hasErrors()){
//            categoryService.getCategoryRepository().findById(catId).map((category)->{
//                product.setCategory(category);
//                return productService.getProductRepository().save(product);
//            }).orElseThrow(()->new EntityNotFoundException("Not Found Category("+catId+")"));
//            jsonResponse.setStatus("SUCCESS");
//            jsonResponse.setResult(product);
//        }else{
//            jsonResponse.setStatus("ERROR");
//            jsonResponse.setResult(br.getAllErrors());
//        }
//        return jsonResponse;
//    }

//    @PutMapping
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
//        Optional<Product> optionalProduct = productService.productRepository.findById(id);
//        if(!optionalProduct.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        updatedProduct.setId(id);
//        return new ResponseEntity<>(productService.save(updatedProduct), HttpStatus.OK);
//    }

//    @DeleteMapping
//    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
//        Optional<Product> optionalProduct = productService.productRepository.findById(id);
//        if(!optionalProduct.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        productService.deletebyID(id);
//        return new ResponseEntity<>(optionalProduct.get(),HttpStatus.OK);
//    }


}
