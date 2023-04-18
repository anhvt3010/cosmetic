package com.anhvt.cosmetic.Repository;


import com.anhvt.cosmetic.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findByNameContaining(String name);
    @Query(value = "select * from product where category_id = ?1", nativeQuery = true)
    Iterable<Product> findByCategory(Long id);

//    @Query(value = "select * from product where price between ?1 and ?2 ", nativeQuery = true)
//    Iterable<Product> findProductByPrice(Float min, Float max);

}
