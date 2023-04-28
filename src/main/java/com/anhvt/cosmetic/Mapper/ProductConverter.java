package com.anhvt.cosmetic.Mapper;

import com.anhvt.cosmetic.DTO.BlogDTO;
import com.anhvt.cosmetic.DTO.ProductDTO;
import com.anhvt.cosmetic.Entity.Blog;
import com.anhvt.cosmetic.Entity.Product;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.anhvt.cosmetic.Utils.Convert.TimestampToDate;

public class ProductConverter {
    public static ProductDTO convertToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setCreated_at(TimestampToDate(product.getCreated_at()));
        productDTO.setGaleryList(product.getGaleryList());
        productDTO.setStatus(product.getStatus());
        return productDTO;
    }
    public static Iterable<ProductDTO> convertToProductDTOs(Iterable<Product> products) {
        return StreamSupport.stream(products.spliterator(), false)
                .map(ProductConverter::convertToProductDTO)
                .collect(Collectors.toList());
    }

}
