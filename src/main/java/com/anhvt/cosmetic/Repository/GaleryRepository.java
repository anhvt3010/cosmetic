package com.anhvt.cosmetic.Repository;


import com.anhvt.cosmetic.Entity.Galery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GaleryRepository extends JpaRepository<Galery, Long> {
    @Query(value = "select * from galery where product_id = ?1 ", nativeQuery = true)
    Iterable<Galery> findByProduct(Long id);
}
