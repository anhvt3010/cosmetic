package com.anhvt.cosmetic.Service;

import com.anhvt.cosmetic.DTO.ProductDTO;
import com.anhvt.cosmetic.Entity.Product;
import com.anhvt.cosmetic.Repository.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.anhvt.cosmetic.Mapper.ProductMapper.convertToProductDTOs;


@Service
@Getter
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Page<Product> findAll(Pageable paging){
        Page<Product> productPages = productRepository.findAll(paging);
        if(productPages.hasContent()) {
            return productPages;
        } else {
            return null;
        }
    }

    public Iterable<Product> findAllREST(Integer pageNo, Integer pageSize){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Product> productPages = productRepository.findAll(paging);
        if(productPages.hasContent()) {
            return productPages;
        } else {
            return null;
        }
    }
    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public Iterable<Product> findByNameContaining(String q){
        return productRepository.findByNameContaining(q);
    }
    public Iterable<Product> findByNameContainingREST(String q){
        return productRepository.findByNameContaining(q);
    }
//    public Iterable<Product> findProductByPrice(Float min, Float max){
//        return IProductRepository.findProductByPrice(min, max);
//    }
    public Iterable<Product> findProductByCategory(Long id){
        return productRepository.findByCategory(id);
    }
    public void save(Product product){
        productRepository.save(product);
    }

    public void update(Long id, Product product){
        Product newProduct = productRepository.findById(id).get();
        product.setName(newProduct.getName());
        //....
    }

    public Optional<Product> findbyID(Long id){
        return productRepository.findById(id);
    }

}
