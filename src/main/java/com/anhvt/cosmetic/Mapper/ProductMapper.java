package com.anhvt.cosmetic.Mapper;

import com.anhvt.cosmetic.DTO.ProductDTO;
import com.anhvt.cosmetic.Entity.Product;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.anhvt.cosmetic.Utils.ConvertDate.TimestampToDate;

public class ProductMapper {
    public static ProductDTO convertToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setCreated_at(TimestampToDate(product.getCreated_at()));
        productDTO.setGaleryList(product.getGaleryList());
        productDTO.setStatus(product.getStatus());
        return productDTO;
    }
    public static Iterable<ProductDTO> convertToProductDTOs(Iterable<Product> products) {
        return StreamSupport.stream(products.spliterator(), false)
                .map(ProductMapper::convertToProductDTO)
                .collect(Collectors.toList());
    }

}
